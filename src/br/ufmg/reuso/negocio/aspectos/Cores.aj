/*
 * Federal University of Minas Gerais 

 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Hérico / Ramiro / Ana / Matheus
 * Date: 24/10/2018
 */

package br.ufmg.reuso.negocio.aspectos;

import br.ufmg.reuso.features.*;
import java.awt.Color;

import javax.swing.JPanel;

import br.ufmg.reuso.negocio.jogo.*;
import br.ufmg.reuso.presentation.ScreenTabuleiro;

public aspect Cores {
		
	Color colorDefault = Color.LIGHT_GRAY;
	
	pointcut init(Jogo T) : call(void Jogo.init(..) )  && target(T) && if(Features.cores);
	
	before(Jogo T) : init(T) {
		
		
		T.cores[0] = Color.RED;
		T.cores[1] = Color.PINK;
		T.cores[2] = Color.ORANGE;
		T.cores[3] = Color.BLUE;
		T.cores[4] = Color.GRAY;
		T.cores[5] = Color.GREEN;

	}
	

	
	pointcut getPanelButtonsRight(ScreenTabuleiro T) : call(void ScreenTabuleiro.getPanelButtonsRight(..) )  && target(T) && if(Features.cores);;
	
	before(ScreenTabuleiro T) : getPanelButtonsRight(T) {
		
		T.colorBack = Color.BLACK;

	}
	
	
    pointcut getPanelButtonsLeft(ScreenTabuleiro T) : call(void ScreenTabuleiro.getPanelButtonsLeft(..) )  && target(T) && if(Features.cores);;
	
	before(ScreenTabuleiro T) : getPanelButtonsLeft(T) {
		
		T.colorBack = Color.YELLOW;
		
	}
	
		
    pointcut getPanelMesas(ScreenTabuleiro T) : call(void ScreenTabuleiro.getPanelMesas(..) )  && target(T) && if(Features.cores);;
	
	before(ScreenTabuleiro T) : getPanelMesas(T) {
		
		T.colorBack = Color.CYAN;
		
	}
	
		
    pointcut getPanelTitleBoards(ScreenTabuleiro T) : call(JPanel ScreenTabuleiro.getPanelTitleBoards(..) )  && target(T) && if(Features.cores);;
	
	before(ScreenTabuleiro T) : getPanelTitleBoards(T) {
		
		Color cor = T.getJogador().getCor();
		
		T.colorBack = cor;
		
	}
	
	
    pointcut getPanelCards(ScreenTabuleiro T) : call(void ScreenTabuleiro.getPanelCards(..) )  && target(T) && if(Features.cores);;
	
	before(ScreenTabuleiro T) : getPanelCards(T) {
		
		Color cor = T.getJogador().getCor();
		
		T.colorBack = cor;
		
	}
	
}