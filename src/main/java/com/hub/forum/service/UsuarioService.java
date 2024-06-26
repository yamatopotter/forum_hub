package com.hub.forum.service;

import com.hub.forum.domain.DTO.Cadastro.DataCadastro;
import com.hub.forum.domain.DTO.Cadastro.DataCadastroFromAdmin;
import com.hub.forum.domain.DTO.Usuario.DataUsuario;
import com.hub.forum.domain.DTO.Usuario.UpdateDataUsuario;
import com.hub.forum.infra.security.SecurityConfiguration;
import com.hub.forum.domain.model.Usuario;
import com.hub.forum.repository.PerfilRepository;
import com.hub.forum.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private SecurityConfiguration securityConfiguration;

    public DataUsuario cadastrarNovoUsuario(DataCadastro dados){
        var passwordEncrypter = securityConfiguration.passwordEncoder();
        var userPerfil = perfilRepository.findByNome("USER");
        var usuario = new Usuario(null, dados.nome(), dados.email(), passwordEncrypter.encode(dados.senha()), userPerfil, true);
        usuarioRepository.save(usuario);

        return new DataUsuario(usuario);
    }

    private void isAdmin() {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!usuarioLogado.getPerfil().getNome().equals("ADMIN")) {
            try {
                throw new IllegalAccessException("Acesso negado!");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void validationOfPermission(Long usuarioId) {
        Usuario usuarioLogado = usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!usuarioLogado.getId().equals(usuarioId) || !usuarioLogado.getPerfil().getNome().equals("ADMIN")) {
            try {
                throw new IllegalAccessException("Acesso negado!");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void delete(Long id) {
        isAdmin();

        var deleteUser = usuarioRepository.getReferenceById(id);
        deleteUser.delete();
    }

    public DataUsuario update(UpdateDataUsuario usuario) {
        validationOfPermission(usuario.id());

        var updateUser = usuarioRepository.findByEmail(usuario.email());
        var passwordEncrypter = securityConfiguration.passwordEncoder();
        if(usuario.senha() != null){
            usuario = usuario
                    .withEncryptedPassword(
                            passwordEncrypter.encode(usuario.senha()));
        }

        if(usuario.perfilId() != null){
            var newPerfil = perfilRepository.getReferenceById(usuario.perfilId());
            updateUser.update(usuario, newPerfil);
        }
        else{
            updateUser.update(usuario);
        }

        return new DataUsuario(updateUser);
    }

    public Page<DataUsuario> list(Pageable paginacao) {
        return usuarioRepository.findAllByAtivoTrue(paginacao).map(DataUsuario::new);
    }

    public DataUsuario detail(Long id) {
        return new DataUsuario(usuarioRepository.getReferenceById(id));
    }

    public DataUsuario create(DataCadastroFromAdmin dataCadastroFromAdmin) {
        var passwordEncrypter = securityConfiguration.passwordEncoder();
        var userPerfil = perfilRepository.findByNome(dataCadastroFromAdmin.perfilName().toUpperCase());
        var usuario = new Usuario(null, dataCadastroFromAdmin.nome(), dataCadastroFromAdmin.email(), passwordEncrypter.encode(dataCadastroFromAdmin.senha()), userPerfil, true);
        usuarioRepository.save(usuario);

        return new DataUsuario(usuario);
    }
}
