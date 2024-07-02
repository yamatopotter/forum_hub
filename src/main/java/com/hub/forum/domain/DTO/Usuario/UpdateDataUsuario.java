package com.hub.forum.domain.DTO.Usuario;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record UpdateDataUsuario(@NotNull Long id,
                                String nome,
                                String email,
                                String senha,
                                @JsonAlias("perfil_id") Long perfilId,
                                Boolean ativo) {
    public UpdateDataUsuario withEncryptedPassword(String password){
        return new UpdateDataUsuario(id, nome, email, password, perfilId, ativo);
    }
}
