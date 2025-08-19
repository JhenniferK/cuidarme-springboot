package br.edu.ifpb.es.cuidarme.rest.dto.Pagamento;

import br.edu.ifpb.es.cuidarme.model.Metodo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagamentoSalvarRequestDTO {

    private Long id;
    @NotBlank
    private int valor;
    @NotBlank
    private LocalDateTime data;
    @NotBlank
    private Metodo metodo;
    @NotBlank
    private String status;

}