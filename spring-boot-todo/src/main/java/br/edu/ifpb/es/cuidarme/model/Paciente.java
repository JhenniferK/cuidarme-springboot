package br.edu.ifpb.es.cuidarme.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
@Table(name = "Paciente")
public class Paciente {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID lookupId;

    @Setter
    @Getter
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Setter
    @Getter
    @Column(name = "CPF", unique = true, nullable = false)
    private String cpf;

    @Setter
    @Getter
    @Column(name = "RG", nullable = false)
    private String rg;

    @Setter
    @Getter
    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private LocalDate dataNascimento;

    @Setter
    @Getter
    @Column(name = "SEXO", nullable = false)
    private String sexo;

    @Setter
    @Getter
    @Column(name = "ESTADO_CIVIL", nullable = false)
    private String estadoCivil;

    @Setter
    @Getter
    @Column(name = "GRAU_INSTRUCAO", nullable = false)
    private String grauInstrucao;

    @Setter
    @Getter
    @Column(name = "PROFISSAO", nullable = false)
    private String profissao;

    @Setter
    @Getter
    @Column(name = "TELEFONE", nullable = false)
    private String telefonePessoal;

    @Setter
    @Getter
    @Embedded
    @Column(name = "END_PESSOAL", nullable = false)
    @AttributeOverrides({
            @AttributeOverride(name="logradouro", column=@Column(name = "LOGRADOURO_PESSOAL", nullable = false)),
            @AttributeOverride(name="numero", column=@Column(name = "NUMERO_PESSOAL")),
            @AttributeOverride(name="cep", column=@Column(name = "CEP_PESSOAL", nullable = false)),
            @AttributeOverride(name="cidade", column=@Column(name = "CIDADE_PESSOAL", nullable = false)),
            @AttributeOverride(name="estado", column=@Column(name = "ESTADO_PESSOAL", nullable = false)),
    })
    private Endereco enderecoPessoal;

    @Setter
    @Getter
    @Embedded
    @Column(name = "END_TRABALHO")
    @AttributeOverrides({
            @AttributeOverride(name="logradouro", column=@Column(name = "LOGRADOURO_TRABALHO")),
            @AttributeOverride(name="numero", column=@Column(name = "NUMERO_TRABALHO")),
            @AttributeOverride(name="cep", column=@Column(name = "CEP_TRABALHO")),
            @AttributeOverride(name="cidade", column=@Column(name = "CIDADE_TRABALHO")),
            @AttributeOverride(name="estado", column=@Column(name = "ESTADO_TRABALHO"))
    })
    private Endereco enderecoTrabalho;

    @Setter
    @Getter
    @Column(name = "INFOS_ADICIONAIS")
    private String infoAdicionais;

    @Setter
    @Getter
    @Embedded
    @Column(name = "CONTATO_EMERGENCIA")
    @AttributeOverrides({
            @AttributeOverride(name="nome", column=@Column(name = "NOME_CONTATOEMERGENCIA")),
            @AttributeOverride(name="telefone", column =@Column(name = "TELEFONE_CONTATOEMERGENCIA"))
    })
    private ContatoEmergencia contatoEmergencia;

    @Setter
    @Getter
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atendimento> atendimentos = new ArrayList<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prontuario> prontuarios = new ArrayList<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @PrePersist
    private void init() {
        this.lookupId = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id) && Objects.equals(nome, paciente.nome) && Objects.equals(cpf, paciente.cpf) && Objects.equals(rg, paciente.rg) && Objects.equals(dataNascimento, paciente.dataNascimento) && Objects.equals(sexo, paciente.sexo) && Objects.equals(estadoCivil, paciente.estadoCivil) && Objects.equals(grauInstrucao, paciente.grauInstrucao) && Objects.equals(profissao, paciente.profissao) && Objects.equals(telefonePessoal, paciente.telefonePessoal) && Objects.equals(enderecoPessoal, paciente.enderecoPessoal) && Objects.equals(enderecoTrabalho, paciente.enderecoTrabalho) && Objects.equals(infoAdicionais, paciente.infoAdicionais) && Objects.equals(contatoEmergencia, paciente.contatoEmergencia) && Objects.equals(atendimentos, paciente.atendimentos) && Objects.equals(prontuarios, paciente.prontuarios) && Objects.equals(pagamentos, paciente.pagamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, rg, dataNascimento, sexo, estadoCivil, grauInstrucao, profissao, telefonePessoal, enderecoPessoal, enderecoTrabalho, infoAdicionais, contatoEmergencia, atendimentos, prontuarios, pagamentos);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", sexo='" + sexo + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", grauInstrucao='" + grauInstrucao + '\'' +
                ", profissao='" + profissao + '\'' +
                ", telefonePessoal='" + telefonePessoal + '\'' +
                ", enderecoPessoal=" + enderecoPessoal +
                ", enderecoTrabalho=" + enderecoTrabalho +
                ", infoAdicionais='" + infoAdicionais + '\'' +
                ", contatoEmergencia=" + contatoEmergencia +
                ", atendimentos=" + atendimentos +
                ", prontuarios=" + prontuarios +
                ", pagamentos=" + pagamentos +
                '}';
    }
}
