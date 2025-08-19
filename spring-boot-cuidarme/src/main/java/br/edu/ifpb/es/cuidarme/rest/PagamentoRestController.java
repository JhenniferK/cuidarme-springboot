package br.edu.ifpb.es.cuidarme.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.mapper.PagamentoMapper;
import br.edu.ifpb.es.cuidarme.model.Pagamento;
import br.edu.ifpb.es.cuidarme.model.StatusPagamento;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoSalvarRequestDTO;
import br.edu.ifpb.es.cuidarme.service.PagamentoService;
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
@RequestMapping("/pagamento")
public class PagamentoRestController implements PagamentoRestControllerApi {

    @Autowired
    private PagamentoMapper pagamentoMapper;

    @Autowired
    private PagamentoService pagamentoService;

    @Override
    @GetMapping("/listar")
    public ResponseEntity<List<PagamentoResponseDTO>> listar() throws SistemaException {
        List<Pagamento> objs = pagamentoService.recuperarTodos();
        List<PagamentoResponseDTO> resultado = objs.stream()
                .map(pagamentoMapper::from)
                .toList();
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PostMapping("/cadastrar")
    public ResponseEntity<PagamentoResponseDTO> adicionar(@RequestBody @Valid PagamentoSalvarRequestDTO dto) throws SistemaException {
        Pagamento objNovo = pagamentoMapper.from(dto);
        Pagamento objCriado = pagamentoService.criar(objNovo);
        PagamentoResponseDTO resultado = pagamentoMapper.from(objCriado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @GetMapping("recuperar/{lookupId}")
    public ResponseEntity<PagamentoResponseDTO> recuperarPor(@PathVariable UUID lookupId) throws SistemaException {
        Pagamento obj = validarExiste(lookupId);
        PagamentoResponseDTO resultado = pagamentoMapper.from(obj);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @PatchMapping("atualizar/{lookupId}")
    public ResponseEntity<PagamentoResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid PagamentoSalvarRequestDTO dto) throws SistemaException {
        Pagamento objExistente = validarExiste(lookupId);
        objExistente.setData(dto.getData());
        objExistente.setValor(dto.getValor());
        objExistente.setMetodo(dto.getMetodo());
        objExistente.setStatusPagamento(StatusPagamento.valueOf(dto.getStatus()));
        Pagamento objAtualizado = pagamentoService.atualizar(objExistente);
        PagamentoResponseDTO resultado = pagamentoMapper.from(objAtualizado);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("remover/{lookupId}")
    public ResponseEntity<Void> remover(@PathVariable UUID lookupId) throws SistemaException {
        Pagamento obj = validarExiste(lookupId);
        pagamentoService.remover(obj);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<PagamentoResponseDTO>> buscar(PagamentoBuscarDTO dto) throws SistemaException {
        Page<Pagamento> objs = pagamentoService.buscar(dto);

        Page<PagamentoResponseDTO> resultado = objs
                .map(pagamentoMapper::from);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    private Pagamento validarExiste(UUID lookupId) {
        Optional<Pagamento> opt = pagamentoService.buscarPor(lookupId);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException(String.format("Entidade 'Pagamento' de lookupId '%s' não foi encontrada!", lookupId));
        }
        return opt.get();
    }
}
