package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

// TODO: Auto-generated Javadoc
/**
 * The Class Casilla.
 */
public class Casilla extends JButton {
	
	/** The casilla size. */
	private static int casillaSize=0;
	
	/** The max casillas. */
	private static int maxCasillas=0;
	
	/** The Constant hundido. */
	private static  final Icon hundido = new ImageIcon("src/imagenes/hundido.gif");
	
	/** The Constant agua. */
	private static  final Icon agua = new ImageIcon("src/imagenes/agua.gif");
	
	/** The Constant tocado. */
	private static  final Icon tocado = new ImageIcon("src/imagenes/tocado.gif");
	
	/** The col. */
	private int idCasilla, row, col;
	
	/** The has barco. */
	private boolean hasBarco;
	
	/** The zona destruida. */
	private boolean zonaDestruida;
	
	/** The imagen. */
	private Icon imagen;
	
	/** The is water. */
	private boolean isWater;
	
	/** The naufragado. */
	private boolean naufragado;
	
	/**
	 * Instantiates a new casilla.
	 *
	 * @param idCasilla the id casilla
	 * @param row the row
	 * @param col the col
	 */
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
	
	/**
	 * Sets the casilla size max casillas.
	 *
	 * @param tamanho the tamanho
	 * @param numeroCasillas the numero casillas
	 */
	//establece un máximo de casillas y un tamaño para las casillas
	public static void setCasillaSizeMaxCasillas(int tamanho,int numeroCasillas) {
		casillaSize= tamanho;
		maxCasillas= numeroCasillas;	
	}
	
	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	//retorna la fila de la casilla
	public int getRow() {
		return row;
	}
	
	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	//retorna la columna de la casilla
	public int getCol() {
		return col;
	}
	
	/**
	 * Sets the imagen.
	 *
	 * @param imagen the new imagen
	 */
	//Le asigna una imágen a la casilla
	public void setImagen(Icon imagen) {
		this.imagen = imagen;
		this.setIcon(imagen);
	}
	
	/**
	 * Checks if is checks for barco.
	 *
	 * @return true, if is checks for barco
	 */
	//Retorna si la casilla tiene una parte del barco
	public boolean isHasBarco() {
		return hasBarco;
	}
	
	/**
	 * Sets the has barco.
	 */
	//Le asigna un barco a la casilla
	public void setHasBarco() {
		this.hasBarco = true;
	}
	
	/**
	 * Checks if is zona destruida.
	 *
	 * @return true, if is zona destruida
	 */
	//muestra si la casilla ha sido destruida (Impactada por un disparo)
	public boolean isZonaDestruida() {
		return zonaDestruida;
	}
	
	/**
	 * Sets the zona destruida.
	 *
	 * @param zonaDestruida the new zona destruida
	 */
	//Destruye la casilla
	public void setZonaDestruida(boolean zonaDestruida) {
		this.zonaDestruida = zonaDestruida;
		
		if(this.isHasBarco()) {
			setImagen(tocado);
		}if(this.isWater && !isHasBarco()) {
			setImagen(agua);
		}
	}
	
	/**
	 * Naufragar barco.
	 */
	//Método que pone la casilla en naufragado
	public void naufragarBarco() {
		this.naufragado=true;
		setImagen(hundido);
	}
	
	/**
	 * Gets the id casilla.
	 *
	 * @return the id casilla
	 */
	//Retorna el ID de la casilla
	public int getIdCasilla() {
		return idCasilla;
	}
	
	/**
	 * Gets the imagen.
	 *
	 * @return the imagen
	 */
	//Retorna el icono de la casilla
	public Icon getImagen() {
		return imagen;
	}
	
	/**
	 * Checks if is water.
	 *
	 * @return true, if is water
	 */
	//Muesta si la casilla es agua o no
	public boolean isWater() {
		return isWater;
	}
	
	/**
	 * Checks if is naufragado.
	 *
	 * @return true, if is naufragado
	 */
	//Muestra si la casilla está naufragada o no
	public boolean isNaufragado() {
		return naufragado;
	}

}
