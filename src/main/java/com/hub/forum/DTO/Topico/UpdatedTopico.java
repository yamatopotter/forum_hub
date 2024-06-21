package com.hub.forum.DTO.Topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hub.forum.DTO.Curso.DataCurso;
import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.model.Curso;
import com.hub.forum.model.Status;
import com.hub.forum.model.Topico;
import com.hub.forum.model.Usuario;

import java.time.LocalDateTime;

public record UpdatedTopico(Long id,
                               String titulo,
                               String mensagem,
                               @JsonAlias("data_criacao") LocalDateTime dataCriacao,
                               Status status,
                               DataUsuario usuario,
                               DataCurso curso) {

    public UpdatedTopico(Topico topico) {
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
