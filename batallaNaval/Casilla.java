package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Casilla extends JButton {
	private static int width = 10;
	private static int height = 10;
	private static String rutaDelArchivoAgua = "";
	private int idCasilla, row, col;
	private boolean hasBarco;
	private boolean zonaDestruida;
	private ImageIcon imagen;
	
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
		this.setPreferredSize(new Dimension(width, height));
	}

	public void cambiarImagen(ImageIcon imagen) {
		this.imagen = imagen;
		this.setIcon(imagen);
	}
	public boolean isHasBarco() {
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
	
}
