package com.hub.forum.DTO.Resposta;

import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.model.Resposta;

import java.time.LocalDateTime;

public record ParentResponse(Long id,
                             String mensagem,
                             LocalDateTime dataCriacao,
                             DataUsuario autor,
                             Boolean solucao) {
    public ParentResponse(Resposta resposta){
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                new DataUsuario(resposta.getAutor()),
                resposta.isSolucao()
        );
    }
}
