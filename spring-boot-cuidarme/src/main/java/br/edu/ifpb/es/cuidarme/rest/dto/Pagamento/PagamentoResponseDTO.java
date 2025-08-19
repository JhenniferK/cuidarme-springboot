package br.edu.ifpb.es.cuidarme.rest.dto.Pagamento;

import br.edu.ifpb.es.cuidarme.model.Metodo;
import br.edu.ifpb.es.cuidarme.model.StatusPagamento;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagamentoResponseDTO {

    private Long id;
    private int valor;
    private LocalDateTime data;
    private Metodo metodo;
    private StatusPagamento status;

}