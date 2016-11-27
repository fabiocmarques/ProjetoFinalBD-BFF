package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.Favorecido;
import Negocio.Funcao;

public class DaoFuncao {
	String bd;
	String senha;
	
	
	public DaoFuncao(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	
	public void createFuncao(Funcao funcao){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	stmt = c.createStatement();
	         String favSql = "INSERT INTO FUNCAO (CODFUN, NOMEFUN) "
	               + "VALUES ('" + funcao.getCodFuncao() + "', '" + funcao.getNomeFuncao() + "');";
	         stmt.execute(favSql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public void updateFuncao(Funcao funcao){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE Funcao SET codfun = '" + funcao.getCodFuncao() + "', "
	    				+ "nomefun = '" + funcao.getNomeFuncao()
	    				+ "' WHERE codFun = " + funcao.getCodFuncao() + ";";
		    stmt = c.createStatement();
		    stmt.execute(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public Funcao recuperaFuncao(int codFun){
		Funcao funcao = new Funcao();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "SELECT * FROM FUNCAO WHERE CodFun = '" + codFun + "';";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){
			    funcao.setCodFuncao(rs.getInt("codfun"));
			    funcao.setNomeFuncao(rs.getString("nomefun"));
		    }
		    else
		    	funcao = null;
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }

		return funcao;
	}
	
	public void deletaFuncao(Funcao funcao){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM FUNCAO WHERE codFun = '" + funcao.getCodFuncao() + "';";
		    stmt = c.createStatement();
		    stmt.execute(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }

	}
}
