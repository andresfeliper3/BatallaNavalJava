package batallaNaval;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Fragata extends Barco {
	public static final String rutaFile="src/imagenes/fragata.jpg";
	private static BufferedImage bufferImage = null; 
	public static final int numeroCasillas = 1;
	
	public Fragata(String nombre, int numeroCasillas) {
		super(nombre, numeroCasillas);
		// TODO Auto-generated constructor stub
		
		try {
				bufferImage = ImageIO.read(new File(rutaFile));
				
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo fragata.jpg");
		}
	}

}
