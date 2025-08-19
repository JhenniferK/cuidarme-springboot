package br.edu.ifpb.es.cuidarme.rest;

import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.mapper.PsicologoMapper;
import br.edu.ifpb.es.cuidarme.model.Psicologo;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoLoginRequestDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.service.PsicologoService;
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
@RequestMapping("/psicologo")
public class PsicologoRestController implements PsicologoRestControllerApi {

    @Autowired
    private PsicologoMapper psicologoMapper;

    @Autowired
    private PsicologoService psicologoService;

    @Override
    @PostMapping("/cadastrar")
    public ResponseEntity<PsicologoResponseDTO> adicionar(@RequestBody @Valid PsicologoSalvarRequestDTO dto) throws SistemaException {
        Psicologo objNovo = psicologoMapper.from(dto);
        Psicologo objCriado = psicologoService.criar(objNovo);
        PsicologoResponseDTO resultado = psicologoMapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<PsicologoResponseDTO> login(@RequestBody PsicologoLoginRequestDTO loginRequest) throws SistemaException {
        Psicologo psicologo = psicologoService.validarLogin(loginRequest.getEmail(), loginRequest.getSenha());
        PsicologoResponseDTO resultado = psicologoMapper.from(psicologo);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("recuperar/{lookupId}")
    public ResponseEntity<PsicologoResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws SistemaException {
        Psicologo obj = validarExiste(lookupId);
        PsicologoResponseDTO resultado = psicologoMapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("atualizar/{lookupId}")
    public ResponseEntity<PsicologoResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid PsicologoSalvarRequestDTO dto) throws SistemaException {
        Psicologo objExistente = validarExiste(lookupId);
        objExistente.setNome(dto.getNome());
        objExistente.setEmail(dto.getEmail());
        objExistente.setSenha(dto.getSenha());
        Psicologo objAtualizado = psicologoService.atualizar(objExistente);
        PsicologoResponseDTO resultado = psicologoMapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("remover/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws SistemaException {
        Psicologo obj = validarExiste(lookupId);
        psicologoService.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<PsicologoResponseDTO>> buscar(PsicologoBuscarDTO dto) throws SistemaException {
        Page<Psicologo> objs = psicologoService.buscar(dto);

        Page<PsicologoResponseDTO> resultado = objs
                .map(psicologoMapper::from);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    private Psicologo validarExiste(UUID lookupId) {
        Optional<Psicologo> opt = psicologoService.buscarPor(lookupId);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException(String.format("Entidade 'Psicologo' de lookupId '%s' não foi encontrada!", lookupId));
        }
        return opt.get();
    }
}
