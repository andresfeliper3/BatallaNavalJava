package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Casilla extends JButton {
	private static int casillaSize=0;
	private static int maxCasillas=0;
	
	private static  final Icon hundido = new ImageIcon("src/imagenes/hundido.gif");
	private static  final Icon agua = new ImageIcon("src/imagenes/agua.gif");
	private static  final Icon tocado = new ImageIcon("src/imagenes/tocado.gif");
	
	private int idCasilla, row, col;
	private boolean hasBarco;
	private boolean zonaDestruida;
	private Icon imagen;
	private boolean isWater;
	private boolean naufragado;
	
	//Constructor
	public Casilla(int idCasilla, int row, int col) {
		this.idCasilla = idCasilla;
		this.row = row;
		this.col = col;
		this.hasBarco = false;
		this.zonaDestruida = false;
		//Imagen de agua predeterminada
		
		if(row == 0 && col == 0) {
			
		}
		else if(row == 0) {
			char letra = (char) ('A' + (col-1));
			this.setText(Character.toString(letra));
			this.isWater = false;

			
		}else if(col == 0) {
			this.setText(row+"");
			this.isWater = false;


		}else {
			this.setIcon(imagen);
			this.isWater = true;
			this.setBackground(Color.CYAN);
		}
		//Configuración del botón
		
		this.setPreferredSize(new Dimension(casillaSize, casillaSize));
	}

	public static void setCasillaSizeMaxCasillas(int tamanho,int numeroCasillas) {
		casillaSize= tamanho;
		maxCasillas= numeroCasillas;	
	}
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setImagen(Icon imagen) {
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
		
		if(this.isHasBarco()) {
			setImagen(tocado);
		}if(this.isWater && !isHasBarco()) {
			setImagen(agua);
		}
	}
	public void naufragarBarco() {
		this.naufragado=true;
		setImagen(hundido);
	}

	public int getIdCasilla() {
		return idCasilla;
	}
	public Icon getImagen() {
		return imagen;
	}
	public boolean isWater() {
		return isWater;
	}
	public boolean isNaufragado() {
		return naufragado;
	}

}
