package br.edu.ifpb.es.cuidarme.rest.dto.ContatoEmergencia;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContatoEmergenciaDTO {

    private String nome;
    private String telefone;
    private String parentesco;

}