-- Apagar e recriar o banco de dados
DROP DATABASE IF EXISTS INKStudioTattoo;
CREATE DATABASE INKStudioTattoo;

-- Usar o banco de dados
USE INKStudioTattoo;

-- Tabela Usuario
CREATE TABLE Usuario (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    nascimento VARCHAR(15),
    nome VARCHAR(60) NOT NULL,
    cpf VARCHAR(20),
    fotoPerfil BLOB NULL,
    statusUsuario VARCHAR(10) NOT NULL DEFAULT 'ATIVO'
);

-- Tabela Funcionario
CREATE TABLE Funcionario (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    nascimento VARCHAR(15) NOT NULL,
    descricao VARCHAR(1000) NOT NULL,
    servico VARCHAR(20) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    fotoPerfil BLOB NULL,
    fotoTattoo BLOB NULL,
    statusUsuario VARCHAR(10) NOT NULL DEFAULT 'ATIVO'
);

-- Tabela Orcamento
CREATE TABLE Orcamento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cor VARCHAR(10),
    horas VARCHAR(5),
    valor DECIMAL(10,1),
    data DATE,
    id_funcionario INT,
    id_usuario INT,
    statusOrcamento VARCHAR(10) NOT NULL DEFAULT 'ESPERANDO',
    CONSTRAINT fk_funcionario FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

-- Tabela Agenda
CREATE TABLE Agenda (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data DATE,
    horas VARCHAR(5),
    servico VARCHAR(15),
    valor DECIMAL(10,1),
    id_funcionario INT,
    id_usuario INT,
    id_orcamento INT,
    CONSTRAINT fk_funcionario_agenda FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    CONSTRAINT fk_usuario_agenda FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    CONSTRAINT fk_orcamento_agenda FOREIGN KEY (id_orcamento) REFERENCES Orcamento(id)
);

-- Fale Conosco
CREATE table faleConosco(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) not null,
    email VARCHAR(50) not null,
    tipo VARCHAR(50) not null,
    descricao varchar(100) not null
)