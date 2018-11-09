/*
 * Federal University of Minas Gerais 
 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Michael David
 * Date: 16/07/2011
 */

package br.ufmg.reuso.features;



/**
 * @author 
 *
 */



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
