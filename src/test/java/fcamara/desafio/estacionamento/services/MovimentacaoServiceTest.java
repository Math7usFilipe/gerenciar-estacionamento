package fcamara.desafio.estacionamento.services;

import fcamara.desafio.estacionamento.Services.MovimentacaoService;
import fcamara.desafio.estacionamento.entities.Movimentacao;
import fcamara.desafio.estacionamento.entities.Veiculo;
import fcamara.desafio.estacionamento.entities.Estabelecimento;
import fcamara.desafio.estacionamento.repositories.MovimentacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovimentacaoServiceTest {

    @Mock
    private MovimentacaoRepository movimentacaoRepository;

    @InjectMocks
    private MovimentacaoService movimentacaoService;

    private Movimentacao movimentacao;
    private Veiculo veiculo;
    private Estabelecimento estabelecimento;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setPlaca("ABC-1234");

        estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estacionamento Central");

        movimentacao = new Movimentacao();
        movimentacao.setId(1L);
        movimentacao.setVeiculo(veiculo);
        movimentacao.setEstabelecimento(estabelecimento);
        movimentacao.setDataEntrada(LocalDateTime.now());
    }

    @Test
    void testGerarRelatorioEntradaSaida() {
        when(movimentacaoRepository.findByVeiculoId(1L)).thenReturn(Arrays.asList(movimentacao));

        String relatorio = movimentacaoService.gerarRelatorioEntradaSaida(1L);

        assertNotNull(relatorio);
        assertTrue(relatorio.contains("ABC-1234"));
        assertTrue(relatorio.contains("Estacionamento Central"));
        verify(movimentacaoRepository, times(1)).findByVeiculoId(1L);
    }

    @Test
    void testGerarRelatorioEntradaSaidaPorEstabelecimento() {
        when(movimentacaoRepository.findByEstabelecimentoId(1L)).thenReturn(Arrays.asList(movimentacao));

        String relatorio = movimentacaoService.gerarRelatorioEntradaSaidaPorEstabelecimento(1L);

        assertNotNull(relatorio);
        assertTrue(relatorio.contains("ABC-1234"));
        assertTrue(relatorio.contains("Estacionamento Central"));
        verify(movimentacaoRepository, times(1)).findByEstabelecimentoId(1L);
    }

    @Test
    void testGerarSumarioPorHora() {
        LocalDateTime inicio = LocalDateTime.now().minusHours(1);
        LocalDateTime fim = LocalDateTime.now().plusHours(1);

        when(movimentacaoRepository.findByDataSaidaIsNull()).thenReturn(Arrays.asList(movimentacao));

        String sumario = movimentacaoService.gerarSumarioPorHora(inicio, fim);

        assertNotNull(sumario);
        assertTrue(sumario.contains("ABC-1234"));
        assertTrue(sumario.contains("Estacionamento Central"));
        verify(movimentacaoRepository, times(1)).findByDataSaidaIsNull();
    }

    @Test
    void testRegistrarEntrada() {
        when(movimentacaoRepository.save(any(Movimentacao.class))).thenReturn(movimentacao);

        Movimentacao resultado = movimentacaoService.registrarEntrada(movimentacao);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("ABC-1234", resultado.getVeiculo().getPlaca());
        verify(movimentacaoRepository, times(1)).save(movimentacao);
    }

    @Test
    void testRegistrarSaida() {
        when(movimentacaoRepository.findById(1L)).thenReturn(Optional.of(movimentacao));
        when(movimentacaoRepository.save(any(Movimentacao.class))).thenReturn(movimentacao);

        Movimentacao resultado = movimentacaoService.registrarSaida(1L);

        assertNotNull(resultado);
        assertNotNull(resultado.getDataSaida());
        verify(movimentacaoRepository, times(1)).findById(1L);
        verify(movimentacaoRepository, times(1)).save(movimentacao);
    }

    @Test
    void testRegistrarSaidaVeiculoJaSaiu() {
        movimentacao.setDataSaida(LocalDateTime.now());
        when(movimentacaoRepository.findById(1L)).thenReturn(Optional.of(movimentacao));

        assertThrows(IllegalStateException.class, () -> movimentacaoService.registrarSaida(1L));
        verify(movimentacaoRepository, times(1)).findById(1L);
        verify(movimentacaoRepository, never()).save(any(Movimentacao.class));
    }

    @Test
    void testRegistrarSaidaMovimentacaoNaoEncontrada() {
        when(movimentacaoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> movimentacaoService.registrarSaida(1L));
        verify(movimentacaoRepository, times(1)).findById(1L);
        verify(movimentacaoRepository, never()).save(any(Movimentacao.class));
    }
}