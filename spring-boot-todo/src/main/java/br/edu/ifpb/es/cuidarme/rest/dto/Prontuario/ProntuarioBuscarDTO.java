package br.edu.ifpb.es.cuidarme.rest.dto.Prontuario;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProntuarioBuscarDTO {

    private Long id;
    private LocalDateTime dataRegistro;
    private Integer numeroPagina = 0;
    private Integer tamanhoPagina = 10;

}
