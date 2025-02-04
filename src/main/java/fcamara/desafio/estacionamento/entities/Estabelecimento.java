package fcamara.desafio.estacionamento.entities;

import jakarta.persistence.*;


@Entity
@Table (name = "estabelecimento")
public class Estabelecimento {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = false)
    private String nome;

    @Column (nullable = false, unique = true)
    private String cnpj;

    @Column (nullable = false)
    private String endereco;

    @Column (nullable = false)
    private int telefone;

    @Column (nullable = false)
    private int vagasMotos;

    public Estabelecimento() {}

    public Estabelecimento(Long id, String nome, String cnpj, String endereco, int telefone, int vagasMotos) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.vagasMotos = vagasMotos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getVagasMotos() {
        return vagasMotos;
    }

    public void setVagasMotos(int vagasMotos) {
        this.vagasMotos = vagasMotos;
    }
}
