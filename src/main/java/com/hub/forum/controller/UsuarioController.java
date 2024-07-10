package com.hub.forum.controller;

import com.hub.forum.domain.DTO.Cadastro.DataCadastroFromAdmin;
import com.hub.forum.domain.DTO.Resposta.CreateRespostaWithoutParent;
import com.hub.forum.domain.DTO.Resposta.CreatedRespostaFromTopico;
import com.hub.forum.domain.DTO.Topico.DetailDataTopico;
import com.hub.forum.domain.DTO.Topico.ListDataTopico;
import com.hub.forum.domain.DTO.Topico.UpdateDataTopico;
import com.hub.forum.domain.DTO.Topico.UpdatedTopico;
import com.hub.forum.domain.DTO.Usuario.DataUsuario;
import com.hub.forum.domain.DTO.Usuario.UpdateDataUsuario;
import com.hub.forum.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Exclui um usuário na aplicação",
            description = "Realiza a exclusão de um usuário da aplicação",
            tags = {"Usuários"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable(value="id") Long id) {
        usuarioService.delete(id.longValue());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza o usuário na aplicação",
            description = "Realiza a atualização de um usuário da aplicação",
            tags = {"Usuários"},
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
                    description = "Exemplo de payload para atualizar um usuário",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateDataUsuario.class)
                    )
            ))
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataUsuario usuario) {
        var updatedUser = usuarioService.update(usuario);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Buscando todos os usuários", description = "Lista com páginação todos os usuários",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = DataUsuario.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<DataUsuario>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return ResponseEntity.ok(usuarioService.list(paginacao));
    }

    @Operation(summary = "Buscar o usuário pelo ID",
            description = "Busca o usuário pelo ID com todos os detalhes",
            tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = DataUsuario.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity detail(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(usuarioService.detail(id));
    }

    @Operation(summary = "Criar um usuário na aplicação",
            tags = {"Usuários"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Exemplo de payload para criar uma usuário",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DataCadastroFromAdmin.class)
                    )
            ))
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = DataCadastroFromAdmin.class))
    })
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid DataCadastroFromAdmin dataCadastroFromAdmin, UriComponentsBuilder uriBuilder){
        var newUsuario = usuarioService.create(dataCadastroFromAdmin);

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(newUsuario.id()).toUri();
        return ResponseEntity.created(uri).body(newUsuario);
    }
}
