drop database INKstudiotattoo;

create database INKStudioTattoo;

use INKStudioTattoo;

create table Usuario(
	id int PRIMARY KEY not null identity(1,1),
	email varchar(50) not null,
	senha varchar(20) not null,
	telefone varchar(15) not null,
	nascimento varchar(15),
	nome varchar(60) not null,
	cpf varchar(20),
    fotoPerfil varBinary(MAX) null,
	statusUsuario varchar(10) not null default 'ATIVO'
);

create table Funcionario(
	id int primary key not null identity(1,1),
    nome varchar(50) not null,
    nascimento varchar(15) not null,
    descricao varchar(1000) not null,
    servico varchar(20) not null,
    cpf varchar(20) not null,
    email varchar(50) not null,
    telefone varchar(15) not null,
    senha varchar(20) not null,
    fotoPerfil varBinary(MAX) null,
    fotoTattoo varBinary(MAX) null,
	statusUsuario varchar(10) not null default 'ATIVO'
);

CREATE TABLE Orcamento (
    id INT identity(1,1) PRIMARY KEY,
    cor VARCHAR(10),
    horas VARCHAR(5),
	valor decimal(10,1),
    data DATE,
    id_funcionario INT,
    id_usuario INT,
	statusOrcamento VARCHAR(10) not null default 'ESPERANDO',
    CONSTRAINT fk_funcionario FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Agenda (
    id INT identity(1,1) PRIMARY KEY,
    data DATE,
    horas VARCHAR(5),
    servico VARCHAR(15),
    valor decimal(10,1),
    id_funcionario INT,
    id_usuario INT,
    id_orcamento INT,
    CONSTRAINT fk_funcionario_agenda FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    CONSTRAINT fk_usuario_agenda FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    CONSTRAINT fk_orcamento_agenda FOREIGN KEY (id_orcamento) REFERENCES Orcamento(id)
);

select * from Orcamento;

ALTER TABLE orcamento
ALTER COLUMN statusOrcamento  VARCHAR(10) NULL ;

