# Desafio CI&T

## Front-End
Nosso front-end foi inteiramente desenvolvido com o auxilio do Vue.js e do Quasar Framework.

[Vue,js](https://vuejs.org/)

[Quasar Framework](https://quasar.dev/)

Nessa primeira parte temos a criação dos quadros, uma tela de exibição de todos os quadros criados e ao clicar em um deles você será redirecioando para uma tela onde são exibidas listas de tarefas.

### Criando um Quadro
![alt text](https://github.com/BeOneSix/To-doList/blob/master/imagens/cria%C3%A7%C3%A3o%20de%20quadro.jfif)

### Quadros
![alt text](https://github.com/BeOneSix/To-doList/blob/master/imagens/Quadros.jfif)

### Listas
![alt text](https://github.com/BeOneSix/To-doList/blob/master/imagens/listas.jfif)

## Back-End

### Recursos Spring Boot


## Banco de Dados

### Script

create database trelloDB;
 
use trelloDB;
 
CREATE TABLE cartao (
    id int NOT NULL AUTO_INCREMENT,
    titulo varchar(80) NULL,
    descricao varchar(80) NULL,
    listas_id int NOT NULL,
    CONSTRAINT cartao_pk PRIMARY KEY (id)
);
 

CREATE TABLE listas (
    ID int NOT NULL AUTO_INCREMENT,
    TITULO varchar(80) NULL,
    PLACAS_ID int NOT NULL,
    CONSTRAINT listas_pk PRIMARY KEY (id)
);
 
 
CREATE TABLE placas (
    ID int NOT NULL AUTO_INCREMENT,
    NOME varchar(80) NOT NULL,
    USUARIO_ID int NOT NULL,
    CONSTRAINT plcas_pk PRIMARY KEY (id)
);
 
 
 
CREATE TABLE usuario (
    ID int NOT NULL AUTO_INCREMENT,
    NOME varchar(50) NULL,
    EMAIL varchar(80) NULL,
    SENHA varchar(20) NULL,
    CONSTRAINT usuario_pk PRIMARY KEY (id)
);
 

ALTER TABLE cartao ADD CONSTRAINT CARTAO_LISTAS FOREIGN KEY CARTAO_LISTAS (LISTAS_ID)
    REFERENCES LISTAS (id);
 
 
ALTER TABLE listas ADD CONSTRAINT LISTAS_PLACAS FOREIGN KEY LISTAS_PLACAS (PLACAS_ID)
    REFERENCES PLACAS (id);
 

ALTER TABLE placas ADD CONSTRAINT PLACAS_USUARIO FOREIGN KEY PLACAS_USUARIO (USUARIO_ID)
    REFERENCES USUARIO (id);
