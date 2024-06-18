create table respostas(
    id bigint not null auto_increment,
    mensagem text not null,
    dataCriacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id bigint not null,
    solucao boolean not null,
    topico_id bigint not null,

    primary key(id),
    CONSTRAINT fk_topico FOREIGN KEY (topico_id) REFERENCES topicos(id)
);