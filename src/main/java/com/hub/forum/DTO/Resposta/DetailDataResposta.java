package com.hub.forum.DTO.Resposta;

import com.hub.forum.model.Resposta;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;

public record DetailDataResposta(Long id,
                                 String mensagem,
                                 LocalDateTime dataCriacao,
                                 Topico topico) {

    public DetailDataResposta (Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao(), resposta.getTopico());
    }
}
