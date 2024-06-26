package com.hub.forum.Controller;

import com.hub.forum.domain.DTO.Cadastro.DataCadastroFromAdmin;
import com.hub.forum.domain.DTO.Usuario.DataUsuario;
import com.hub.forum.domain.DTO.Usuario.UpdateDataUsuario;
import com.hub.forum.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@RequestParam Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataUsuario usuario) {
        var updatedUser = usuarioService.update(usuario);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping
    public ResponseEntity<Page<DataUsuario>> list(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return ResponseEntity.ok(usuarioService.list(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(Long id) {
        return ResponseEntity.ok(usuarioService.detail(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid DataCadastroFromAdmin dataCadastroFromAdmin, UriComponentsBuilder uriBuilder){
        var newUsuario = usuarioService.create(dataCadastroFromAdmin);

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(newUsuario.id()).toUri();
        return ResponseEntity.created(uri).body(newUsuario);
    }
}
