package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.OrgaoSup;

public class DaoOrgaoSup {
	String bd;
	String senha;
	
	
	public DaoOrgaoSup(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	public void createOrgaoSup(OrgaoSup orgSup){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	stmt = c.createStatement();
	         String orgSupSql = "INSERT INTO ORGAOSUPERIOR (CODORGSUP, NOMEORGSUP) "
		               + "VALUES ('" + orgSup.getCodOrgSup() + "', '" + orgSup.getNomeOrgSup() + "');";
	         stmt.executeUpdate(orgSupSql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public void updateOrgaoSup(OrgaoSup orgaoSup){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE ORGAOSUPERIOR SET codorgsup = '" + orgaoSup.getCodOrgSup() + "', "
	    				+ "nomeOrgSup = '" + orgaoSup.getNomeOrgSup()
	    				+ "' WHERE CodOrgSup = '" + orgaoSup.getCodOrgSup() + "';";
		    stmt = c.createStatement();
		    stmt.execute(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public OrgaoSup recuperaOrgSup(int codOrgSup){
		OrgaoSup orgSup = new OrgaoSup();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "SELECT * FROM ORGAOSUPERIOR WHERE CodOrgSup = '" + codOrgSup + "';";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){
			    orgSup.setCodOrgSup(rs.getInt("codorgsup"));
			    orgSup.setNomeOrgSup(rs.getString("nomeorgsup"));
		    }
		    else 
		    	orgSup = null;
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }

		return orgSup;
	}
	
	public void deletaOrgaoSup(OrgaoSup orgSup){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM ORGAOSUPERIOR WHERE CodOrgSup = '" + orgSup.getCodOrgSup() + "';";
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
