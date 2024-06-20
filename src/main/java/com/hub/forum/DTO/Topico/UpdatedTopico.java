package com.hub.forum.DTO.Topico;

import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.model.Curso;
import com.hub.forum.model.Status;
import com.hub.forum.model.Topico;
import com.hub.forum.model.Usuario;

import java.time.LocalDateTime;

public record UpdatedTopico(Long id,
                               String titulo,
                               String mensagem,
                               LocalDateTime dataCriacao,
                               Status status,
                               DataUsuario usuario,
                               Curso curso) {

    public UpdatedTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), new DataUsuario(topico.getUsuario()), topico.getCurso());
    }
}
