use redlink;
create table tb_link(
	id int primary key auto_increment,
    url varchar(255) not null,
    curto varchar(8) not null unique,
    userid int not null,
    data_criacao timestamp
);
insert into tb_link (url, curto, usuario, data_criacao) values ('url', (select substr(to_base64((select count(*) as total from tb_link)),1.7)), 'admin', sysdate());

select to_base64("http://teste.com") from tb_link;

create table tb_user(
	id int primary key auto_increment,
    usuario varchar(35) not null,
    senha varchar(20) not null
);

insert into tb_user (usuario, senha) values('leonardo', 'leonardo');
insert into tb_user (usuario, senha) values('ana', 'ana');
insert into tb_user (usuario, senha) values('admin', 'admin');

ALTER TABLE `tb_link` ADD CONSTRAINT `fk_userid` FOREIGN KEY ( `userid` ) REFERENCES `tb_user` ( `id` );
