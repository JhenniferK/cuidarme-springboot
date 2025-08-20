package br.edu.ifpb.es.cuidarme.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.mapper.ProntuarioMapper;
import br.edu.ifpb.es.cuidarme.model.Prontuario;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioRestController implements ProntuarioRestControllerApi {

    @Autowired
    private ProntuarioMapper prontuarioMapper;

    @Autowired
    private ProntuarioService prontuarioService;

    @Override
    @GetMapping("/listar")
    public ResponseEntity<List<ProntuarioResponseDTO>> listar() {
        List<Prontuario> objs = prontuarioService.recuperarTodos();
        List<ProntuarioResponseDTO> resultado = objs.stream()
                .map(prontuarioMapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping("/adicionar")
    public ResponseEntity<ProntuarioResponseDTO> adicionar(@RequestBody @Valid ProntuarioSalvarRequestDTO dto) throws SistemaException {
        Prontuario objNovo = prontuarioMapper.from(dto);
        Prontuario objCriado = prontuarioService.criar(objNovo);
        ProntuarioResponseDTO resultado = prontuarioMapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("recuperar/{lookupId}")
    public ResponseEntity<ProntuarioResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws SistemaException {
        Prontuario obj = validarExiste(lookupId);
        ProntuarioResponseDTO resultado = prontuarioMapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("atualizar/{lookupId}")
    public ResponseEntity<ProntuarioResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid ProntuarioSalvarRequestDTO dto) throws SistemaException {
        Prontuario objExistente = validarExiste(lookupId);
        objExistente.setDataRegistro(dto.getDataRegistro());
        objExistente.setDescricao(dto.getDescricao());
        Prontuario objAtualizado = prontuarioService.atualizar(objExistente);
        ProntuarioResponseDTO resultado = prontuarioMapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("remover/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws SistemaException {
        Prontuario obj = validarExiste(lookupId);
        prontuarioService.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<ProntuarioResponseDTO>> buscar(ProntuarioBuscarDTO dto) throws SistemaException {
        Page<Prontuario> objs = prontuarioService.buscar(dto);

        Page<ProntuarioResponseDTO> resultado = objs
                .map(prontuarioMapper::from);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    private Prontuario validarExiste(UUID lookupId) throws SistemaException {
        Optional<Prontuario> opt = prontuarioService.buscarPor(lookupId);
        if (opt.isEmpty()) {
            throw new SistemaException("Prontuário com o ID " + lookupId + " não foi encontrado.");
        }
        return opt.get();
    }
}
