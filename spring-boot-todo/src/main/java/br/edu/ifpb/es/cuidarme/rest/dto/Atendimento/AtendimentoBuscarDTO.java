package br.edu.ifpb.es.cuidarme.rest.dto.Atendimento;

import br.edu.ifpb.es.cuidarme.model.StatusAtendimento;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtendimentoBuscarDTO {

    private LocalDateTime data;
    private String localidade;
    private StatusAtendimento status;
    private Integer numeroPagina = 0;
    private Integer tamanhoPagina = 10;

}
