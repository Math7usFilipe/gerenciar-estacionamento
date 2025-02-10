package fcamara.desafio.estacionamento.repositories;

import fcamara.desafio.estacionamento.entities.Estabelecimento;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles ("test")
class EstabelecimentoRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void findByCnpj() {
    }
    
}