package batallaNaval;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;

// TODO: Auto-generated Javadoc
/**
 * The Class Barco.
 */
public abstract class Barco {

	/** The tamanho. */
	protected int tamanho;
	
	/** The naufragado. */
	protected boolean naufragado=false;
	
	/** The casillas donde estoy. */
	protected Casilla[] casillasDondeEstoy;
	
	/** The buffered image. */
	protected BufferedImage bufferedImage = null;
	
	/**
	 * Instantiates a new barco.
	 *
	 * @param tamanhoBarco the tamanho barco
	 */
	//Constructor del Barco
	public Barco(int tamanhoBarco) {
		
		this.tamanho = tamanhoBarco;
		this.casillasDondeEstoy = new Casilla[tamanho];
	}
	
	/**
	 * Disparo acertado.
	 *
	 * @param casilla the casilla
	 * @return true, if successful
	 */
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
	
	/**
	 * Hundir barco.
	 */
	//Método que se encarga de recorrer todas las casillas donde se encuentra ubicado el barco para naufragarlo completamente
	private void hundirBarco() {
		
		for(int i=0;i <casillasDondeEstoy.length;i++) {
			casillasDondeEstoy[i].naufragarBarco();
		}
		naufragado = true;
	}
	
	/**
	 * Revisar barco.
	 */
	//Método que se encarga de revisar el barco, si todas sus partes fueron impactadas, entonces el barco se naufraga
	private void revisarBarco() {
		for(int i=0;i <casillasDondeEstoy.length;i++) {
			if(!casillasDondeEstoy[i].isZonaDestruida()) {
				return;
			}
		}
		hundirBarco();
	}
	
	/**
	 * Gets the tamanho.
	 *
	 * @return the tamanho
	 */
	//retorna el tamaño del barco
	public int getTamanho() {
		return tamanho;
	}
	
	/**
	 * Gets the buffered image.
	 *
	 * @return the buffered image
	 */
	// retorna la imágen correspondiente al barco dependiendo del tipo del barco
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	/**
	 * Sets the casillas donde estoy.
	 *
	 * @param casilla the new casillas donde estoy
	 */
	//Método que se encarga de decirle al barco en qué casillas de los tableros va a estar posicionado
	public void setCasillasDondeEstoy(Casilla casilla) {
		
		for(int i =0;i<casillasDondeEstoy.length;i++) {
			if(casillasDondeEstoy[i] == null) {
				casillasDondeEstoy[i] = casilla;
				break;
			}
		}
	}
	
	/**
	 * Gets the casillas donde estoy.
	 *
	 * @return the casillas donde estoy
	 */
	//retorna el arreglo de casillas del tablero donde se encuentra el barco ubicado
	public Casilla[] getCasillasDondeEstoy() {
		return casillasDondeEstoy;
	}
	
	/**
	 * Gets the estado barco.
	 *
	 * @return the estado barco
	 */
	//Retorna el estado del barco, es decir; si el barco fue hundido, el barco pasa a estado naufragado = true
	public boolean getEstadoBarco() {
		return naufragado;
	}
	
	

}
