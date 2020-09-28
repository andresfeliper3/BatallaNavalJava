package batallaNaval;

import java.awt.image.BufferedImage;

public abstract class Barco {

	protected int tamanho;
	protected boolean naufragado=false;
	protected Casilla[] casillasDondeEstoy;
	protected static BufferedImage bufferImage = null; 
	public Barco(int tamanhoBarco) {
		
		
		this.tamanho = tamanhoBarco;
		this.casillasDondeEstoy = new Casilla[tamanho];

	}
	
	public boolean disparoAcertado(Casilla casilla) {
		// TODO Auto-generated method stub
		for(int i = 0; i <= casillasDondeEstoy.length;i++ ) {

			if(casilla == casillasDondeEstoy[i]) {

				casillasDondeEstoy[i] = null;
				revisarBarco();
				return true;
			}
		}
		return false;

	}
	
	public boolean getEstadoBarco() {
		return naufragado;
	}
	
	public void hundirBarco() {
		naufragado = true;
	}
	
	public void revisarBarco() {
		for(int i=0;i <casillasDondeEstoy.length;) {
			
			if(casillasDondeEstoy[i] != null) {
				return;
			}
		}
		hundirBarco();
	}

	public int getTamanho() {
		return tamanho;
	}

	public static BufferedImage getBufferImage() {
		return bufferImage;
	}
	
	
}
