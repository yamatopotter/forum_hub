package com.hub.forum.domain.DTO.Autenticacao;

import jakarta.validation.constraints.NotNull;

public record DataAutenticacao(@NotNull String email,
                               @NotNull String senha) {
}
