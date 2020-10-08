package batallaNaval;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TableroPrincipal extends JPanel {
	private int casillaSize = 50;
	private int gridSize = 11;
	private int cantidadBarcos = 10;
	private Barco[] barcos;
	private Casilla[][] casillas;
	private BufferedImage bufferedImage = null;
	private Border border;
	private Escucha escucha;
	private Casilla casillaSeleccionada;
	private int orientacion; //1: derecha, 2: izquierda, 3: abajo, 4: arriba
	//Para quitar
	private ImageIcon imagen;
	
	//Constructor 
	public TableroPrincipal(Barco[] pcBarcos) {
		//Layout
		this.setLayout(new GridLayout(gridSize, gridSize));
		border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize, gridSize);
		//Escucha
		escucha = new Escucha();
		//casillas
		casillas = new Casilla[gridSize][gridSize];
		//barcos
		barcos= new Barco[cantidadBarcos];
		this.barcos = pcBarcos;
		
		//JPanel configuration
		pintarCasillas();
		acomodarBarcos();
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
	
	//Random de posición inicial para un barco
	private void randomCasilla() {
		Random rand = new Random();
		int randomRow = rand.nextInt(10) + 1;
		int randomCol = rand.nextInt(10) + 1;
		casillaSeleccionada = casillas[randomRow][randomCol];
		
	}
	private void randomDirection() {
		Random rand = new Random();
		orientacion = rand.nextInt(4) + 1;
	
	}
	//Retorna true si es posible poner el barco.
	//Si su parámetro es 0, quiere decir que es la primera parte de un barco.
	boolean porDerecha, porIzquierda, porArriba, porAbajo;
	private boolean cabeElBarco(int param, Barco barcoSeleccionado) {
		int row = casillaSeleccionada.getRow();
		int col = casillaSeleccionada.getCol();
		porDerecha = true;
		porIzquierda = true;
		porArriba = true;
		porAbajo = true;
		switch(param) {
		//Primera parte del barco
			case 0:
				if(!casillaSeleccionada.getHasBarco()) {
					return true;
				}
				return false;
			//Por derecha
			case 1: 
				for(int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
					if(col + cambioEnCasilla >= gridSize  ) {
						porDerecha = false;
						break;
					}
					if(casillas[row][col + cambioEnCasilla].getHasBarco()) {
						porDerecha = false;
						break;
					}
				}
				break;
				//Por izquierda
			case 2: 
				for(int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
					if(col - cambioEnCasilla < 1) {
						porIzquierda = false;
						break;
					}
					if(casillas[row][col - cambioEnCasilla].getHasBarco()) {
						porIzquierda = false;
						break;
					}
				}
				break;
				//Por abajo
			case 3: 
				for(int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
					if(row + cambioEnCasilla >= gridSize) {
						porAbajo = false;
						break;
					}
					if(casillas[row + cambioEnCasilla][col].getHasBarco()) {
						porAbajo = false;
						break;
					}
				}
				break;
				//Por arriba
			case 4: 
				for(int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
					if(row - cambioEnCasilla < 1) {
						porArriba = false;
						break;
					}
					if(casillas[row - cambioEnCasilla][col].getHasBarco()) {
						porArriba = false;
						break;
					}
				}
				break;
		}
		if(porDerecha || porIzquierda || porArriba || porAbajo) {
			return true;
		}
		return false;
	}
	
	private void acomodarBarcos() {		
		for(int barco = 0; barco < barcos.length; barco++) {	
			imagen = new ImageIcon(barcos[barco].getBufferedImage());
			do {
				do {	
					randomCasilla();	
				} while(!cabeElBarco(0, barcos[barco]));
				randomDirection();
			} while(!cabeElBarco(orientacion, barcos[barco]));
				
			//Ciclo para poner todo el barco
		
			switch(orientacion) {
			//Por derecha
			case 1:
				for(int casilla = 0; casilla < barcos[barco].getTamanho(); casilla++) {
					
					barcos[barco].setCasillasDondeEstoy(casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + casilla]);
					casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + casilla].setImagen(imagen);
				}
				break;
			//por izquierda
			case 2:
				for(int casilla = 0; casilla < barcos[barco].getTamanho(); casilla++) {
					
					barcos[barco].setCasillasDondeEstoy(casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - casilla]);
					casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - casilla].setImagen(imagen);
				}
				break;
			//por abajo
			case 3:
				for(int casilla = 0; casilla < barcos[barco].getTamanho(); casilla++) {
					
					barcos[barco].setCasillasDondeEstoy(casillas[casillaSeleccionada.getRow() + casilla][casillaSeleccionada.getCol()]);
					casillas[casillaSeleccionada.getRow() + casilla][casillaSeleccionada.getCol()].setImagen(imagen);
				}
				break;
			//por arriba
			case 4:
				for(int casilla = 0; casilla < barcos[barco].getTamanho(); casilla++) {
				
					barcos[barco].setCasillasDondeEstoy(casillas[casillaSeleccionada.getRow() - casilla][casillaSeleccionada.getCol()]);
					casillas[casillaSeleccionada.getRow() - casilla][casillaSeleccionada.getCol()].setImagen(imagen);
				}
				break;
				
			}
			
		}
	}
	
	private class Escucha implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
