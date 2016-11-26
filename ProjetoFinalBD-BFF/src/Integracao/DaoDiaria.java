package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Negocio.Diaria;
import Negocio.Favorecido;
import Negocio.Programa;

public class DaoDiaria {
	public float gastoFuncionario(String nome){
		Connection c = null;
		Statement stmt = null;
		float result = 0;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos "
		    		+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
		    		+ "WHERE NomeFav = " + nome + " GROUP BY Diaria.CodFavorecido";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    result = rs.getFloat("Total_Gastos");
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	    
	    return result;
	      
	}

	public int numDiariaPorOrgaoSuperior(String nomeOrgaoSup){
		Connection c = null;
		Statement stmt = null;
		int result = 0;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT NomeOrgSup, COUNT(CodDiaria) AS Num_Diarias "
	    			+ "FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes "
	    			+ "JOIN OrgaoSubordinado ON UnidadeGestora.CodOrgSub = OrgaoSubordinado.CodOrgSub "
	    			+ "JOIN OrgaoSuperior ON OrgaoSubordinado.CodOrgSup = OrgaoSuperior.CodOrgSup "
	    			+ "WHERE NomeOrgSup =  " + nomeOrgaoSup + " GROUP BY OrgaoSuperior.CodOrgSup;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    result = rs.getInt("Num_Diarias");
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	    
	    return result;
	}
	
	public int numDiariaPorOrgaoSubordinado(String nomeOrgaoSub){
		Connection c = null;
		Statement stmt = null;
		int result = 0;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT NomeOrgSub, COUNT(CodDiaria) AS Num_Diarias "
	    			+ "FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes "
	    			+ "JOIN OrgaoSubordinado ON UnidadeGestora.CodOrgSub = OrgaoSubordinado.CodOrgSub "
	    			+ "WHERE NomeOrgSub = " + nomeOrgaoSub + " GROUP BY OrgaoSubordinado.CodOrgSub;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    result = rs.getInt("Num_Diarias");
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	    
	    return result;
	}
	
	public int numDiariaPorUnidadeGestora(String nomeUnidadeGestora){
		Connection c = null;
		Statement stmt = null;
		int result = 0;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT NomeUniGes, COUNT(CodDiaria) AS Num_Diarias "
	    			+ "FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes  "
	    			+ "WHERE NomeUniGes = " + nomeUnidadeGestora
	    			+ "GROUP BY UnidadeGestora.CodUniGes;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    result = rs.getInt("Num_Diarias");
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	    
	    return result;
	}

	public ArrayList<Diaria> DiariasConcedidas(String nomeFuncionario){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Diaria> diarias = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT NomeFav, Cpf, DocPagamento, GestaoPagamento, DataPagamento, ValorPagamento "
	    			+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
	    			+ "WHERE NomeFav = " + nomeFuncionario
	    			+ " ORDER BY NomeFav, DataPagamento;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    diarias = new ArrayList<Diaria>();
		    
		    while(rs.next()){
		    	
		    	Favorecido favorecido = new Favorecido();
		    	favorecido.setNomeFavorecido(rs.getString("NomeFav"));
		    	favorecido.setCpf(rs.getString("Cpf"));
		    	
		    	
		    	Diaria diaria = new Diaria();
		    	diaria.setValorPagamento(rs.getFloat("ValorPagamento"));
		    	diaria.setFavorecido(favorecido);
		    	diaria.setGestaoPag(rs.getInt("GestaoPagamento"));
		    	diaria.setDataPagamento(rs.getDate("DataPagamento"));
		    	diaria.setDocPagamento(rs.getString("DocPagamento"));
		    	
		    	diarias.add(diaria);
		    }
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	    
	    return diarias;
	}

