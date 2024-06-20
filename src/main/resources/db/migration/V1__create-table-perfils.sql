create table perfils(
    id bigint not null auto_increment,
    nome varchar(20) not null,

    primary key(id)
);

insert into perfils values (null, "ADMIN");
insert into perfils values (null, "MODERATOR");
insert into perfils values (null, "USER");
