package com.hub.forum.domain.DTO.Curso;

import jakarta.validation.constraints.NotNull;

public record CreateDataCurso(@NotNull String nome,
                              @NotNull String categoria) {
}
