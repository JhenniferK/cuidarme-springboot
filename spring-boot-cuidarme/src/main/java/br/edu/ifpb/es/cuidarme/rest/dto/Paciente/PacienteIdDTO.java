package br.edu.ifpb.es.cuidarme.rest.dto.Paciente;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

@Data
public class PacienteIdDTO {

    @NotNull
    private UUID lookupId;

}
