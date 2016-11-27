package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.SubFuncao;

public class DaoSubfuncao {
	String bd;
	String senha;
	
	
	public DaoSubfuncao(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	public void createSubfuncao(SubFuncao subf){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	stmt = c.createStatement();
	        String subfSql = "INSERT INTO subfuncao (codsubfun, nomesubfun) "
	               + "VALUES ('" + subf.getCodSubFun() + "', '" + subf.getNomeSubFun() + "');";
	        stmt.executeUpdate(subfSql);     
	     
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public void updateSubfuncao(SubFuncao subf){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE subfuncao SET codsubfun = '" + subf.getCodSubFun() + "', "
	    				+ "nomesubfun = '" + subf.getNomeSubFun() + "'"
	    				+ " WHERE codsubfun = '" + subf.getCodSubFun() + "';";
		    stmt = c.createStatement();
		    stmt.execute(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public SubFuncao recuperaSubfuncao(String codSubf){
		SubFuncao sub = new SubFuncao();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "SELECT * FROM subfuncao WHERE Codsubfun = '" + codSubf + "';";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){
			    sub.setCodSubFun(rs.getInt("codsubfun"));
			    sub.setNomeSubFun(rs.getString("nomesubfun"));
		    }
		    else
		    	sub = null;
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }

		return sub;
	}
	
	public void deletaSubfuncao(SubFuncao subf){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM ACAO WHERE CodAcao = '" + subf.getCodSubFun() + "';";
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
