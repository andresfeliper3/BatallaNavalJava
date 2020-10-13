package batallaNaval;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class Submarino.
 */
public class Submarino extends Barco {

	public static final String rutaFile="src/imagenes/submarino.png";
	public static final int numeroCasillas = 3;

	/**
	 * Instantiates a new submarino.
	 */
	public Submarino() {
			
		super(numeroCasillas);
		
		try {
				bufferedImage = ImageIO.read(new File(rutaFile));
				
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo submarino.png");
		}
	}
}
