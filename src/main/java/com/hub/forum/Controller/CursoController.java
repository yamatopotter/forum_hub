package com.hub.forum.Controller;

import com.hub.forum.DTO.Curso.CreateDataCurso;
import com.hub.forum.DTO.Curso.DataCurso;
import com.hub.forum.DTO.Curso.UpdateDataCurso;
import com.hub.forum.service.CursoService;
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
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateDataCurso curso, UriComponentsBuilder uriBuilder){
        DataCurso createdCurso = cursoService.create(curso);

        var uri = uriBuilder.path("/curso/{id}").buildAndExpand(createdCurso.id()).toUri();

        return ResponseEntity.created(uri).body(createdCurso);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataCurso curso){
        var updatedCurso = cursoService.update(curso);

        return ResponseEntity.ok(updatedCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DataCurso>> list(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        return ResponseEntity.ok(cursoService.list(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.detail(id));
    }
}
