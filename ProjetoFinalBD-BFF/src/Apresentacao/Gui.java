package Apresentacao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Integracao.DaoOrgaoSub;
import Integracao.DaoOrgaoSup;
import Integracao.DaoPrograma;
import Integracao.DaoSubfuncao;
import Integracao.DaoUnidadeGestora;
import Negocio.OrgaoSub;
import Negocio.OrgaoSup;
import Negocio.Programa;
import Negocio.SubFuncao;
import Negocio.UnidadeGestora;

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
		JComboBox<String> comboTabelas = new JComboBox<String>(tabelas);
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
		JComboBox<String> comboTabelas = new JComboBox<String>(tabelas);
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
		JPanel painelSeletorTab = new JPanel();
		JLabel t1 = new JLabel("Selecione a tabela para remoção: ");
		JComboBox<String> comboTabelas = new JComboBox<String>(tabelas);
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
						attTab(painelInf, "Ação", "acao");
						break;
						
					case 1://Diária
						painelSeletorTab.setVisible(false);
						attTab(painelInf, "Diária", "diaria");
						break;
						
					case 2://Favorecido
						painelSeletorTab.setVisible(false);
						attTab(painelInf, "Favorecido", "favorecido");
						break;
						
					case 3://Função
						painelSeletorTab.setVisible(false);
						attTab(painelInf, "Função", "funcao");
						break;
						
					case 4://Órgão Subordinado
						painelSeletorTab.setVisible(false);
						attTab(painelInf, "Órgão Subordinado", "orgaosubordinado");
						break;
						
					case 5://Órgão Superior
						painelSeletorTab.setVisible(false);
						attTab(painelInf, "Órgão Superior", "orgaosuperior");
						break;
						
					case 6://Programa
						painelSeletorTab.setVisible(false);
						attTab(painelInf, "Programa", "programa");
						break;
						
					case 7://Subfunção
						painelSeletorTab.setVisible(false);
						attTab(painelInf, "Subfunção", "subfuncao");
						break;
						
					default://Unidade gestora
						painelSeletorTab.setVisible(false);
						attTab(painelInf, "Unidade gestora", "unidadegestora");
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

	protected void attTab(JPanel painelInf, String nome, String tabela) {
		JPanel p = new JPanel();
		JLabel t[] = new JLabel[11];
		JTextField in[] = new JTextField[11];
		JButton confirma = new JButton("Confirmar");
		int camposLeitura, i;
		
		for(i = 0; i < 11; ++i)
			t[i] = new JLabel();		
		
		for(i = 0; i < 11; ++i)
			in[i] = new JTextField();
		
		
		confirma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
								
			}
		});
		
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));		
		
		
		switch(tabela){
			case "acao":
				t[0].setText("Código da Ação (PK): ");
				t[1].setText("Nome da Ação: ");
				t[2].setText("Linguagem citada: ");
				for(i = 0; i < 3; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
				
			case "diaria":
				t[0] = new JLabel("Código da Diária (PK): ");
				t[1] = new JLabel("Documento de pagamento: ");
				t[2] = new JLabel("Código da Unidade Gestora (FK): ");
				t[3] = new JLabel("Código da Subfunção (FK): ");
				t[4] = new JLabel("Código da Função (FK): ");
				t[5] = new JLabel("Código da Ação (FK): ");
				t[6] = new JLabel("Código do Programa (FK): ");
				t[7] = new JLabel("Código do Favorecido (FK): ");
				t[8] = new JLabel("Valor do pagamento: ");
				t[9] = new JLabel("Gestão do pagamento: ");
				t[10] = new JLabel("Data do pagamento: ");
				for(i = 0; i < 11; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
				
			case "funcao":
				t[0].setText("Código da Função (PK): ");
				t[1].setText("Nome da Função: ");
				for(i = 0; i < 2; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
			
			case "programa":
				t[0].setText("Código do Programa (PK): ");
				t[1].setText("Nome do Programa: ");
				for(i = 0; i < 2; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
			
			case "favorecido":
				t[0].setText("Código do Favorecido (PK): ");
				t[1].setText("CPF do Favorecido: ");
				t[2].setText("Nome do Favorecido: ");
				for(i = 0; i < 3; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
				
			case "subfuncao":
				t[0].setText("Código da Subfunção (PK): ");
				t[1].setText("Nome da Subfunção: ");
				for(i = 0; i < 2; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
				
			case "unidadegestora":
				t[0].setText("Código da Unidade Gestora (PK): ");
				t[1].setText("Código do Órgão Subordinado (FK): ");
				t[2].setText("Nome da Unidade Gestora: ");
				for(i = 0; i < 3; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
				
			case "orgaosubordinado":
				t[0].setText("Código do Órgão Subordinado (PK): ");
				t[1].setText("Código do Órgão Superior (FK): ");
				t[2].setText("Nome do Órgão Subordinado: ");
				for(i = 0; i < 3; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
				
			case "orgaosuperior":
				t[0].setText("Código do Órgão Superior (PK): ");
				t[1].setText("Nome do Órgão Superior: ");
				for(i = 0; i < 2; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				camposLeitura = i;
				break;
			
			default:
				break;
		}
		
		p.add(confirma);
		
		painelInf.add(p);
	}


	protected void Buscar(JFrame janela){
	
	}
	
	protected void insereUniGest(JPanel painelInserir) {
		DaoUnidadeGestora dao = new DaoUnidadeGestora("Diarias", "1234");
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
				UnidadeGestora uGes = new UnidadeGestora();
				OrgaoSub org = new OrgaoSub();
				
				org.setCodOrgaoSub(Integer.parseInt(codOrgSub.getText()));
				
				uGes.setCodUniGes(Integer.parseInt(codUniGes.getText()));
				uGes.setNomeUnidadeGestora(nomeUniGest.getText());
				uGes.setOrgaoSub(org);	
				
				dao.createUnidadeGestora(uGes);			
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
		DaoSubfuncao dao = new DaoSubfuncao("Diarias", "1234");
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
				SubFuncao subf = new SubFuncao();
				
				subf.setCodSubFun(Integer.parseInt(codSubFun.getText()));
				subf.setNomeSubFun(nomeSubFun.getText());
				
				dao.createSubfuncao(subf);
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
		DaoPrograma dao = new DaoPrograma("Diarias", "1234");
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
				Programa prog = new Programa();
				
				prog.setCodProg(Integer.parseInt(codProg.getText()));
				prog.setNomeProg(nomeProg.getText());
				
				dao.createPrograma(prog);
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
		DaoOrgaoSup dao = new DaoOrgaoSup("Diarias", "1234");
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
				OrgaoSup org = new OrgaoSup();
				
				org.setCodOrgSup(Integer.parseInt(codOrgSup.getText()));
				org.setNomeOrgSup(nomeOrgSup.getText());
				
				dao.createOrgaoSup(org);
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
		DaoOrgaoSub dao = new DaoOrgaoSub("Diarias", "1234");
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
				OrgaoSub oSub = new OrgaoSub();
				OrgaoSup oSup = new OrgaoSup();
				
				oSup.setCodOrgSup(Integer.parseInt(codOrgSup.getText()));
				
				oSub.setCodOrgaoSub(Integer.parseInt(codOrgSub.getText()));
				oSub.setNomeOrgaoSub(nomeOrgSub.getText());
				oSub.setOrgSup(oSup);
				
				dao.createOrgaoSub(oSub);			
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
		JPanel p = new JPanel();
		JLabel t[] = new JLabel[10];
		JTextField in[] = new JTextField[10];
		JButton confirmar = new JButton("Confirmar");
		int i;
		
		for(i = 0; i < 10; ++i){
			in[i] = new JTextField();
		}
		
		t[0] = new JLabel("Documento de pagamento: ");
		t[1] = new JLabel("Código da Unidade Gestora (FK): ");
		t[2] = new JLabel("Código da Subfunção (FK): ");
		t[3] = new JLabel("Código da Função (FK): ");
		t[4] = new JLabel("Código da Ação (FK): ");
		t[5] = new JLabel("Código do Programa (FK): ");
		t[6] = new JLabel("Código do Favorecido (FK): ");
		t[7] = new JLabel("Valor do pagamento: ");
		t[8] = new JLabel("Gestão do pagamento: ");
		t[9] = new JLabel("Data do pagamento: ");
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * 
				 * */				
			}
		});
		
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		
		for(i = 0; i < 10; ++i){
			p.add(t[i]);
			p.add(in[i]);
		}
		
		painelInserir.add(p);		
		
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