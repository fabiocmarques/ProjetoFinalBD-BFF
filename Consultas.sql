-- Consultas

-- Quanto que um funcionario gastou de julho a dezembro.
SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos
FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido
WHERE NomeFav = 'MARCIO RODRIGUES DE CARVALHO'
GROUP BY Diaria.CodFavorecido;

-- Quantas diárias estão atreladas a um órgão superior, órgão subordinado ou unidade gestora.
-- Órgão Superior
SELECT NomeOrgSup, COUNT(CodDiaria) AS Num_Diarias
FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes 
		JOIN OrgaoSubordinado ON UnidadeGestora.CodOrgSub = OrgaoSubordinado.CodOrgSub
		JOIN OrgaoSuperior ON OrgaoSubordinado.CodOrgSup = OrgaoSuperior.CodOrgSup
WHERE NomeOrgSup = 'PRESIDENCIA DA REPUBLICA'
GROUP BY OrgaoSuperior.CodOrgSup;

-- Órgão Subordinado
SELECT NomeOrgSub, COUNT(CodDiaria) AS Num_Diarias
FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes 
	JOIN OrgaoSubordinado ON UnidadeGestora.CodOrgSub = OrgaoSubordinado.CodOrgSub
WHERE NomeOrgSub = 'EMPRESA BRASIL DE COMUNICACAO'
GROUP BY OrgaoSubordinado.CodOrgSub;

-- Unidade Gestora
SELECT NomeUniGes, COUNT(CodDiaria) AS Num_Diarias
FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes 
WHERE NomeUniGes = 'SECRETARIA DE ADMINISTRACAO/PR'
GROUP BY UnidadeGestora.CodUniGes;

-- Listar diárias que um funcionário pegou.
SELECT NomeFav, Cpf, DocPagamento, GestaoPagamento, DataPagamento, ValorPagamento
FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido
WHERE NomeFav = 'MARCIO RODRIGUES DE CARVALHO'
ORDER BY NomeFav, DataPagamento;

-- Listar os favorecidos de uma determinada função ou subfunção em ordem crescente ou decrescente de gastos.
-- Função, em ordem crescente
SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos
FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido
		JOIN Funcao ON Diaria.CodFun = Funcao.CodFun
WHERE NomeFun = 'Agricultura'
GROUP BY Favorecido.CodFavorecido
ORDER BY Total_Gastos ASC;

-- Função, em ordem decrescente
SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos
FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido
		JOIN Funcao ON Diaria.CodFun = Funcao.CodFun
WHERE NomeFun = 'Agricultura'
GROUP BY Favorecido.CodFavorecido
ORDER BY Total_Gastos DESC;

-- Subfunção, em ordem crescente
SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos
FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido
		JOIN Subfuncao ON Diaria.CodSubFun = Subfuncao.CodSubFun
WHERE NomeSubFun = 'Ensino Profissional'
GROUP BY Favorecido.CodFavorecido
ORDER BY Total_Gastos ASC;

-- Subfunção, em ordem decrescente
SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos
FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido
		JOIN Subfuncao ON Diaria.CodSubFun = Subfuncao.CodSubFun
WHERE NomeSubFun = 'Ensino Profissional'
GROUP BY Favorecido.CodFavorecido
ORDER BY Total_Gastos DESC;

-- Listar todos os programas em ordem crescente ou decrescente da quantidade de diárias.
-- Em ordem crescente
SELECT programa.codprog, nomeprog, COUNT(programa.codprog) AS Num_Diarias
FROM programa
JOIN diaria ON diaria.codprog = programa.codprog
GROUP BY programa.codprog
ORDER BY Num_Diarias ASC

-- Em ordem decrescente
SELECT programa.codprog, nomeprog, COUNT(programa.codprog) AS Num_Diarias
FROM programa
JOIN diaria ON diaria.codprog = programa.codprog
GROUP BY programa.codprog
ORDER BY Num_Diarias DESC