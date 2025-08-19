package br.edu.ifpb.es.cuidarme.rest.dto.Prontuario;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProntuarioResponseDTO {

    private UUID lookupId;
    private String descricao;
    private LocalDateTime dataRegistro;

}