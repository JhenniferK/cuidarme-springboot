package br.edu.ifpb.es.cuidarme.rest;

import java.util.List;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioSalvarRequestDTO;
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

@Tag(name = "prontuario", description = "API Prontuarios")
public interface ProntuarioRestControllerApi {

    @Operation(summary = "Retornar todos os prontuarios.",
            description = "Retorna todos os prontuarios que estão armazenados, sem restrição alguma de quantidade.",
            tags = { "todo" }) // XXX: Com a definição deste atributo "tag" você poderia associar o endpoint definido aqui
    // em um outro controller. A associação é feita mediante o uso da tag definida no outro controller.
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProntuarioResponseDTO.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<List<ProntuarioResponseDTO>> listar() throws SistemaException;

    @Operation(summary = "Adicionar um prontuario.",
            description = "Adicionar um novo prontuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProntuarioResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<ProntuarioResponseDTO> adicionar(@RequestBody(description = "Dados do prontuario a ser adicionado.")
                                                   ProntuarioSalvarRequestDTO dto) throws SistemaException;

    @Operation(summary = "Recuperar um prontuario existente.",
            description = "Recuper um prontuario existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProntuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Prontuario com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<ProntuarioResponseDTO> recuperarPor(@Parameter(description = "LookupId do prontuario a ser recuperado.")
                                                      UUID lookupId) throws SistemaException;

    @Operation(summary = "Atualizar dados de um prontuario existente.",
            description = "Atualiza dados de um prontuario existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProntuarioResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Prontuario com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<ProntuarioResponseDTO> atualizar(@Parameter(description = "LookupId do prontuario a ser atualizado.")
                                                   UUID lookupId,
                                                   @RequestBody(description = "Dados do prontuario a ser atualizado.")
                                                    ProntuarioSalvarRequestDTO dto) throws SistemaException;

    @Operation(summary = "Remover um prontuario existente.",
            description = "Remove um prontuario existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Prontuario com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> remover(@Parameter(description = "LookupId do prontuario a ser removido.")
                                 UUID lookupId) throws SistemaException;

    @Operation(summary = "Recuperar prontuarios existentes.",
            description = "Recupera prontuarios existentes de forma paginada com base nos seguintes filtros opcionais: ativo ou não")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            /*array = @ArraySchema(schema = @Schema(implementation = TodoResponseDTO.class))*/
                            schema = @Schema(implementation = Page.class, contentSchema = ProntuarioResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<ProntuarioResponseDTO>> buscar(@ParameterObject ProntuarioBuscarDTO dto) throws SistemaException;

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

