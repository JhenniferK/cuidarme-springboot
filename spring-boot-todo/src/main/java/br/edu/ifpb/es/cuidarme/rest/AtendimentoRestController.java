package br.edu.ifpb.es.cuidarme.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.mapper.AtendimentoMapper;
import br.edu.ifpb.es.cuidarme.model.Atendimento;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.service.AtendimentoService;
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
@RequestMapping("/atendimento")
public class AtendimentoRestController implements AtendimentoRestControllerApi {

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private AtendimentoService atendimentoService;

    @Override
    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDTO>> listar() throws SistemaException {
        List<Atendimento> objs = atendimentoService.recuperarTodos();
        List<AtendimentoResponseDTO> resultado = objs.stream()
                .map(atendimentoMapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> adicionar(@RequestBody @Valid AtendimentoSalvarRequestDTO dto) throws SistemaException {
        Atendimento objNovo = atendimentoMapper.from(dto);
        Atendimento objCriado = atendimentoService.criar(objNovo);
        AtendimentoResponseDTO resultado = atendimentoMapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{lookupId}")
    public ResponseEntity<AtendimentoResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws SistemaException {
        // Recuperar
        Atendimento obj = validarExiste(lookupId);
        AtendimentoResponseDTO resultado = atendimentoMapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{lookupId}")
    public ResponseEntity<AtendimentoResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid AtendimentoSalvarRequestDTO dto) throws SistemaException {
        Atendimento objExistente = validarExiste(lookupId);
        objExistente.setData(dto.getData());
        objExistente.setLocalidade(dto.getLocalidade());
        objExistente.setStatusAtendimento(dto.getStatus());
        Atendimento objAtualizado = atendimentoService.atualizar(objExistente);
        AtendimentoResponseDTO resultado = atendimentoMapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws SistemaException {
        Atendimento obj = validarExiste(lookupId);
        atendimentoService.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<AtendimentoResponseDTO>> buscar(AtendimentoBuscarDTO dto) throws SistemaException {
        Page<Atendimento> objs = atendimentoService.buscar(dto);

        Page<AtendimentoResponseDTO> resultado = objs
                .map(atendimentoMapper::from);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    private Atendimento validarExiste(UUID lookupId) {
        // Checar se entidade existe
        Optional<Atendimento> opt = atendimentoService.buscarPor(lookupId);
        if (!opt.isPresent()) {
            // Lançar erro porque não encontrou
            throw new IllegalArgumentException(String.format("Entidade 'Atendimento' de lookupId '%s' não foi encontrada!", lookupId));
        }
        // Retornar entidade encontrada
        return opt.get();
    }
}
