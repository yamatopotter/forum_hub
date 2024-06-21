ALTER TABLE usuarios ADD CONSTRAINT fk_perfil_usuario foreign key (perfil_id) references perfils(id);
ALTER TABLE respostas ADD CONSTRAINT fk_topico_respostas FOREIGN KEY (topico_id) REFERENCES topicos(id);
ALTER TABLE respostas ADD CONSTRAINT fk_usuario_respostas FOREIGN KEY (usuario_id) REFERENCES usuarios(id);
ALTER TABLE topicos ADD CONSTRAINT fk_usuario_topico FOREIGN KEY (usuario_id) REFERENCES usuarios(id);
ALTER TABLE topicos ADD CONSTRAINT fk_solucao_topico FOREIGN KEY (solucao_id) REFERENCES respostas(id);
ALTER TABLE topicos ADD CONSTRAINT fk_curso_topico FOREIGN KEY (curso_id) REFERENCES cursos(id);
ALTER TABLE respostas_filhas ADD CONSTRAINT fk_respostas_resposta_pai FOREIGN KEY (resposta_pai_id) REFERENCES respostas(id);
ALTER TABLE respostas_filhas ADD CONSTRAINT fk_respostas_resposta_filha FOREIGN KEY (resposta_filha_id) REFERENCES respostas(id);
