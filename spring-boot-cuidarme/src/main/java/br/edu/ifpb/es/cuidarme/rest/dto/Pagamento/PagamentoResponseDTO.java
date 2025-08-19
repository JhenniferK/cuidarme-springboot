package br.edu.ifpb.es.cuidarme.rest.dto.Pagamento;

import br.edu.ifpb.es.cuidarme.model.Metodo;
import br.edu.ifpb.es.cuidarme.model.StatusPagamento;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PagamentoResponseDTO {

    private UUID lookupId;
    private int valor;
    private LocalDateTime data;
    private Metodo metodo;
    private StatusPagamento status;

}