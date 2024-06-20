package com.hub.forum.Controller;

import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @PostMapping
    @Transactional
    public ResponseEntity create(CreateDataCurso curso){

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(Long id){

    }

    @PutMapping
    @Transactional
    public ResponseEntity update(UpdateDataCurso curso){

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> list(Pageable paginacao){

    }

    @GetMapping("/{id}")
    public ResponseEntity detail(Long id){

    }
}
