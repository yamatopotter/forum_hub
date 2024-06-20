package com.hub.forum.service;

import com.hub.forum.DTO.Resposta.CreateResposta;
import com.hub.forum.DTO.Resposta.CreatedRespostaFromResposta;
import com.hub.forum.DTO.Resposta.DetailDataResposta;
import com.hub.forum.model.Resposta;
import com.hub.forum.model.Usuario;
import com.hub.forum.repository.RespostaRepository;
import com.hub.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespostaService {
    @Autowired
    private RespostaRepository respostaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public CreatedRespostaFromResposta create(CreateResposta resposta) {
        var parentResponse = respostaRepository.getReferenceById(resposta.respostaId());
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        var newResponse = new Resposta(null, resposta.mensagem(), LocalDateTime.now(), usuarioLogado, false, parentResponse.getTopico(), null, true);
        respostaRepository.save(newResponse);

        return new CreatedRespostaFromResposta(newResponse, parentResponse);
    }

    public DetailDataResposta edit(String mensagem, Long id) {
        var editResposta = respostaRepository.getReferenceById(id);


    }
}
