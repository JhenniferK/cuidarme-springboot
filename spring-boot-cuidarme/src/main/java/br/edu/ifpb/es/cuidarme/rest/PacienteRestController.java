package br.edu.ifpb.es.cuidarme.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.mapper.ContatoEmergenciaMapper;
import br.edu.ifpb.es.cuidarme.mapper.EnderecoMapper;
import br.edu.ifpb.es.cuidarme.mapper.PacienteMapper;
import br.edu.ifpb.es.cuidarme.model.Paciente;
import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Paciente.PacienteSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.service.PacienteService;
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
@RequestMapping("/paciente")
public class PacienteRestController implements PacienteRestControllerApi {

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private EnderecoMapper enderecoMapper;
    @Autowired
    private ContatoEmergenciaMapper contatoEmergenciaMapper;

    @Override
    @GetMapping("/listar")
    public ResponseEntity<List<PacienteResponseDTO>> listar() {
        List<Paciente> objs = pacienteService.recuperarTodos();
        List<PacienteResponseDTO> resultado = objs.stream()
                .map(pacienteMapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping("/cadastrar")
    public ResponseEntity<PacienteResponseDTO> adicionar(@RequestBody @Valid PacienteSalvarRequestDTO dto) throws SistemaException {
        Paciente objNovo = pacienteMapper.from(dto);
        Paciente objCriado = pacienteService.criar(objNovo);
        PacienteResponseDTO resultado = pacienteMapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("buscar/{lookupId}")
    public ResponseEntity<PacienteResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws SistemaException {
        Paciente obj = validarExiste(lookupId);
        PacienteResponseDTO resultado = pacienteMapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("atualizar/{lookupId}")
    public ResponseEntity<PacienteResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid PacienteSalvarRequestDTO dto) throws SistemaException {
        Paciente objExistente = validarExiste(lookupId);
        objExistente.setNome(dto.getNome());
        objExistente.setCpf(dto.getCpf());
        objExistente.setRg(dto.getRg());
        objExistente.setDataNascimento(dto.getDataNascimento());
        objExistente.setSexo(dto.getSexo());
        objExistente.setEstadoCivil(dto.getEstadoCivil());
        objExistente.setGrauInstrucao(dto.getGrauInstrucao());
        objExistente.setProfissao(dto.getProfissao());
        objExistente.setTelefone(dto.getTelefone());
        objExistente.setEnderecoPessoal(enderecoMapper.from(dto.getEnderecoPessoal()));
        objExistente.setEnderecoTrabalho(enderecoMapper.from(dto.getEnderecoTrabalho()));
        objExistente.setInfoAdicionais(dto.getInfoAdicionais());
        objExistente.setContatoEmergencia(contatoEmergenciaMapper.from(dto.getContatoEmergencia()));
        Paciente objAtualizado = pacienteService.atualizar(objExistente);
        PacienteResponseDTO resultado = pacienteMapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("remover/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws SistemaException {
        Paciente obj = validarExiste(lookupId);
        pacienteService.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<PacienteResponseDTO>> buscar(PacienteBuscarDTO dto) throws SistemaException {
        Page<Paciente> objs = pacienteService.buscar(dto);

        Page<PacienteResponseDTO> resultado = objs
                .map(pacienteMapper::from);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    private Paciente validarExiste(UUID lookupId) throws SistemaException {
        Optional<Paciente> opt = pacienteService.buscarPor(lookupId);
        if (opt.isEmpty()) {
            throw new SistemaException("Paciente com o ID " + lookupId + " não foi encontrado.");
        }
        return opt.get();
    }
}
