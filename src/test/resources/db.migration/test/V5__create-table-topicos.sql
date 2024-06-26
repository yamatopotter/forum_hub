create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem text not null,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status varchar(10) not null,
    usuario_id bigint not null,
    curso_id bigint not null,
    solucao_id bigint,
    ativo boolean not null,

    primary key(id)
);