package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.Acao;
import Negocio.Favorecido;

public class DaoFavorecido {
	String bd;
	String senha;
	
	
	public DaoFavorecido(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	public void createFavorecido(Favorecido favorecido){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	stmt = c.createStatement();
	         String favSql = "INSERT INTO FAVORECIDO (CODFAVORECIDO, CPF, NOMEFAV) "
	               + "VALUES (" + favorecido.getCodFavorecido() + ", " + favorecido.getCpf() + ", " + favorecido.getNomeFavorecido() + ");";
	         stmt.executeUpdate(favSql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	}
	
	public void updateFavorecido(Favorecido favorecido){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE Favorecido SET codfavorecido = " + favorecido.getCodFavorecido() + ", "
	    				+ "cpf = " + favorecido.getCpf() + ", "
	    				+ "nomefav = " + favorecido.getNomeFavorecido()
	    				+ " WHERE CodFavorecido = " + favorecido.getCodFavorecido() + ";";
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
	
	public Favorecido recuperaFavorecido(int codFavorecido){
		Favorecido favorecido = new Favorecido();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "SELECT * FROM FAVORECIDO WHERE CodFavorecido = " + codFavorecido + ";";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    favorecido.setCodFavorecido(rs.getInt("codfavorecido"));
		    favorecido.setCpf(rs.getString("cpf"));
		    favorecido.setNomeFavorecido(rs.getString("nomefav"));
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }

		return favorecido;
	}
	
	public void deletaFavorecido(Favorecido favorecido){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM FAVORECIDO WHERE CodFavorecido = " + favorecido.getCodFavorecido() + ";";
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
