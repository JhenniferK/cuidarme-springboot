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
@Table(name = "Prontuario")
public class Prontuario {

    @Id
    @Setter
    @Getter
    @Column(nullable = false, unique = true)
    private UUID lookupId;

    @Setter
    @Getter
    @Column(name = "DESCRICAO")
    private String descricao;

    @Setter
    @Getter
    @Column(name = "DATA_REGISTRO", nullable = false)
    private LocalDateTime dataRegistro;

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
    public void prePersist() {
        this.lookupId = UUID.randomUUID();
        this.dataRegistro = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prontuario that = (Prontuario) o;
        return Objects.equals(descricao, that.descricao) && Objects.equals(dataRegistro, that.dataRegistro) && Objects.equals(psicologo, that.psicologo) && Objects.equals(paciente, that.paciente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, dataRegistro, psicologo, paciente);
    }

    @Override
    public String toString() {
        return "Prontuario{" +
                ", descricao='" + descricao + '\'' +
                ", dataRegistro=" + dataRegistro +
                ", psicologo=" + psicologo +
                ", paciente=" + paciente +
                '}';
    }
}