	public ArrayList<Favorecido> listarFavorecidosPorFuncao(String nomeFuncao, boolean isAsc){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Favorecido> favorecidos = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos "
	    			+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
	    			+ "JOIN Funcao ON Diaria.CodFun = Funcao.CodFun "
	    			+ "WHERE NomeFun = " + nomeFuncao
	    			+ " GROUP BY Favorecido.CodFavorecido "
	    			+ "ORDER BY Total_Gastos " + (isAsc ? "ASC;" : "DESC;");
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    favorecidos = new ArrayList<Favorecido>();
		    
		    while(rs.next()){
		    	
		    	Favorecido favorecido = new Favorecido();
		    	favorecido.setNomeFavorecido(rs.getString("NomeFav"));
		    	favorecido.setCpf(rs.getString("Cpf"));
		    	
		    	favorecidos.add(favorecido);
		    }
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
		
		return favorecidos;
	}
	

	public ArrayList<Favorecido> listarFavorecidosPorSubFuncao(String nomeSubFuncao, boolean isAsc){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Favorecido> favorecidos = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos "
	    			+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
	    			+ "JOIN Subfuncao ON Diaria.CodSubFun = Subfuncao.CodSubFun "
	    			+ "WHERE NomeSubFun = " + nomeSubFuncao
	    			+ " GROUP BY Favorecido.CodFavorecido "
	    			+ "ORDER BY Total_Gastos " + (isAsc ? "ASC;" : "DESC;");
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    favorecidos = new ArrayList<Favorecido>();
		    
		    while(rs.next()){
		    	
		    	Favorecido favorecido = new Favorecido();
		    	favorecido.setNomeFavorecido(rs.getString("NomeFav"));
		    	favorecido.setCpf(rs.getString("Cpf"));
		    	
		    	favorecidos.add(favorecido);
		    }
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
		
		return favorecidos;
	}
	
	public ArrayList<Programa> listarProgramasPorDiarias(boolean isAsc){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Programa> programas = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT programa.codprog, nomeprog, COUNT(programa.codprog) AS Num_Diarias "
	    			+ "FROM programa "
	    			+ "JOIN diaria ON diaria.codprog = programa.codprog "
	    			+ "GROUP BY programa.codprog "
	    			+ "ORDER BY Num_Diarias " +  (isAsc ? "ASC;" : "DESC;");
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    programas = new ArrayList<Programa>();
		    
		    while(rs.next()){
		    	
		    	Programa programa = new Programa();
		    	programa.setCodProg(rs.getInt("codprog"));
		    	programa.setNomeProg(rs.getString("nomeprog"));
		    	
		    	programas.add(programa);
		    }
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
		
		return programas;
	}

	public void createDiaria(Diaria diaria){
		Connection c = null;
	      Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/Diarias",
	            "postgres", "senha123");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");

	         stmt = c.createStatement();
	         String acaoSql = "INSERT INTO ACAO (CODACAO,NOMEMACAO,LINGUAGEMCIDADA) "
	               + "VALUES (" + diaria.getAcao().getCodAcao() + ", " + diaria.getAcao().getNome() + ", " + diaria.getAcao().getLinguagemCidada() + ");";
	         stmt.executeUpdate(acaoSql);
	         
	         stmt = c.createStatement();
	         String favSql = "INSERT INTO FAVORECIDO (CODFAVORECIDO, CPF, NOMEFAV) "
	               + "VALUES (" + diaria.getFavorecido().getCodFavorecido() + ", " + diaria.getFavorecido().getCpf() + ", " + diaria.getFavorecido().getNomeFavorecido() + ");";
	         stmt.executeUpdate(favSql);
	         
	         stmt = c.createStatement();
	         String progSql = "INSERT INTO PROGRAMA (CODPROG, NOMEPROG) "
	               + "VALUES (" + diaria.getPrograma().getCodProg() + ", " + diaria.getPrograma().getNomeProg() + ");";
	         stmt.executeUpdate(progSql);
	         
	         stmt = c.createStatement();
	         String funSql = "INSERT INTO FUNCAO (CODFUN, NOMEFUN) "
		               + "VALUES (" + diaria.getFuncao().getCodFuncao() + ", " + diaria.getFuncao().getNomeFuncao() + ");";
	         stmt.executeUpdate(funSql);
	         
