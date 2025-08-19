package br.edu.ifpb.es.cuidarme.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Pagamento")
public class Pagamento {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, unique = true)
    private UUID lookupId;

    @Setter
    @Getter
    @Column(name = "VALOR", nullable = false)
    private Integer valor;

    @Setter
    @Getter
    @Column(name = "DATA", nullable = false)
    private LocalDateTime data;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "METODO", nullable = false)
    private Metodo metodo;

    @Setter
    @Getter
    @Column(name = "STATUS", nullable = false)
    private StatusPagamento statusPagamento;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "PACIENTE_ID")
    private Paciente paciente;

    @PrePersist
    public void prePersist() {
        this.lookupId = UUID.randomUUID();
        this.data = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) && Objects.equals(valor, pagamento.valor) && metodo == pagamento.metodo && Objects.equals(statusPagamento, pagamento.statusPagamento) && Objects.equals(data, pagamento.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, metodo, statusPagamento, data);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", valor=" + valor +
                ", metodo='" + metodo + '\'' +
                ", status=" + statusPagamento +
                ", data=" + data +
                '}';
    }
}
