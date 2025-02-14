package fcamara.desafio.estacionamento.controllers;

import fcamara.desafio.estacionamento.Controller.VeiculoController;
import fcamara.desafio.estacionamento.Services.VeiculoService;
import fcamara.desafio.estacionamento.entities.Veiculo;
import fcamara.desafio.estacionamento.entities.enums.TipoVeiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VeiculoControllerTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoService veiculoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarVeiculo() {
        Veiculo veiculo = new Veiculo("Toyota", "Corolla", "Preto", "ABC-1234", TipoVeiculo.CARRO);
        when(veiculoService.criarVeiculo(veiculo)).thenReturn(veiculo);

        ResponseEntity<Veiculo> response = veiculoController.criarVeiculo(veiculo);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
        verify(veiculoService, times(1)).criarVeiculo(veiculo);
    }

    @Test
    void testListarVeiculo() {
        Veiculo veiculo1 = new Veiculo("Toyota", "Corolla", "Preto", "ABC-1234", TipoVeiculo.CARRO);
        Veiculo veiculo2 = new Veiculo("Honda", "Civic", "Branco", "XYZ-5678", TipoVeiculo.CARRO);

        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);
        when(veiculoService.listarVeiculo()).thenReturn(veiculos);

        ResponseEntity<List<Veiculo>> response = veiculoController.listarVeiculo();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(veiculos, response.getBody());
        verify(veiculoService, times(1)).listarVeiculo();
    }

    @Test
    void testObterVeiculoPorIdExistente() {
        Veiculo veiculo = new Veiculo("Toyota", "Corolla", "Preto", "ABC-1234", TipoVeiculo.CARRO);
        when(veiculoService.buscarVeiculoPorId(1L)).thenReturn(Optional.of(veiculo));

        ResponseEntity<Veiculo> response = veiculoController.obterVeiculoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
        verify(veiculoService, times(1)).buscarVeiculoPorId(1L);
    }

    @Test
    void testAtualizarVeiculo() {
        Veiculo veiculoAtualizado = new Veiculo("Toyota", "Corolla", "Preto", "ABC-1234", TipoVeiculo.CARRO);
        when(veiculoService.atualizarVeiculo(1L, veiculoAtualizado)).thenReturn(veiculoAtualizado);

        ResponseEntity<Veiculo> response = veiculoController.atualizarVeiculo(1L, veiculoAtualizado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoAtualizado, response.getBody());
        verify(veiculoService, times(1)).atualizarVeiculo(1L, veiculoAtualizado);
    }

    @Test
    void testBuscarPorTipo() {
        Veiculo veiculo1 = new Veiculo("Toyota", "Corolla", "Preto", "ABC-1234", TipoVeiculo.CARRO);
        Veiculo veiculo2 = new Veiculo("Honda", "Civic", "Branco", "XYZ-5678", TipoVeiculo.CARRO);
        List<Veiculo> veiculos = Arrays.asList(veiculo1, veiculo2);
        when(veiculoService.buscarVeiculosPorId(TipoVeiculo.CARRO)).thenReturn(veiculos);

        ResponseEntity<List<Veiculo>> response = veiculoController.buscarPorTipo(TipoVeiculo.CARRO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(veiculos, response.getBody());
        verify(veiculoService, times(1)).buscarVeiculosPorId(TipoVeiculo.CARRO);
    }

}
