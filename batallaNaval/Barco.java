package batallaNaval;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public abstract class Barco {

	protected int tamanho;
	protected boolean naufragado=false;
	protected Casilla[] casillasDondeEstoy;
	protected BufferedImage bufferedImage = null;
	
	//Constructor del Barco
	public Barco(int tamanhoBarco) {
		
		this.tamanho = tamanhoBarco;
		this.casillasDondeEstoy = new Casilla[tamanho];
	}
	//Examina el disparo generado en los tableros para determinar si el barco fue impactado o no
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
	//Método que se encarga de recorrer todas las casillas donde se encuentra ubicado el barco para naufragarlo completamente
	private void hundirBarco() {
		
		for(int i=0;i <casillasDondeEstoy.length;i++) {
			casillasDondeEstoy[i].naufragarBarco();
		}
		naufragado = true;
	}
	//Método que se encarga de revisar el barco, si todas sus partes fueron impactadas, entonces el barco se naufraga
	private void revisarBarco() {
		for(int i=0;i <casillasDondeEstoy.length;i++) {
			if(!casillasDondeEstoy[i].isZonaDestruida()) {
				return;
			}
		}
		hundirBarco();
	}
	//retorna el tamaño del barco
	public int getTamanho() {
		return tamanho;
	}
	// retorna la imágen correspondiente al barco dependiendo del tipo del barco
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	//Método que se encarga de decirle al barco en qué casillas de los tableros va a estar posicionado
	public void setCasillasDondeEstoy(Casilla casilla) {
		
		for(int i =0;i<casillasDondeEstoy.length;i++) {
			if(casillasDondeEstoy[i] == null) {
				casillasDondeEstoy[i] = casilla;
				break;
			}
		}
	}
	//retorna el arreglo de casillas del tablero donde se encuentra el barco ubicado
	public Casilla[] getCasillasDondeEstoy() {
		return casillasDondeEstoy;
	}
	//Retorna el estado del barco, es decir; si el barco fue hundido, el barco pasa a estado naufragado = true
	public boolean getEstadoBarco() {
		return naufragado;
	}
	
	

}
