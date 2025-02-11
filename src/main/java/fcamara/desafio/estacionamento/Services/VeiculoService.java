package fcamara.desafio.estacionamento.Services;

import fcamara.desafio.estacionamento.entities.Veiculo;
import fcamara.desafio.estacionamento.entities.enums.TipoVeiculo;
import fcamara.desafio.estacionamento.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo criarVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarVeiculo() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarVeiculoPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(id)
                .map(veiculo -> {
                    veiculo.setMarca(veiculoAtualizado.getMarca());
                    veiculo.setModelo(veiculoAtualizado.getModelo());
                    veiculo.setCor(veiculoAtualizado.getCor());
                    veiculo.setPlaca(veiculoAtualizado.getPlaca());
                    veiculo.setTipo(veiculoAtualizado.getTipo());
                    return veiculoRepository.save(veiculo);
                })
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com o ID: " + id));
    }

    public List<Veiculo> buscarVeiculosPorId(TipoVeiculo tipo) {
        return veiculoRepository.findByTipo(tipo);
    }
}
