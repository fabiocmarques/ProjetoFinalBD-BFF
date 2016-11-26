package Negocio;

public class OrgaoSub {
	int codOrgaoSub;
	OrgaoSup orgSup;
	String nomeOrgaoSub;
	
	
	public OrgaoSup getOrgSup() {
		return orgSup;
	}
	public void setOrgSup(OrgaoSup orgSup) {
		this.orgSup = orgSup;
	}
	public int getCodOrgaoSub() {
		return codOrgaoSub;
	}
	public void setCodOrgaoSub(int codOrgaoSub) {
		this.codOrgaoSub = codOrgaoSub;
	}
	public String getNomeOrgaoSub() {
		return nomeOrgaoSub;
	}
	public void setNomeOrgaoSub(String nomeOrgaoSub) {
		this.nomeOrgaoSub = nomeOrgaoSub;
	}
	
	
}
