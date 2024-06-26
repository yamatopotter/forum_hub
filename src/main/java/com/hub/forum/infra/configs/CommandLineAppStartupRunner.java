package com.hub.forum.infra.configs;

import com.hub.forum.infra.security.SecurityConfiguration;
import com.hub.forum.domain.model.Usuario;
import com.hub.forum.repository.PerfilRepository;
import com.hub.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PerfilRepository perfilRepository;
    @Autowired
    SecurityConfiguration securityConfiguration;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.countAdministrators() == 0) {
            var perfilAdministrador = perfilRepository.findByNome("ADMIN");
            var passwordEncoder = securityConfiguration.passwordEncoder();
            var defaultAdministrator = Usuario.builder().nome("Administrador Forum Hub").email("administrator@forumhub.com").senha(passwordEncoder.encode("administrador")).perfil(perfilAdministrador).ativo(true).build();

            usuarioRepository.save(defaultAdministrator);
        } else {
        }
    }
}
