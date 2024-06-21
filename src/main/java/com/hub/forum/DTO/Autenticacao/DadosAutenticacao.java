package com.hub.forum.DTO.Autenticacao;

import jakarta.validation.constraints.NotNull;

public record DadosAutenticacao(@NotNull String email,
                                @NotNull String senha) {
}
