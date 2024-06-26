package com.hub.forum.domain.DTO.Resposta;

import jakarta.validation.constraints.NotNull;

public record CreateRespostaWithoutParent(@NotNull String titulo,
                                          @NotNull String mensagem) {
}
