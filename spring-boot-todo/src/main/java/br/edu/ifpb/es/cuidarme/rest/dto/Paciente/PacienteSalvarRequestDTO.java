package br.edu.ifpb.es.cuidarme.rest.dto.Paciente;

import br.edu.ifpb.es.cuidarme.model.ContatoEmergencia;
import br.edu.ifpb.es.cuidarme.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteSalvarRequestDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String rg;
    @NotBlank
    private LocalDate dataNascimento;

    @NotBlank
    private String sexo;

    @NotBlank
    private String estadoCivil;

    @NotBlank
    private String grauInstrucao;

    @NotBlank
    private String profissao;

    @NotBlank
    private String telefonePessoal;

    @NotBlank
    private Endereco enderecoPessoal;

    @NotBlank
    private Endereco enderecoTrabalho;

    @NotBlank
    private String infoAdicionais;

    @NotBlank
    private ContatoEmergencia contatoEmergencia;

}