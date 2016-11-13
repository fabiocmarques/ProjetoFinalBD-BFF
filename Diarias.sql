DROP TABLE IF EXISTS Diaria;
DROP TABLE IF EXISTS UnidadeGestora;
DROP TABLE IF EXISTS OrgaoSubordinado;
DROP TABLE IF EXISTS OrgaoSuperior;
DROP TABLE IF EXISTS Subfuncao;
DROP TABLE IF EXISTS Funcao;
DROP TABLE IF EXISTS Acao;
DROP TABLE IF EXISTS Programa;
DROP TABLE IF EXISTS Favorecido;
DROP TABLE IF EXISTS TabelaTemporaria;

create table OrgaoSuperior(
	CodOrgSup int,
	NomeOrgSup text,
	primary key(CodOrgSup)
	);

create table OrgaoSubordinado(
	CodOrgSub int,
	CodOrgSup int references OrgaoSuperior(CodOrgSup),
	NomeOrgSub text,
	primary key(CodOrgSub)
);

create table UnidadeGestora(
	CodUniGes int,
	CodOrgSub int references OrgaoSubordinado(CodOrgSub),
	NomeUniGes text,
	primary key(CodUniGes)
);

create table Subfuncao(
	CodSubFun int,
	NomeSubFun text,
	primary key(CodSubFun)
);

create table Funcao(
	CodFun int,
	NomeFun text,
	primary key(CodFun)
);

create table Acao(
	CodAcao varchar(4),
	NomeAcao text,
	LinguagemCidada text,
	primary key(CodAcao)
);

create table Programa(
	CodProg int,
	NomeProg text,
	primary key(CodProg)
);

create table Favorecido(
	CodFavorecido serial,
	Cpf varchar(14),
	NomeFav text,
	primary key(CodFavorecido)
);

create table Diaria(
    CodDiaria serial,
	DocPagamento text,
	CodUniGes int references UnidadeGestora(CodUniGes),
	CodSubFun int references Subfuncao(CodSubFun),
	CodFun int references Funcao(CodFun),
	CodAcao varchar(4) references Acao(CodAcao),
	CodProg int references Programa(CodProg),
	CodFavorecido int references Favorecido(CodFavorecido),
	ValorPagamento real,
	GestaoPagamento int,
	DataPagamento date,
	primary key(CodDiaria)
);

create table TabelaTemporaria(
	CodOrgSup text,
	NomeOrgSup text,
	CodOrgSub text,
	NomeOrgSub text,
	CodUniGes text,
	NomeUniGes text,
	CodFun text,
	NomeFun text,
	CodSubFun text,
	NomeSubFun text,
	CodProg text,
	NomeProg text,
	CodAcao text,
	NomeAcao text,
	LinguagemCidada text,
	Cpf text,
	NomeFav text,
	DocPagamento text,
	GestaoPagamento text,
	DataPagamento text,
	ValorPagamento text,
    CodTemporaria serial,
	primary key(CodTemporaria)
);

-- É preciso mudar o path para cada computador
COPY TabelaTemporaria(CodOrgSup, NomeOrgSup, CodOrgSub, NomeOrgSub, CodUniGes, NomeUniGes, CodFun, NomeFun, CodSubFun, NomeSubFun, CodProg, NomeProg, CodAcao, NomeAcao, LinguagemCidada, Cpf, NomeFav, DocPagamento, GestaoPagamento, DataPagamento, ValorPagamento)
FROM 'E:\\Dropbox\\UnB\\Semestre_6\\Bancos_de_Dados\\Projeto\\ProjetoFinalBD-BFF\\201412_Diarias.csv'
WITH DELIMITER E'\t' CSV HEADER
ENCODING 'win1252';

INSERT INTO OrgaoSuperior (CodOrgSup, NomeOrgSup)
	SELECT DISTINCT ON (CodOrgSup) NULLIF(CodOrgSup, 'Detalhamento das informações não disponível.')::int, NomeOrgSup
    FROM TabelaTemporaria
    WHERE NomeOrgSup != 'Detalhamento das informações não disponível.'
    ORDER BY (CodOrgSup);

