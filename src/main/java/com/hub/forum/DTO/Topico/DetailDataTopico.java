package com.hub.forum.DTO.Topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.hub.forum.DTO.Resposta.DetailDataResposta;
import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.model.Resposta;
import com.hub.forum.model.Status;
import com.hub.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DetailDataTopico(Long id,
                               String titulo,
                               String mensagem,
                               @JsonAlias("data_criacao") LocalDateTime dataCriacao,
                               Status status,
                               DataUsuario usuario,
                               List<DetailDataResposta> respostas,
                               DetailDataResposta solucao,
                               Boolean ativo) {

    public DetailDataTopico (Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                new DataUsuario(topico.getUsuario()),
                topico.getRespostas().stream().map(DetailDataResposta::new).toList(),
                topico.getSolucao() != null ? new DetailDataResposta(topico.getSolucao()):null,
                topico.getAtivo());
    }
}
