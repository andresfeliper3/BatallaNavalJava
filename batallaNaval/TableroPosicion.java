package batallaNaval;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TableroPosicion extends JPanel {

	private Barco barcoSeleccionado;
	private Barco[] barcos;
	private int tamanhoCasillas = 10;
	private int cantidadBarcos = 10;
	private Casilla[][] casillas;
	private BufferedImage bufferedImage;
	
	//Constructor 
	public TableroPosicion() {
		//Layout
		this.setLayout(new GridLayout(tamanhoCasillas, tamanhoCasillas));
		
		//JPanel configuration
		this.setPreferredSize(new Dimension(100, 100));
		pintarCasillas();
		
	}
	
	private void pintarCasillas() {
		//Creación de barcos y casillas
		barcos = new Barco[cantidadBarcos];
		casillas = new Casilla[tamanhoCasillas][tamanhoCasillas];
		//Pintar casillas
		int id = 0;
		for(int row = 0; row < tamanhoCasillas; row++) {
			for(int col = 0; col < tamanhoCasillas; col++ ) {
				casillas[row][col] = new Casilla(id, row, col);
				add(casillas[row][col]);
				id++;
			}
		}
	}
	
	
	
}
