package com.hub.forum.controller;

import com.hub.forum.domain.DTO.Curso.DataCurso;
import com.hub.forum.domain.DTO.Resposta.*;
import com.hub.forum.domain.DTO.Topico.*;
import com.hub.forum.service.TopicoService;
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
@RequestMapping("/topico")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @Operation(summary = "Criar um tópico para o fórum", tags = {"Tópicos"})
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreatedTopico.class))
    })
    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateDataTopico topico, UriComponentsBuilder uriBuilder) {
        CreatedTopico createdTopico = topicoService.create(topico);
        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(createdTopico.id()).toUri();

        return ResponseEntity.created(uri).body(createdTopico);
    }

    @Operation(summary = "Criar uma resposta para o tópico",
            tags = {"Respostas"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Exemplo de payload para criar uma resposta para o tópico",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CreateRespostaWithoutParent.class)
                    )
            ))
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreatedRespostaFromTopico.class))
    })
    @PostMapping("/{id}/resposta")
    @Transactional
    public ResponseEntity createResposta(@RequestBody @Valid CreateRespostaWithoutParent resposta, @PathVariable Long id, UriComponentsBuilder uriBuilder) {
        CreatedRespostaFromTopico createdResposta = topicoService.createNewResposta(resposta, id);
        var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(createdResposta.id()).toUri();

        return ResponseEntity.created(uri).body(createdResposta);
    }

    @Operation(summary = "Exclui um tópico na aplicação",
            description = "Realiza a exclusão de uma resposta da aplicação",
            tags = {"Tópicos"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        topicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza o tópico na aplicação",
            description = "Realiza a atualização de um tópico da aplicação",
            tags = {"Tópicos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = UpdatedTopico.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Exemplo de payload para atualizar um tópico",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateDataTopico.class)
                    )
            ))
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataTopico topico) {
        var updatedTopico = topicoService.update(topico);

        return ResponseEntity.ok(updatedTopico);
    }


    @Operation(summary = "Buscando todos os tópicos", description = "Lista com páginação todos os tópicos",
            tags = {"Tópicos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ListDataTopico.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<ListDataTopico>> list(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        return ResponseEntity.ok(topicoService.list(paginacao));
    }

    @Operation(summary = "Buscar o tópico pelo ID",
            description = "Busca o tópico pelo ID com todos os detalhes e respostas",
            tags = {"Tópicos"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = DetailDataTopico.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity detail(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.detail(id));
    }
}
