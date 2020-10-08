package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class TableroPrincipal extends JPanel {

	private Barco[] barcos;
	private Casilla[][] casillas;
	private BufferedImage bufferImage = null;
	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private int casillaSize = 50;
	private int gridSize = 11;
	//private Escucha escucha;
	int clickDisponibles;
	int particionImagen=0;
	
	
	public TableroPrincipal(Barco[] pcBarcos) {
		
		this.barcos = pcBarcos;
		//Layout
		this.setLayout(new GridLayout(gridSize,gridSize));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);

		casillas = new Casilla[casillaSize][casillaSize];
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize);
		
		pintarCasillas();
		
	}
	//Pinta todas las casillas del tablero principal
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
	
	//Genera un número al azar de 1 a 4
	private int randomDirection() {
		
		Random random = new Random();
		int direction = random.nextInt(4)+1;
		
		return direction;
		
	}
	//Genera un número al azar de 0 a 120
	private int randomPosition() {
		
		Random random = new Random();
		int idCasilla = random.nextInt(121);
		
		return idCasilla;
	}
	
	
	
	
	
	
	
	
	
	
	
}
