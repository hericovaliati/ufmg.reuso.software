/*
 * Federal University of Minas Gerais 
 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Michael David
 * Date: 16/07/2011
 */


package br.ufmg.reuso.negocio.jogadorIterador;

/**
 * @author Michael David
 *
 */

import java.util.Iterator;

public class JogadorIterador<Jogador> implements Iterable<Jogador> {

    private Jogador[] arrayList;
    private int currentSize;

    public JogadorIterador(Jogador[] newArray) {
        this.arrayList = newArray;
        this.currentSize = arrayList.length;
    }

    @Override
    public Iterator<Jogador> iterator() {
        Iterator<Jogador> it = new Iterator<Jogador>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Jogador next() {
                return arrayList[currentIndex++];
            }
            

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}

