package com.hub.forum.DTO.Resposta;

import jakarta.validation.constraints.NotNull;

public record MensagemResposta(@NotNull String mensagem) {
}
