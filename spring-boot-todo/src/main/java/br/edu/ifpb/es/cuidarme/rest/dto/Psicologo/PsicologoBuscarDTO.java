package br.edu.ifpb.es.cuidarme.rest.dto.Psicologo;

import lombok.Data;

@Data
public class PsicologoBuscarDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Integer numeroPagina = 0;
    private Integer tamanhoPagina = 10;

}
