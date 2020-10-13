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

	/** The Constant rutaFile. */
	public static final String rutaFile="src/imagenes/fragata.png";
	
	/** The Constant numeroCasillas. */
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
