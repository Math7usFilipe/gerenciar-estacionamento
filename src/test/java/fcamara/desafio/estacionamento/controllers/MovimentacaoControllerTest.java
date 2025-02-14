package fcamara.desafio.estacionamento.controllers;

import fcamara.desafio.estacionamento.Controller.MovimentacaoController;
import fcamara.desafio.estacionamento.Services.MovimentacaoService;
import fcamara.desafio.estacionamento.entities.Movimentacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovimentacaoControllerTest {

    @InjectMocks
    private MovimentacaoController movimentacaoController;

    @Mock
    private MovimentacaoService movimentacaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGerarRelatorioEntradaSaida() {
        Long veiculoId = 1L;
        String relatorioEsperado = "Relatório de movimentações para o veículo de ID 1";
        when(movimentacaoService.gerarRelatorioEntradaSaida(veiculoId)).thenReturn(relatorioEsperado);

        ResponseEntity<String> response = movimentacaoController.gerarRelatorioEntradaSaida(veiculoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(relatorioEsperado, response.getBody());
        verify(movimentacaoService, times(1)).gerarRelatorioEntradaSaida(veiculoId);
    }

    @Test
    void testGerarRelatorioEntradaSaidaPorEstabelecimento() {
        Long estabelecimentoId = 1L;
        String relatorioEsperado = "Relatório de movimentações para o estabelecimento de ID 1";
        when(movimentacaoService.gerarRelatorioEntradaSaidaPorEstabelecimento(estabelecimentoId)).thenReturn(relatorioEsperado);

        ResponseEntity<String> response = movimentacaoController.gerarRelatorioEntradaSaidaPorEstabelecimento(estabelecimentoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(relatorioEsperado, response.getBody());
        verify(movimentacaoService, times(1)).gerarRelatorioEntradaSaidaPorEstabelecimento(estabelecimentoId);
    }

    @Test
    void testGerarSumarioPorHora() {
        LocalDateTime inicio = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime fim = LocalDateTime.of(2023, 10, 1, 18, 0);
        String sumarioEsperado = "Sumário de movimentações entre 2023-10-01T10:00 e 2023-10-01T18:00";
        when(movimentacaoService.gerarSumarioPorHora(inicio, fim)).thenReturn(sumarioEsperado);

        ResponseEntity<String> response = movimentacaoController.gerarSumarioPorHora(inicio.toString(), fim.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sumarioEsperado, response.getBody());
        verify(movimentacaoService, times(1)).gerarSumarioPorHora(inicio, fim);
    }

    @Test
    void testRegistrarEntrada() {
        Movimentacao movimentacao = new Movimentacao();
        Movimentacao movimentacaoSalva = new Movimentacao();
        movimentacaoSalva.setId(1L);
        when(movimentacaoService.registrarEntrada(movimentacao)).thenReturn(movimentacaoSalva);

        ResponseEntity<Movimentacao> response = movimentacaoController.registrarEntrada(movimentacao);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimentacaoSalva, response.getBody());
        verify(movimentacaoService, times(1)).registrarEntrada(movimentacao);
    }

    @Test
    void testRegistrarSaida_Sucesso() {
        Long movimentacaoId = 1L;
        Movimentacao movimentacaoAtualizada = new Movimentacao();
        movimentacaoAtualizada.setId(movimentacaoId);
        when(movimentacaoService.registrarSaida(movimentacaoId)).thenReturn(movimentacaoAtualizada);

        ResponseEntity<Movimentacao> response = movimentacaoController.registrarSaida(movimentacaoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimentacaoAtualizada, response.getBody());
        verify(movimentacaoService, times(1)).registrarSaida(movimentacaoId);
    }

    @Test
    void testRegistrarSaida_NaoEncontrado() {
        Long movimentacaoId = 1L;
        when(movimentacaoService.registrarSaida(movimentacaoId)).thenThrow(new IllegalArgumentException("Movimentação não encontrada"));

        ResponseEntity<Movimentacao> response = movimentacaoController.registrarSaida(movimentacaoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(movimentacaoService, times(1)).registrarSaida(movimentacaoId);
    }

    @Test
    void testRegistrarSaida_JaRegistrada() {
        Long movimentacaoId = 1L;
        when(movimentacaoService.registrarSaida(movimentacaoId)).thenThrow(new IllegalStateException("Veículo já saiu"));

        ResponseEntity<Movimentacao> response = movimentacaoController.registrarSaida(movimentacaoId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(movimentacaoService, times(1)).registrarSaida(movimentacaoId);
    }
}