/*
 * Federal University of Minas Gerais 
 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Alisson
 * Date: 18/07/2011
 * 
 * Tabuleiro do jogo SimulesSPL
 *   
 */
package br.ufmg.reuso.presentation;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;



import br.ufmg.reuso.negocio.carta.Artefato;
import br.ufmg.reuso.negocio.carta.Carta;
//#ifdef ConceptCard
import br.ufmg.reuso.negocio.carta.CartaBonificacao;
//#endif
import br.ufmg.reuso.negocio.carta.CartaEngenheiro;

import br.ufmg.reuso.negocio.jogador.Jogador;
import br.ufmg.reuso.negocio.jogo.GameController;
import br.ufmg.reuso.negocio.jogo.Jogo;
import br.ufmg.reuso.negocio.mesa.Mesa;

/**
 * @author Alisson Classe responsável por pintar o tabuleiro com as informações
 *         do jogador atual e permitir sua manipulação do jogo
 */
// =====================================================================================//
// Inicio da classe ScreenTabuleiro
// =====================================================================================//
public class ScreenTabuleiro extends JDialog {

	// Variáveis globais
	private static final long serialVersionUID = 2797977843067840264L;

	// Paineis
	public JPanel panelBase;
	public JPanel panelButtonsLeft;
	public JPanel panelButtonsRight;
	public JPanel panelMesas;
	public JPanel panelCartas;
	public JPanel panelTabuleiro;
	public JPanel jpanel;

	public JButton buttonTabuleiro;
	public JButton buttonProjeto;
	public JButton buttonDados;
	public JButton buttonEnd;

	// Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);

	public Color colorBack = Color.LIGHT_GRAY;

	// Variáveis que fazem ligação com o jogo
	public Jogador jogador = null;
	public Jogador oponent = null;
	Jogo jogo = null;
	public Mesa board = null;
	Carta cartaAtiva = null;

	// Variáveis utilizadas para posicionamento dos paineis.
	public int x, y, width, height, yInc, xInc;
	public Dimension dimPanel;
	public Dimension mySise = new Dimension();

	// =====================================================================================//
	/**
	 * @param jogador Jogador atual a manipular o jogo
	 * @param jogo    Jogo responsável pelo controle das informações apresentadas
	 * 
	 *                Se os parâmetros forem nulos um tabuleiro vazio será pintado.
	 */
	public ScreenTabuleiro(Jogador jogador, Jogo jogo) {
		super();

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		this.jogador = jogador;
		this.jogo = jogo;

		board = null;

		cartaAtiva = null;
		initialize();

		panelTabuleiro = getPanelBase();

	}

	// =====================================================================================//

	/**
	 * Define as dimensoes do painel iguais as dimensões da tela
	 * 
	 */
	private void initialize() {

	}

	// =====================================================================================//
	/**
	 * Define o painel principal da janela de diálogo
	 * 
	 * @return JPanel contendo o container base do tabuleiro
	 * 
	 */
	private JPanel getPanelBase() {

		JPanel jpanel = new JPanel();

		BorderLayout brd = new BorderLayout();
		jpanel.setLayout(brd);

		JLabel lblNoth = new JLabel();
		lblNoth.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		lblNoth.setBackground(colorBack);
		lblNoth.setOpaque(true);
		jpanel.add(lblNoth, BorderLayout.NORTH);
		getPanelButtonsLeft();
		jpanel.add(panelButtonsLeft, BorderLayout.WEST);
		getPanelButtonsRight();
		jpanel.add(panelButtonsRight, BorderLayout.EAST);

		if (jogador != null) {
			getPanelMesas();
			panelMesas.setEnabled(oponent == null);
			jpanel.add(panelMesas, BorderLayout.CENTER);
			//panelCartas = 
			getPanelCards();
			jpanel.add(panelCartas, BorderLayout.SOUTH);

		}

		this.setContentPane(jpanel);

		panelBase = jpanel;

		return jpanel;
	}

