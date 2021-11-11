CREATE TABLE cartao (
    id int NOT NULL AUTO_INCREMENT,
    titulo varchar(80) NULL,
    descricao varchar(80) NULL,
    listas_id int NOT NULL,
    CONSTRAINT cartao_pk PRIMARY KEY (id)
);

CREATE TABLE listas (
    id int NOT NULL AUTO_INCREMENT,
    titulo varchar(80) NULL,
    placas_id int NOT NULL,
    CONSTRAINT listas_pk PRIMARY KEY (id)
);
 
CREATE TABLE placas (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(80) NOT NULL,
    usuario_id int NOT NULL,
    CONSTRAINT plcas_pk PRIMARY KEY (id)
);

CREATE TABLE usuario (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(50) NULL,
    email varchar(80) NULL,
    senha varchar(20) NULL,
    CONSTRAINT usuario_pk PRIMARY KEY (id)
);

ALTER TABLE cartao ADD CONSTRAINT cartao_listas FOREIGN KEY cartao_listas (listas_id)
    REFERENCES listas (id);
 
ALTER TABLE listas ADD CONSTRAINT listas_placas FOREIGN KEY listas_placas (placas_id)
    REFERENCES placas (id);

ALTER TABLE placas ADD CONSTRAINT placas_usuario FOREIGN KEY placas_usuario (usuario_id)
    REFERENCES usuario (id);