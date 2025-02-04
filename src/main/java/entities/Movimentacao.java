package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table (name = "movimentacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "veiculo_id", nullable = false)
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn (name = "estabelecimento_id", nullable = false)
    private Estabelecimento estabelecimento;
    @Column (nullable = false)
    private LocalDateTime dataEntrada;

    private LocalDateTime dataSaida;
}
