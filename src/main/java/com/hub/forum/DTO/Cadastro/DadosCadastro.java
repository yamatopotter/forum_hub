package com.hub.forum.DTO.Cadastro;

import jakarta.validation.constraints.NotNull;

public record DadosCadastro(@NotNull String nome,
                            @NotNull String email,
                            @NotNull String senha) {
}
