package com.hub.forum.DTO.Topico;

import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.model.Curso;
import com.hub.forum.model.Status;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;

public record ListDataTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        Status status,
        DataUsuario usuario,
        Curso curso
) {

    public ListDataTopico(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), new DataUsuario(topico.getUsuario()), topico.getCurso());
    }
}
