package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Negocio.Acao;
import Negocio.Diaria;
import Negocio.Favorecido;
import Negocio.Funcao;
import Negocio.OrgaoSub;
import Negocio.OrgaoSup;
import Negocio.Programa;
import Negocio.SubFuncao;
import Negocio.UnidadeGestora;

public class DaoDiaria {
	String bd;
	String senha;
	
	
	public DaoDiaria(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	public float gastoFuncionario(String nome){
		Connection c = null;
		Statement stmt = null;
		float result = 0;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String query = "SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos "
		    		+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
		    		+ "WHERE NomeFav = '" + nome + "' GROUP BY favorecido.CodFavorecido";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    if(rs.next()){
		    	result = rs.getFloat("Total_Gastos");
		    }
		    else
		    	result = -1;
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String query = "SELECT NomeOrgSup, COUNT(CodDiaria) AS Num_Diarias "
	    			+ "FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes "
	    			+ "JOIN OrgaoSubordinado ON UnidadeGestora.CodOrgSub = OrgaoSubordinado.CodOrgSub "
	    			+ "JOIN OrgaoSuperior ON OrgaoSubordinado.CodOrgSup = OrgaoSuperior.CodOrgSup "
	    			+ "WHERE NomeOrgSup =  '" + nomeOrgaoSup + "' GROUP BY OrgaoSuperior.CodOrgSup;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    if(rs.next())
		    	result = rs.getInt("Num_Diarias");
		    else
		    	result = -1;
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String query = "SELECT NomeOrgSub, COUNT(CodDiaria) AS Num_Diarias "
	    			+ "FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes "
	    			+ "JOIN OrgaoSubordinado ON UnidadeGestora.CodOrgSub = OrgaoSubordinado.CodOrgSub "
	    			+ "WHERE NomeOrgSub = '" + nomeOrgaoSub + "' GROUP BY OrgaoSubordinado.CodOrgSub;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    if(rs.next())
		    	result = rs.getInt("Num_Diarias");
		    else 
		    	result = -1;
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String query = "SELECT NomeUniGes, COUNT(CodDiaria) AS Num_Diarias "
	    			+ "FROM Diaria JOIN UnidadeGestora ON Diaria.CodUniGes = UnidadeGestora.CodUniGes  "
	    			+ "WHERE NomeUniGes = '" + nomeUnidadeGestora
	    			+ "' GROUP BY UnidadeGestora.CodUniGes;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    if(rs.next())
		    	result = rs.getInt("Num_Diarias");
		    else 
		    	result = -1;
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String query = "SELECT NomeFav, Cpf, DocPagamento, GestaoPagamento, DataPagamento, ValorPagamento "
	    			+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
	    			+ "WHERE NomeFav = '" + nomeFuncionario
	    			+ "' ORDER BY NomeFav, DataPagamento;";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    diarias = new ArrayList<Diaria>();
		    
		    while(rs.next()){
		    	
		    	Favorecido favorecido = new Favorecido();
		    	favorecido.setNomeFavorecido(rs.getString("NomeFav"));
		    	favorecido.setCpf(rs.getString("Cpf"));
		    	
		    	
		    	Diaria diaria = new Diaria();
		    	diaria.setValorPagamento(rs.getFloat("valoragamento"));
		    	diaria.setFavorecido(favorecido);
		    	diaria.setGestaoPag(rs.getInt("gestaopagamento"));
		    	diaria.setDataPagamento(rs.getDate("datapagamento"));
		    	diaria.setDocPagamento(rs.getString("docpagamento"));
		    	
		    	diarias.add(diaria);
		    }
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String query = "SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos "
	    			+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
	    			+ "JOIN Funcao ON Diaria.CodFun = Funcao.CodFun "
	    			+ "WHERE NomeFun = '" + nomeFuncao
	    			+ "' GROUP BY Favorecido.CodFavorecido "
	    			+ "ORDER BY Total_Gastos " + (isAsc ? "ASC;" : "DESC;");
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    favorecidos = new ArrayList<Favorecido>();
		    
		    while(rs.next()){
		    	
		    	Favorecido favorecido = new Favorecido();
		    	favorecido.setNomeFavorecido(rs.getString("nomefav"));
		    	favorecido.setCpf(rs.getString("cpf"));
		    	
		    	favorecidos.add(favorecido);
		    }
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String query = "SELECT NomeFav, Cpf, SUM(ValorPagamento) AS Total_Gastos "
	    			+ "FROM Favorecido JOIN Diaria ON Favorecido.CodFavorecido = Diaria.CodFavorecido "
	    			+ "JOIN Subfuncao ON Diaria.CodSubFun = Subfuncao.CodSubFun "
	    			+ "WHERE NomeSubFun = '" + nomeSubFuncao
	    			+ "' GROUP BY Favorecido.CodFavorecido "
	    			+ "ORDER BY Total_Gastos " + (isAsc ? "ASC;" : "DESC;");
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    favorecidos = new ArrayList<Favorecido>();
		    
		    while(rs.next()){
		    	
		    	Favorecido favorecido = new Favorecido();
		    	favorecido.setNomeFavorecido(rs.getString("nomefav"));
		    	favorecido.setCpf(rs.getString("cpf"));
		    	
		    	favorecidos.add(favorecido);
		    }
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
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
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	            "postgres", senha);
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         stmt = c.createStatement();
	         String diariaSql = "INSERT INTO DIARIA (DOCPAGAMENTO, CODUNIGES, CODSUBFUN, CODFUN, CODACAO, CODPROG, CODFAVORECIDO, VALORPAGAMENTO, GESTAOPAGAMENTO, DATAPAGAMENTO) "
		               + "VALUES ('" + diaria.getDocPagamento() + "', '"  + diaria.getGestor().getCodUniGes() + "', '"  + diaria.getSubFuncao().getCodSubFun() + "', '"  
		               + diaria.getFuncao().getCodFuncao() +  "', '"  + diaria.getAcao().getCodAcao()  + "', '"  + diaria.getPrograma().getCodProg() +  "', '"  + diaria.getFavorecido().getCodFavorecido() + "', '"  
		               + diaria.getValorPagamento() + "', '"  + diaria.getGestaoPag() + "', '"  + diaria.getDataPagamento() +  "');";
	         stmt.executeUpdate(diariaSql);
	        
	         stmt.close();
	         c.commit();
	         c.close();
	      } catch (Exception e) {
	         System.out.println( e.getClass().getName()+": "+ e.getMessage() );
	         
	      }
	      System.out.println("Records created successfully");
	   }
	
	public void deleteDiaria(Diaria diaria){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM DIARIA WHERE CODDIARIA = '" + diaria.getCodDiaria() + "';";
		    stmt = c.createStatement();
		    stmt.execute(sql);		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String query = "SELECT codDiaria, docPagamento, valorPagamento, gestaoPagamento, datapagamento, acao.*, favorecido.*, programa.*, funcao.*, subfuncao.*, unidadeGestora.coduniges, unidadeGestora.nomeuniges, orgaoSubordinado.codOrgSub, orgaoSubordinado.nomeOrgSub, orgaoSuperior.* FROM diaria  "
	    			+ "JOIN acao ON diaria.codAcao = acao.codAcao "
	    			+ "JOIN favorecido ON diaria.codFavorecido = favorecido.codfavorecido "
	    			+ "JOIN programa ON diaria.codprog = programa.codprog "
	    			+ "JOIN funcao ON diaria.codFun = funcao.codFun "
	    			+ "JOIN subfuncao ON diaria.codSubFun = subfuncao.codSubFun "
	    			+ "JOIN unidadeGestora ON diaria.codUniGes = unidadeGestora.codUniGes "
	    			+ "JOIN orgaoSubordinado ON orgaoSubordinado.codOrgSub = unidadeGestora.codOrgSub "
	    			+ "JOIN orgaoSuperior ON orgaoSuperior.codOrgSup = orgaoSubordinado.codOrgSup "
	    			+ "WHERE codDiaria = '" + codDiaria + "';";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		    
		    if(rs.next()){
			    
			    diaria.setCodDiaria(rs.getInt("coddiaria"));
			    diaria.setDocPagamento(rs.getString("docpagamento"));
			    diaria.setValorPagamento(rs.getFloat("valorpagamento"));
			    diaria.setGestaoPag(rs.getInt("gestaopagamento"));
			    diaria.setDataPagamento(rs.getDate("datapagamento"));
			    
			    Acao acao = new Acao();
			    
			    acao.setCodAcao(rs.getString("codacao"));
			    acao.setLinguagemCidada(rs.getString("linguagemcidada"));
			    acao.setNome(rs.getString("nomeacao"));
			    diaria.setAcao(acao);
			    
			    Favorecido favorecido = new Favorecido();
			    
			    favorecido.setCodFavorecido(rs.getInt("codfavorecido"));
			    favorecido.setCpf(rs.getString("cpf"));
			    favorecido.setNomeFavorecido(rs.getString("nomefav"));
			    diaria.setFavorecido(favorecido);
			    
			    Programa programa = new Programa();
			    
			    programa.setCodProg(rs.getInt("codprog"));
			    programa.setNomeProg(rs.getString("nomeprog"));
			    diaria.setPrograma(programa);
			    
			    Funcao funcao = new Funcao();
			    
			    funcao.setCodFuncao(rs.getInt("codfun"));
			    funcao.setNomeFuncao(rs.getString("nomefun"));
			    diaria.setFuncao(funcao);
			    
			    SubFuncao subFuncao = new SubFuncao();
			    
			    subFuncao.setCodSubFun(rs.getInt("codsubfun"));
			    subFuncao.setNomeSubFun(rs.getString("nomesubfun"));
			    diaria.setSubFuncao(subFuncao);
			    
			    UnidadeGestora unidadeGestora = new UnidadeGestora();
			    
			    unidadeGestora.setCodUniGes(rs.getInt("coduniges"));
			    unidadeGestora.setNomeUnidadeGestora(rs.getString("nomeuniges"));
			    diaria.setGestor(unidadeGestora);
			    
			    OrgaoSub orgaoSub = new OrgaoSub();
			    
			    orgaoSub.setCodOrgaoSub(rs.getInt("codOrgSub"));
			    orgaoSub.setNomeOrgaoSub(rs.getString("nomeorgsub"));
			    unidadeGestora.setOrgaoSub(orgaoSub);
			    
			    OrgaoSup orgaoSup = new OrgaoSup();
			    
			    orgaoSup.setCodOrgSup(rs.getInt("codorgsup"));
			    orgaoSup.setNomeOrgSup(rs.getString("nomeorgsup"));
			    orgaoSub.setOrgSup(orgaoSup);
		    }
		    else
		    	diaria = null;
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE Diaria set "
	    					+ "codAcao = '" + diaria.getAcao().getCodAcao() + "', "
	    					+ "codFav = '" + diaria.getFavorecido().getCodFavorecido() + "', "
	    					+ "codProg = '" + diaria.getPrograma().getCodProg() + "', "
	    					+ "codFun = '" + diaria.getFuncao().getCodFuncao() + "', "
	    					+ "codSubFun = '" + diaria.getSubFuncao().getCodSubFun() + "', "
	    					+ "codUniGes = '" + diaria.getGestor().getCodUniGes() + "', "
	    					+ "docPagamento = '" + diaria.getDocPagamento() + "', "
	    					+ "valorPagamento = '" + diaria.getValorPagamento() + "', "
	    					+ "gestaoPagamento = '" + diaria.getGestaoPag() + "', "
	    					+ "dataPagamento = '" + diaria.getDataPagamento()
	    					+ "' where ID ='" + diaria.getCodDiaria() +  "';";
	    	
		    stmt = c.createStatement();
		    stmt.execute(sql);
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	    
	}

}






















