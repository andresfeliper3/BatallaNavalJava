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
		this.setPreferredSize(new Dimension(casillaSize, casillaSize));
	}
	//establece un máximo de casillas y un tamaño para las casillas
	public static void setCasillaSizeMaxCasillas(int tamanho,int numeroCasillas) {
		casillaSize= tamanho;
		maxCasillas= numeroCasillas;	
	}
	//retorna la fila de la casilla
	public int getRow() {
		return row;
	}
	//retorna la columna de la casilla
	public int getCol() {
		return col;
	}
	//Le asigna una imágen a la casilla
	public void setImagen(Icon imagen) {
		this.imagen = imagen;
		this.setIcon(imagen);
	}
	//Retorna si la casilla tiene una parte del barco
	public boolean isHasBarco() {
		return hasBarco;
	}
	//Le asigna un barco a la casilla
	public void setHasBarco() {
		this.hasBarco = true;
	}
	//muestra si la casilla ha sido destruida (Impactada por un disparo)
	public boolean isZonaDestruida() {
		return zonaDestruida;
	}
	//Destruye la casilla
	public void setZonaDestruida(boolean zonaDestruida) {
		this.zonaDestruida = zonaDestruida;
		
		if(this.isHasBarco()) {
			setImagen(tocado);
		}if(this.isWater && !isHasBarco()) {
			setImagen(agua);
		}
	}
	//Método que pone la casilla en naufragado
	public void naufragarBarco() {
		this.naufragado=true;
		setImagen(hundido);
	}
	//Retorna el ID de la casilla
	public int getIdCasilla() {
		return idCasilla;
	}
	//Retorna el icono de la casilla
	public Icon getImagen() {
		return imagen;
	}
	//Muesta si la casilla es agua o no
	public boolean isWater() {
		return isWater;
	}
	//Muestra si la casilla está naufragada o no
	public boolean isNaufragado() {
		return naufragado;
	}

}
