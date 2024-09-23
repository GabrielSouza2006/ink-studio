drop database INKstudiotattoo;

create database INKStudioTattoo;

use INKStudioTattoo;

create table Usuario(
	id int PRIMARY KEY not null auto_increment,
	email varchar(50) not null,
	senha varchar(20) not null,
	telefone varchar(15) not null,
	nascimento varchar(15),
	nome varchar(60) not null,
	cpf varchar(20),
    fotoPerfil varBinary(MAX) null,
);

create table Funcionario(
	id int primary key not null,
    nome varchar(50) not null,
    nascimento varchar(15) not null,
    descricao varchar(1000) not null,
    servico varchar(20) not null,
    cpf varchar(20) not null
    email varchar(50) not null,
    telefone varchar(15) not null,
    senha varchar(20) not null,
    fotoPerfil varBinary(MAX) null,
    fotoTattoo varBinary(MAX) null
);

CREATE TABLE Orcamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cor VARCHAR(10),
    horas VARCHAR(5),
    data DATE,
    statusOrcamento VARCHAR(10),
    id_funcionario INT,
    id_usuario INT,
    CONSTRAINT fk_funcionario FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Agenda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE,
    horas VARCHAR(5),
    servico VARCHAR(15),
    valor decimal(10,2),
    statusAgenda VARCHAR(10) default 'ESPERANDO',
    id_funcionario INT,
    id_usuario INT,
    id_orcamento INT,
    CONSTRAINT fk_funcionario_agenda FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    CONSTRAINT fk_usuario_agenda FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    CONSTRAINT fk_orcamento_agenda FOREIGN KEY (id_orcamento) REFERENCES Orcamento(id)
);