	// =====================================================================================//
	/**
	 * Pinta o painel com os botões de acesso geral do usuário
	 * 
	 * @return JPanel contendo o container base do tabuleiro
	 * 
	 */
	private void getPanelButtonsLeft() {

		
	}

	// =====================================================================================//
	/**
	 * @return JPanel com o painel direito do tabuleiro.
	 * 
	 */
	// TODO [ARS] Este painel pode apresentar problemas e conceitos ativos
	private void getPanelButtonsRight() {
		
		
	}

	// =====================================================================================//
	// Inicio da construção das mesas dos engenheiros
	// =====================================================================================//

	/**
	 * A função é responsável por pintar tanto as mesas com os engenheiros quanto os
	 * títulos destas
	 * 
	 * @return JPanel contendo as mesas com engenheiros e artefatos
	 */
	private void getPanelMesas() {


	}

	// =====================================================================================//
	/**
	 * @param mesa       representa a posição no vetor de mesas no jogo em que o
	 *                   engenheiro está alocado
	 * @param panelBoard representa o painel vertical em que será pintada a mesa
	 * @return retorna o painel com o engenheiro pintado
	 */
	public JPanel getEnginerLabel(int mesa, JPanel panelBoard) {

		JLabel label = null;
		if (board.getCartaMesa() != null) {
			label = new JLabel();
			height = yInc;
			ImageIcon img = ComponentCard.getImageScalable(
					ScreenInteraction.imagePath + board.getCartaMesa().getCodigoCarta() + ".png", width, 0);
			if (img == null) {
				img = ComponentCard.getImageScalable(ScreenInteraction.imagePath + "smile.png", 0, height);
			}
			label.setIcon(img);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBounds(x, y, width, height);
			// label.setBorder(borderW);
			panelBoard.add(label);

			label = new JLabel(board.getCartaMesa().getNomeEngenheiro(), JLabel.CENTER);
			y += height;
			height = yInc / 2;
			label.setBounds(x, y, width, height);
			// label.setBorder(borderW);
			panelBoard.add(label);

			JButton btnEng = new JButton("Usar");
			y += height;
			btnEng.setActionCommand(Integer.toString(mesa));
			btnEng.addActionListener(getActionEnginer());
			btnEng.setBounds(x, y, width, height);
			btnEng.setEnabled(oponent == null);
			// btnEng.setBorder(borderW);
			panelBoard.add(btnEng);

		} else {
			label = new JLabel("<html><center>Mesa do<br> Eng. " + Integer.toString(mesa + 1), JLabel.CENTER);
			height = 2 * yInc;
			label.setBounds(x, y, width, height);
			// label.setBorder(borderW);
			panelBoard.add(label);
		}

		return panelBoard;
	}

	// =====================================================================================//
	/**
	 * @return JPanel com o painel com as mesas dos engenheiros.
	 * 
	 */
	public JPanel getPanelTitleBoards() {

		JPanel panelTitle = new JPanel();
		panelTitle.setLayout(null);
		panelTitle.setBackground(colorBack);

		// Border borderW = BorderFactory.createLineBorder(Color.WHITE);

		JLabel label = new JLabel(
				"<html><center>Player:<br>" + (oponent == null ? jogador.getNome() : oponent.getNome()), JLabel.CENTER);
		label.setBounds(x, y, width, height);
		// label.setBorder(borderW);
		panelTitle.add(label);

		label = new JLabel("Requisitos", JLabel.CENTER);
		y += height;
		height = yInc;
		label.setBounds(x, y, width, height);
		// label.setBorder(borderW);
		panelTitle.add(label);

		label = new JLabel("Desenhos", JLabel.CENTER);
		y += height;
		label.setBounds(x, y, width, height);
		// label.setBorder(borderW);
		panelTitle.add(label);

		label = new JLabel("Códigos", JLabel.CENTER);
		y += height;
		label.setBounds(x, y, width, height);
		// label.setBorder(borderW);
		panelTitle.add(label);

		label = new JLabel("Rastros", JLabel.CENTER);
		y += height;
		label.setBounds(x, y, width, height);
		// label.setBorder(borderW);
		panelTitle.add(label);

		label = new JLabel("Ajudas", JLabel.CENTER);
		y += height;
		label.setBounds(x, y, width, height);
		// label.setBorder(borderW);
		panelTitle.add(label);

		label = new JLabel("Integração", JLabel.CENTER);
		y += height;
		height = yInc / 2;
		label.setBounds(x, y, width, height);
		// label.setBorder(borderW);
		panelTitle.add(label);

		panelTitle.setBounds(0, 0, width, dimPanel.height);

		return panelTitle;

	}

