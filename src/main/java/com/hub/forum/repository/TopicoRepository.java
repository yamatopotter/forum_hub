package com.hub.forum.repository;

import com.hub.forum.model.Curso;
import com.hub.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
