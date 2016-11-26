package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.SubFuncao;

public class DaoSubfuncao {
	public void createSubfuncao(SubFuncao subf){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	stmt = c.createStatement();
	        String subfSql = "INSERT INTO subfuncao (codsubfun, nomesubfun) "
	               + "VALUES (" + subf.getCodSubFun() + ", " + subf.getNomeSubFun() + ");";
	        stmt.executeUpdate(subfSql);     
	     
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	}
	
	public void updateSubfuncao(SubFuncao subf){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", "senha123");
	    	
	    	String sql = "UPDATE subfuncao SET codsubfun = " + subf.getCodSubFun() + ", "
	    				+ "nomesubfun = " + subf.getNomeSubFun() + ", "
	    				+ " WHERE codsubfun = " + subf.getCodSubFun() + ";";
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
	
	public SubFuncao recuperaSubfuncao(String codSubf){
		SubFuncao sub = new SubFuncao();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String sql = "SELECT * FROM subfuncao WHERE Codsubfuncao = " + codSubf + ";";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    sub.setCodSubFun(rs.getInt("codsubfuncao"));
		    sub.setNomeSubFun(rs.getString("nomesubfun"));
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }

		return sub;
	}
	
	public void deletaSubfuncao(SubFuncao subf){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String sql = "DELETE FROM ACAO WHERE CodAcao = " + subf.getCodSubFun() + ";";
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
