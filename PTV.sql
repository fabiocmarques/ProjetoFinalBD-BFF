-- View
CREATE VIEW Dados_Uteis_View AS
	SELECT NomeOrgSup, NomeOrgSub, NomeUniGes, NomeFun, NomeSubFun, NomeAcao, NomeProg, NomeFav, Cpf, DocPagamento, DataPagamento, ValorPagamento FROM 
    	((((((((Diaria NATURAL JOIN UnidadeGestora) NATURAL JOIN OrgaoSubordinado) NATURAL JOIN OrgaoSuperior) NATURAL JOIN SubFuncao) NATURAL JOIN Funcao) 
        NATURAL JOIN Acao) NATURAL JOIN Programa) NATURAL JOIN Favorecido);
    
-- DROP VIEW Dados_Uteis_View;

-- Trigger
INSERT INTO Diaria (CodDiaria, DocPagamento, ValorPagamento) values(0, 'Total', 0);

CREATE OR REPLACE FUNCTION processa_diarias_gatilho() RETURNS trigger AS $diarias_gatilho$
	BEGIN
    	IF NEW.ValorPagamento <= 0 THEN 
        	RAISE EXCEPTION 'O valor do pagamento precisa ser maior que zero';
        	RETURN NEW;
		END IF;
		IF (TG_OP = 'DELETE') THEN
			UPDATE Diaria SET ValorPagamento = ValorPagamento - OLD.ValorPagamento WHERE CodDiaria = 0;
			RETURN OLD;
		ELSIF (TG_OP = 'UPDATE' AND NEW.CodDiaria != 0) THEN -- NEW.CodDiaria != 0 evita o recursive trigger
			UPDATE Diaria SET ValorPagamento = ValorPagamento - OLD.ValorPagamento + NEW.ValorPagamento WHERE CodDiaria = 0;
			RETURN NEW;
		ELSEIF (TG_OP = 'INSERT') THEN
			UPDATE Diaria SET ValorPagamento = ValorPagamento + NEW.ValorPagamento WHERE CodDiaria = 0;
			RETURN NEW;
		END IF;
		RETURN NEW;
	END;
$diarias_gatilho$ LANGUAGE plpgsql;

CREATE TRIGGER diarias_gatilho AFTER INSERT OR UPDATE OR DELETE ON Diaria
	FOR EACH ROW EXECUTE PROCEDURE processa_diarias_gatilho();
    
-- DROP TRIGGER diarias_gatilho ON Diaria;

-- Procedure (que, dada uma condição, roda o ETL)
CREATE OR REPLACE FUNCTION preenche_tab_temporaria(csvpath text) RETURNS void AS $body$
	BEGIN
    	IF csvpath IS NULL OR csvpath = '' THEN
        	RAISE EXCEPTION 'O path do CSV precisa ser diferente de NULL';
        ELSE
        	EXECUTE format(
                $ETL$
                COPY TabelaTemporaria(CodOrgSup, NomeOrgSup, CodOrgSub, NomeOrgSub, CodUniGes, NomeUniGes, CodFun, NomeFun, CodSubFun, NomeSubFun, CodProg, NomeProg, CodAcao, NomeAcao, LinguagemCidada, Cpf, NomeFav, DocPagamento, GestaoPagamento, DataPagamento, ValorPagamento)
				FROM %L
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
                $ETL$, csvpath); -- valor substituido na linha 46, no %L
		END IF;
	END;
$body$ LANGUAGE plpgsql;

SELECT preenche_tab_temporaria('E:\\Dropbox\\UnB\\Semestre_6\\Bancos_de_Dados\\Projeto\\ProjetoFinalBD-BFF\\201412_Diarias.csv');