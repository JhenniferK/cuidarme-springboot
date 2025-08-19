package br.edu.ifpb.es.cuidarme.rest.dto.Pagamento;

import br.edu.ifpb.es.cuidarme.model.Metodo;
import br.edu.ifpb.es.cuidarme.model.StatusPagamento;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

import java.time.LocalDateTime;

@Data
public class PagamentoBuscarDTO {

    private UUID lookupId;
    private LocalDateTime data;
    private Metodo metodo;
    private StatusPagamento status;
    private Integer numeroPagina = 0;
    private Integer tamanhoPagina = 10;

}
