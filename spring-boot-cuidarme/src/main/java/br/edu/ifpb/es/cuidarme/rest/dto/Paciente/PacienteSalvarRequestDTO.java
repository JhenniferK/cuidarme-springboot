package br.edu.ifpb.es.cuidarme.rest.dto.Paciente;

import br.edu.ifpb.es.cuidarme.rest.dto.ContatoEmergencia.ContatoEmergenciaDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Endereco.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
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
    private String telefone;
    @NotNull
    private EnderecoDTO enderecoPessoal;
    @NotNull
    private EnderecoDTO enderecoTrabalho;
    @NotNull
    private String infoAdicionais;
    @NotNull
    private ContatoEmergenciaDTO contatoEmergencia;

}