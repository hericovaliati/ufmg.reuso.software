/*
 * Federal University of Minas Gerais 

 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Hérico / Ramiro / Ana / Matheus
 * Date: 24/10/2018
 */

//
package br.ufmg.reuso.negocio.aspectos;

import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import br.ufmg.reuso.negocio.dado.*;

import java.net.URLConnection;
import org.apache.commons.io.IOUtils;
import br.ufmg.reuso.features.*;

/*
 * Aspecto responsável por consultar serviço na web para obtenção de números realmente
 * aleatórios e não apenas pseudoaleatórios.
 */




public aspect Aleatoriedade {
	
    
	pointcut gerarandom(Dado D) : call(void Dado.gerarandom(..) )  && target(D) && if(Features.aletoriedade);

	after(Dado D) : gerarandom(D) {
		try {
			int n = 0;
			D = new Dado();

			URL url;
			url = new URL("https://www.random.org/integers/?num=1&min=1&max=6&col=1&base=10&format=plain&rnd=new");
			URLConnection con;

			con = url.openConnection();

			InputStream in;

			in = con.getInputStream();

			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String body;

			body = IOUtils.toString(in, encoding).replaceAll("[^a-zA-Z0-9]", "");

			n = Integer.valueOf(body);

			D.newrandom(n);
		} catch (IOException e) {
			System.out.println("Provavel erro de conexão com servidor: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
