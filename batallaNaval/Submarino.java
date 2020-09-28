package batallaNaval;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Submarino extends Barco {

	public static final String rutaFile="src/imagenes/submarino.jpg";
	public static final int numeroCasillas = 3;

	public Submarino() {
		super(numeroCasillas);
		// TODO Auto-generated constructor stub	
		try {
				bufferedImage = ImageIO.read(new File(rutaFile));
				
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo submarino.jpg");
		}
	}
	
}
