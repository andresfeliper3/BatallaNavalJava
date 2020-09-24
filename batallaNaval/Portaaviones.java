package batallaNaval;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Portaaviones extends Barco {
	
	public static final String rutaFile="src/imagenes/portaaviones.jpg";
	private static BufferedImage bufferImage = null; 
	public static final int numeroCasillas = 4;

	public Portaaviones(String nombre) {
			
		super(nombre, numeroCasillas);
		
		try {
				bufferImage = ImageIO.read(new File(rutaFile));
				
		}catch(IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"No se ha encontrado el archivo portaaviones.jpg");
		}
	}

	//Retorna true si el barco fue golpeado y false si no fue golpeado.
	@Override
	public boolean disparoAcertado(Casilla casilla) {
		// TODO Auto-generated method stub
		 for(int i = 0; i < casillasDondeEstoy.length; i++) {
			 if(casilla == casillasDondeEstoy[i]) {
				 casillasDondeEstoy[i] = null;
				 return true;
			 }
		 }
		return false;
	}
	
	public void hundirBarco() {
		naufragado = true;
	}
	
	public void revisarEstadoBarco() {
		for(int i = 0; i < casillasDondeEstoy.length; i++) {	
			if(casillasDondeEstoy[i] != null) {
				return;
			}
		}
		hundirBarco();
	}

}
