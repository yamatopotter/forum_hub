package com.hub.forum.DTO.Topico;

import com.hub.forum.model.Curso;
import com.hub.forum.model.Status;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;

public record CreatedTopico(Long id, String titulo, String mensagem, LocalDateTime dataCriacao, Status status, Curso curso) {
    public CreatedTopico (Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getCurso());
    }
}
