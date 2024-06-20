package com.hub.forum.DTO.Usuario;

import jakarta.validation.constraints.NotNull;

public record UpdateDataUsuario(@NotNull Long id,
                                String nome,
                                String email,
                                String senha,
                                Long perfilId,
                                Boolean ativo) {
    public UpdateDataUsuario withEncryptedPassword(String password){
        return new UpdateDataUsuario(id, nome, email, password, perfilId, ativo);
    }
}
