package Negocio;

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
	String dataPagamento;
	
	
	
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
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	
}
