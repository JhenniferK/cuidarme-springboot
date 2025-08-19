package br.edu.ifpb.es.cuidarme.rest.dto.Prontuario;

import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

@Data
public class ProntuarioBuscarDTO {

    private UUID lookupId;
    private LocalDateTime dataRegistro;
    private Integer numeroPagina = 0;
    private Integer tamanhoPagina = 10;

}
