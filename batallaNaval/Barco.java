package batallaNaval;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public abstract class Barco {

	protected int tamanho;
	protected boolean naufragado=false;
	protected Casilla[] casillasDondeEstoy;
	protected BufferedImage bufferedImage = null; 
	public Barco(int tamanhoBarco) {
		
		
		this.tamanho = tamanhoBarco;
		this.casillasDondeEstoy = new Casilla[tamanho];

	}
	
	public boolean disparoAcertado(Casilla casilla) {
		// TODO Auto-generated method stub
		for(int i = 0; i < casillasDondeEstoy.length;i++ ) {

			if(casilla == casillasDondeEstoy[i]) {

				casillasDondeEstoy[i].setZonaDestruida(true);
				revisarBarco();
				
				return true;
			}
		}
		return false;

	}
	
	public boolean getEstadoBarco() {
		return naufragado;
	}
	
	private void hundirBarco() {
		
		for(int i=0;i <casillasDondeEstoy.length;i++) {
			casillasDondeEstoy[i].naufragarBarco();
		}
		naufragado = true;
	}
	
	private void revisarBarco() {
		for(int i=0;i <casillasDondeEstoy.length;i++) {
			
			if(!casillasDondeEstoy[i].isZonaDestruida()) {
				return;
			}
		}
		hundirBarco();
	}

	public int getTamanho() {
		return tamanho;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public void setCasillasDondeEstoy(Casilla casilla) {
		
		for(int i =0;i<casillasDondeEstoy.length;i++) {
			if(casillasDondeEstoy[i] == null) {
				casillasDondeEstoy[i] = casilla;
				break;
			}
		}
	}

	public Casilla[] getCasillasDondeEstoy() {
		return casillasDondeEstoy;
	}
	

}
