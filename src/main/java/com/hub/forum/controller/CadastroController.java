package com.hub.forum.controller;

import com.hub.forum.domain.DTO.Cadastro.DataCadastro;
import com.hub.forum.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity efetuarCadastro(@RequestBody @Valid DataCadastro dados, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = usuarioService.cadastrarNovoUsuario(dados);

        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.id()).toUri();

        return ResponseEntity.created(uri).body(usuario);
    }
}
