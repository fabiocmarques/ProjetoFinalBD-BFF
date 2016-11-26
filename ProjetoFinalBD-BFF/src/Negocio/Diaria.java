package Negocio;

import java.sql.Date;

public class Diaria {
	UnidadeGestora gestor;
	SubFuncao subFuncao;
	Funcao funcao;
	Acao acao;
	Programa programa;
	Favorecido favorecido;
	int gestaoPag;
	float valorPagamento;
	String docPagamento;
	Date dataPagamento;
	int codDiaria;
	
	
	
	public int getCodDiaria() {
		return codDiaria;
	}
	public void setCodDiaria(int codDiaria) {
		this.codDiaria = codDiaria;
	}
	public UnidadeGestora getGestor() {
		return gestor;
	}
	public void setGestor(UnidadeGestora gestor) {
		this.gestor = gestor;
	}
	public SubFuncao getSubFuncao() {
		return subFuncao;
	}
	public void setSubFuncao(SubFuncao subFuncao) {
		this.subFuncao = subFuncao;
	}
	public Funcao getFuncao() {
		return funcao;
	}
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	public Programa getPrograma() {
		return programa;
	}
	public void setPrograma(Programa programa) {
		this.programa = programa;
	}
	public Favorecido getFavorecido() {
		return favorecido;
	}
	public void setFavorecido(Favorecido favorecido) {
		this.favorecido = favorecido;
	}
	public String getDocPagamento() {
		return docPagamento;
	}
	public void setDocPagamento(String docPagamento) {
		this.docPagamento = docPagamento;
	}
	public int getGestaoPag() {
		return gestaoPag;
	}
	public void setGestaoPag(int gestaoPag) {
		this.gestaoPag = gestaoPag;
	}
	public float getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(float valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
