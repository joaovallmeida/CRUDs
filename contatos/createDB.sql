create database dbContatos;
use dbContatos;
create table contatos(
nome varchar(40) not null,
email varchar(40) primary key not null,
telefone varchar(40) not null
);