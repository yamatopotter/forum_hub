package com.hub.forum.DTO.Resposta;

import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.model.Resposta;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;

public record DetailDataResposta(Long id,
                                 String mensagem,
                                 LocalDateTime dataCriacao,
                                 DataUsuario autor,
                                 Topico topico) {

    public DetailDataResposta (Resposta resposta){
        this(resposta.getId(), resposta.getMensagem(), resposta.getDataCriacao(), new DataUsuario(resposta.getAutor()), resposta.getTopico());
    }
}
