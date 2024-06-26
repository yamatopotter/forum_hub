package com.hub.forum.domain.DTO.Cadastro;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DataCadastroFromAdmin (@NotNull String nome,
                                     @NotNull String email,
                                     @NotNull String senha,
                                     @JsonAlias("perfil_name") @NotNull String perfilName){
}
