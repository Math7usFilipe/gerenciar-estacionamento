package Controller;

import Services.EstabelecimentoServices;
import entities.Estabelecimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoServices estabelecimentoServices;

    @PostMapping
    public Estabelecimento criarEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
        return estabelecimentoServices.criarEstabelecimento(estabelecimento);
    }

    @GetMapping
    public List<Estabelecimento> listarEstabelecimento() {
        return estabelecimentoServices.listarEstabelecimento();
    }

    @GetMapping ("/{id}")
    public Optional<Estabelecimento> buscarEstabelecimentoPorId(@PathVariable Long id) {
        return estabelecimentoServices.buscarEstabelecimentoPorId(id);
    }

    @PutMapping ("/{id}")
    public Estabelecimento atualizarEstabelecimento(@PathVariable Long id, @RequestBody Estabelecimento estabelecimento) {
        return estabelecimentoServices.atualizarEstabelecimento(id, estabelecimento);
    }

    @DeleteMapping ("/{id}")
    public void deletarEstabelecimento(@PathVariable Long id) {
        estabelecimentoServices.deletarEstabelecimento(id);
    }
}
