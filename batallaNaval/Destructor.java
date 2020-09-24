package batallaNaval;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Destructor extends Barco {

	public static final String rutaFile="src/imagenes/destructor.jpg";
	private static BufferedImage bufferImage = null; 
	public static final int numeroCasillas = 2;

	public Destructor(String nombre) {
			
		super(nombre,numeroCasillas);
		naufragado=true;
		
		try {
				bufferImage = ImageIO.read(new File(rutaFile));
				
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo destructor.jpg");
		}
	}
	
}
