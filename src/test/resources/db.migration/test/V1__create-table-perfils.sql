create table perfils(
    id bigint not null auto_increment,
    nome varchar(20) not null,

    primary key(id)
);

INSERT INTO perfils VALUES (1, 'ADMIN');
INSERT INTO perfils VALUES (2, 'MODERATOR');
INSERT INTO perfils VALUES (3, 'USER');