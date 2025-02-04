package entities;

import enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String marca;

    @Column (nullable = false)
    private String modelo;

    @Column (nullable = false)
    private String cor;

    @Column (nullable = false)
    private String placa;

    @Column (nullable = false)
    private TipoVeiculo tipo;
}
