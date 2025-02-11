package fcamara.desafio.estacionamento.Controller;

import fcamara.desafio.estacionamento.Services.VeiculoService;
import fcamara.desafio.estacionamento.entities.Veiculo;
import fcamara.desafio.estacionamento.entities.enums.TipoVeiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.criarVeiculo(veiculo);
        return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculo() {
        List<Veiculo> veiculos = veiculoService.listarVeiculo();
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> obterVeiculoPorId(@PathVariable Long id) {
        return veiculoService.buscarVeiculoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping ("/{id}")
    public  ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        try {
            Veiculo veiculoAtualizado = veiculoService.atualizarVeiculo(id, veiculo);
            return new ResponseEntity<>(veiculoAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping ("/tipo/{tipo}")
    public ResponseEntity<List<Veiculo>> buscarPorTipo(@PathVariable TipoVeiculo tipo) {
        List<Veiculo> veiculos = veiculoService.buscarVeiculosPorId(tipo);
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }
}