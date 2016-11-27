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

-- Procedure (usada como início do ETL)
CREATE OR REPLACE FUNCTION preenche_tab_temporaria(csvpath text) RETURNS void AS $body$
	BEGIN
    	IF csvpath IS NULL OR csvpath = '' THEN
        	RAISE EXCEPTION 'O path do CSV precisa ser diferente de NULL';
        ELSE
        	EXECUTE format($$COPY TabelaTemporaria(CodOrgSup, NomeOrgSup, CodOrgSub, NomeOrgSub, CodUniGes, NomeUniGes, CodFun, NomeFun, CodSubFun, NomeSubFun, CodProg, NomeProg, CodAcao, NomeAcao, LinguagemCidada, Cpf, NomeFav, DocPagamento, GestaoPagamento, DataPagamento, ValorPagamento)
							FROM %L
							WITH DELIMITER E'\t' CSV HEADER
							ENCODING 'win1252'$$, csvpath);
		END IF;
	END;
$body$ LANGUAGE plpgsql;

SELECT preenche_tab_temporaria('E:\\Dropbox\\UnB\\Semestre_6\\Bancos_de_Dados\\Projeto\\ProjetoFinalBD-BFF\\201412_Diarias.csv');