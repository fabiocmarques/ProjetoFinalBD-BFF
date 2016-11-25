package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Gui {
	
	String[] tabelas;
	
	public Gui(){
		tabelas = new String[]{"Ação", "Diária", "Favorecido", "Função", "Órgão Subordinado", "Órgão Superior",
				"Programa", "Subfunção", "Unidade Gestora"};		
	}
	

	public static void main(String[] args) {
		Gui gui = new Gui();
			
		gui.go();

	}
	
	public void go(){
		JFrame janela = new JFrame();
		JPanel painelBotoes = new JPanel(), painelSup = new JPanel(), painelInf = new JPanel();
		JButton botaoInserir = new JButton("Inserir"), botaoRemover = new JButton("Remover"), botaoAtualizar = new JButton("Atualizar"), botaoBuscar = new JButton("Buscar"), botaoSair = new JButton("Sair");
		JLabel titulo = new JLabel("Passagens e diárias de Servidores - Julho a Dezembro de 2014");
		
		painelInf.setBackground(Color.RED);
		
		painelBotoes.setBackground(Color.BLUE);
		painelBotoes.add(botaoInserir);
		painelBotoes.add(botaoRemover);
		painelBotoes.add(botaoAtualizar);
		painelBotoes.add(botaoBuscar);
		painelBotoes.add(botaoSair);
		
		/*
		 * Inicio Listeners dos botões.
		 * 
		 * */
		botaoInserir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				painelInf.removeAll();
				Inserir(painelInf);
			}
		});
		
		botaoRemover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				painelInf.removeAll();
				Remover(painelInf);				
			}
		});
		
		botaoAtualizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				painelInf.removeAll();
				Atualizar(painelInf);
				
			}
		});
		
		botaoBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Buscar(janela);
				janela.getContentPane().remove(painelInf);
			}
		});
		
		botaoSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				janela.setVisible(false);				
			}
		});
		
		/*
		 * Fim Listeners dos botões.
		 * 
		 * */
		
		painelSup.setLayout(new BoxLayout(painelSup, BoxLayout.Y_AXIS));
		painelSup.add(titulo);
		painelSup.add(painelBotoes);
		
		
		janela.getContentPane().add(BorderLayout.NORTH, painelSup);
		janela.getContentPane().add(BorderLayout.CENTER, painelInf);
		
		janela.setSize(600,600);
		janela.setVisible(true);
	}
	/*
	 * Tabelas: Ação, Diária, Favorecido, Função, Órgão Subordinado, Órgão Superior, 
	 * 			Programa, Subfunção, Unidade Gestora.
	 * */
	
	public void Inserir(JPanel painelInf){
		JPanel painelSeletorTab = new JPanel();
		JLabel t1 = new JLabel("Selecione a tabela para inserção: ");
		JComboBox comboTabelas = new JComboBox(tabelas);
		JButton confirmaTabela = new JButton("Confirmar");
		
		/*
		 * Listener do botão de confirmar.
		 * */
		
		confirmaTabela.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(comboTabelas.getSelectedIndex()){
					case 0://Ação
						painelSeletorTab.setVisible(false);
						insereAcao(painelInf);
						break;
						
					case 1://Diária
						painelSeletorTab.setVisible(false);
						insereDiaria(painelInf);
						break;
						
					case 2://Favorecido
						painelSeletorTab.setVisible(false);
						insereFavorecido(painelInf);
						break;
						
					case 3://Função
						painelSeletorTab.setVisible(false);
						insereFuncao(painelInf);
						break;
						
					case 4://Órgão Subordinado
						painelSeletorTab.setVisible(false);
						insereOrgSub(painelInf);
						break;
						
					case 5://Órgão Superior
						painelSeletorTab.setVisible(false);
						insereOrgSup(painelInf);
						break;
						
					case 6://Programa
						painelSeletorTab.setVisible(false);
						insereProg(painelInf);
						break;
						
					case 7://Subfunção
						painelSeletorTab.setVisible(false);
						insereSubfunc(painelInf);
						break;
						
					default://Unidade gestora
						painelSeletorTab.setVisible(false);
						insereUniGest(painelInf);
						break;
				}
			}
		});
		
		/**/
		
		painelInf.setBackground(Color.GREEN);
		painelSeletorTab.setBackground(Color.WHITE);
		
		painelSeletorTab.setLayout(new BoxLayout(painelSeletorTab, BoxLayout.Y_AXIS));
		
		painelSeletorTab.add(t1);
		painelSeletorTab.add(comboTabelas);
		painelSeletorTab.add(confirmaTabela);
		
		painelInf.add(painelSeletorTab);
		painelInf.repaint();
		painelInf.revalidate();
		
	}


	protected void Remover(JPanel painelInf){
		JPanel painelSeletorTab = new JPanel();
		JLabel t1 = new JLabel("Selecione a tabela para remoção: ");
		JComboBox comboTabelas = new JComboBox(tabelas);
		JButton confirmaTabela = new JButton("Confirmar");
		
		/*
		 * Listener do botão de confirmar.
		 * */
		
		confirmaTabela.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(comboTabelas.getSelectedIndex()){
					case 0://Ação
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Ação", "acao");
						break;
						
					case 1://Diária
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Diária", "diaria");
						break;
						
					case 2://Favorecido
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Favorecido", "favorecido");
						break;
						
					case 3://Função
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Função", "funcao");
						break;
						
					case 4://Órgão Subordinado
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Órgão Subordinado", "orgaosubordinado");
						break;
						
					case 5://Órgão Superior
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Órgão Superior", "orgaosuperior");
						break;
						
					case 6://Programa
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Programa", "programa");
						break;
						
					case 7://Subfunção
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Subfunção", "subfuncao");
						break;
						
					default://Unidade gestora
						painelSeletorTab.setVisible(false);
						removeEntrada(painelInf, "Unidade gestora", "unidadegestora");
						break;
				}
			}
		});
		
		/**/
		
		painelInf.setBackground(Color.GREEN);
		painelSeletorTab.setBackground(Color.WHITE);
		
		painelSeletorTab.setLayout(new BoxLayout(painelSeletorTab, BoxLayout.Y_AXIS));
		
		painelSeletorTab.add(t1);
		painelSeletorTab.add(comboTabelas);
		painelSeletorTab.add(confirmaTabela);
		
		painelInf.add(painelSeletorTab);
		painelInf.repaint();
		painelInf.revalidate();
	}
	
	protected void removeEntrada(JPanel painelInf, String nome, String tabela) {
		JPanel p = new JPanel();
		JLabel lb = new JLabel("Digite a chave da entrada da tabela de " + nome + ": ");
		JTextField chave = new JTextField();
		JButton confirma = new JButton("Confirmar");
		
		confirma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Realizar a remoção com a chave em "chave.getText()".
				 * */
			}
		});
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lb);
		p.add(chave);
		p.add(confirma);
		
		painelInf.add(p);
	}


	protected void Atualizar(JPanel painelInf){
		
	}

	protected void Buscar(JFrame janela){
	
	}
	
	protected void insereUniGest(JPanel painelInserir) {
		JPanel p = new JPanel();
		JLabel lbCodUniGes = new JLabel("Código da Unidade Gestora (PK): "), lbCodOrgSub = new JLabel("Código do Órgão Subordinado (FK): "), lbNomeUniGes = new JLabel("Nome da Unidade Gestora: ");
		JTextField codUniGes = new JTextField(), codOrgSub = new JTextField(), nomeUniGest = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		
		/*
		 * Para pegar os textos dos JTextField: "objeto.getText()" .
		 * */
		
		/*
		 * Listener do botão de confirmar, que chama o método de inserção.
		 * */
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Insere no banco com os dados conseguidos.
				 * 
				 * Precisa confirmar se o "codUniGes" é único.
				 * 
				 * Precisa confirmar se "codOrgSub" está na tabela OrgaoSubordinado.
				 * */				
			}
		});
		
		/**/
		
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lbCodUniGes);
		p.add(codUniGes);
		p.add(lbCodOrgSub);
		p.add(codOrgSub);
		p.add(lbNomeUniGes);
		p.add(nomeUniGest);
		p.add(confirmar);
		
		painelInserir.add(p);		
	}


	protected void insereSubfunc(JPanel painelInserir) {
		JPanel p = new JPanel();
		JLabel lbCodSubFun = new JLabel("Código da Subfunção (PK): "), lbNomeSubFun = new JLabel("Nome da Subfunção: ");
		JTextField codSubFun = new JTextField(), nomeSubFun = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		
		/*
		 * Para pegar os textos dos JTextField: "objeto.getText()" .
		 * */
		
		/*
		 * Listener do botão de confirmar, que chama o método de inserção.
		 * */
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Insere no banco com os dados conseguidos.
				 * 
				 * Precisa confirmar se o codSubFun é único.
				 *
				 * */				
			}
		});
		
		/**/
		
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lbCodSubFun);
		p.add(codSubFun);
		p.add(lbNomeSubFun);
		p.add(nomeSubFun);
		p.add(confirmar);
		
		painelInserir.add(p);
		
	}


	protected void insereProg(JPanel painelInserir) {
		JPanel p = new JPanel();
		JLabel lbCodProg = new JLabel("Código do Programa (PK): "), lbNomeProg = new JLabel("Nome do Programa: ");
		JTextField codProg = new JTextField(), nomeProg = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		
		/*
		 * Para pegar os textos dos JTextField: "objeto.getText()" .
		 * */
		
		/*
		 * Listener do botão de confirmar, que chama o método de inserção.
		 * */
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Insere no banco com os dados conseguidos.
				 * 
				 * Precisa confirmar se o codProg é único.
				 * 
				 * */				
			}
		});
		
		/**/
		
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lbCodProg);
		p.add(codProg);
		p.add(lbNomeProg);
		p.add(nomeProg);
		p.add(confirmar);
		
		painelInserir.add(p);
	}


	protected void insereOrgSup(JPanel painelInserir) {
		JPanel p = new JPanel();
		JLabel lbCodOrgSup = new JLabel("Código do Órgão Superior (PK): "), lbNomeOrgSup = new JLabel("Nome do Órgão Superior: ");
		JTextField codOrgSup = new JTextField(), nomeOrgSup = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		
		/*
		 * Para pegar os textos dos JTextField: "objeto.getText()" .
		 * */
		
		/*
		 * Listener do botão de confirmar, que chama o método de inserção.
		 * */
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Insere no banco com os dados conseguidos.
				 * 
				 * Precisa confirmar se o codOrgSup é único.
				 *
				 * */				
			}
		});
		
		/**/
		
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lbCodOrgSup);
		p.add(codOrgSup);
		p.add(lbNomeOrgSup);
		p.add(nomeOrgSup);
		p.add(confirmar);
		
		painelInserir.add(p);
		
	}


	protected void insereOrgSub(JPanel painelInserir) {
		JPanel p = new JPanel();
		JLabel lbCodOrgSub = new JLabel("Código do Órgão Subordinado (PK): "), lbCodOrgSup = new JLabel("Código do Órgão Superior (FK): "), lbNomeOrgSub = new JLabel("Nome do Órgão Subordinado: ");
		JTextField codOrgSub = new JTextField(), codOrgSup = new JTextField(), nomeOrgSub = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		
		/*
		 * Para pegar os textos dos JTextField: "objeto.getText()" .
		 * */
		
		/*
		 * Listener do botão de confirmar, que chama o método de inserção.
		 * */
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Insere no banco com os dados conseguidos.
				 * 
				 * Precisa confirmar se o "codOrgSub" é único.
				 * 
				 * Precisa confirmar se "codOrgSup" está na tabela OrgaoSuperior.
				 * */				
			}
		});
		
		/**/
		
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lbCodOrgSub);
		p.add(codOrgSub);
		p.add(lbCodOrgSup);
		p.add(codOrgSup);
		p.add(lbNomeOrgSub);
		p.add(nomeOrgSub);
		p.add(confirmar);
		
		painelInserir.add(p);
	}


	protected void insereFuncao(JPanel painelInserir) {
		JPanel p = new JPanel();
		JLabel lbCodFunc = new JLabel("Código da Função (PK): "), lbNomeFunc = new JLabel("Nome da Função: ");
		JTextField codFunc = new JTextField(), nomeFunc = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		
		/*
		 * Para pegar os textos dos JTextField: "objeto.getText()" .
		 * */
		
		/*
		 * Listener do botão de confirmar, que chama o método de inserção.
		 * */
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Insere no banco com os dados conseguidos.
				 * 
				 * Precisa confirmar se o codFunc é único.
				 *
				 * */				
			}
		});
		
		/**/
		
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lbCodFunc);
		p.add(codFunc);
		p.add(lbNomeFunc);
		p.add(nomeFunc);
		p.add(confirmar);
		
		painelInserir.add(p);
		
	}


	protected void insereFavorecido(JPanel painelInserir) {
		JPanel p = new JPanel();
		JLabel lbCPF = new JLabel("CPF do Favorecido: "), lbNomeFav = new JLabel("Nome do Favorecido: ");
		JTextField CPF = new JTextField(), nomeFav = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		
		/*
		 * Para pegar os textos dos JTextField: "objeto.getText()" .
		 * */
		
		/*
		 * Listener do botão de confirmar, que chama o método de inserção.
		 * */
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Insere no banco com os dados conseguidos.
				 * 
				 * */				
			}
		});
		
		/**/
		
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lbCPF);
		p.add(CPF);
		p.add(lbNomeFav);
		p.add(nomeFav);
		p.add(confirmar);
		
		painelInserir.add(p);
	}


	protected void insereDiaria(JPanel painelInserir) {
		// TODO Auto-generated method stub
		
	}


	protected void insereAcao(JPanel painelInserir) {
		JPanel p = new JPanel();
		JLabel lbCodAcao = new JLabel("Código da Ação (4 caracteres, PK): "), lbNomeAcao = new JLabel("Nome da Ação: "), lbLinguagem = new JLabel("Linguagem citada: ");
		JTextField codAcao = new JTextField(), nomeAcao = new JTextField(), linguagem = new JTextField();
		JButton confirmar = new JButton("Confirmar");
		
		/*
		 * Para pegar os textos dos JTextField: "objeto.getText()" .
		 * */
		
		/*
		 * Listener do botão de confirmar, que chama o método de inserção.
		 * */
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Insere no banco com os dados conseguidos.
				 * 
				 * Precisa confirmar se o "codAcao" é único.
				 *
				 * */				
			}
		});
		
		/**/
		
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		
		p.add(lbCodAcao);
		p.add(codAcao);
		p.add(lbNomeAcao);
		p.add(nomeAcao);
		p.add(lbLinguagem);
		p.add(linguagem);
		p.add(confirmar);
		
		painelInserir.add(p);
	}

}