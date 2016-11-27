-- O início do ETL está no fim do PTV.sql

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
    SELECT DISTINCT ON (Cpf, NomeFav) NULLIF(Cpf, 'Detalhamento das informações não disponível.')::varchar(14), NomeFav
    FROM TabelaTemporaria
    WHERE NomeFav != 'Detalhamento das informações não disponível.'
    ORDER BY (Cpf);

INSERT INTO Diaria (DocPagamento, CodUniGes, CodSubFun, CodFun, CodAcao, CodProg, CodFavorecido, ValorPagamento, GestaoPagamento, DataPagamento)
    SELECT DocPagamento,
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