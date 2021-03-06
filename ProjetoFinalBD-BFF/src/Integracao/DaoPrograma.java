package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.Favorecido;
import Negocio.Programa;

public class DaoPrograma {
	String bd;
	String senha;
	
	
	public DaoPrograma(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	public void createPrograma(Programa programa){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	stmt = c.createStatement();
	         String progSql = "INSERT INTO PROGRAMA (CODPROG, NOMEPROG) "
	               + "VALUES ('" + programa.getCodProg() + "', '" + programa.getNomeProg() + "');";
	         stmt.execute(progSql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public void updatePrograma(Programa programa){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE PROGRAMA SET codprog = '" + programa.getCodProg() + "', "
	    				+ "nomeprog = '" + programa.getNomeProg()
	    				+ "' WHERE Codprog = '" + programa.getCodProg() + "';";
		    stmt = c.createStatement();
		    stmt.execute(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public Programa recuperaPrograma(int codProg){
		Programa programa = new Programa();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "SELECT * FROM Programa WHERE Codprog = '" + codProg + "';";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){
			    programa.setCodProg(rs.getInt("codprog"));
			    programa.setNomeProg(rs.getString("nomeprog"));
		    }
		    else
		    	programa = null;
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }

		return programa;
	}
	
	public void deletaPrograma(Programa programa){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM PROGRAMA WHERE CodProg = '" + programa.getCodProg() + "';";
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
