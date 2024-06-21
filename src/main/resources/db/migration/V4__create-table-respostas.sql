create table respostas(
    id bigint not null auto_increment,
    mensagem text not null,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id bigint not null,
    solucao boolean not null,
    topico_id bigint not null,
    ativo boolean not null,

    primary key(id)
);