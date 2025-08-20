package br.edu.ifpb.es.cuidarme.rest;

import java.util.UUID;

import br.edu.ifpb.es.cuidarme.exception.SistemaException;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoBuscarDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoResponseDTO;
import br.edu.ifpb.es.cuidarme.rest.dto.Psicologo.PsicologoSalvarRequestDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "psicologo", description = "API Psicologos")
public interface PsicologoRestControllerApi {

    @Operation(summary = "Adicionar um psicólogo.",
            description = "Adicionar um novo psicólogo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PsicologoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<PsicologoResponseDTO> adicionar(@RequestBody(description = "Dados do psicólogo a ser adicionado.")
                                                   PsicologoSalvarRequestDTO dto) throws SistemaException;

    @Operation(summary = "Recuperar um psicólogo existente.",
            description = "Recuper um psicólogo existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PsicologoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Psicólogo com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<PsicologoResponseDTO> recuperarPor(@Parameter(description = "LookupId do psicólogo a ser recuperado.")
                                                     UUID lookupId) throws SistemaException;

    @Operation(summary = "Atualizar dados de um psicólogo existente.",
            description = "Atualiza dados de um psicólogo existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PsicologoResponseDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Psicólogo com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<PsicologoResponseDTO> atualizar(@Parameter(description = "LookupId do psicólogo a ser atualizado.")
                                                  UUID lookupId,
                                                  @RequestBody(description = "Dados do psicólogo a ser atualizado.")
                                                  PsicologoSalvarRequestDTO dto) throws SistemaException;

    @Operation(summary = "Remover um psicólogo existente.",
            description = "Remove um psicólogo existente com base no seu lookupId.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Operação realizada com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Psicólogo com lookupId não encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Void> remover(@Parameter(description = "LookupId do psicólogo a ser removido.")
                                 UUID lookupId) throws SistemaException;

    @Operation(summary = "Recuperar psicólogo existentes.",
            description = "Recupera psicólogos existentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Operação realizada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class, contentSchema = PsicologoResponseDTO.class))),
            @ApiResponse(responseCode = "500",
                    description = "Erro inesperado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    ResponseEntity<Page<PsicologoResponseDTO>> buscar(@ParameterObject PsicologoBuscarDTO dto) throws SistemaException;
}