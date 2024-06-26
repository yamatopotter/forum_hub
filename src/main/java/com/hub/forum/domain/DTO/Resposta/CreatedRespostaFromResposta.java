package com.hub.forum.domain.DTO.Resposta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hub.forum.domain.model.Resposta;

import java.time.LocalDateTime;

public record CreatedRespostaFromResposta(Long id,
                                          String mensagem,
                                          @JsonAlias("data_criacao") LocalDateTime dataCriacao,
                                          @JsonAlias("topico_id") Long topicoId,
                                          ParentResponse parentResponse) {
    public CreatedRespostaFromResposta(Resposta newResponse, Resposta respostaPai) {
        this(
                newResponse.getId(),
                newResponse.getMensagem(),
                newResponse.getDataCriacao(),
                newResponse.getTopico().getId(),
                new ParentResponse(respostaPai)
        );
    }
}
