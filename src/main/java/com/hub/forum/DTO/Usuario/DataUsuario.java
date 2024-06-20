package com.hub.forum.DTO.Usuario;

import com.hub.forum.model.Usuario;

public record DataUsuario(Long id,
                          String nome,
                          String email,
                          String perfil,
                          Boolean ativo) {
    public DataUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil().getNome(), usuario.getAtivo());
    }
}
