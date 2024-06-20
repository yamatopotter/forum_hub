package com.hub.forum.DTO.Curso;

import jakarta.validation.constraints.NotNull;

public record CreateDataCurso(@NotNull String nome,
                              @NotNull String categoria) {
}
