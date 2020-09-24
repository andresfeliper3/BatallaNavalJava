package batallaNaval;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Portaaviones extends Barco {
	
	public static final String rutaFile="src/imagenes/portaaviones.jpg";
	private static BufferedImage bufferImage = null; 
	private Casillas[] casillasDondeEstoy;

	public Portaaviones(String nombre) {
			
		super(nombre);
		
		try {
				bufferImage = ImageIO.read(new File(rutaFile));
				casillasDondeEstoy = new int[4];
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo portaaviones.jpg");
		}
	}

	@Override
	public boolean disparoAcertado(int idCasilla) {
		// TODO Auto-generated method stub
		return false;
	}

}
