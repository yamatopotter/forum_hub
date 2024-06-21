package com.hub.forum.DTO.Resposta;

import jakarta.validation.constraints.NotNull;

public record CreateRespostaWithoutParent(@NotNull String titulo,
                                          @NotNull String mensagem) {
}
