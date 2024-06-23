package com.hub.forum.service;

import com.hub.forum.DTO.Cadastro.DataCadastro;
import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.infra.security.SecurityConfiguration;
import com.hub.forum.model.Perfil;
import com.hub.forum.model.Usuario;
import com.hub.forum.repository.PerfilRepository;
import com.hub.forum.repository.UsuarioRepository;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private SecurityConfiguration securityConfiguration;
    private UsuarioService usuarioService;
    @Mock
    private PerfilRepository perfilRepository;


    @BeforeEach
    void setUp(){
        perfilRepository.save(new Perfil(null, "USER"));
        usuarioService = new UsuarioService(usuarioRepository, perfilRepository, securityConfiguration);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        when(securityConfiguration.passwordEncoder()).thenReturn(passwordEncoder);
    }

    @Test
    void cadastroDeNovoUsuario() {
        DataCadastro dataCadastro = new DataCadastro("Teste", "teste@teste.com", "12345678");
        DataUsuario usuarioCadastrado = usuarioService.cadastrarNovoUsuario(dataCadastro);

        ArgumentCaptor<Usuario> usuarioArgumentCaptor = ArgumentCaptor.forClass(Usuario.class);

        verify(usuarioRepository).save(usuarioArgumentCaptor.capture());

        Usuario usuarioSalvo = usuarioArgumentCaptor.getValue();

        assertThat(usuarioSalvo.getNome()).isEqualTo("Teste");
        assertThat(usuarioSalvo.getEmail()).isEqualTo("teste@teste.com");

    }

    @Test
    void update() {
    }

    @Test
    void list() {
        Pageable paginacao = null;
        usuarioService.list(paginacao);

        verify(usuarioRepository).findAllByAtivoTrue(paginacao);
    }

    @Test
    void detail() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }
}
