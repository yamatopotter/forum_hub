package com.hub.forum.Controller;

import com.hub.forum.DTO.Resposta.CreateResposta;
import com.hub.forum.DTO.Resposta.CreatedRespostaFromResposta;
import com.hub.forum.service.RespostaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/resposta")
public class RespostaController {
    @Autowired
    private RespostaService respostaService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateResposta resposta, UriComponentsBuilder uriBuilder){
        CreatedRespostaFromResposta createdResposta = respostaService.create(resposta);

        var uri = uriBuilder.path("/resposta/{id}").buildAndExpand(createdResposta.id()).toUri();

        return ResponseEntity.created(uri).body(createdResposta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity edit(@RequestBody String mensagem, @RequestParam Long id){
        var updatedResposta = respostaService.edit(mensagem, id);
    }
}
