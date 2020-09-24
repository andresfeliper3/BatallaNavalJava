package batallaNaval;

import javax.swing.ImageIcon;

public abstract class Barco {

	protected String nombre;
	protected int tamanho;
	protected boolean naufragado=false;
	protected Casilla[] casillasDondeEstoy;
	
	public Barco(String nombre, int numeroCasillas) {
		
		this.nombre = nombre;
		this.tamanho = numeroCasillas;
		this.casillasDondeEstoy = new Casilla[tamanho];
	}
	
	//Retorna true si el barco fue golpeado y false si no fue golpeado.
	public boolean disparoAcertado(Casilla casilla) {
		// TODO Auto-generated method stub
		
		 for(int i = 0; i < casillasDondeEstoy.length; i++) {
			 if(casilla == casillasDondeEstoy[i]) {
				 casillasDondeEstoy[i] = null;
				 revisarEstadoBarco();
				 return true;
			 }
		 }
		return false;
	}
	
	private void hundirBarco() {
		naufragado = true;
	}
	
	private void revisarEstadoBarco() {
		for(int i = 0; i < casillasDondeEstoy.length; i++) {	
			if(casillasDondeEstoy[i] != null) {
				return;
			}
		}
		hundirBarco();
	}
	
	public boolean getEstadoBarco() {
		return naufragado;
	}
	
}
