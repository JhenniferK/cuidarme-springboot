package br.edu.ifpb.es.cuidarme.rest.dto.Psicologo;

import br.edu.ifpb.es.cuidarme.model.Paciente;
import lombok.Data;

import java.util.UUID;

@Data
public class PsicologoResponseDTO {

    private UUID lookupId;
    private String nome;
    private String email;
    private String senha;

}
