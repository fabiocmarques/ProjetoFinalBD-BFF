package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.Favorecido;
import Negocio.OrgaoSub;
import Negocio.OrgaoSup;

public class DaoOrgaoSub {
	public void createOrgaoSub(OrgaoSub orgaoSub){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	stmt = c.createStatement();
		    String orgSubSql = "INSERT INTO ORGAOSUBORDINADO (CODORGSUB, CODORGSUP, NOMEORGSUB) "
		    				+ "VALUES (" + orgaoSub.getCodOrgaoSub() + ", " + orgaoSub.getOrgSup().getCodOrgSup() + ", " + orgaoSub.getNomeOrgaoSub() + ");";
		    stmt.executeUpdate(orgSubSql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }
	}
	
	public void updateOrgaoSub(OrgaoSub orgaoSub){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", "senha123");
	    	
	    	String sql = "UPDATE OrgaoSub SET codorgsub = " + orgaoSub.getCodOrgaoSub() + ", "
	    				+ "nomeorgsub = " + orgaoSub.getNomeOrgaoSub() + ", "
	    				+ "codorgsup = " + orgaoSub.getOrgSup().getCodOrgSup()
	    				+ " WHERE CodOrgSup = " + orgaoSub.getCodOrgaoSub() + ";";
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
	
	public OrgaoSub recuperaOrgaoSub(int codOrgaoSub){
		OrgaoSub orgSub = new OrgaoSub();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String sql = "SELECT ORGAOSUBORDINADO.* AND ORGAOSUPERIOR.NOMEORGSUP FROM ORGAOSUBORDINADO "
	    			+ "JOIN  ORGAOSUPERIOR ON ORGAOSUBORDINADO.codorgsup = ORGAOSUPERIOR.codorgsup" 
	    			+ "WHERE CodOrgSub = " + codOrgaoSub + ";";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    orgSub.setCodOrgaoSub(rs.getInt("codorgsub"));
		    orgSub.setNomeOrgaoSub(rs.getString("nomeorgsub"));
		    
		    OrgaoSup orgSup = new OrgaoSup();
		    
		    orgSup.setCodOrgSup(rs.getInt("codorgsup"));
		    orgSup.setNomeOrgSup(rs.getString("nomeorgsup"));
		    orgSub.setOrgSup(orgSup);
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	    	  System.exit(0);
	      }

		return orgSub;
	}
	
	public void deletaOrgaoSub(OrgaoSub orgSub){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/testdb",
	    	"postgres", "senha123");
	    	
	    	String sql = "DELETE FROM ORGAOSUBORDINADO WHERE CodOrgSub = " + orgSub.getCodOrgaoSub() + ";";
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
