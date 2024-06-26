package com.hub.forum.domain.DTO.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateDataTopico(@NotBlank String titulo,
                               @NotBlank String mensagem,
                               @NotNull Long cursoId) {
}
