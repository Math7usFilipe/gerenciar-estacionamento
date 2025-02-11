package fcamara.desafio.estacionamento.Services;

import fcamara.desafio.estacionamento.entities.Movimentacao;
import fcamara.desafio.estacionamento.repositories.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public String gerarRelatorioEntradaSaida(Long id) {
        List<Movimentacao> movimentacaos = movimentacaoRepository.findByVeiculoId(id);
        if(movimentacaos.isEmpty()){
            return "Não há movimentações para o veículo de ID " + id;
        }
        StringBuilder relatorio = new StringBuilder();
        for (Movimentacao movimentacao : movimentacaos) {
            relatorio.append("Veículo: ").append(movimentacao.getVeiculo().getPlaca())
                    .append(", Estabelecimento: ").append(movimentacao.getEstabelecimento().getNome())
                    .append(", Entrada: ").append(movimentacao.getDataEntrada())
                    .append(", Saída: ").append(movimentacao.getDataSaida() != null ? movimentacao.getDataSaida() : "Ainda no loval")
                    .append("\n");
        }
        return relatorio.toString();
    }

    public String gerarRelatorioEntradaSaidaPorEstabelecimento(Long id) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByEstabelecimentoId(id);

        if (movimentacoes.isEmpty()) {
            return "Não há movimentações para o estabelecimento de ID " + id;
        }

        StringBuilder relatorio = new StringBuilder();
        for (Movimentacao movimentacao : movimentacoes) {
            relatorio.append("Veículo: ").append(movimentacao.getVeiculo().getPlaca())
                    .append(", Estabelecimento: ").append(movimentacao.getEstabelecimento().getNome())
                    .append(", Entrada: ").append(movimentacao.getDataEntrada())
                    .append(", Saída: ").append(movimentacao.getDataSaida() != null ? movimentacao.getDataSaida() : "Ainda no local")
                    .append("\n");
        }

        return relatorio.toString();
    }

    public String gerarSumarioPorHora(LocalDateTime inicio, LocalDateTime fim) {
        List<Movimentacao> movimentacaos = movimentacaoRepository.findByDataSaidaIsNull();
        StringBuilder sumario = new StringBuilder();
        for(Movimentacao movimentacao : movimentacaos) {
            if(movimentacao.getDataEntrada().isAfter(inicio) && movimentacao.getDataEntrada().isBefore(fim)) {
                Long horasEstacionado = ChronoUnit.HOURS.between(movimentacao.getDataEntrada(), LocalDateTime.now());
                sumario.append("Veículo: ").append(movimentacao.getVeiculo().getPlaca())
                        .append(", Estabelecimento: ").append(movimentacao.getEstabelecimento().getNome())
                        .append(", Entrada: ").append(movimentacao.getDataEntrada())
                        .append(", Horas no Estacionamento: ").append(horasEstacionado)
                        .append("\n");
            }
        }
        if(sumario.length() == 0) {
            return "Nenhuma movimentação registrada no período informado.";
        }
        return sumario.toString();
    }

    public Optional<Movimentacao> obterUltimaMovimentacao(Long id) {
        return movimentacaoRepository.findTopByVeiculoIdOrderByDataEntradaDesc(id);
    }

    public Movimentacao registrarEntrada(Movimentacao movimentacao) {
        movimentacao.setDataEntrada(LocalDateTime.now());
        return movimentacaoRepository.save(movimentacao);
    }

    public Movimentacao registrarSaida(Long id) {
        Optional<Movimentacao> movimentacaoOpt = movimentacaoRepository.findById(id);

        if(movimentacaoOpt.isPresent()) {
            Movimentacao movimentacao = movimentacaoOpt.get();
            if(movimentacao.getDataSaida() != null) {
                throw new IllegalStateException("Este veículo já foi registrado como saído.");
            }
            movimentacao.setDataSaida(LocalDateTime.now());
            return movimentacaoRepository.save(movimentacao);
        } else {
            throw new IllegalArgumentException("Movimentação com ID " + id + " não encontrada.");
        }
    }
}
