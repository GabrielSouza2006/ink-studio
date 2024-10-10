-- Apagar e recriar o banco de dados
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'INKStudioTattoo')
    DROP DATABASE INKStudioTattoo;
GO

CREATE DATABASE INKStudioTattoo;
GO

-- Usar o banco de dados
USE INKStudioTattoo;
GO

-- Tabela Usuario
CREATE TABLE Usuario (
    id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    email NVARCHAR(50) NOT NULL,
    senha NVARCHAR(20) NOT NULL,
    telefone NVARCHAR(15) NOT NULL,
    nascimento NVARCHAR(15),
    nome NVARCHAR(60) NOT NULL,
    cpf NVARCHAR(20),
    fotoPerfil VARBINARY(MAX) NULL,
    statusUsuario NVARCHAR(10) NOT NULL DEFAULT 'ATIVO'
);
GO

-- Tabela Funcionario
CREATE TABLE Funcionario (
    id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    nome NVARCHAR(50) NOT NULL,
    nascimento NVARCHAR(15) NOT NULL,
    descricao NVARCHAR(1000) NOT NULL,
    servico NVARCHAR(20) NOT NULL,
    cpf NVARCHAR(20) NOT NULL,
    email NVARCHAR(50) NOT NULL,
    telefone NVARCHAR(15) NOT NULL,
    senha NVARCHAR(20) NOT NULL,
    fotoPerfil VARBINARY(MAX) NULL,
    fotoTattoo VARBINARY(MAX) NULL,
    statusUsuario NVARCHAR(10) NOT NULL DEFAULT 'ATIVO'
);
GO

-- Tabela Orcamento
CREATE TABLE Orcamento (
    id INT PRIMARY KEY IDENTITY(1,1),
    cor NVARCHAR(10),
    horas NVARCHAR(5),
    valor DECIMAL(10, 1),
    data DATE,
    id_funcionario INT,
    id_usuario INT,
    statusOrcamento NVARCHAR(10) NOT NULL DEFAULT 'ESPERANDO',
    CONSTRAINT fk_funcionario FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    CONSTRAINT fk_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);
GO

-- Tabela Agenda
CREATE TABLE Agenda (
    id INT PRIMARY KEY IDENTITY(1,1),
    data DATE,
    horas NVARCHAR(5),
    servico NVARCHAR(15),
    valor DECIMAL(10, 1),
    id_funcionario INT,
    id_usuario INT,
    id_orcamento INT,
    CONSTRAINT fk_funcionario_agenda FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id),
    CONSTRAINT fk_usuario_agenda FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    CONSTRAINT fk_orcamento_agenda FOREIGN KEY (id_orcamento) REFERENCES Orcamento(id)
);
GO

-- Tabela Fale Conosco
CREATE TABLE faleConosco(
    id INT PRIMARY KEY IDENTITY(1,1),
    nome NVARCHAR(50) NOT NULL,
    email NVARCHAR(50) NOT NULL,
    tipo NVARCHAR(50) NOT NULL,
    descricao NVARCHAR(100) NOT NULL
);
GO
