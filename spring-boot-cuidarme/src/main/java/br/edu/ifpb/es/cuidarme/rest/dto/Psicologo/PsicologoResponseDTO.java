package br.edu.ifpb.es.cuidarme.rest.dto.Psicologo;

import lombok.Data;

@Data
public class PsicologoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;

}
