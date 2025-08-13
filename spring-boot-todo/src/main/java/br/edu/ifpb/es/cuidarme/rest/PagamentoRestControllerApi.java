package br.edu.ifpb.es.cuidarme.rest;

import java.util.List;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoSalvarRequestDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "pagamento", description = "API Pagamentos")
public interface PagamentoRestControllerApi {

    @Operation(summary = "Retornar todos os pagamentos.",
            description = "Retorna todos os pagamentos que estão armazenados, sem restrição alguma de quantidade.",
            tags = { "todo" }) // XXX: Com a definição deste atributo "tag" você poderia associar o endpoint definido aqui
    // em um outro controller. A associação é feita mediante o uso da tag definida no outro controller.
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PagamentoResponseDTO.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<List<PagamentoResponseDTO>> listar() throws SistemaException;

    @Operation(summary = "Adicionar um pagamento.",
            description = "Adicionar um novo pagamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagamentoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<PagamentoResponseDTO> adicionar(@RequestBody(description = "Dados do pagamento a ser adicionado.")
                                                     PagamentoSalvarRequestDTO dto) throws SistemaException;

    @Operation(summary = "Recuperar um pagamento existente.",
            description = "Recuper um pagamento existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagamentoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Pagamento com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<PagamentoResponseDTO> recuperarPor(@Parameter(description = "LookupId do pagamento a ser recuperado.")
                                                        UUID lookupId) throws SistemaException;

    @Operation(summary = "Atualizar dados de um pagamento existente.",
            description = "Atualiza dados de um pagamento existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagamentoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Pagamento com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<PagamentoResponseDTO> atualizar(@Parameter(description = "LookupId do pagamento a ser atualizado.")
                                                     UUID lookupId,
                                                     @RequestBody(description = "Dados do pagamento a ser atualizado.")
                                                   PagamentoSalvarRequestDTO dto) throws SistemaException;

    @Operation(summary = "Remover um pagamento existente.",
            description = "Remove um pagamento existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Pagamento com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> remover(@Parameter(description = "LookupId do pagamento a ser removido.")
                                 UUID lookupId) throws SistemaException;

    @Operation(summary = "Recuperar pagamentos existentes.",
            description = "Recupera pagamentos existentes de forma paginada com base nos seguintes filtros opcionais: ativo ou não")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            /*array = @ArraySchema(schema = @Schema(implementation = TodoResponseDTO.class))*/
                            schema = @Schema(implementation = Page.class, contentSchema = PagamentoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<PagamentoResponseDTO>> buscar(@ParameterObject PagamentoBuscarDTO dto) throws SistemaException;

//    @Operation(summary = "Marcar uma tarefa como concluída.",
//            description = "Marca uma tarefa como concluída, lançando erro caso ela já esteja concluída.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Operação realizada com sucesso.",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = TodoResponseDTO.class))),
//            @ApiResponse(responseCode = "400",
//                    description = "Tarefa com lookupId NÃO encontrada ou tarefa já foi concluída.",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ProblemDetail.class))),
//            @ApiResponse(responseCode = "500",
//                    description = "Erro inesperado.",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ProblemDetail.class))),
//    })
//    ResponseEntity<TodoResponseDTO> fazerTarefa(@Parameter(description = "LookupId da tarefa a ser marcada como concluída.")
//                                                UUID lookupId) throws TodoException;

//    @Operation(summary = "Desfazer uma tarefa que foi concluída.",
//            description = "Desfaz uma tarefa que foi concluída, lançando erro caso ela não tenha sido concluída ainda.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200",
//                    description = "Operação realizada com sucesso.",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = TodoResponseDTO.class))),
//            @ApiResponse(responseCode = "400",
//                    description = "Tarefa com lookupId NÃO encontrada ou tarefa NÃO está marcada como concluída.",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ProblemDetail.class))),
//            @ApiResponse(responseCode = "500",
//                    description = "Erro inesperado.",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = ProblemDetail.class))),
//    })
//    ResponseEntity<TodoResponseDTO> desfazerTarefa(@Parameter(description = "LookupId da tarefa a ser desfeita.")
//                                                   UUID lookupId) throws TodoException;

}
