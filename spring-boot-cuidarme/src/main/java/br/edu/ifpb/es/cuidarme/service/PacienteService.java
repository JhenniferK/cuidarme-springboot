package br.edu.ifpb.es.cuidarme.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.model.Paciente;
import br.edu.ifpb.es.cuidarme.repository.PacienteRepository;
import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteBuscarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Transactional
    public Paciente criar(Paciente obj) {
        return repository.save(obj);
    }

    public List<Paciente> recuperarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPor(UUID lookupId) {
        Paciente objExemplo = Paciente.builder()
                .lookupId(lookupId)
                .build();
        Example<Paciente> exemplo = Example.of(objExemplo);

        return repository.findOne(exemplo);
    }

    @Transactional
    public Paciente atualizar(Paciente obj) {
        return repository.save(obj);
    }

    @Transactional
    public void remover(Paciente obj) {
        repository.delete(obj);
    }

    public Page<Paciente> buscar(PacienteBuscarDTO dto) {
        PageRequest pageRequest = PageRequest.of(dto.getNumeroPagina(), dto.getTamanhoPagina());
        return repository.buscarPor(dto, pageRequest);
    }

}