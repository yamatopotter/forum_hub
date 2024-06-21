package com.hub.forum.service;

import com.hub.forum.DTO.Resposta.CreateResposta;
import com.hub.forum.DTO.Resposta.CreatedRespostaFromResposta;
import com.hub.forum.DTO.Resposta.DetailDataResposta;
import com.hub.forum.model.Resposta;
import com.hub.forum.model.Usuario;
import com.hub.forum.repository.RespostaRepository;
import com.hub.forum.repository.TopicoRepository;
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
    @Autowired
    private TopicoRepository topicoRepository;

    public CreatedRespostaFromResposta create(CreateResposta resposta) {
        var parentResponse = respostaRepository.getReferenceById(resposta.respostaId());
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        var newResponse = new Resposta(null, resposta.mensagem(), LocalDateTime.now(), usuarioLogado, false, parentResponse.getTopico(), null, true);
        respostaRepository.save(newResponse);

        return new CreatedRespostaFromResposta(newResponse, parentResponse);
    }

    public DetailDataResposta edit(String mensagem, Long id) {
        Resposta editResposta = respostaRepository.getReferenceById(id);
        editResposta.update(mensagem);

        return new DetailDataResposta(editResposta);
    }


    public DetailDataResposta detail(Long id) {
        return new DetailDataResposta(respostaRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        Resposta resposta = respostaRepository.getReferenceById(id);

        if(resposta.getRespostas().size()>0){
           throw new IllegalArgumentException("Essa resposta não pode ser excluida por conter outras respostas");
        }
        else{
            validationOfUpdate(resposta.getAutor().getId());
            resposta.delete();
        }
    }

    private void validationOfUpdate(Long usuarioId) {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!usuarioLogado.getId().equals(usuarioId) || !usuarioLogado.getPerfil().getNome().equals("ADMIN") || !usuarioLogado.getPerfil().getNome().equals("MODERATOR")) {
            try {
                throw new IllegalAccessException("Acesso negado!");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setAsSolution(Long id) {
        var resposta = respostaRepository.getReferenceById(id);
        var topico = topicoRepository.getReferenceById(resposta.getTopico().getId());
        validationOfUpdate(topico.getId());

        resposta.isSolucao();
        topico.isSolucao(resposta);
    }
}
