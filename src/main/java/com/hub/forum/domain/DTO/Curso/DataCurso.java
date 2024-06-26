package com.hub.forum.domain.DTO.Curso;

import com.hub.forum.domain.model.Curso;

public record DataCurso(Long id,
                        String nome,
                        String categoria,
                        Boolean ativo) {

    public DataCurso(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria(), curso.getAtivo());
    }
}
