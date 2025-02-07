package fcamara.desafio.estacionamento.Controller;

import fcamara.desafio.estacionamento.Services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping ("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping ("/relatorio/veiculo/id")
    public ResponseEntity<String> gerarRelatorioEntradaSaida(@PathVariable Long id) {
        String relatorio = movimentacaoService.gerarRelatorioEntradaSaida(id);
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping ("/relatorio/estabelecimento/id")
    public ResponseEntity<String> gerarRelatorioEntradaSaidaPorEstabelecimento(@PathVariable Long id) {
        String relatorio = movimentacaoService.gerarRelatorioEntradaSaidaPorEstabelecimento(id);
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping ("/sumario")
    public ResponseEntity<String> gerarSumarioPorHora(@RequestParam("inicio") String inicio, @RequestParam("fim") String fim) {
        LocalDateTime inicoDate = LocalDateTime.parse(inicio);
        LocalDateTime fimDate = LocalDateTime.parse(fim);
        String sumario = movimentacaoService.gerarSumarioPorHora(inicoDate, fimDate);
        return ResponseEntity.ok(sumario);
    }
}
