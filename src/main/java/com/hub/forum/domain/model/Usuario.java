package com.hub.forum.domain.model;

import com.hub.forum.domain.DTO.Usuario.UpdateDataUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name="Usuario")
@Table(name="usuarios")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique=true)
    private String email;
    private String senha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="perfil_id")
    private Perfil perfil;
    private Boolean ativo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("AUTHENTICATED_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", perfil=" + perfil +
                '}';
    }

    public void delete(){
        this.ativo = false;
    }

    public void activate(){
        this.ativo = true;
    }

    public void update(UpdateDataUsuario usuario){
        if(usuario.nome() != null){
            this.nome = usuario.nome();
        }

        if(usuario.email() != null){
            this.email = usuario.email();
        }

        if(usuario.senha() != null){
            this.senha = usuario.senha();
        }

        if(usuario.ativo() != null){
            this.ativo = usuario.ativo();
        }
    }

    public void update(UpdateDataUsuario usuario, Perfil perfil){
        if(usuario.nome() != null){
            this.nome = usuario.nome();
        }

        if(usuario.email() != null){
            this.email = usuario.email();
        }

        if(usuario.senha() != null){
            this.senha = usuario.senha();
        }

        if(usuario.perfilId() != null){
            this.perfil = perfil;
        }

        if(usuario.ativo() != null){
            this.ativo = usuario.ativo();
        }
    }
}