INSERT INTO OrgaoSubordinado (CodOrgSub, CodOrgSup, NomeOrgSub)
	SELECT DISTINCT ON (CodOrgSub) NULLIF(CodOrgSub, 'Detalhamento das informações não disponível.')::int,
    	NULLIF(CodOrgSup, 'Detalhamento das informações não disponível.')::int, NomeOrgSub
    FROM TabelaTemporaria
    WHERE NomeOrgSub != 'Detalhamento das informações não disponível.'
    ORDER BY (CodOrgSub);
    
INSERT INTO UnidadeGestora (CodUniGes, CodOrgSub, NomeUniGes)
	SELECT DISTINCT ON (CodUniGes) NULLIF(CodUniGes, 'Detalhamento das informações não disponível.')::int,
    	NULLIF(CodOrgSub, 'Detalhamento das informações não disponível.')::int, NomeUniGes
    FROM TabelaTemporaria
    WHERE NomeUniGes != 'Detalhamento das informações não disponível.'
    ORDER BY (CodUniGes);
    
INSERT INTO Funcao (CodFun, NomeFun)
	SELECT DISTINCT ON (CodFun) NULLIF(CodFun, 'Detalhamento das informações não disponível.')::int, NomeFun
    FROM TabelaTemporaria
    WHERE NomeFun != 'Detalhamento das informações não disponível.'
    ORDER BY (CodFun);
    
INSERT INTO Subfuncao (CodSubFun, NomeSubFun)
	SELECT DISTINCT ON (CodSubFun) NULLIF(CodSubFun, 'Detalhamento das informações não disponível.')::int, NomeSubFun
    FROM TabelaTemporaria
    WHERE NomeSubFun != 'Detalhamento das informações não disponível.'
    ORDER BY (CodSubFun);
    
INSERT INTO Programa (CodProg, NomeProg)
	SELECT DISTINCT ON (CodProg) NULLIF(CodProg, 'Detalhamento das informações não disponível.')::int, NomeProg
    FROM TabelaTemporaria
    WHERE NomeProg != 'Detalhamento das informações não disponível.'
    ORDER BY (CodProg);

INSERT INTO Acao (CodAcao, NomeAcao, LinguagemCidada)
	SELECT DISTINCT ON (CodAcao) NULLIF(CodAcao, 'Detalhamento das informações não disponível.')::varchar(4), NomeAcao, LinguagemCidada
    FROM TabelaTemporaria
    WHERE NomeAcao != 'Detalhamento das informações não disponível.'
    ORDER BY (CodAcao);
    
INSERT INTO Favorecido (Cpf, NomeFav)
	SELECT DISTINCT ON (Cpf) NULLIF(Cpf, 'Detalhamento das informações não disponível.')::varchar(14), NomeFav
    FROM TabelaTemporaria
    WHERE NomeFav != 'Detalhamento das informações não disponível.'
    ORDER BY (Cpf);

INSERT INTO Diaria (DocPagamento, CodUniGes, CodSubFun, CodFun, CodAcao, CodProg, CodFavorecido, ValorPagamento, GestaoPagamento, DataPagamento)
	SELECT DISTINCT ON (DocPagamento) DocPagamento,
    	NULLIF(CodUniGes, 'Detalhamento das informações não disponível.')::int,
        NULLIF(CodSubFun, 'Detalhamento das informações não disponível.')::int,
        NULLIF(CodFun, 'Detalhamento das informações não disponível.')::int,
        NULLIF(CodAcao, 'Detalhamento das informações não disponível.')::varchar(4),
        NULLIF(CodProg, 'Detalhamento das informações não disponível.')::int,
        CodFavorecido,
    	NULLIF(regexp_replace(ValorPagamento, ',', '.'), 'Detalhamento das informações não disponível.')::real,
    	NULLIF(GestaoPagamento, 'Detalhamento das informações não disponível.')::int,
    	NULLIF(DataPagamento, 'Detalhamento das informações não disponível.')::date
    FROM TabelaTemporaria JOIN Favorecido ON (TabelaTemporaria.Cpf = Favorecido.Cpf AND TabelaTemporaria.NomeFav = Favorecido.NomeFav)
    WHERE DocPagamento != 'Detalhamento das informações não disponível.'
    ORDER BY (DocPagamento);
    
-- Depois de popular todas as tabelas, TabelaTemporaria pode ser excluída
DROP TABLE TabelaTemporaria;