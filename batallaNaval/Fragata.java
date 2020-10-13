/* Autores: Jose David Barona Hern�ndez - 1727590
 *                  Andr�s Felipe Rinc�n    - 1922840
 * Correos: jose.david.barona@correounivalle.edu.co 
 *             andres.rincon.lopez@correounivalle.edu.co
 * Mini proyecto 2: Batalla Naval
 * Fecha: 13/10/2020
 * 
 * */
package batallaNaval;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class Fragata.
 */
public class Fragata extends Barco {

	
	public static final String rutaFile="src/imagenes/fragata.png";
	public static final int numeroCasillas = 1;

	/**
	 * Instantiates a new fragata.
	 */
	public Fragata() {
			
		super(numeroCasillas);
		
		try {
				bufferedImage = ImageIO.read(new File(rutaFile));
				
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo fragata.png");
		}
	}
}
