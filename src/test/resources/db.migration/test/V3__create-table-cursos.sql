create table cursos(
    id bigint not null auto_increment,
    nome varchar(50) not null,
    categoria varchar(30) not null,
    ativo boolean not null,

    primary key(id)
);