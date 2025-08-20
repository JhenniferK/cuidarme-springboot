package br.edu.ifpb.es.cuidarme.rest;

import java.util.List;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoSalvarRequestDTO;
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

@Tag(name = "atendimento", description = "API Atendimentos")
public interface AtendimentoRestControllerApi {

    @Operation(summary = "Retornar todos os atendimentos.",
            description = "Retorna todos os atendimentos que estão armazenados, sem restrição alguma de quantidade.",
            tags = { "todo" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AtendimentoResponseDTO.class)))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<List<AtendimentoResponseDTO>> listar() throws SistemaException;

    @Operation(summary = "Adicionar um atendimento.",
            description = "Adicionar um novo atendimento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AtendimentoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AtendimentoResponseDTO> adicionar(@RequestBody(description = "Dados do atendimento a ser adicionado.")
                                                  AtendimentoSalvarRequestDTO dto) throws SistemaException;

    @Operation(summary = "Recuperar um atendimento existente.",
            description = "Recuper um atendimento existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AtendimentoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Atendimento com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AtendimentoResponseDTO> recuperarPor(@Parameter(description = "LookupId do atendimento a ser recuperado.")
                                                     UUID lookupId) throws SistemaException;

    @Operation(summary = "Atualizar dados de um atendimento existente.",
            description = "Atualiza dados de um atendimento existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AtendimentoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Atendimento com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<AtendimentoResponseDTO> atualizar(@Parameter(description = "LookupId do atendimento a ser atualizado.")
                                                  UUID lookupId,
                                                  @RequestBody(description = "Dados do atendimento a ser atualizado.")
                                                     AtendimentoSalvarRequestDTO dto) throws SistemaException;

    @Operation(summary = "Cancelar um atendimento existente.",
            description = "Cancela um atendimento existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Atendimento com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> cancelar(@Parameter(description = "LookupId do atendimento a ser cancelado.")
                                 UUID lookupId) throws SistemaException;

    @Operation(summary = "Recuperar atendimentos existentes.",
            description = "Recupera atendimentos existentes de forma paginada com base nos seguintes filtros opcionais: ativo ou não")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            /*array = @ArraySchema(schema = @Schema(implementation = TodoResponseDTO.class))*/
                            schema = @Schema(implementation = Page.class, contentSchema = AtendimentoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<AtendimentoResponseDTO>> buscar(@ParameterObject AtendimentoBuscarDTO dto) throws SistemaException;
}