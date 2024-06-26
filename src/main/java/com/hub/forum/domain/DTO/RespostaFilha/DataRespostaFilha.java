package com.hub.forum.domain.DTO.RespostaFilha;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hub.forum.domain.DTO.Resposta.DetailDataResposta;
import com.hub.forum.domain.model.RespostaFilha;

public record DataRespostaFilha(Long id,
                                @JsonAlias("resposta_pai") DetailDataResposta respostaPai,
                                @JsonAlias("resposta_filha") DetailDataResposta respostaFilha) {

    public DataRespostaFilha(RespostaFilha respostaFilha){
        this(
                respostaFilha.getId(),
                new DetailDataResposta(respostaFilha.getRespostaPai()),
                new DetailDataResposta(respostaFilha.getRespostaFilha())
        );
    }
}
