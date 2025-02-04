package fcamara.desafio.estacionamento.Services;

import fcamara.desafio.estacionamento.entities.Estabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fcamara.desafio.estacionamento.repositories.EstabelecimentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoServices {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    //create
    public Estabelecimento criarEstabelecimento(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }
    //Read all
    public List<Estabelecimento> listarEstabelecimento() {
        return estabelecimentoRepository.findAll();
    }
    //Read by id
    public Optional<Estabelecimento> buscarEstabelecimentoPorId(Long id) {
        return estabelecimentoRepository.findById(id);
    }
    //Update
    public Estabelecimento atualizarEstabelecimento(Long id, Estabelecimento estabelecimentoAtualizado) {
        return estabelecimentoRepository.findById(id)
                .map(estabelecimento -> {
                    estabelecimento.setNome(estabelecimento.getNome());
                    estabelecimento.setEndereco(estabelecimento.getEndereco());
                    estabelecimento.setTelefone(estabelecimento.getTelefone());
                    return estabelecimentoRepository.save(estabelecimento);
                })
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado com o ID:" + id));
    }
    //Delete
    public void deletarEstabelecimento(Long id) {
        estabelecimentoRepository.deleteById(id);
    }
}
