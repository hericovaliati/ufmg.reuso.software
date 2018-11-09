/*
 * Federal University of Minas Gerais 

 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Hérico / Ramiro / Ana / Matheus
 * Date: 24/10/2018
 */

package br.ufmg.reuso.negocio.aspectos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import javax.swing.JButton;
import javax.swing.JLabel;

import br.ufmg.reuso.negocio.carta.Artefato;
import br.ufmg.reuso.negocio.carta.Carta;
import br.ufmg.reuso.negocio.carta.CartaBonificacao;
import br.ufmg.reuso.negocio.carta.CartaEngenheiro;
import br.ufmg.reuso.negocio.carta.CartaPenalizacao;
import br.ufmg.reuso.negocio.jogador.Jogador;
import br.ufmg.reuso.presentation.ComponentCard;
import br.ufmg.reuso.presentation.ScreenTabuleiro;

public aspect Dimensoes {
		
	Color colorDefault = Color.LIGHT_GRAY;
	// ***** pointcut initialize ****************************************************** //
	
	pointcut initialize(ScreenTabuleiro T) : call(void ScreenTabuleiro.initialize(..) )  && target(T);
	
	after(ScreenTabuleiro T) : initialize(T) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		screenSize.setSize(1000, 700);
		T.setPreferredSize(screenSize);
		T.mySise = screenSize;
	}
	
	
	pointcut getPanelButtonsRight(ScreenTabuleiro T) : call(void ScreenTabuleiro.getPanelButtonsRight(..) )  && target(T);
	
	after(ScreenTabuleiro T) : getPanelButtonsRight(T) {
		
		Dimension myDim = new Dimension(150,500);
		
		//JPanel jpanel = new JPanel();
		T.panelButtonsRight = new JPanel();

		T.panelButtonsRight.setBorder(BorderFactory.createRaisedBevelBorder());

		T.panelButtonsRight.setPreferredSize(myDim);

		T.panelButtonsRight.setBackground(T.colorBack);		
		
		T.panelButtonsRight.setLayout(null);
		
		String messager = "Situação do jogo\n";

		// Font font = new Font("Default", Font.PLAIN, 25);

		if (T.jogador != null) {
			// jogador.getTabuleiro().getMesas()[1].getModuloIntegrado()

		}

		JTextPane paneDesc = new JTextPane();
		SimpleAttributeSet bSet = new SimpleAttributeSet();
		StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_JUSTIFIED);
		StyledDocument doc = paneDesc.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), bSet, false);
		paneDesc.setText(messager);
		paneDesc.setInheritsPopupMenu(true);
		paneDesc.setAlignmentX( java.awt.Component.CENTER_ALIGNMENT);
		// paneDesc.setFont(font);
		paneDesc.setBorder(BorderFactory.createLineBorder(Color.white));
		paneDesc.setEditable(false);
		paneDesc.setEnabled(false);
		JScrollPane sliderPaneDesc = new JScrollPane(paneDesc);
		sliderPaneDesc.setViewportView(paneDesc);
		sliderPaneDesc.setBounds(0, 0, myDim.width, 90 * myDim.height / 100);
		T.panelButtonsRight.add(sliderPaneDesc);
		T.colorBack = colorDefault;
		
	}
	
	pointcut getPanelButtonsLeft(ScreenTabuleiro T) : call(void ScreenTabuleiro.getPanelButtonsLeft(..) )  && target(T);
	
	
	after(ScreenTabuleiro T) : getPanelButtonsLeft(T) {
		
		Dimension myDim = new Dimension(150, 400);
		//Dimension myDim = new Dimension(T.mySise.width * 10 / 100, T.mySise.height * 60 / 100);

		// Define componentes
		T.panelButtonsLeft = new JPanel();
		T.panelButtonsLeft.setBorder(BorderFactory.createRaisedBevelBorder());
		T.panelButtonsLeft.setBackground(T.colorBack);
		T.panelButtonsLeft.setPreferredSize(myDim);
		T.panelButtonsLeft.setLayout(null);

		T.buttonTabuleiro = new JButton((T.oponent == null ? "<html><Center>Ver Tabuleiro do Oponente"
				: "<html><Center>Voltar para seu tabuleiro."));
		T.buttonTabuleiro.setActionCommand("Ver Tabuleiro do Oponente");
		T.buttonTabuleiro.addActionListener(T.getActionButtonPanel());

		T.buttonProjeto = new JButton("<html><Center>Ver Projeto");
		T.buttonProjeto.setActionCommand("Ver Projeto");
		T.buttonProjeto.addActionListener(T.getActionButtonPanel());

		T.buttonDados = new JButton("<html><Center>Jogar Dados");
		boolean jogadados;
		if ((T.jogador == null) || (T.oponent != null)) {
			jogadados = false;
		} else {
			jogadados = !T.jogador.isDadoJogado();
		}
		T.buttonDados.setEnabled(jogadados);
		T.buttonDados.setActionCommand("Jogar Dados");
		T.buttonDados.addActionListener(T.getActionButtonPanel());

		T.buttonEnd = new JButton("<html><Center>Terminar Jogada");
		T.buttonEnd.setActionCommand("Terminar Jogada");
		T.buttonEnd.addActionListener(T.getActionButtonPanel());
		T.buttonEnd.setEnabled(T.oponent == null);

		// Define posições
		int x, y, width, height, ygap, numButtons;
		x = y = width = height = ygap = numButtons = 0;

		numButtons = 4;
		width = myDim.width * 90 / 100;
		x = (myDim.width - width) / 2;
		ygap = (myDim.height) / ((2 * numButtons) + 1);
		height = (myDim.height) / (2 * numButtons + 1);
		y = ygap / 2;
		T.buttonTabuleiro.setBounds(x, y, width, height);

		y += ygap + height;
		T.buttonProjeto.setBounds(x, y, width, height);

		y += ygap + height;
		T.buttonDados.setBounds(x, y, width, height);

		y += ygap + height;
		T.buttonEnd.setBounds(x, y, width, height);

		// Define Adiciona no painel
		T.panelButtonsLeft.add(T.buttonTabuleiro);
		T.panelButtonsLeft.add(T.buttonProjeto);
		T.panelButtonsLeft.add(T.buttonDados);
		T.panelButtonsLeft.add(T.buttonEnd);

		//return jpanel;
		
		T.colorBack = colorDefault;
		
	}
	
    pointcut getPanelMesas(ScreenTabuleiro T) : call(void ScreenTabuleiro.getPanelMesas(..) )  && target(T);
	

	
	after(ScreenTabuleiro T) : getPanelMesas(T) {
		T.panelMesas = new JPanel();
		
		T.panelMesas.setLayout(null);

		T.panelMesas.setBorder(BorderFactory.createLoweredBevelBorder());

		T.panelMesas.setBackground(T.colorBack);

		T.dimPanel = new Dimension(T.mySise.width * 70 / 100, T.mySise.height * 57 / 100);

		T.panelMesas.setPreferredSize(T.dimPanel);

		Border borderW = BorderFactory.createLineBorder(Color.white, 1);

		// int x, y, height, width, xInc, yInc; // Definido em escopo geral

		T.x = T.y = 0;
		T.xInc = T.dimPanel.width / 6;
		T.yInc = 2 * T.dimPanel.height / 15;
		T.height = T.yInc * 2;
		T.width = T.xInc;

		// Desenha os títulos das mesas
		T.panelMesas.add(T.getPanelTitleBoards());

		{
			JPanel panelBoard;
			JScrollPane scrollBoard;
			Jogador jogadorAtual = (T.oponent == null ? T.jogador : T.oponent);

			for (int i = 0; i < jogadorAtual.getTabuleiro().getMesas().length; i++) {

				T.board = jogadorAtual.getTabuleiro().getMesas()[i];

				panelBoard = new JPanel();
				panelBoard.setLayout(null);
				panelBoard.setBackground(T.colorBack);

				T.y = 0;
				T.height = T.yInc;
				T.width = T.xInc;
				T.x = 0;
				int maxBoardSize = 0;

				JLabel label = null;

				// Desenha os engenheiros na mesa
				panelBoard = T.getEnginerLabel(i, panelBoard);

				// ScreenInteraction.getScreenInteraction().exibirMensagem(Integer.toString(x),
				// "");

				{// Inicio do colocação da matriz de artefatos
					ArrayList<ArrayList<Artefato>> modulo = new ArrayList<ArrayList<Artefato>>();
					modulo.add(T.board.getRequisitos());
					modulo.add(T.board.getDesenhos());
					modulo.add(T.board.getCodigos());
					modulo.add(T.board.getRastros());
					modulo.add(T.board.getAjudas());

					Vector<String> names = new Vector<String>(
							Arrays.asList(new String[] { "Requisitos", "Desenhos", "Códigos", "Rastros", "Ajudas" }));
					int j = 0;

					int mesa = i;

					// Para cada tipo de artefato
					Iterator<ArrayList<Artefato>> itModulo = modulo.iterator();
					while (itModulo.hasNext()) {

						ArrayList<Artefato> artefatos = itModulo.next();
						T.x = 0;

						if (artefatos.size() > 0) {
							Iterator<Artefato> it = artefatos.iterator();
							Artefato art = null;
							ImageIcon img = null;
							T.y += T.height;
							T.height = T.yInc / 2;
							while (it.hasNext()) {

								art = it.next();

								img = T.getImageArtefact(art);

								label = new JLabel();

								label.setIcon(img);

								label.setBounds(T.x + 2, T.y + (T.height / 2), img.getIconWidth(), img.getIconHeight());

								if (it.hasNext() == true) {
									T.x += img.getIconWidth();
									maxBoardSize = (T.x > maxBoardSize ? T.x : maxBoardSize);
								}

								// label.setBorder(borderW);
								panelBoard.add(label);

							} // Fim do while sobre os artafatos de um tipo

							// y += height/2;
							T.height = T.yInc;

						} else { // Se não há artefatos do tipo atual
							label = new JLabel(names.elementAt(j) + " mesa " + Integer.toString(mesa + 1));
							label.setOpaque(false);

							T.y += T.height;
							T.height = T.yInc;
							label.setBounds(T.x, T.y, T.width, T.height);
							// label.setBorder(borderW);
							panelBoard.add(label);
						}

						j++;
					} // Fim do while sobre tipos de artefatos

					// Inserir Requisitos

				} // Fim do colocação da matriz de artefatos

				T.x = 0;

				JButton buttonIntegrate = new JButton("Integrar");
				T.y += T.height;
				T.height = T.yInc / 2;
				// width = (maxBoardSize > width ? maxBoardSize : width);
				buttonIntegrate.setBounds(T.x, T.y, T.width, T.height);
				buttonIntegrate.setActionCommand(Integer.toString(i));
				buttonIntegrate.addActionListener(T.getActionIntegrate());
				buttonIntegrate.setEnabled(T.oponent == null);

				panelBoard.add(buttonIntegrate);

				panelBoard.setBounds(T.width * (i + 1), 0, maxBoardSize, T.dimPanel.height);
				panelBoard.setPreferredSize(new Dimension(maxBoardSize, T.dimPanel.height));
				panelBoard.setBorder(borderW);

				scrollBoard = new JScrollPane();
				scrollBoard.setBorder(null);
				scrollBoard.setBounds(T.width * (i + 1), 0, T.width, T.dimPanel.height);
				scrollBoard.setViewportView(panelBoard);
				T.panelMesas.add(scrollBoard);

			}

		}
		//return jpanel;
		
		T.colorBack = colorDefault;
		
	}
	
    pointcut getPanelTitleBoards(ScreenTabuleiro T) : call(JPanel ScreenTabuleiro.getPanelTitleBoards(..) )  && target(T);
	

	
	after(ScreenTabuleiro T) : getPanelTitleBoards(T) {
		
		
		T.colorBack = colorDefault;
		
	}
	
    pointcut getPanelCards(ScreenTabuleiro T) : call(void ScreenTabuleiro.getPanelCards(..) )  && target(T);
	

	
	after(ScreenTabuleiro T) : getPanelCards(T) {
		Dimension Mydim = new Dimension(T.mySise.width, T.mySise.height * 37 / 100);

		Border border = BorderFactory.createRaisedBevelBorder();

		T.panelCartas = new JPanel();
		T.panelCartas.setLayout(null);
		T.panelCartas.setBackground(T.colorBack);
		T.panelCartas.setPreferredSize(Mydim);
		T.panelCartas.setBorder(border);

		Carta[] carta = T.jogador.getCartas();

		int x, y, width, height, xgap;

		x = y = width = height = xgap = 0;

		xgap = (Mydim.width / (carta.length + 1)) * 30 / 100;
		x = xgap;

		JPanel component = null;
		for (int i = 0; i < carta.length; i++) {

			height = Mydim.height * 80 / 100;
			width = (Mydim.width / carta.length) * 70 / 100;

			x = (x == xgap ? x : x + xgap);
			y = Mydim.height * 5 / 100;

			if (carta[i] != null) {
				component = new ComponentCard(carta[i], T);
				component.setBorder(border);
				component.setBounds(x, y, width, height);
				T.panelCartas.add(component);

				y += height;
				width = width / 2;

				height = Mydim.height * 10 / 100;
				JButton btnUse = new JButton("Usar");
				btnUse.setName("Usar");
				btnUse.setBounds(x, y, width, height);
				btnUse.setActionCommand(Integer.toString(i));
				btnUse.addActionListener(T.getActionButtonCard());

				if (T.oponent == null) {
					if (carta[i] instanceof CartaPenalizacao) {
						btnUse.setEnabled(false);
					}
				} else {
					if (
					// #ifdef ConceptCard
					(carta[i] instanceof CartaBonificacao) ||
					// #endif
							(carta[i] instanceof CartaEngenheiro)) {
						btnUse.setEnabled(false);
					}
				}

				T.panelCartas.add(btnUse);

				x += width;
				JButton btnDiscard = new JButton("Descartar");
				btnDiscard.setName("Descartar");
				btnDiscard.setBounds(x, y, width, height);
				btnDiscard.setActionCommand(Integer.toString(i));
				btnDiscard.addActionListener(T.getActionButtonCard());
				T.panelCartas.add(btnDiscard);

				x += width;

			} // Fim da carta[i]
			else {
				JLabel label = new JLabel("Vazio", JLabel.CENTER);
				label.setBorder(border);
				label.setBounds(x, y, width, height);
				T.panelCartas.add(label);
				x += width;
			}

		} // end for

		//return jpanel;
		
		T.colorBack = colorDefault;
		
	}
    
}