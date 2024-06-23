package com.hub.forum.Controller;

import com.hub.forum.service.UsuarioService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @DisplayName("Deveria devolver codigo http 403 quando não houver permissão para excluir")
//    void create() throws Exception {
//        var response = mockMvc.perform(post("/cadastro")).andReturn().getResponse();
//        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
//    }

//    @Test
//    @DisplayName("Deveria devolver codigo http 403 quando não houver permissão para excluir")
//    @WithMockUser
//    void deleteUsuario_cenario1() throws Exception {
//        var response = mockMvc.perform(delete("/usuario/1")).andReturn().getResponse();
//        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
//    }

    @Test
    void update() {
    }

    @Test
    void list() {
    }

    @Test
    void detail() {
    }

}