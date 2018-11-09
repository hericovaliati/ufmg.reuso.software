/*
 * Federal University of Minas Gerais 
 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Michael David
 * Date: 16/07/2011
 */

package br.ufmg.reuso.negocio.dado;



/**
 * @author Michael David
 *
 */

import java.util.Random;

public class Dado {
	
	public static final Dado INSTANCE = new Dado();
	private int sorteados[] = new int[6];
	public int random;

	public Dado() {

	}
	
	public static Dado getInstance() {
		
	    return INSTANCE;
	}

	public void gerarandom() {
		Random sorteio = new Random();
		random = sorteio.nextInt(6) + 1;
	}

	public int sortearValor() {
		int valorSorteado = 0;
		gerarandom();
		valorSorteado = random;
		return valorSorteado;
	}

	public void newrandom(int n) {
		random = n;
	}

	public int sortearValorSemRepeticao() {
		int valorSorteado = 0;
		boolean semrepetir = false;
		while (!semrepetir) {
			Random sorteio = new Random();
			valorSorteado = sorteio.nextInt(6) + 1;
			if (sorteados[valorSorteado - 1] == 0) {
				sorteados[valorSorteado - 1] = 1;
				semrepetir = true;
			}
		}
		return valorSorteado;
	}

	public int contarPontos() {
		int valorSorteado = 0;
		gerarandom();
		valorSorteado = random;
		return valorSorteado;
	}
}
