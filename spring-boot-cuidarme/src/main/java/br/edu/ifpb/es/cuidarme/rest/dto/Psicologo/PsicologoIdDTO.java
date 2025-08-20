package br.edu.ifpb.es.cuidarme.rest.dto.Psicologo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class PsicologoIdDTO {

    @NotNull
    private UUID lookupId;

}
