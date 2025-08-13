package br.edu.ifpb.es.cuidarme.rest.dto.Paciente;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteResponseDTO {

        private Long id;
        private String nome;
        private String cpf;
        private String rg;
        private LocalDate dataNascimento;
        private String sexo;
        private String estadoCivil;
        private String grauInstrucao;
        private String profissao;
        private String telefonePessoal;
        private String infoAdicionais;

}
