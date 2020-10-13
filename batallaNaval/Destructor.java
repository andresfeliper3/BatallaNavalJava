/* Autores: Jose David Barona Hernández - 1727590
 *                  Andrés Felipe Rincón    - 1922840
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
 * The Class Destructor.
 */
public class Destructor extends Barco {


	public static final String rutaFile="src/imagenes/destructor.png";
	public static final int numeroCasillas = 2;

	/**
	 * Instantiates a new destructor.
	 */
	public Destructor() {
			
		super(numeroCasillas);
		
		try {
				bufferedImage = ImageIO.read(new File(rutaFile));
				
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo destructor.png");
		}
	}
	
}
