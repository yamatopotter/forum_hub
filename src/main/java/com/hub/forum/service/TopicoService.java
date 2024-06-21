package com.hub.forum.service;

import com.hub.forum.DTO.Resposta.CreateRespostaWithoutParent;
import com.hub.forum.DTO.Resposta.CreatedRespostaFromTopico;
import com.hub.forum.DTO.Topico.*;
import com.hub.forum.model.*;
import com.hub.forum.repository.CursoRepository;
import com.hub.forum.repository.RespostaRepository;
import com.hub.forum.repository.TopicoRepository;
import com.hub.forum.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private RespostaRepository respostaRepository;

    public CreatedTopico create(@Valid CreateDataTopico topico) {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        var curso = cursoRepository.findById(topico.cursoId()).get();

        var newTopico = new Topico(null, topico.titulo(), topico.mensagem(), LocalDateTime.now(), Status.OPEN, usuarioLogado, curso, null, null, true);
        topicoRepository.save(newTopico);

        return new CreatedTopico(newTopico);
    }

    public void delete(Long id) {
        var topicoParaDeletar = topicoRepository.getReferenceById(id);
        validationOfExclusion(topicoParaDeletar.getUsuario().getId());

        topicoParaDeletar.excluir();
    }

    public UpdatedTopico update(UpdateDataTopico topico) {
        var topicoParaAtualizar = topicoRepository.getReferenceById(topico.id());
        Curso curso = null;
        if (topico.cursoId() != null) {
            curso = cursoRepository.findById(topico.cursoId()).get();
        }
        validationOfUpdate(topicoParaAtualizar.getUsuario().getId());

        topicoParaAtualizar.update(topico, curso);

        return new UpdatedTopico(topicoParaAtualizar);
    }

    public Page<ListDataTopico> list(Pageable paginacao) {
        return topicoRepository.findAllByAtivoTrue(paginacao).map(ListDataTopico::new);
    }

    public DetailDataTopico detail(Long id) {
        return new DetailDataTopico(topicoRepository.getReferenceById(id));
    }

    private void validationOfExclusion(Long usuarioId) {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!usuarioLogado.getId().equals(usuarioId) || !usuarioLogado.getPerfil().getNome().equals("ADMIN")) {
            try {
                throw new IllegalAccessException("Acesso negado!");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
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

    public CreatedRespostaFromTopico createNewResposta(CreateRespostaWithoutParent resposta, Long id) {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Topico topico = topicoRepository.findById(id).get();
        Resposta newResposta = new Resposta(null, resposta.mensagem(), LocalDateTime.now(), usuarioLogado, false, topico, null,true);

        respostaRepository.save(newResposta);
        return new CreatedRespostaFromTopico(newResposta);
    }


}
