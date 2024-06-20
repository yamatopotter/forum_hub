package com.hub.forum.DTO.Topico;

import jakarta.validation.constraints.NotBlank;

public record CreateDataTopico(@NotBlank String titulo,
                               @NotBlank String mensagem,
                               @NotBlank Long cursoId) {
}
