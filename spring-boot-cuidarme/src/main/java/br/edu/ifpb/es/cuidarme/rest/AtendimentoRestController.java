package br.edu.ifpb.es.cuidarme.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.mapper.AtendimentoMapper;
import br.edu.ifpb.es.cuidarme.model.Atendimento;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoRemarcarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoRestController implements AtendimentoRestControllerApi {

    @Autowired
    private AtendimentoMapper atendimentoMapper;

    @Autowired
    private AtendimentoService atendimentoService;

    @Override
    @GetMapping("/listar")
    public ResponseEntity<List<AtendimentoResponseDTO>> listar() {
        List<Atendimento> objs = atendimentoService.recuperarTodos();
        List<AtendimentoResponseDTO> resultado = objs.stream()
                .map(atendimentoMapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping("/salvar")
    public ResponseEntity<AtendimentoResponseDTO> adicionar(@RequestBody @Valid AtendimentoSalvarRequestDTO dto) throws SistemaException {
        Atendimento objNovo = atendimentoMapper.from(dto);
        Atendimento objCriado = atendimentoService.criar(objNovo);
        AtendimentoResponseDTO resultado = atendimentoMapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{lookupId}")
    public ResponseEntity<AtendimentoResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws SistemaException {
        Atendimento obj = validarExiste(lookupId);
        AtendimentoResponseDTO resultado = atendimentoMapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("atualizar/{lookupId}")
    public ResponseEntity<AtendimentoResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid AtendimentoSalvarRequestDTO dto) throws SistemaException {
        Atendimento objExistente = validarExiste(lookupId);
        objExistente.setData(dto.getData());
        objExistente.setLocalidade(dto.getLocalidade());
        objExistente.setStatus(dto.getStatus());
        Atendimento objAtualizado = atendimentoService.atualizar(objExistente);
        AtendimentoResponseDTO resultado = atendimentoMapper.from(objAtualizado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PatchMapping("/remarcar/{lookupId}")
    public ResponseEntity<AtendimentoResponseDTO> remarcar(@PathVariable UUID lookupId, @RequestBody @Valid AtendimentoRemarcarDTO dto) {
        Atendimento atendimentoAtualizado = atendimentoService.remarcar(lookupId, dto.getData(), dto.getLocalidade());
        AtendimentoResponseDTO resultado = atendimentoMapper.from(atendimentoAtualizado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PutMapping("/cancelar/{lookupId}")
    public ResponseEntity<Void> cancelar(@PathVariable UUID lookupId) throws SistemaException {
        atendimentoService.cancelar(lookupId);
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

    private Atendimento validarExiste(UUID lookupId) throws SistemaException {
        Optional<Atendimento> opt = atendimentoService.buscarPor(lookupId);
        if (opt.isEmpty()) {
            throw new SistemaException("Atendimento com o ID " + lookupId + " não foi encontrado.");
        }
        return opt.get();
    }
}
