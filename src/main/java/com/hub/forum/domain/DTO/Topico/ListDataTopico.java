package com.hub.forum.domain.DTO.Topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hub.forum.domain.DTO.Curso.DataCurso;
import com.hub.forum.domain.DTO.Usuario.DataUsuario;
import com.hub.forum.domain.model.Status;
import com.hub.forum.domain.model.Topico;

import java.time.LocalDateTime;

public record ListDataTopico(
        Long id,
        String titulo,
        String mensagem,
        @JsonAlias("data_criacao") LocalDateTime dataCriacao,
        Status status,
        DataUsuario usuario,
        DataCurso curso
) {

    public ListDataTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                new DataUsuario(topico.getUsuario()),
                new DataCurso(topico.getCurso())
        );
    }
}
