package com.hub.forum.DTO.Resposta;

import com.hub.forum.model.Resposta;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;

public record CreatedRespostaFromResposta(Long id,
                                          String mensagem,
                                          LocalDateTime dataCriacao,
                                          Topico topico,
                                          ParentResponse parentResponse) {
    public CreatedRespostaFromResposta(Resposta newResponse, Resposta respostaPai) {
        this(
                newResponse.getId(),
                newResponse.getMensagem(),
                newResponse.getDataCriacao(),
                newResponse.getTopico(),
                new ParentResponse(respostaPai)
        );
    }
}
