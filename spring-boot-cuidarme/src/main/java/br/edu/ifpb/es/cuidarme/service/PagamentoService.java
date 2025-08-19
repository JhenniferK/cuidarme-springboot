package br.edu.ifpb.es.cuidarme.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.model.Pagamento;
import br.edu.ifpb.es.cuidarme.repository.PagamentoRepository;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoBuscarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional
    public Pagamento criar(Pagamento obj) {
        return repository.save(obj);
    }

    public List<Pagamento> recuperarTodos() {
        return repository.findAll();
    }

    public Optional<Pagamento> buscarPor(UUID lookupId) {
        Pagamento objExemplo = Pagamento.builder()
                .lookupId(lookupId)
                .build();
        Example<Pagamento> exemplo = Example.of(objExemplo);
        return repository.findOne(exemplo);
    }

    @Transactional
    public Pagamento atualizar(Pagamento obj) {
        return repository.save(obj);
    }

    @Transactional
    public void remover(Pagamento obj) {
        repository.delete(obj);
    }

    public Page<Pagamento> buscar(PagamentoBuscarDTO dto) {
        PageRequest pageRequest = PageRequest.of(dto.getNumeroPagina(), dto.getTamanhoPagina());
        return repository.buscarPor(dto, pageRequest);
    }

}