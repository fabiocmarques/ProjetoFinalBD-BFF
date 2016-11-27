package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JDialog;

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
	        String acaoSql = "INSERT INTO ACAO (CODACAO,NOMEACAO,LINGUAGEMCIDADA) "
	               + "VALUES ('" + acao.getCodAcao() + "', '" + acao.getNome() + "', '" + acao.getLinguagemCidada() + "');";
	        stmt.executeUpdate(acaoSql);    
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  
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
	    	
	    	String sql = "UPDATE ACAO SET codacao = '" + acao.getCodAcao() + "', "
	    				+ "nomeacao = '" + acao.getNome() + "', "
	    				+ "ligaugemcidada = '" + acao.getLinguagemCidada()
	    				+ "' WHERE CodAcao = '" + acao.getCodAcao() + "';";
		    stmt = c.createStatement();
		    stmt.execute(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	
	    	String sql = "SELECT * FROM ACAO WHERE CodAcao = '" + codAcao + "';";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    if(rs.next()){
		    	acao.setCodAcao(rs.getString("codacao"));
			    acao.setLinguagemCidada(rs.getString("linguagemcidada"));
			    acao.setNome(rs.getString("nomeacao"));
		    }
		    else{
		    	acao = null;
		    }
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
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
	    	
	    	String sql = "DELETE FROM ACAO WHERE codacao = '" + acao.getCodAcao() + "';";
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