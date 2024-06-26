package com.hub.forum.domain.DTO.Resposta;

import jakarta.validation.constraints.NotNull;

public record MensagemResposta(@NotNull String mensagem) {
}
