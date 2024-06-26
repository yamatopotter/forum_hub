create table respostas_filhas(
    id bigint not null auto_increment,
    resposta_pai_id bigint not null,
    resposta_filha_id bigint not null,

    primary key(id)
);