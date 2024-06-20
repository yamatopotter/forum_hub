create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem text not null,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status varchar(10) not null,
    usuario_id bigint not null,
    curso_id bigint not null,
    ativo boolean not null,

    primary key(id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);