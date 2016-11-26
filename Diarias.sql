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