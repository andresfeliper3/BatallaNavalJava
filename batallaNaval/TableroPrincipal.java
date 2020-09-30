package batallaNaval;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TableroPrincipal extends JPanel {
	private Barco barcoSeleccionado;
	private int casillaSize = 50;
	private int gridSize = 10;
	private int cantidadBarcos = 10;
	private Barco[] barcos = new Barco[cantidadBarcos];
	private Casilla[][] casillas = new Casilla[gridSize][gridSize];
	private BufferedImage bufferedImage = null;
	private Border border;
	
	//Constructor 
	public TableroPrincipal() {
		//Layout
		this.setLayout(new GridLayout(gridSize, gridSize));
		border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize);
		
		//JPanel configuration
		pintarCasillas();
		
	}
	//Pinta las casillas inicialmente con agua
	private void pintarCasillas() {
		//Creación de barcos y casillas
		
		//Pintar casillas
		int id = 0;
		for(int row = 0; row < gridSize; row++) {
			for(int col = 0; col < gridSize; col++ ) {
				casillas[row][col] = new Casilla(id, row, col);
				add(casillas[row][col]);
				id++;
			}
		}
	}
}
