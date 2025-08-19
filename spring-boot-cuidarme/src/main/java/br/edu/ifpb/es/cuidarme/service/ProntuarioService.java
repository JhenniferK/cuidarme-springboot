package br.edu.ifpb.es.cuidarme.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.model.Prontuario;
import br.edu.ifpb.es.cuidarme.repository.ProntuarioRepository;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioBuscarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository repository;

    @Transactional
    public Prontuario criar(Prontuario obj) {
        return repository.save(obj);
    }

    public List<Prontuario> recuperarTodos() {
        return repository.findAll();
    }

    public Optional<Prontuario> buscarPor(UUID lookupId) {
        Prontuario objExemplo = Prontuario.builder()
                .lookupId(lookupId)
                .build();
        Example<Prontuario> exemplo = Example.of(objExemplo);
        return repository.findOne(exemplo);
    }

    @Transactional
    public Prontuario atualizar(Prontuario obj) {
        return repository.save(obj);
    }

    @Transactional
    public void remover(Prontuario obj) {
        repository.delete(obj);
    }

    public Page<Prontuario> buscar(ProntuarioBuscarDTO dto) {
        PageRequest pageRequest = PageRequest.of(dto.getNumeroPagina(), dto.getTamanhoPagina());
        return repository.buscarPor(dto, pageRequest);
    }

}