	// =====================================================================================//
	// Fim da construção das mesas dos engenheiros
	// =====================================================================================//

	/**
	 * Métod responsável por pintar as cartas no tabuleiro
	 * 
	 * @return JPanel que pinta as cartas no tabuleiro.
	 */
	private void getPanelCards() {


	}

	// =====================================================================================//
	/**
	 * Recebe um artefato a ser pintado e verifica, segundo seu estado qual a imagem
	 * correspondente
	 * 
	 * @param art - Artefato a ser pintado
	 * 
	 * @return a imagem no formato ImagIcon a ser pintada em um Label
	 */
	public ImageIcon getImageArtefact(Artefato art) {
		ImageIcon img = null;
		if (art.isPoorQuality() == true) { // Artifact
			// Bad

			if (art.inspected() == true) {

				if (art.isBug() == true) {
					img = ComponentCard.getImageScalable(ScreenInteraction.imagePath + "artefactBadBugged.png", 0,
							height);
				} else {
					img = ComponentCard.getImageScalable(ScreenInteraction.imagePath + "artefactBadOk.png", 0, height);
				}

			} else { // Artifact Bad not inspectioned
				img = ComponentCard.getImageScalable(ScreenInteraction.imagePath + "artefactBad.png", 0, height);
			}

		} else { // Artifact God

			if (art.inspected() == true) {

				if (art.isBug() == true) {
					img = ComponentCard.getImageScalable(ScreenInteraction.imagePath + "artefactGoodBugged.png", 0,
							height);
				} else {
					img = ComponentCard.getImageScalable(ScreenInteraction.imagePath + "artefactGoodOk.png", 0, height);
				}

			} else { // Artifact Bad not inspectioned
				img = ComponentCard.getImageScalable(ScreenInteraction.imagePath + "artefactGood.png", 0, height);

			}

		}
		return img;
	}

