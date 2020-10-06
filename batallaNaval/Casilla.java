package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Casilla extends JButton {
	private static int casillaSize = 0;
	private static int maxCasillas = 0;
	private static String rutaDelArchivoAgua = "";
	private int idCasilla, row, col;
	private boolean hasBarco;
	private boolean zonaDestruida;
	private Icon imagen;
	//private Portaaviones avioncito;
	
	//Constructor
	public Casilla(int idCasilla, int row, int col) {
		this.idCasilla = idCasilla;
		this.row = row;
		this.col = col;
		this.hasBarco = false;
		this.zonaDestruida = false;
		//Imagen de agua predeterminada
		imagen = new ImageIcon(rutaDelArchivoAgua);
		this.setIcon(imagen);
		
		//Configuración del botón
		this.setBackground(Color.CYAN);
		this.setPreferredSize(new Dimension(casillaSize, casillaSize));
	}
	public static void setCasillaSizeMaxCasillas(int size, int cantidadCasillas ) {
		casillaSize = size;
		maxCasillas = cantidadCasillas;
	}

	public void setImagen(Icon imagen) {
		this.imagen = imagen;
		this.setIcon(imagen);
	}
	public Icon getImagen() {
		return imagen;
	}
	public boolean getHasBarco() {
		return hasBarco;
	}

	public void setHasBarco() {
		this.hasBarco = true;
	}

	public boolean isZonaDestruida() {
		return zonaDestruida;
	}

	public void setZonaDestruida(boolean zonaDestruida) {
		this.zonaDestruida = zonaDestruida;
	}

	public int getIdCasilla() {
		return idCasilla;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
}
