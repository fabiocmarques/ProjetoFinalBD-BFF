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
	CodAcao int,
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
	DocPagamento text,
	CodUniGes int references UnidadeGestora(CodUniGes),
	CodSubFun int references Subfuncao(CodSubFun),
	CodFun int references Funcao(CodFun),
	CodAcao int references Acao(CodAcao),
	CodProg int references Programa(CodProg),
	CodFavorecido int references Favorecido(CodFavorecido),
	ValorPagamento real,
	GestaoPagamento int,
	DataPagamento date,
	primary key(DocPagamento)
);