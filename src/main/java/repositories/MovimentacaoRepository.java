package repositories;

import entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByVeiculoId(Long id);

    List<Movimentacao> findByEstabelecimentoId(Long id);

    Optional<Movimentacao> findTopByVeiculoIdOrderByDataEntradaDesc(Long id);

    List<Movimentacao> findByDataSaidaIsNull();
}
