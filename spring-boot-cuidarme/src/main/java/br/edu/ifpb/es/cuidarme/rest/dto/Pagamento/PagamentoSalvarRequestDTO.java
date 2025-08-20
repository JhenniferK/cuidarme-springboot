package br.edu.ifpb.es.cuidarme.rest.dto.Pagamento;

import br.edu.ifpb.es.cuidarme.model.Metodo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PagamentoSalvarRequestDTO {

    @NotBlank
    private int valor;
    @NotNull
    private LocalDateTime data;
    @NotNull
    private Metodo metodo;
    @NotBlank
    private String status;

}