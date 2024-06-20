package com.hub.forum.DTO.Topico;

import com.hub.forum.model.Curso;
import jakarta.validation.constraints.NotNull;

public record UpdateDataTopico(
        @NotNull Long id,
        String titulo,
        String mensagem,
        Long cursoId
) {
}
