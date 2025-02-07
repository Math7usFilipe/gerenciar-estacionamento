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

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        if(!veiculoRepository.existsById(id)){
            throw new RuntimeException("Veículo não encontrado para atualização");
        }
        veiculo.setId(id);
        return veiculoRepository.save(veiculo);
    }

    public String registrarEntrada(Long id) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        if(veiculo.isEmpty()) {
            throw new RuntimeException("Veículo não encontrado para entrada");
        }
        return "Entrada registrada para o veículo de placa: " + veiculo.get().getPlaca();
    }

    public String registraSainda(Long id) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(id);
        if(veiculo.isEmpty()){
            throw new RuntimeException("Veículo não encontrado para saída");
        }
        return "Saída registrada para o veículo de placa: " + veiculo.get().getPlaca();
    }

    public List<Veiculo> buscarVeiculosPorId(TipoVeiculo tipo) {
        return veiculoRepository.findByTipo(tipo);
    }
}
