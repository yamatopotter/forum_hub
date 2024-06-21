package com.hub.forum.DTO.Resposta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hub.forum.model.Resposta;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;

public record CreatedRespostaFromTopico(Long id,
                                        String mensagem,
                                        @JsonAlias("data_criacao") LocalDateTime dataCriacao,
                                        @JsonAlias("topico_id") Long topicoId) {
    public CreatedRespostaFromTopico(Resposta newResponse) {
        this(
                newResponse.getId(),
                newResponse.getMensagem(),
                newResponse.getDataCriacao(),
                newResponse.getTopico().getId()
        );
    }
}
