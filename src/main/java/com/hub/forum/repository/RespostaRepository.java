package com.hub.forum.repository;

import com.hub.forum.model.Curso;
import com.hub.forum.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
}
