package fcamara.desafio.estacionamento.services;

import fcamara.desafio.estacionamento.Services.EstabelecimentoServices;
import fcamara.desafio.estacionamento.entities.Estabelecimento;
import fcamara.desafio.estacionamento.repositories.EstabelecimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstabelecimentoServiceTest {
    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @InjectMocks
    private EstabelecimentoServices estabelecimentoServices;

    private Estabelecimento estabelecimento;

    @BeforeEach
    void setUp() {
        estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estacionamento Central");
        estabelecimento.setEndereco("Rua Principal, 123");
        estabelecimento.setTelefone("(87) 1231-3123");
    }

    @Test
    void testCriarEstabelecimento() {
        when(estabelecimentoRepository.save(any(Estabelecimento.class))).thenReturn(estabelecimento);

        Estabelecimento resultado = estabelecimentoServices.criarEstabelecimento(estabelecimento);

        assertNotNull(resultado);
        assertEquals("Estacionamento Central", resultado.getNome());
        assertEquals("Rua Principal, 123", resultado.getEndereco());
        assertEquals("(87) 1231-3123", resultado.getTelefone());
        verify(estabelecimentoRepository, times(1)).save(estabelecimento);
    }

    @Test
    void testListarEstabelecimento() {
        when(estabelecimentoRepository.findAll()).thenReturn(Arrays.asList(estabelecimento));

        List<Estabelecimento> resultado = estabelecimentoServices.listarEstabelecimento();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Estacionamento Central", resultado.get(0).getNome());
        assertEquals("Rua Principal, 123", resultado.get(0).getEndereco());
        assertEquals("(87) 1231-3123", resultado.get(0).getTelefone());
        verify(estabelecimentoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarEstabelecimentoPorId() {
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.of(estabelecimento));

        Optional<Estabelecimento> resultado = estabelecimentoServices.buscarEstabelecimentoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Estacionamento Central", resultado.get().getNome());
        assertEquals("Rua Principal, 123", resultado.get().getEndereco());
        assertEquals("(87) 1231-3123", resultado.get().getTelefone());
        verify(estabelecimentoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarEstabelecimentoPorIdNaoEncontrado() {
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Estabelecimento> resultado = estabelecimentoServices.buscarEstabelecimentoPorId(1L);

        assertFalse(resultado.isPresent());
        verify(estabelecimentoRepository, times(1)).findById(1L);
    }

    @Test
    void testAtualizarEstabelecimento() {
        Estabelecimento estabelecimentoAtualizado = new Estabelecimento();
        estabelecimentoAtualizado.setNome("Estacionamento Novo");
        estabelecimentoAtualizado.setEndereco("Rua Secundária, 456");
        estabelecimentoAtualizado.setTelefone("(89) 8789-312321");

        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.of(estabelecimento));
        when(estabelecimentoRepository.save(any(Estabelecimento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Estabelecimento resultado = estabelecimentoServices.atualizarEstabelecimento(1L, estabelecimentoAtualizado);

        assertNotNull(resultado);
        assertEquals("Estacionamento Novo", resultado.getNome());
        assertEquals("Rua Secundária, 456", resultado.getEndereco());
        assertEquals("(89) 8789-312321", resultado.getTelefone());
        verify(estabelecimentoRepository, times(1)).findById(1L);
        verify(estabelecimentoRepository, times(1)).save(estabelecimento);
    }

    @Test
    void testDeletarEstabelecimento() {
        doNothing().when(estabelecimentoRepository).deleteById(1L);

        estabelecimentoServices.deletarEstabelecimento(1L);

        verify(estabelecimentoRepository, times(1)).deleteById(1L);
    }
}