	// =====================================================================================//
	/**
	 * @return ActionListener responsável pelo botão usar dos engenheiros
	 * 
	 *         Controla a ação dos engenheiros
	 */
	private ActionListener getActionEnginer() {

		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				int mesa = Integer.parseInt(event.getActionCommand());

				cartaAtiva = jogador.getTabuleiro().getMesas()[mesa].getCartaMesa();

				int back = ScreenCard.createAndShowCard(cartaAtiva, ScreenTabuleiro.this).getReturn();

				switch (back) {
				case ScreenCard.USE:
					Object[] possibilities = { "Produzir Artefato", "Inspecionar Artefato", "Corrigir Artefato" };
					String s = (String) JOptionPane.showInputDialog(ScreenTabuleiro.this,
							"Selecione a ação do engenheiro", "SimulES", JOptionPane.PLAIN_MESSAGE, null, possibilities,
							"Produzir Artefato");

					// If a string was returned, say so.
					if ((s != null) && (s.length() > 0)) {

						if (s.compareToIgnoreCase("Produzir Artefato") == 0) {
							jogador = GameController.getGameController().produzirArtefatoI(jogo, jogador,
									(CartaEngenheiro) cartaAtiva);
						} else if (s.compareToIgnoreCase("Inspecionar Artefato") == 0) {
							jogador = GameController.getGameController().inspecionarArtefatoI(jogo, jogador,
									(CartaEngenheiro) cartaAtiva);
						} else if (s.compareToIgnoreCase("Corrigir Artefato") == 0) {
							jogador = GameController.getGameController().corrigirArtefatoI(jogo, jogador,
									(CartaEngenheiro) cartaAtiva);
						}

					}

					break;
				case ScreenCard.DISCARD:
					jogador = GameController.getGameController().demitirEngenheiro(jogo, jogador,
							(CartaEngenheiro) cartaAtiva);
					break;
				case ScreenCard.BACK:
					// Do nothing
					break;
				}

				if (ScreenCard.BACK != back) {
					panelBase.remove(panelMesas);
					getPanelMesas();
					if (panelMesas != null) {
						panelBase.add(panelMesas, BorderLayout.CENTER);
						refresh(); // Todo [ARS] alterado para refresh ver
									// atualização
					}
				}

			}
		};

		return actionListener;
	}

	// =====================================================================================//

	/**
	 * @return ActionListener responsável por controlar a ação dos botões abaixo das
	 *         cartas no painel de cartas na posição inferior do tabuleiro.
	 */
	public ActionListener getActionButtonCard() {

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (((JButton) e.getSource()).getName().compareToIgnoreCase("Descartar") == 0) {

					System.out.println(((JButton) e.getSource()).getName() + " " + e.getActionCommand());

					Carta[] cartas = new Carta[1];
					int numCard = Integer.parseInt(e.getActionCommand());
					cartas[0] = ScreenTabuleiro.this.jogador.getCartas()[numCard];
					jogador = GameController.getGameController().descartarCartas(jogo, jogador, cartas);

				} else if (((JButton) e.getSource()).getName().compareToIgnoreCase("Usar") == 0) {
					System.out.println(((JButton) e.getSource()).getName() + " " + e.getActionCommand());
					int numCard = Integer.parseInt(e.getActionCommand());
					Carta carta = ScreenTabuleiro.this.jogador.getCartas()[numCard];
					if (carta instanceof CartaEngenheiro) {
						jogador = GameController.getGameController().contratarEngenheiroI(jogo, jogador,
								(CartaEngenheiro) carta);
					}
					// #ifdef ConceptCard
					else if (carta instanceof CartaBonificacao) {
						jogador = GameController.getGameController().inserirBeneficio(jogo, jogador,
								(CartaBonificacao) carta);
					}
					// #endif
				} else {
					System.out.println(((JButton) e.getSource()).getName() + " " + e.getActionCommand() + " Não bateu");
				}
				ScreenTabuleiro.this.refresh();
			}
		};
		return action;
	}

	// =====================================================================================//

	/**
	 * @return ActionListener responsável por controlar os eventos dos botões do
	 *         painel esquerdo do tabuleiro
	 */
	public ActionListener getActionButtonPanel() {

		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getActionCommand().compareToIgnoreCase("Terminar Jogada") == 0) {

					ScreenTabuleiro.this.setVisible(false);

				} else if (e.getActionCommand().compareToIgnoreCase("Jogar Dados") == 0) {

					jogador = GameController.getGameController().jogarDados(jogo, jogador);

					if (jogador.getCartas().length > 0) {
						panelBase.remove(panelCartas);
						//panelCartas = 
						getPanelCards();
						if (panelCartas != null) {
							ScreenTabuleiro.this.refresh();
						}
					}

				} else if (e.getActionCommand().compareToIgnoreCase("Ver Projeto") == 0) {

					GameController.getGameController().verProjeto(jogo);

				} else if (e.getActionCommand().compareToIgnoreCase("Ver Tabuleiro do Oponente") == 0) {

					{
						if (oponent == null) {

							Jogador[] jogadores = jogo.getJogadores();
							String[] nomes = new String[jogadores.length];
							for (int i = 0; i < jogadores.length; i++) {
								nomes[i] = jogadores[i].getNome();
							}

							String nome = ScreenChooseGamer.createAndShowGetGamers(nomes, jogador.getNome())
									.getReturn();

							for (int i = 0; i < jogadores.length; i++) {
								if (nome.compareToIgnoreCase(jogadores[i].getNome()) == 0) {
									oponent = jogadores[i];
								}
							}

						} else {
							oponent = null;
						}
						ScreenTabuleiro.this.refresh();
					} // FIM DO TESTE VER OPONENTE

				}
				System.out.println(e.getActionCommand());
			}

		};

		return action;
	}

	// =====================================================================================//
	/**
	 * @return ActionListener responsável por controlar a chamada de integração de
	 *         módulos.
	 */
	public ActionListener getActionIntegrate() {
		ActionListener action = (new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jogador != null) {
					int numMesa = Integer.parseInt(e.getActionCommand());
					CartaEngenheiro carta = jogador.getTabuleiro().getMesas()[numMesa].getCartaMesa();
					jogador = GameController.getGameController().integrarModuloI(jogo, jogador, carta, numMesa);
					// ScreenInteraction.getScreenInteraction().exibirMensagem("Mesa a integra "
					// + Integer.toString(numMesa) , "");
					ScreenTabuleiro.this.refresh();
				}
			}
		});
		return action;
	}

	// =====================================================================================//

	/**
	 * Método responsável por repintar todo o tabuleiro. O método recalcula a
	 * pisição e recria os objetos a serem pintados.
	 * 
	 * útil principalemente quando o jogador tem propriedades alteradas ou muda-se o
	 * jogador.
	 * 
	 */
	protected void refresh() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				panelTabuleiro = getPanelBase();
				panelCartas.revalidate();
				panelCartas.repaint();

			}
		});
	}

	// =====================================================================================//
	// Métods get e set para jogo e jogador
	/**
	 * @return the jogador
	 */
	public Jogador getJogador() {
		return jogador;
	}

	/**
	 * @param jogador the jogador to set
	 */
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
		this.refresh();
	}

	public void setJogo(Jogo jogoAtual) {
		this.jogo = jogoAtual;
	}

	// =====================================================================================//
	// =====================================================================================//
	/**
	 * Cria a tela que mostra o tabuleiro e a torna visível.
	 * 
	 * @param jogador - Variávelque representa o jogador atual
	 * @param jogo    - Variávelque representa o jogo atual
	 * 
	 * @return ScreenTabuleiro - Retorna a janela de diálogo com as informações do
	 *         jogador.
	 * 
	 *         Para obter o valor de retorno pode-se utilizar a janela com a
	 *         chamada:
	 * 
	 *         createAndShowCard(jogador, jogo);
	 * 
	 *         O tabuleiro não retorna nada ao método chamador, porem pode fazer
	 *         várias chamadas diferentes ao motor do jogo e somente retorna o
	 *         controle ao selecionada a opção de terminar jogada, ou ao fechar a
	 *         janela do jogo, fato que termina o programa.
	 * 
	 */
	public static ScreenTabuleiro createAndShowTabuleiro(Jogador jogador, Jogo jogo) {
		// Cria e configura a tela.
		ScreenTabuleiro scr = new ScreenTabuleiro(jogador, jogo);
		scr.rootPane.setOpaque(true);
		scr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		scr.setSize(1024, 768);
		scr.pack();
		scr.setModal(true);
		scr.addWindowListener(ScreenInteraction.getScreenInteraction().windowsExitGame());
		return scr;
	}

	/****************************************************************************/
	/**************************** TEST FUNCTION *********************************/
	/****************************************************************************/
	public static void main(String[] args) {
		// O uso da Thread com a utilização de invokeLater tem a
		// função da construção total da GUI para somente então
		// apresentá-la na tela.
		final Jogador jogador = new Jogador("Jose", 300, Color.BLACK);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ScreenTabuleiro tab = createAndShowTabuleiro(jogador, null);
				tab.setVisible(true);
			}
		});
	}

}
// =====================================================================================//
// Fim da classe ScreenTabuleiro
// =====================================================================================//
