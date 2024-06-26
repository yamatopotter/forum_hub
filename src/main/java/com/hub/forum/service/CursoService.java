package com.hub.forum.service;

import com.hub.forum.domain.DTO.Curso.CreateDataCurso;
import com.hub.forum.domain.DTO.Curso.DataCurso;
import com.hub.forum.domain.DTO.Curso.UpdateDataCurso;
import com.hub.forum.domain.ValidacaoException;
import com.hub.forum.domain.model.Curso;
import com.hub.forum.domain.model.Usuario;
import com.hub.forum.repository.CursoRepository;
import com.hub.forum.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DataCurso create(@Valid CreateDataCurso curso) {
        isAdmin();

        Curso newCurso = new Curso(null, curso.nome(), curso.categoria(), true);
        cursoRepository.save(newCurso);

        return new DataCurso(newCurso);
    }

    private void isAdmin() {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!usuarioLogado.getPerfil().getNome().equals("ADMIN")) {
            throw new ValidacaoException("Acesso negado!");
        }
    }

    public void delete(Long id) {
        isAdmin();

        Curso curso = cursoRepository.getReferenceById(id);
        curso.delete();
    }


    public DataCurso update(UpdateDataCurso curso) {
        isAdmin();

        Curso updateCurso = cursoRepository.getReferenceById(curso.id());
        updateCurso.update(curso);

        return new DataCurso(updateCurso);
    }

    public Page<DataCurso> list(Pageable paginacao) {
        return cursoRepository.findAllByAtivoTrue(paginacao).map(DataCurso::new);
    }

    public DataCurso detail(Long id) {
        return new DataCurso(cursoRepository.getReferenceById(id));
    }
}
