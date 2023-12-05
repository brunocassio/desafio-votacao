package com.sicredi.desafiovotacao.controller;

import com.sicredi.desafiovotacao.dto.PautaDTO;
import com.sicredi.desafiovotacao.dto.ResultadoVotacaoDTO;
import com.sicredi.desafiovotacao.exception.PautaNotFoundException;
import com.sicredi.desafiovotacao.model.Associado;
import com.sicredi.desafiovotacao.model.Pauta;
import com.sicredi.desafiovotacao.model.Voto;
import com.sicredi.desafiovotacao.service.AssociadoService;
import com.sicredi.desafiovotacao.service.PautaService;
import com.sicredi.desafiovotacao.service.VotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/pauta")
public class PautaController {

    private final PautaService pautaService;
    private final VotoService votoService;
    private final AssociadoService associadoService;
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

    public PautaController(PautaService pautaService, VotoService votoService, AssociadoService associadoService) {
        this.pautaService = pautaService;
        this.votoService = votoService;
        this.associadoService = associadoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pauta> cadastrarPauta(@RequestBody PautaDTO dto) {
        return ResponseEntity.ok(pautaService.inserir(dto));
    }

    @PostMapping("/{id}/registrar-voto")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> registrarVoto(
                                @PathVariable("id") Long id,
                                @RequestParam("associadoId") Long associadoId,
                                @RequestParam("voto") String voto) {
        Pauta pauta = pautaService.buscarPauta(id);
        if (!pauta.isVotacaoAberta()) {
            return ResponseEntity.ok("Votação encerrada para a pauta: " + pauta.getTitulo());
        }
        List<Voto> votosPorPauta = votoService.buscarVotosPorPauta(id);
        Associado associado = associadoService.buscarAssociadoById(associadoId);

        if (votosPorPauta.size() > 0) {
            if (votosPorPauta.stream().anyMatch(v -> v.getAssociado().getId() == associadoId)) {
                return ResponseEntity.ok("Associado já votou nesta pauta.");
            } else {
                registrarVoto(voto, pauta, associado);
                return ResponseEntity.ok("Voto registrado com sucesso para a pauta: " + pauta.getTitulo());
            }
        } else {
            registrarVoto(voto, pauta, associado);
            return ResponseEntity.ok("Voto registrado com sucesso para a pauta: " + pauta.getTitulo());
        }
    }

    @PatchMapping("/{id}/abrir-votacao")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> abrirVotacao(@PathVariable("id") Long id,
                                               @RequestParam(name = "duracao", required = false) Long duracao) {
        Pauta pauta = pautaService.buscarPauta(id);
        pauta.setVotacaoAberta(true);
        pautaService.editarPauta(pauta);

        if (duracao == null) {
            duracao = 1L;
        }
        executorService.schedule(() -> encerrarVotacao(id), duracao, TimeUnit.MINUTES);

        return ResponseEntity.ok("Votação iniciada para a pauta: " + pauta.getTitulo() +
                ". A votação será encerrada em " + duracao + " minutos.");
    }

    @GetMapping("/{id}/contabilizar-votos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultadoVotacaoDTO> contabilizarVotos(@PathVariable("id") Long id) {
        List<Voto> votosPorPauta = votoService.buscarVotosPorPauta(id);
        return ResponseEntity.ok(calcularResultadoVotacao(votosPorPauta));
    }

    private ResultadoVotacaoDTO calcularResultadoVotacao(List<Voto> votosPorPauta) {
        Map<String, Long> contagemPorValor = votosPorPauta.stream()
                .collect(Collectors.groupingBy(Voto::getOpcao, Collectors.counting()));

        long contadorS = contagemPorValor.getOrDefault("S", 0L);
        long contadorN = contagemPorValor.getOrDefault("N", 0L);
        long totalVotos = votosPorPauta.size();

        if (contadorS > contadorN) {
            return ResultadoVotacaoDTO.builder().count(totalVotos).opcao("S").build();
        } else if (contadorS < contadorN) {
            return ResultadoVotacaoDTO.builder().count(totalVotos).opcao("N").build();
        } else {
            return ResultadoVotacaoDTO.builder().count(totalVotos).opcao("Empate").build();
        }
    }

    private void registrarVoto(String voto, Pauta pauta, Associado associado) {
        Voto votoDoAssociado = Voto.builder()
                .opcao(voto)
                .pauta(pauta)
                .associado(associado)
                .build();
        votoService.registrarVoto(votoDoAssociado);
    }

    private void encerrarVotacao(Long id) {
        Pauta pauta = pautaService.buscarPauta(id);
        pauta.setVotacaoAberta(false);
        pautaService.editarPauta(pauta);
    }
}
