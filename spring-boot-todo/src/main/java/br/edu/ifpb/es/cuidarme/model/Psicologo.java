package br.edu.ifpb.es.cuidarme.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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
@Table(name = "Psicologo")
public class Psicologo {

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
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Setter
    @Getter
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Setter
    @Getter
    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Setter
    @Getter
    @OneToMany(mappedBy = "psicologo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atendimento> atendimentos = new ArrayList<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "psicologo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prontuario> prontuarios = new ArrayList<>();

    @PrePersist
    protected void prePersist() {
        this.lookupId = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Psicologo psicologo = (Psicologo) o;
        return Objects.equals(id, psicologo.id) && Objects.equals(nome, psicologo.nome) && Objects.equals(email, psicologo.email) && Objects.equals(senha, psicologo.senha) && Objects.equals(atendimentos, psicologo.atendimentos) && Objects.equals(prontuarios, psicologo.prontuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha, atendimentos, prontuarios);
    }

    @Override
    public String toString() {
        return "Psicologo{" +
                "crp=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", Atendimentos=" + atendimentos + '\'' +
                ", prontuarios=" + prontuarios +
                '}';
    }
}