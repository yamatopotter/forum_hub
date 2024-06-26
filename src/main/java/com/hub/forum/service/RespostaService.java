package com.hub.forum.service;

import com.hub.forum.domain.DTO.Resposta.CreatedRespostaFromResposta;
import com.hub.forum.domain.DTO.Resposta.DetailDataResposta;
import com.hub.forum.domain.ValidacaoException;
import com.hub.forum.domain.model.Resposta;
import com.hub.forum.domain.model.RespostaFilha;
import com.hub.forum.domain.model.Usuario;
import com.hub.forum.repository.RespostaFilhaRepository;
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
    private RespostaFilhaRepository respostaFilhaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    public CreatedRespostaFromResposta create(String mensagem, Long id) {
        Resposta parentResponse = respostaRepository.getReferenceById(id);
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        Resposta newResponse = new Resposta(null, mensagem, LocalDateTime.now(), usuarioLogado, false, parentResponse.getTopico(), null, true);
        respostaRepository.save(newResponse);

        var newRespostaFilha = new RespostaFilha(null, parentResponse, newResponse);
        respostaFilhaRepository.save(newRespostaFilha);

        return new CreatedRespostaFromResposta(newResponse, parentResponse);
    }

    public DetailDataResposta edit(String mensagem, Long id) {
        Resposta editResposta = respostaRepository.getReferenceById(id);
        validationOfUpdate(editResposta.getAutor().getId());
        editResposta.update(mensagem);

        return new DetailDataResposta(editResposta);
    }


    public DetailDataResposta detail(Long id) {
        return new DetailDataResposta(respostaRepository.getReferenceById(id));
    }

    public void delete(Long id) {
        Resposta resposta = respostaRepository.getReferenceById(id);

        if(!resposta.getRespostas().isEmpty()){
           throw new ValidacaoException("Essa resposta não pode ser excluida por conter outras respostas");
        }
        else{
            validationOfUpdate(resposta.getAutor().getId());
            resposta.delete();
        }
    }

    private void validationOfUpdate(Long usuarioId) {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!usuarioLogado.getId().equals(usuarioId) && !usuarioLogado.getPerfil().getNome().equals("ADMIN") && !usuarioLogado.getPerfil().getNome().equals("MODERATOR")) {
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
        validationOfUpdate(topico.getUsuario().getId());

        resposta.isSolucao();
        topico.isSolucao(resposta);
    }
}
