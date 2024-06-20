create table respostas(
    id bigint not null auto_increment,
    mensagem text not null,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id bigint not null,
    solucao boolean not null,
    topico_id bigint not null,

    primary key(id),
    CONSTRAINT fk_topico_respostas FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_usuario_respostas FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);