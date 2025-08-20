package br.edu.ifpb.es.cuidarme.rest.dto.Paciente;

import br.edu.ifpb.es.cuidarme.model.ContatoEmergencia;
import br.edu.ifpb.es.cuidarme.model.Endereco;
import br.edu.ifpb.es.cuidarme.model.Psicologo;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioResponseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class PacienteResponseDTO {

        private UUID lookupId;
        private String nome;
        private String cpf;
        private String rg;
        private LocalDate dataNascimento;
        private String sexo;
        private String estadoCivil;
        private String grauInstrucao;
        private String profissao;
        private String telefone;
        private Endereco enderecoPessoal;
        private Endereco enderecoTrabalho;
        private String infoAdicionais;
        private ContatoEmergencia contatoEmergencia;
        private Psicologo psicologo;
        @Getter
        @Setter
        private List<ProntuarioResponseDTO> prontuarios;

}