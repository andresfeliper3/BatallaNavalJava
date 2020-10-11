package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Casilla extends JButton {
	public static final ImageIcon hundido = new ImageIcon("src/imagenes/hundido.gif");	
	public static final ImageIcon errado = new ImageIcon("src/imagenes/agua.gif");
	public static final ImageIcon tocado = new ImageIcon("src/imagenes/tocado.gif");
	private static int casillaSize = 0;
	private static int maxCasillas = 0;
	private static int lineCasillas = 0;
	private int idCasilla, row, col;
	private boolean hasBarco, zonaDestruida, hasWater;
	private Icon imagen;
	private String texto;		
	//Constructor
	public Casilla(int idCasilla, int row, int col) {
		this.idCasilla = idCasilla;
		this.row = row;
		this.col = col;
		this.hasBarco = false;
		this.zonaDestruida = false;
		//Revisar si tiene agua
		revisarSiTieneAgua();	
	
		
		this.setPreferredSize(new Dimension(casillaSize, casillaSize));
	}
	public static void setCasillaSizeMaxCasillas(int size, int cantidadCasillas, int casillasDeLinea ) {
		casillaSize = size;
		maxCasillas = cantidadCasillas;
		lineCasillas = casillasDeLinea;
	}

	//Revisa si está en la primera fila para poner letras o en la primera columna para poner números
	private void revisarSiTieneAgua() {
		//Primera ficha
		if(row == 0 && col == 0) {
			this.setText("");
			this.hasWater = false;
		}
		else if(row == 0) {
			this.hasWater = false;
			char letra = (char)('A' + col-1);
			this.setText(String.valueOf(letra));
		}
		else if(col == 0) {
			this.hasWater = false;
			this.setText(String.valueOf(row));
		}
		else {
			this.hasWater = true;
			this.setBackground(Color.CYAN);
		}
	}
	public void setImagen(Icon imagen) {
		this.imagen = imagen;
		this.setIcon(imagen);
	}
	public void setHasWater(boolean hasWater) {
		this.hasWater = hasWater;
	}
	public boolean getHasWater() {
		return hasWater;
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
		if(hasBarco) {
			this.setIcon(tocado);
		}
		else {
			this.setIcon(errado);
		}
		this.zonaDestruida = zonaDestruida;
	}
	
	public void naufragarBarco() {
		this.setIcon(hundido);
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
