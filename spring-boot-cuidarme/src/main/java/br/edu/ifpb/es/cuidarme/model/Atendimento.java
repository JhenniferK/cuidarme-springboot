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
@Table(name = "Atendimento")
public class Atendimento {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID lookupId;

    @Setter
    @Getter
    @Column(name = "DATA", nullable = false)
    private LocalDateTime data;

    @Setter
    @Getter
    @Column(name = "LOCALIDADE", nullable = false)
    private String localidade;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private StatusAtendimento statusAtendimento;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "PSICOLOGO_ID")
    private Psicologo psicologo;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "PACIENTE_ID")
    private Paciente paciente;

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
        this.data = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendimento that = (Atendimento) o;
        return Objects.equals(id, that.id) && Objects.equals(data, that.data) && Objects.equals(localidade, that.localidade) && Objects.equals(statusAtendimento, that.statusAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, localidade, statusAtendimento);
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "id=" + id +
                ", data=" + data +
                ", localidade='" + localidade + '\'' +
                ", agendado=" + statusAtendimento +
                ", psicologo=" + psicologo +
                ", paciente=" + paciente +
                '}';
    }
}
