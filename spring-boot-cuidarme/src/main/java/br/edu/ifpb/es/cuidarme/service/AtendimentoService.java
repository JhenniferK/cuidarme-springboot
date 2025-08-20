package br.edu.ifpb.es.cuidarme.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.model.Atendimento;
import br.edu.ifpb.es.cuidarme.model.StatusAtendimento;
import br.edu.ifpb.es.cuidarme.repository.AtendimentoRepository;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoBuscarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;

    @Transactional
    public Atendimento criar(Atendimento obj) {
        return repository.save(obj);
    }

    public List<Atendimento> recuperarTodos() {
        return repository.findAll();
    }

    public Optional<Atendimento> buscarPor(UUID lookupId) {
        Atendimento objExemplo = Atendimento.builder()
                .lookupId(lookupId)
                .build();
        Example<Atendimento> exemplo = Example.of(objExemplo);
        return repository.findOne(exemplo);
    }

    @Transactional
    public Atendimento atualizar(Atendimento obj) {
        return repository.save(obj);
    }

    @Transactional
    public Atendimento remarcar(UUID lookupId, LocalDateTime novaData, String novaLocalidade) {
        Atendimento atendimento = repository.findByLookupId(lookupId)
                .orElseThrow(() -> new IllegalArgumentException("Atendimento com lookupId " + lookupId + " não encontrado."));

        atendimento.setData(novaData);
        atendimento.setLocalidade(novaLocalidade);

        atendimento.setStatus(StatusAtendimento.AGENDADO);

        return repository.save(atendimento);
    }

    @Transactional
    public void cancelar(UUID lookupId) throws SistemaException {
        Atendimento atendimento = repository.findByLookupId(lookupId)
                .orElseThrow(() -> new SistemaException("Atendimento não encontrado"));

        atendimento.setStatus(StatusAtendimento.CANCELADO);
        repository.save(atendimento);
    }

    public Page<Atendimento> buscar(AtendimentoBuscarDTO dto) {
        PageRequest pageRequest = PageRequest.of(dto.getNumeroPagina(), dto.getTamanhoPagina());
        return repository.buscarPor(dto, pageRequest);
    }

}