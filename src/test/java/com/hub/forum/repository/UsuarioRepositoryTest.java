package com.hub.forum.repository;

import com.hub.forum.domain.model.Perfil;
import com.hub.forum.domain.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Test
    void contagemDeAdministradoresDeveSerUm() {
//        Given
        Perfil perfil = perfilRepository.findByNome("ADMIN");
        Usuario user = new Usuario(1L, "Administrador", "administrador@forumhub.com", "12345678", perfil, true);
        usuarioRepository.save(user);
//        When
        Long expected = usuarioRepository.countAdministrators();
//        Then
        assertThat(expected).isEqualTo(1L);
    }

    @Test
    void acharUsuarioPorEmail() {
//        Given
        Perfil perfil = perfilRepository.findByNome("USER");
        Usuario user = new Usuario(null, "Usuario de teste", "teste@test.com", "12345678", perfil, true);
        usuarioRepository.save(user);

//        When
        Usuario expected = usuarioRepository.findByEmail("teste@test.com");

//        Then
        assertThat(expected.getEmail()).isEqualTo(user.getEmail());
    }
}