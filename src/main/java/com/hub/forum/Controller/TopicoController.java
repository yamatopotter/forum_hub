package com.hub.forum.Controller;

import com.hub.forum.DTO.Topico.CreateDataTopico;
import com.hub.forum.DTO.Topico.CreatedTopico;
import com.hub.forum.DTO.Topico.ListDataTopico;
import com.hub.forum.DTO.Topico.UpdateDataTopico;
import com.hub.forum.service.TopicoService;
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
@RequestMapping("/topico")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateDataTopico topico, UriComponentsBuilder uriBuilder) {
        CreatedTopico createdTopico = topicoService.create(topico);
        var uri = uriBuilder.path("/topico/{id}").buildAndExpand(createdTopico.id()).toUri();

        return ResponseEntity.created(uri).body(createdTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(Long id) {
        topicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataTopico topico) {
        var topicoAtualizado = topicoService.update(topico);

        return ResponseEntity.ok(topicoAtualizado);
    }

    @GetMapping
    public ResponseEntity<Page<ListDataTopico>> list(@PageableDefault(size=10, sort={"dataCriacao"})Pageable paginacao) {
        return ResponseEntity.ok(topicoService.list(paginacao));

    }

    @GetMapping("/{id}")
    public ResponseEntity detail(Long id) {
        return ResponseEntity.ok(topicoService.detail(id));
    }
}
