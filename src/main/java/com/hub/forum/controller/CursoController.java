package com.hub.forum.controller;

import com.hub.forum.domain.DTO.Curso.CreateDataCurso;
import com.hub.forum.domain.DTO.Curso.DataCurso;
import com.hub.forum.domain.DTO.Curso.UpdateDataCurso;
import com.hub.forum.domain.DTO.Usuario.DataUsuario;
import com.hub.forum.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Criar curso na aplicação - Somente administrador", tags = {"Cursos"})
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DataCurso.class))
    })
    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateDataCurso curso, UriComponentsBuilder uriBuilder) {
        DataCurso createdCurso = cursoService.create(curso);

        var uri = uriBuilder.path("/curso/{id}").buildAndExpand(createdCurso.id()).toUri();

        return ResponseEntity.created(uri).body(createdCurso);
    }

    @Operation(summary = "Excluir curso na aplicação", description = "Realiza a exclusão de um curso da aplicação", tags={"Cursos"},responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza o curso na aplicação", description = "Realiza a atualização de um curso da aplicação", tags={"Cursos"},responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DataCurso.class)
                            )
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Exemplo de payload para atualizar um curso",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateDataCurso.class)
                    )
            ))
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataCurso curso) {
        var updatedCurso = cursoService.update(curso);

        return ResponseEntity.ok(updatedCurso);
    }

    @Operation(summary = "Buscando todos os cursos", description = "Lista com páginação todos os cursos",
            tags = {"Cursos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = DataCurso.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<DataCurso>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(cursoService.list(paginacao));
    }

    @Operation(summary = "Buscar a curso pelo ID", description = "Buscar o curso pelo ID",
            tags = {"Cursos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = DataCurso.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity detail(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.detail(id));
    }
}
