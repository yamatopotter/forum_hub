package com.hub.forum.domain.DTO.Curso;

import jakarta.validation.constraints.NotNull;

public record UpdateDataCurso(@NotNull Long id,
                              String nome,
                              String categoria,
                              Boolean ativo) {
}
