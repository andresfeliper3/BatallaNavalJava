package batallaNaval;

import javax.swing.ImageIcon;

public abstract class Barco {

	private String nombre;
	private int tamanho;
	private ImageIcon imagen;
	boolean naufragado=false;
	
	public Barco(String nombre) {
		
		this.nombre = nombre;
	}
	
	public abstract boolean disparoAcertado(int idCasilla);
	
	public boolean getEstadoBarco() {
		return naufragado;
	}
	
}
