package fcamara.desafio.estacionamento.repositories;

import fcamara.desafio.estacionamento.entities.Veiculo;
import fcamara.desafio.estacionamento.entities.enums.TipoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByTipo(TipoVeiculo tipo); // Corrigido para "findByTipo"
}
