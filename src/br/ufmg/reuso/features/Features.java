/*
 * Federal University of Minas Gerais 
 * Department of Computer Science
 * Simules-SPL Project
 */

package br.ufmg.reuso.features;



public class Features {
	public static boolean aletoriedade = true;
	public static boolean cores = true;
	
	public static final Features INSTANCE = new Features();


	public Features() {

	}
	
	public static Features getInstance() {
		
	    return INSTANCE;
	}

}
