package com.hub.forum.DTO.Resposta;

import com.hub.forum.model.Resposta;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;

public record CreatedRespostaFromTopico(Long id,
                                        String mensagem,
                                        LocalDateTime dataCriacao,
                                        Topico topico) {
    public CreatedRespostaFromTopico(Resposta newResponse) {
        this(
                newResponse.getId(),
                newResponse.getMensagem(),
                newResponse.getDataCriacao(),
                newResponse.getTopico()
        );
    }
}
