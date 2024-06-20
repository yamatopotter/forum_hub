package com.hub.forum.service;

import com.hub.forum.DTO.Curso.CreateDataCurso;
import com.hub.forum.DTO.Curso.DataCurso;
import com.hub.forum.DTO.Curso.UpdateDataCurso;
import com.hub.forum.model.Curso;
import com.hub.forum.model.Usuario;
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
        validationOfPermission();

        Curso newCurso = new Curso(null, curso.nome(), curso.categoria(), true);
        cursoRepository.save(newCurso);

        return new DataCurso(newCurso);
    }

    private void validationOfPermission() {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!usuarioLogado.getPerfil().getNome().equals("ADMIN")) {
            try {
                throw new IllegalAccessException("Acesso negado!");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete(Long id) {
        validationOfPermission();

        Curso curso = cursoRepository.getReferenceById(id);
        curso.delete();
    }


    public DataCurso update(UpdateDataCurso curso) {
        validationOfPermission();

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
