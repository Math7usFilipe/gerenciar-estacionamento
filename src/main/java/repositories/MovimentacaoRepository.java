package repositories;

import entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByVeiculoId(Long id);

    List<Movimentacao> findByEstabelecimentoId(Long id);

    Optional<Movimentacao> findTopByVeiculoIdOrderByDataEntradaDesc(Long id);

    List<Movimentacao> findByDataSaidaIsNull();
}
