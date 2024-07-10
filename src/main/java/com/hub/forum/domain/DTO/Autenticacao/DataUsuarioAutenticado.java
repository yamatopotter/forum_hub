package com.hub.forum.domain.DTO.Autenticacao;

import jakarta.validation.constraints.NotNull;

public record DataUsuarioAutenticado(@NotNull String token) {
}
