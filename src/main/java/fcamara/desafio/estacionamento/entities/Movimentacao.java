package fcamara.desafio.estacionamento.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private Estabelecimento estabelecimento;

    @Column(nullable = false)
    private LocalDateTime dataEntrada;

    private LocalDateTime dataSaida;

    // Construtor padrão (obrigatório para o JPA)
    public Movimentacao() {
    }

    // Construtor com parâmetros (opcional, mas útil)
    public Movimentacao(Veiculo veiculo, Estabelecimento estabelecimento, LocalDateTime dataEntrada) {
        this.veiculo = veiculo;
        this.estabelecimento = estabelecimento;
        this.dataEntrada = dataEntrada;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    // Método toString (opcional, mas útil para debug)
    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", veiculo=" + veiculo +
                ", estabelecimento=" + estabelecimento +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                '}';
    }
}