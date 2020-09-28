package batallaNaval;


import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Portaaviones extends Barco {
	
	public static final String rutaFile="src/imagenes/portaaviones.jpg";
	public static final int numeroCasillas = 4;

	public Portaaviones() {
			
		super(numeroCasillas);
		
		try {
				bufferedImage = ImageIO.read(new File(rutaFile));
				
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo portaaviones.jpg");
		}
	}
	

}
