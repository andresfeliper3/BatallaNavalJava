package batallaNaval;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public abstract class Barco {

	protected int tamanho;
	protected boolean naufragado = false;
	protected Casilla[] casillasDondeEstoy;
	protected BufferedImage bufferedImage = null;
	
	public Barco(int numeroCasillas) {
		
		this.tamanho = numeroCasillas;
		this.casillasDondeEstoy = new Casilla[tamanho];
	}
	
	//Retorna true si el barco fue golpeado y false si no fue golpeado.
	public boolean disparoAcertado(Casilla casilla) {
		// TODO Auto-generated method stub
		
		 for(int i = 0; i < casillasDondeEstoy.length; i++) {
			 if(casilla == casillasDondeEstoy[i]) {
				 casillasDondeEstoy[i].setZonaDestruida(true);
				 revisarEstadoBarco();
				 return true;
			 }
		 }
		return false;
	}
	//Añade una casilla al array del barco
	public void setCasillasDondeEstoy(Casilla casilla) {
		for(int i = 0; i < tamanho; i++) {
			if(casillasDondeEstoy[i] == null) {
				casillasDondeEstoy[i] = casilla;
				break;
			}
		}
	}
	private void hundirBarco() {
		naufragado = true;
		for(int i = 0; i < casillasDondeEstoy.length; i++) {
			casillasDondeEstoy[i].naufragarBarco();
		}
	}
	
	public int getTamanho() {
		return tamanho;
	}

	//Revisa  el estado del barco
	private void revisarEstadoBarco() {
		for(int i = 0; i < casillasDondeEstoy.length; i++) {	
			if(!casillasDondeEstoy[i].isZonaDestruida()) {
				return;
			}
		}
		hundirBarco();
	}
	
	public boolean getEstadoBarco() {
		return naufragado;
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public Casilla[] getCasillasDondeEstoy() {
		return casillasDondeEstoy;
	}
	
	
}
