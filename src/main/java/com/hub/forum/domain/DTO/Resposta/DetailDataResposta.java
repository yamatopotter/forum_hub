package com.hub.forum.domain.DTO.Resposta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hub.forum.domain.DTO.Usuario.DataUsuario;
import com.hub.forum.domain.model.Resposta;
import com.hub.forum.domain.model.RespostaFilha;

import java.time.LocalDateTime;
import java.util.List;

public record DetailDataResposta(Long id,
                                 String mensagem,
                                 @JsonAlias("data_criacao") LocalDateTime dataCriacao,
                                 DataUsuario autor,
                                 @JsonAlias("topico_id") Long topicoId,
                                 List<DetailDataResposta> respostas) {

    public DetailDataResposta (Resposta resposta){
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getDataCriacao(),
                new DataUsuario(resposta.getAutor()),
                resposta.getTopico().getId(),
                resposta.getRespostas() != null ?
                        resposta.getRespostas().stream().map(DetailDataResposta::new).toList() : null
        );
    }

    public DetailDataResposta(RespostaFilha respostaFilha) {
        this(
                respostaFilha.getRespostaFilha().getId(),
                respostaFilha.getRespostaFilha().getMensagem(),
                respostaFilha.getRespostaFilha().getDataCriacao(),
                new DataUsuario(respostaFilha.getRespostaFilha().getAutor()),
                respostaFilha.getRespostaFilha().getTopico().getId(),
                null
        );
    }
}
