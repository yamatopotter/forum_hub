package com.hub.forum.DTO.Curso;

import jakarta.validation.constraints.NotNull;

public record UpdateDataCurso(@NotNull Long id,
                              String nome,
                              String categoria,
                              Boolean ativo) {
}
