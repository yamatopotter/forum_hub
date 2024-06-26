package com.hub.forum.domain.DTO.Cadastro;

import jakarta.validation.constraints.NotNull;

public record DataCadastro(@NotNull String nome,
                           @NotNull String email,
                           @NotNull String senha) {
}
