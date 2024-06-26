package com.hub.forum.domain.DTO.Resposta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hub.forum.domain.DTO.Usuario.DataUsuario;
import com.hub.forum.domain.model.Resposta;

import java.time.LocalDateTime;

public record ParentResponse(Long id,
                             String mensagem,
                             @JsonAlias("data_criacao") LocalDateTime dataCriacao,
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
