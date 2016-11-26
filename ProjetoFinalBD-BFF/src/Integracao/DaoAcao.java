package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.Acao;

public class DaoAcao {
	String bd;
	String senha;
	
	
	public DaoAcao(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	public void createAcao(Acao acao){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	stmt = c.createStatement();
	        String acaoSql = "INSERT INTO ACAO (CODACAO,NOMEMACAO,LINGUAGEMCIDADA) "
	               + "VALUES (" + acao.getCodAcao() + ", " + acao.getNome() + ", " + acao.getLinguagemCidada() + ");";
	        stmt.executeUpdate(acaoSql);
	        
	     
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	}
	
	public void updateAcao(Acao acao){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE ACAO SET codacao = " + acao.getCodAcao() + ", "
	    				+ "nomeacao = " + acao.getNome() + ", "
	    				+ "ligaugemcidada = " + acao.getLinguagemCidada()
	    				+ " WHERE CodAcao = " + acao.getCodAcao() + ";";
		    stmt = c.createStatement();
		    stmt.executeQuery(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	}
	
	public Acao recuperaAcao(String codAcao){
		Acao acao = new Acao();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "SELECT * FROM ACAO WHERE CodAcao = " + acao.getCodAcao() + ";";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    acao.setCodAcao(rs.getString("codacao"));
		    acao.setLinguagemCidada(rs.getString("liguagemcidada"));
		    acao.setNome(rs.getString("nomeacao"));
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }

		return acao;
	}
	
	public void deletaAcao(Acao acao){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM ACAO WHERE CodAcao = " + acao.getCodAcao() + ";";
		    stmt = c.createStatement();
		    stmt.executeQuery(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }

	}
}