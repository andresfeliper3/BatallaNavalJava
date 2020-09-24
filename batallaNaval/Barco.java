package batallaNaval;

import javax.swing.ImageIcon;

public abstract class Barco {

	protected String nombre;
	protected int tamanho;
	protected boolean naufragado=false;
	protected Casilla[] casillasDondeEstoy;
	
	public Barco(String nombre, int tamanhoBarco) {
		
		this.nombre = nombre;
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
}
