package br.edu.ifpb.es.cuidarme.rest.dto.Prontuario;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProntuarioResponseDTO {

    private Long id;
    private String descricao;
    private LocalDateTime dataRegistro;

}