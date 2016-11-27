package Integracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Negocio.Favorecido;
import Negocio.OrgaoSub;
import Negocio.OrgaoSup;

public class DaoOrgaoSub {
	String bd;
	String senha;
	
	
	public DaoOrgaoSub(String bd, String senha){
		this.bd = bd;
		this.senha = senha;
	}
	
	public void createOrgaoSub(OrgaoSub orgaoSub){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	stmt = c.createStatement();
		    String orgSubSql = "INSERT INTO ORGAOSUBORDINADO (CODORGSUB, CODORGSUP, NOMEORGSUB) "
		    				+ "VALUES (" + orgaoSub.getCodOrgaoSub() + ", " + orgaoSub.getOrgSup().getCodOrgSup() + ", " + orgaoSub.getNomeOrgaoSub() + ");";
		    stmt.executeUpdate(orgSubSql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public void updateOrgaoSub(OrgaoSub orgaoSub){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/Diarias",
	    	"postgres", senha);
	    	
	    	String sql = "UPDATE OrgaoSubordinado SET codorgsub = '" + orgaoSub.getCodOrgaoSub() + "', "
	    				+ "nomeorgsub = '" + orgaoSub.getNomeOrgaoSub() + "', "
	    				+ "codorgsup = '" + orgaoSub.getOrgSup().getCodOrgSup()
	    				+ "' WHERE CodOrgSub = '" + orgaoSub.getCodOrgaoSub() + "';";
		    stmt = c.createStatement();
		    stmt.execute(sql);
		    
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }
	}
	
	public OrgaoSub recuperaOrgaoSub(int codOrgaoSub){
		OrgaoSub orgSub = new OrgaoSub();
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "SELECT ORGAOSUBORDINADO.*, ORGAOSUPERIOR.NOMEORGSUP FROM ORGAOSUBORDINADO "
	    			+ "JOIN  ORGAOSUPERIOR ON ORGAOSUBORDINADO.codorgsup = ORGAOSUPERIOR.codorgsup" 
	    			+ " WHERE orgaosubordinado.codorgsub = '" + codOrgaoSub + "';";
		    stmt = c.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);
		    
		    if(rs.next()){
			    orgSub.setCodOrgaoSub(rs.getInt("codorgsub"));
			    orgSub.setNomeOrgaoSub(rs.getString("nomeorgsub"));
			    
			    OrgaoSup orgSup = new OrgaoSup();
			    
			    orgSup.setCodOrgSup(rs.getInt("codorgsup"));
			    orgSup.setNomeOrgSup(rs.getString("nomeorgsup"));
			    orgSub.setOrgSup(orgSup);
		    }
		    else
		    	orgSub = null;
		    
		    rs.close();
		    stmt.close();
		    c.close();
	    	
	      } catch (Exception e) {
	    	  e.printStackTrace();
	    	  System.out.println(e.getClass().getName()+": "+e.getMessage());
	    	  
	      }

		return orgSub;
	}
	
	public void deletaOrgaoSub(OrgaoSub orgSub){
		Connection c = null;
		Statement stmt = null;
		
	    try {
	    	Class.forName("org.postgresql.Driver");
	    	c = DriverManager 
	    	.getConnection("jdbc:postgresql://localhost:5432/" + bd,
	    	"postgres", senha);
	    	
	    	String sql = "DELETE FROM ORGAOSUBORDINADO WHERE CodOrgSub = '" + orgSub.getCodOrgaoSub() + "';";
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
