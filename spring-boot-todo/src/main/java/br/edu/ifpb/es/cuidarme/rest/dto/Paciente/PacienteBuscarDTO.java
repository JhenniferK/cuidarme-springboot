package br.edu.ifpb.es.cuidarme.rest.dto.Paciente;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacienteBuscarDTO {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private Integer numeroPagina = 0;
    private Integer tamanhoPagina = 10;

}
