package fcamara.desafio.estacionamento.services;



import fcamara.desafio.estacionamento.Services.VeiculoService;
import fcamara.desafio.estacionamento.entities.Veiculo;
import fcamara.desafio.estacionamento.entities.enums.TipoVeiculo;
import fcamara.desafio.estacionamento.repositories.VeiculoRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoService veiculoService;

    private Veiculo veiculo;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo();

        veiculo.setId(1L);
        veiculo.setMarca("Ford");
        veiculo.setModelo("Fiesta");
        veiculo.setCor("Preto");
        veiculo.setPlaca("ABC-1234");
        veiculo.setTipo(TipoVeiculo.CARRO);
    }

    @Test
    void testCriarVeiculo() {
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        Veiculo resultado = veiculoService.criarVeiculo(veiculo);

        assertNotNull(resultado);
        assertEquals("Ford", resultado.getMarca());
        verify(veiculoRepository, times(1)).save(veiculo);
    }

    @Test
    void testListarVeiculos() {
        when(veiculoRepository.findAll()).thenReturn(Arrays.asList(veiculo));

        List<Veiculo> resultado = veiculoService.listarVeiculo();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Ford", resultado.get(0).getMarca());
        verify(veiculoRepository, times(1)).findAll();
    }

    @Test
    void testBuscarVeiculoPorId() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Optional<Veiculo> resultado = veiculoService.buscarVeiculoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Ford", resultado.get().getMarca());
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarVeiculoPorIdNaoEncontrado() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Veiculo> resultado = veiculoService.buscarVeiculoPorId(1L);

        assertFalse(resultado.isPresent());
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testAtualizarVeiculo() {
        Veiculo veiculoAtualizado = new Veiculo();

        veiculoAtualizado.setMarca("Chevrolet");
        veiculoAtualizado.setModelo("Onix");
        veiculoAtualizado.setCor("Branco");
        veiculoAtualizado.setPlaca("XYZ-5678");
        veiculoAtualizado.setTipo(TipoVeiculo.CARRO);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculoAtualizado);

        Veiculo resultado = veiculoService.atualizarVeiculo(1L, veiculoAtualizado);

        assertNotNull(resultado);
        assertEquals("Chevrolet", resultado.getMarca());
        assertEquals("Onix", resultado.getModelo());
        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, times(1)).save(veiculo);
    }

    @Test
    void testAtualizarVeiculoNaoEncontrado() {
        Veiculo veiculoAtualizado = new Veiculo();

        veiculoAtualizado.setMarca("Chevrolet");

        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            veiculoService.atualizarVeiculo(1L, veiculoAtualizado);
        });

        assertEquals("Veiculo não encontrado com o ID: 1", exception.getMessage());
        verify(veiculoRepository, times(1)).findById(1L);
        verify(veiculoRepository, never()).save(any(Veiculo.class));
    }

    @Test
    void testRegistrarEntrada() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        String resultado = veiculoService.registrarEntrada(1L);

        assertEquals("Entrada registrada para o veiculo de placa: ABC-1234", resultado);
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testRegistrarEntradaNaoEncontrado() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
           veiculoService.registrarEntrada(1L);
        });

        assertEquals("Veiculo não encontrado para entrada", exception.getMessage());
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testRegistraSaida() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        String resultado = veiculoService.registraSainda(1L);

        assertEquals("Saida registrada para o veiculo de placa: ABC-1234", resultado);
        verify(veiculoRepository, times(1)).findById(1L);
    }

    @Test
    void testRegistradaSaidaNaoEntrado() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
           veiculoService.registraSainda(1L);
        });

        assertEquals("Veiculo não encontrado para saida", exception.getMessage());
        verify(veiculoRepository, times(1)).findById(1L);
    }
}
