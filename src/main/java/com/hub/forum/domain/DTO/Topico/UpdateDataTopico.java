package com.hub.forum.domain.DTO.Topico;

import jakarta.validation.constraints.NotNull;

public record UpdateDataTopico(
        @NotNull Long id,
        String titulo,
        String mensagem,
        Long cursoId
) {
}