	         stmt = c.createStatement();
	         String subFunSql = "INSERT INTO SUBFUNCAO (CODSUBFUN, NOMESUBFUN) "
		               + "VALUES (" + diaria.getSubFuncao().getCodSubFun() + ", " + diaria.getSubFuncao().getNomeSubFun() + ");"; 
	         stmt.executeUpdate(subFunSql);
	         
	         stmt = c.createStatement();
	         String orgSupSql = "INSERT INTO ORGAOSUPERIOR (CODORGSUP, NOMEORGSUP) "
		               + "VALUES (" + diaria.getGestor().getOrgaoSub().getOrgSup().getCodOrgSup() + ", " + diaria.getGestor().getOrgaoSub().getOrgSup().getNomeOrgSup() + ");";
	         stmt.executeUpdate(orgSupSql);
	         
	         stmt = c.createStatement();
	         String orgSubSql = "INSERT INTO ORGAOSUBORDINADO (CODORGSUB, CODORGSUP, NOMEORGSUB) "
		               + "VALUES (" + diaria.getGestor().getOrgaoSub().getCodOrgaoSub() + ", " + diaria.getGestor().getOrgaoSub().getOrgSup().getCodOrgSup() + ", " + diaria.getGestor().getOrgaoSub().getNomeOrgaoSub() + ");";
	         stmt.executeUpdate(orgSubSql);
	         
	         stmt = c.createStatement();
	         String gestorSql = "INSERT INTO UNIDADEGESTORA (CODUNIGES, CODORGSUB, NOMEUNIGES) "
		               + "VALUES (" + diaria.getGestor().getCodUniGes() + ", " + diaria.getGestor().getOrgaoSub().getCodOrgaoSub() + ", " + diaria.getGestor().getNomeUnidadeGestora() + ");";
	         stmt.executeUpdate(gestorSql);
	         
	         stmt = c.createStatement();
	         String diariaSql = "INSERT INTO DIARIA (CODDIARIA, DOCPAGAMENTO, CODUNIGES, CODSUBFUN, CODFUN, CODACAO, CODPROG, CODFAVORECIDO, VALORPAGAMENTO, GESTAOPAGAMENTO, DATAPAGAMENTO) "
		               + "VALUES (" + diaria.getCodDiaria() + ", "  + diaria.getDocPagamento() + ", "  + diaria.getGestor().getCodUniGes() + ", "  + diaria.getSubFuncao().getCodSubFun() + ", "  
		               + diaria.getFuncao().getCodFuncao() +  ", "  + diaria.getAcao().getCodAcao()  + ", "  + diaria.getPrograma().getCodProg() +  ", "  + diaria.getFavorecido().getCodFavorecido() + ", "  
		               + diaria.getValorPagamento() + ", "  + diaria.getGestaoPag() + ", "  + diaria.getDataPagamento() +  ");";
	         stmt.executeUpdate(diariaSql);
	        
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Records created successfully");
	   }
	
	public void deleteDiaria(Diaria diaria){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String sql = "DELETE FROM DIARIA WHERE CODDIARIA = " + diaria.getCodDiaria() + ";";
		    stmt = c.createStatement();
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }

	}

	public Diaria recuperaDiaria(int codDiaria){
		Diaria diaria = new Diaria();
		Connection c = null;
		Statement stmt = null;
		float result = 0;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos "
		    		+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
		    		+ "WHERE NomeFav = " + nome + " GROUP BY Diaria.CodFavorecido";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    result = rs.getFloat("Total_Gastos");
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
		
		
		return diaria;
	}
	
	public void updateDiaria(Diaria diaria){
		Connection c = null;
		Statement stmt = null;
		float result = 0;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String query = "UPDATE COMPANY set SALARY = 25000.00 where ID=1;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    result = rs.getFloat("Total_Gastos");
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	    
	}

}






















