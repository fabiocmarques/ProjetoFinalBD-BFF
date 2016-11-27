package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.OrgaoSub;
import Negocio.OrgaoSup;
import Negocio.UnidadeGestora;

public class DaoUnidadeGestora {
	String bd;
	String senha;
	
	
	public DaoUnidadeGestora(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	public void createUnidadeGestora(UnidadeGestora uniGes){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	stmt = c.createStatement();
	         String gestorSql = "INSERT INTO UNIDADEGESTORA (CODUNIGES, CODORGSUB, NOMEUNIGES) "
		               + "VALUES (" + uniGes.getCodUniGes() + ", " + uniGes.getOrgaoSub() + ", " + uniGes.getNomeUnidadeGestora() + ");";
	         stmt.executeUpdate(gestorSql);
	        
	     
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	}
	
	public void updateUnidadeGestora(UnidadeGestora uniGes){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE unidadegestora SET coduniges = " + uniGes.getCodUniGes() + ", "
	    				+ "codorgsub = " + uniGes.getOrgaoSub() + ", "
	    				+ "nomeuniges = " + uniGes.getNomeUnidadeGestora()
	    				+ " WHERE coduniges = " + uniGes.getCodUniGes() + ";";
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
	
	public UnidadeGestora recuperaUnidadeGestora(String uniGes){
		UnidadeGestora uGes = new UnidadeGestora();
		OrgaoSub oSub = new OrgaoSub();
		OrgaoSup oSup = new OrgaoSup();
		Connection c = null;
		Statement stmt = null;
		String sub, sup;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "SELECT * FROM unidadegestora WHERE Coduniges = " + uniGes + ";";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    rs.next();
		    
		    uGes.setCodUniGes(rs.getInt("coduniges"));
		    sub = rs.getString("codorgsub");
		    uGes.setNomeUnidadeGestora(rs.getString("nomeuniges"));
		    
		    sql = "SELECT * FROM orgaosubordinado WHERE codorgsub = " + sub + ";";
		    stmt = c.createStatement();
		    rs = stmt.executeQuery(sql);
		    
		    sup = rs.getString("codorgsup");
		    oSub.setCodOrgaoSub(rs.getInt("codorgsub"));
		    oSub.setNomeOrgaoSub(rs.getString("nomeorgsub"));
		    
		    sql = "SELECT * FROM orgaosuperior WHERE codorgsup = " + sup + ";";
		    stmt = c.createStatement();
		    rs = stmt.executeQuery(sql);
		    
		    oSup.setCodOrgSup(rs.getInt("codorgsup"));
		    oSup.setNomeOrgSup(rs.getString("nomeorgsup"));
		    
		    oSub.setOrgSup(oSup);
		    
		    uGes.setOrgaoSub(oSub);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }

		return uGes;
	}
	
	public void deletaUnidadeGestora(UnidadeGestora uniGes){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM unidadegestora WHERE CodUniGes = " + uniGes.getCodUniGes() + ";";
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
