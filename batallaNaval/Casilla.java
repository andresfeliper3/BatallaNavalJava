package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

// TODO: Auto-generated Javadoc
/**
 * The Class Casilla.
 */
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
	/**
	 * Instantiates a new casilla.
	 *
	 * @param idCasilla the id casilla, posicion donde está la casilla en el tablero
	 * @param row the row
	 * @param col the columna
	 */
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
	 * establece un máximo de casillas y un tamaño para las casillas
	 * @param tamanho the tamanho
	 * @param numeroCasillas the numero casillas
	 */
	public static void setCasillaSizeMaxCasillas(int tamanho,int numeroCasillas) {
		casillaSize= tamanho;
		maxCasillas= numeroCasillas;	
	}
	/**
	 * Gets the row.
	 * 
	 * @return the row retorna la fila de la casilla
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Gets the col.
	 *
	 * @return the col. retorna la columna de la casilla
	 */
	public int getCol() {
		return col;
	}
	/**
	 * Sets the imagen.
	 * Le asigna una imágen a la casilla
	 * @param imagen the new imagen
	 */
	public void setImagen(Icon imagen) {
		this.imagen = imagen;
		this.setIcon(imagen);
	}
	/**
	 * Checks if is checks for barco.
	 *
	 * @return true, if is checks for barco. Retorna si la casilla tiene una parte del barco
	 */
	public boolean isHasBarco() {
		return hasBarco;
	}
	/**
	 * Sets the has barco.
	 * Le asigna un barco a la casilla
	 */
	public void setHasBarco() {
		this.hasBarco = true;
	}
	/**
	 * Checks if is zona destruida.
	 * muestra si la casilla ha sido destruida (Impactada por un disparo)
	 * @return true, if is zona destruida
	 */
	public boolean isZonaDestruida() {
		return zonaDestruida;
	}
	/**
	 * Sets the zona destruida.
	 * Destruye la casilla
	 * @param zonaDestruida the new zona destruida
	 */
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
	 * Método que pone la casilla en naufragado
	 */
	public void naufragarBarco() {
		this.naufragado=true;
		setImagen(hundido);
	}
	/**
	 * Gets the id casilla.
	 *
	 * @return the id casilla. Retorna el ID de la casilla
	 */
	public int getIdCasilla() {
		return idCasilla;
	}
	/**
	 * Gets the imagen.
	 *
	 * @return the imagen. Retorna el icono de la casilla
	 */
	public Icon getImagen() {
		return imagen;
	}
	/**
	 * Checks if is water.
	 * Muesta si la casilla es agua o no
	 * @return true, if is water
	 */
	public boolean isWater() {
		return isWater;
	}
	/**
	 * Checks if is naufragado.
	 * Muestra si la casilla está naufragada o no
	 * @return true, if is naufragado
	 */
	public boolean isNaufragado() {
		return naufragado;
	}

}
