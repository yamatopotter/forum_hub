package com.hub.forum.repository;

import com.hub.forum.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.perfil.id = 1")
    Long countAdministrators();
}
