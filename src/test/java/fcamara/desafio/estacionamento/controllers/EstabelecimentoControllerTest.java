package fcamara.desafio.estacionamento.controllers;

import fcamara.desafio.estacionamento.Controller.EstabelecimentoController;
import fcamara.desafio.estacionamento.Services.EstabelecimentoServices;
import fcamara.desafio.estacionamento.entities.Estabelecimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EstabelecimentoControllerTest {

    @InjectMocks
    private EstabelecimentoController estabelecimentoController;

    @Mock
    private EstabelecimentoServices estabelecimentoServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarEstabelecimento() {
        Estabelecimento estabelecimento = new Estabelecimento(1L, "Estabelecimento Central", "12345678901234", "Rua Principal, 123", "11987654321", 50);
        when(estabelecimentoServices.criarEstabelecimento(estabelecimento)).thenReturn(estabelecimento);

        Estabelecimento response = estabelecimentoController.criarEstabelecimento(estabelecimento);

        assertEquals(estabelecimento, response);
        verify(estabelecimentoServices, times(1)).criarEstabelecimento(estabelecimento);
    }

    @Test
    void testListarEstabelecimento() {
        Estabelecimento estabelecimento1 = new Estabelecimento(1L, "Estacionamento Central", "12345678901234", "Rua Principal, 123", "11987654321", 50);
        Estabelecimento estabelecimento2 = new Estabelecimento(2L, "Estacionamento Norte", "98765432109876", "Avenida Secundária, 456", "11912345678", 30);

        List<Estabelecimento> estabelecimentos = Arrays.asList(estabelecimento1, estabelecimento2);

        when(estabelecimentoServices.listarEstabelecimento()).thenReturn(estabelecimentos);

        List<Estabelecimento> response = estabelecimentoController.listarEstabelecimento();

        assertEquals(2, response.size());
        assertEquals(estabelecimentos, response);
        verify(estabelecimentoServices, times(1)).listarEstabelecimento();
    }

    @Test
    void testBuscarEstabelecimentoPorId() {
        Estabelecimento estabelecimento = new Estabelecimento(1L, "Estacionamento Central", "12345678901234", "Rua Principal, 123", "11987654321", 50);
        when(estabelecimentoServices.buscarEstabelecimentoPorId(1L)).thenReturn(Optional.of(estabelecimento));

        Optional<Estabelecimento> response = estabelecimentoController.buscarEstabelecimentoPorId(1L);

        assertEquals(estabelecimento, response.get());
        verify(estabelecimentoServices, times(1)).buscarEstabelecimentoPorId(1L);
    }

    @Test
    void testAtualizarEstabelecimento() {
        Estabelecimento estabelecimentoAtualizado = new Estabelecimento(1L, "Estacionamento Central Atualizado", "12345678901234", "Rua Principal, 456", "11987654321", 60);
        when(estabelecimentoServices.atualizarEstabelecimento(1L, estabelecimentoAtualizado)).thenReturn(estabelecimentoAtualizado);

        Estabelecimento response = estabelecimentoController.atualizarEstabelecimento(1L, estabelecimentoAtualizado);

        assertEquals(estabelecimentoAtualizado, response);
        verify(estabelecimentoServices, times(1)).atualizarEstabelecimento(1L, estabelecimentoAtualizado);
    }

    @Test
    void testDeletarEstabelecimento() {
        doNothing().when(estabelecimentoServices).deletarEstabelecimento(1L);

        estabelecimentoController.deletarEstabelecimento(1L);

        verify(estabelecimentoServices, times(1)).deletarEstabelecimento(1L);
    }

}
