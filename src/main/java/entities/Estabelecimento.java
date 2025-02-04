package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "estabelecimento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = false)
    private String nome;

    @Column (nullable = false)
    private String cnpj;

    @Column (nullable = false)
    private String endereco;

    @Column (nullable = false)
    private int telefone;

    @Column (nullable = false)
    private int vagasMotos;

    @Column (nullable = false)
    private int vagasCarros;

}
