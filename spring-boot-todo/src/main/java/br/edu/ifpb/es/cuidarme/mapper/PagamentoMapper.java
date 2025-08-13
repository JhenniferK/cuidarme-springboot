package br.edu.ifpb.es.cuidarme.mapper;

import br.edu.ifpb.es.cuidarme.model.Pagamento;
import br.edu.ifpb.es.cuidarme.model.StatusPagamento;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento from(PagamentoSalvarRequestDTO from) {
        Pagamento pagamento = new Pagamento();
        pagamento.setData(from.getData());
        pagamento.setValor(from.getValor());
        pagamento.setMetodo(from.getMetodo());
        pagamento.setStatusPagamento(StatusPagamento.valueOf(from.getStatus()));
        return pagamento;
    }

    public PagamentoResponseDTO from(Pagamento from) {
        PagamentoResponseDTO pagamentoResponseDTO = new PagamentoResponseDTO();
        pagamentoResponseDTO.setData(from.getData());
        pagamentoResponseDTO.setValor(from.getValor());
        pagamentoResponseDTO.setMetodo(from.getMetodo());
        pagamentoResponseDTO.setStatus(from.getStatusPagamento());
        return pagamentoResponseDTO;
    }

}