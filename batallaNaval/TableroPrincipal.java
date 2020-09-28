package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TableroPrincipal extends JPanel {

	private Barco[] barcos;
	private Casilla[][] casillas;
	private BufferedImage bufferImage = null;
	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private int casillaSize = 30;
	private int gridSize = 10;
	private int cantidadBarcos;
	
	
	public TableroPrincipal() {
		//Layout
		this.setLayout(new GridLayout(gridSize,gridSize));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);

		casillas = new Casilla[casillaSize][casillaSize];
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize);
		
		pintarCasillas();
		
	}


	private void pintarCasillas() {
		// TODO Auto-generated method stub
		int id = 0;
		for(int row=0;row<gridSize;row++){
			for(int col=0;col<gridSize;col++) {
				casillas[row][col] = new Casilla(id,row,col);
				add(casillas[row][col]);
				id++;
				
			}
			
		}
	}
}
