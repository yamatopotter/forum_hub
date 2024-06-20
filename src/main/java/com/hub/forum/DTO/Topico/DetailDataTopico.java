package com.hub.forum.DTO.Topico;

import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.model.Resposta;
import com.hub.forum.model.Status;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.List;

public record DetailDataTopico(Long id,
                               String titulo,
                               String mensagem,
                               LocalDateTime dataCriacao,
                               Status status,
                               DataUsuario usuario,
                               List<Resposta> resposta,
                               Resposta solucao,
                               Boolean ativo) {

    public DetailDataTopico (Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                new DataUsuario(topico.getUsuario()),
                topico.getRespostas(),
                topico.getSolucao(),
                topico.getAtivo());
    }
}
