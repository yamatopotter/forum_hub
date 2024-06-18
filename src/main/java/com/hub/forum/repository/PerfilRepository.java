package com.hub.forum.repository;

import com.hub.forum.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Perfil findByNome(String perfil);
}
