package com.hub.forum.controller;

import com.hub.forum.domain.DTO.Resposta.CreatedRespostaFromResposta;
import com.hub.forum.domain.DTO.Resposta.DetailDataResposta;
import com.hub.forum.domain.DTO.Resposta.MensagemResposta;
import com.hub.forum.service.RespostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/resposta")
public class RespostaController {
    @Autowired
    private RespostaService respostaService;

    @Operation(summary = "Criar uma resposta para uma resposta", tags = {"Respostas"})
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CreatedRespostaFromResposta.class))
    })
    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity createRespostaWithParent(@RequestBody @Valid MensagemResposta resposta, @PathVariable Long id, UriComponentsBuilder uriBuilder) {
        CreatedRespostaFromResposta createdResposta = respostaService.create(resposta.mensagem(), id);

        var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(createdResposta.id()).toUri();

        return ResponseEntity.created(uri).body(createdResposta);
    }

    @Operation(summary = "Atualiza a resposta na aplicação",
            description = "Realiza a atualização de uma resposta da aplicação",
            tags = {"Respostas"},
            responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DetailDataResposta.class)
                            )
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Exemplo de payload para atualizar uma resposta",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MensagemResposta.class)
                    )
            ))
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity edit(@RequestBody @Valid MensagemResposta resposta, @PathVariable Long id) {
        var updatedResposta = respostaService.edit(resposta.mensagem(), id);

        return ResponseEntity.ok(updatedResposta);
    }

    @Operation(summary = "Buscar a resposta pelo ID",
            description = "Buscar a resposta pelo ID",
            tags = {"Respostas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = DetailDataResposta.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity detail(@PathVariable Long id) {
        return ResponseEntity.ok(respostaService.detail(id));
    }


    @Operation(summary = "Excluir curso na aplicação",
            description = "Realiza a exclusão de um curso da aplicação",
            tags = {"Respostas"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        respostaService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza a resposta no topico a colocando como solução aplicação",
            description = "Realiza a atualização de uma resposta da aplicação a colocando como solução de um tópico",
            tags = {"Respostas"},
            responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    @PutMapping("/{id}/solucao")
    @Transactional
    public ResponseEntity setAsSolution(@PathVariable Long id) {
        respostaService.setAsSolution(id);
        return ResponseEntity.noContent().build();
    }
}
