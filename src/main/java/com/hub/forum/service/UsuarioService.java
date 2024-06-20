package com.hub.forum.service;

import com.hub.forum.DTO.Cadastro.DadosCadastro;
import com.hub.forum.DTO.Usuario.DataUsuario;
import com.hub.forum.infra.security.SecurityConfiguration;
import com.hub.forum.model.Usuario;
import com.hub.forum.repository.PerfilRepository;
import com.hub.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private SecurityConfiguration securityConfiguration;

    public DataUsuario cadastrarNovoUsuario(DadosCadastro dados){
        var passwordEncrypter = securityConfiguration.passwordEncoder();
        var userPerfil = perfilRepository.findByNome("USER");

        System.out.println(userPerfil);

        var usuario = new Usuario(null, dados.nome(), dados.email(), passwordEncrypter.encode(dados.senha()), userPerfil);

        System.out.println(usuario);
        usuarioRepository.save(usuario);

        return new DataUsuario(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil().getNome());
    }

}
