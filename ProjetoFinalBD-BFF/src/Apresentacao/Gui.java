package Apresentacao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;

import Integracao.DaoAcao;
import Integracao.DaoDiaria;
import Integracao.DaoFavorecido;
import Integracao.DaoFuncao;
import Integracao.DaoOrgaoSub;
import Integracao.DaoOrgaoSup;
import Integracao.DaoPrograma;
import Integracao.DaoSubfuncao;
import Integracao.DaoUnidadeGestora;
import Negocio.Acao;
import Negocio.Diaria;
import Negocio.Favorecido;
import Negocio.Funcao;
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
				switch(nome){
					case "acao":
						Acao acao = new Acao();
						acao.setCodAcao(chave.getText());
						new DaoAcao("Diarias", "senha123").deletaAcao(acao);
						break;
					case "diaria":
						Diaria diaria = new Diaria();
						diaria.setCodDiaria(Integer.parseInt(chave.getText()));
						new DaoDiaria("Diaria", "senha123").deleteDiaria(diaria);
						break;
					case "favorecido":
						Favorecido fav = new Favorecido();
						fav.setCodFavorecido(Integer.parseInt(chave.getText()));
						new DaoFavorecido("Diarias", "senha123").deletaFavorecido(fav);;
						break;
					case "orgaosubordinado":
						OrgaoSub oSub = new OrgaoSub();
						oSub.setCodOrgaoSub(Integer.parseInt(chave.getText()));
						new DaoOrgaoSub("Diarias", "senha123").deletaOrgaoSub(oSub);;
						break;
					case "funcao":
						Funcao fun = new Funcao();
						fun.setCodFuncao(Integer.parseInt(chave.getText()));
						new DaoFuncao("Diarias", "senha123").deletaFuncao(fun);;
						break;
					case "subfuncao":
						SubFuncao subFun = new SubFuncao();
						subFun.setCodSubFun(Integer.parseInt(chave.getText()));
						new DaoSubfuncao("Diarias", "senha123").deletaSubfuncao(subFun);
						break;
					case "orgaosuperior":
						OrgaoSup oSup = new OrgaoSup();
						oSup.setCodOrgSup(Integer.parseInt(chave.getText()));
						new DaoOrgaoSup("Diarias", "senha123").deletaOrgaoSup(oSup);;
						break;
					case "programa":
						Programa prog = new Programa();
						prog.setCodProg(Integer.parseInt(chave.getText()));
						new DaoPrograma("Diarias", "senha123").deletaPrograma(prog);;
						break;
					default : 
						UnidadeGestora uniGes = new UnidadeGestora();
						uniGes.setCodUniGes(Integer.parseInt(chave.getText()));
						new DaoUnidadeGestora("Diarias", "senha123").deletaUnidadeGestora(uniGes);;
				}
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
		JLabel t[] = new JLabel[13];
		JTextField in[] = new JTextField[13];
		JButton confirma = new JButton("Confirmar");
		int camposLeitura, i;
		
		for(i = 0; i < 13; ++i)
			t[i] = new JLabel();		
		
		for(i = 0; i < 13; ++i)
			in[i] = new JTextField();
		
		
		confirma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Funcao fun;
				Favorecido fav;
				Acao a;
				SubFuncao sf;
				Programa prog;
				UnidadeGestora ug;
				OrgaoSub oSub;
				OrgaoSup oSup;
				
				switch(tabela){
				case "acao":
					Acao acao = new Acao();
					acao.setCodAcao(in[0].getText());
					acao.setLinguagemCidada(in[2].getText());
					acao.setNome(in[1].getText());
					new DaoAcao("Diarias", "senha123").updateAcao(acao);
					break;
					
				case "diaria":
					a = new Acao();
					a.setCodAcao(in[5].getText());
					fav = new Favorecido();
					fav.setCodFavorecido(Integer.parseInt(in[7].getText()));
					ug = new UnidadeGestora();
					ug.setCodUniGes(Integer.parseInt(in[2].getText()));
					fun = new Funcao();
					fun.setCodFuncao(Integer.parseInt(in[4].getText()));
					sf = new SubFuncao();
					sf.setCodSubFun(Integer.parseInt(in[3].getText()));
					prog = new Programa();
					prog.setCodProg(Integer.parseInt(in[6].getText()));
					
					Diaria diaria = new Diaria();
					diaria.setAcao(a);
					diaria.setCodDiaria(Integer.parseInt(in[0].getText()));
					diaria.setDocPagamento(in[1].getText());
					diaria.setFavorecido(fav);
					diaria.setFuncao(fun);
					diaria.setGestaoPag(Integer.parseInt(in[9].getText()));
					diaria.setGestor(ug);
					diaria.setPrograma(prog);
					diaria.setSubFuncao(sf);
					diaria.setValorPagamento(Float.parseFloat(in[8].getText()));
//					Colocar data de pagamento!!!! (Fuck Apple)
					
					
					new DaoDiaria("Diarias", "senha123").updateDiaria(diaria);
					break;
				case "funcao":
					fun = new Funcao();
					fun.setCodFuncao(Integer.parseInt(in[0].getText()));
					fun.setNomeFuncao(in[1].getText());
					
					new DaoFuncao("Diarias", "senha123").updateFuncao(fun);
					break;
				
				case "programa":
					prog = new Programa();
					prog.setCodProg(Integer.parseInt(in[0].getText()));
					prog.setNomeProg(in[1].getText());
					new DaoPrograma("Diarias", "senha123").updatePrograma(prog);
					break;
				
				case "favorecido":
					fav = new Favorecido();
					fav.setCodFavorecido(Integer.parseInt(in[0].getText()));
					fav.setCpf(in[1].getText());
					fav.setNomeFavorecido(in[2].getText());
					
					new DaoFavorecido("Diarias", "senha123").updateFavorecido(fav);
					break;
					
				case "subfuncao":			
					sf = new SubFuncao();
					sf.setCodSubFun(Integer.parseInt(in[0].getText()));
					sf.setNomeSubFun(in[1].getText());
					
					new DaoSubfuncao("Diarias", "senha123").updateSubfuncao(sf);
					
					break;
					
				case "unidadegestora":
					oSub = new OrgaoSub();
					oSub.setCodOrgaoSub(Integer.parseInt(in[1].getText()));
					ug = new UnidadeGestora();
					ug.setCodUniGes(Integer.parseInt(in[0].getText()));
					ug.setNomeUnidadeGestora(in[2].getText());
					ug.setOrgaoSub(oSub);
					
					new DaoUnidadeGestora("Diarias", "senha123").updateUnidadeGestora(ug);
					break;
					
				case "orgaosubordinado":
					oSup = new OrgaoSup();
					oSup.setCodOrgSup(Integer.parseInt(in[1].getText()));
					
					oSub = new OrgaoSub();
					oSub.setOrgSup(oSup);
					oSub.setCodOrgaoSub(Integer.parseInt(in[0].getText()));
					oSub.setNomeOrgaoSub(in[2].getText());
					
					new DaoOrgaoSub("Diarias", "senha123").updateOrgaoSub(oSub);
					break;
					
				case "orgaosuperior":
					oSup = new OrgaoSup();
					oSup.setCodOrgSup(Integer.parseInt(in[0].getText()));
					oSup.setNomeOrgSup(in[1].getText());
					new DaoOrgaoSup("Diarias", "senha123").updateOrgaoSup(oSup);
					break;
				
				default:
					break;
				}
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
				
				break;
			case "funcao":
				t[0].setText("Código da Função (PK): ");
				t[1].setText("Nome da Função: ");
				for(i = 0; i < 2; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				break;
			
			case "programa":
				t[0].setText("Código do Programa (PK): ");
				t[1].setText("Nome do Programa: ");
				for(i = 0; i < 2; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				
				break;
			
			case "favorecido":
				t[0].setText("Código do Favorecido (PK): ");
				t[1].setText("CPF do Favorecido: ");
				t[2].setText("Nome do Favorecido: ");
				for(i = 0; i < 3; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				
				break;
				
			case "subfuncao":
				t[0].setText("Código da Subfunção (PK): ");
				t[1].setText("Nome da Subfunção: ");
				for(i = 0; i < 2; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				
				break;
				
			case "unidadegestora":
				t[0].setText("Código da Unidade Gestora (PK): ");
				t[1].setText("Código do Órgão Subordinado (FK): ");
				t[2].setText("Nome da Unidade Gestora: ");
				for(i = 0; i < 3; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				
				break;
				
			case "orgaosubordinado":
				t[0].setText("Código do Órgão Subordinado (PK): ");
				t[1].setText("Código do Órgão Superior (FK): ");
				t[2].setText("Nome do Órgão Subordinado: ");
				for(i = 0; i < 3; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
				
				break;
				
			case "orgaosuperior":
				t[0].setText("Código do Órgão Superior (PK): ");
				t[1].setText("Nome do Órgão Superior: ");
				for(i = 0; i < 2; ++i){
					p.add(t[i]);
					p.add(in[i]);
				}
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
		DaoFuncao dao = new DaoFuncao("Diarias", "1234");
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
				Funcao func = new Funcao();
				
				func.setCodFuncao(Integer.parseInt(codFunc.getText()));
				func.setNomeFuncao(nomeFunc.getText());
				
				dao.createFuncao(func);
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
		DaoFavorecido dao = new DaoFavorecido("Diarias", "senha123");
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
				Favorecido fav = new Favorecido();
				
				fav.setCpf(CPF.getText());
				fav.setNomeFavorecido(nomeFav.getText());
				
				dao.createFavorecido(fav);
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
		DaoDiaria dao = new DaoDiaria("Diarias", "1234");
		JPanel p = new JPanel();
		JLabel t[] = new JLabel[12];
		JTextField in[] = new JTextField[12];
		JButton confirmar = new JButton("Confirmar");
		int i;
		
		for(i = 0; i < 12; ++i){
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
		t[9] = new JLabel("Dia do pagamento: ");
		t[10] = new JLabel("Mês do pagamento: ");
		t[11] = new JLabel("Ano do pagamento: ");
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Diaria diaria = new Diaria();
				Acao acao = new Acao();
				Date data = new Date(Integer.parseInt(in[11].getText()), Integer.parseInt(in[10].getText()), Integer.parseInt(in[9].getText()));
				Favorecido fav = new Favorecido();
				Funcao func = new Funcao();
				SubFuncao subf = new SubFuncao();
				Programa prog = new Programa();
				UnidadeGestora uGest = new UnidadeGestora();
				
				acao.setCodAcao(in[4].getText());
				uGest.setCodUniGes(Integer.parseInt(in[1].getText()));
				fav.setCodFavorecido(Integer.parseInt(in[6].getText()));
				func.setCodFuncao(Integer.parseInt(in[3].getText()));
				subf.setCodSubFun(Integer.parseInt(in[2].getText()));
				prog.setCodProg(Integer.parseInt(in[5].getText()));
				
				
				diaria.setAcao(acao);
				diaria.setDataPagamento(data);
				diaria.setDocPagamento(in[0].getText());				
				diaria.setFavorecido(fav);				
				diaria.setFuncao(func);				
				diaria.setSubFuncao(subf);
				diaria.setGestaoPag(Integer.parseInt(in[8].getText()));				
				diaria.setGestor(uGest);				
				diaria.setPrograma(prog);
				diaria.setValorPagamento(Float.parseFloat(in[7].getText()));
				
				dao.createDiaria(diaria);
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
		DaoAcao dao = new DaoAcao("Diarias", "1234");
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
				Acao acao = new Acao();
				
				acao.setCodAcao(codAcao.getText());
				acao.setLinguagemCidada(linguagem.getText());
				acao.setNome(nomeAcao.getText());
				
				dao.createAcao(acao);				
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