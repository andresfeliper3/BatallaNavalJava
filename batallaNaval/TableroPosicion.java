package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;



public class TableroPosicion extends JPanel {

	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private int casillaSize = 30;
	private int gridSize = 10;
	private int cantidadBarcos = 10;
	private Barco[] barcos = new Barco[cantidadBarcos];
	private Casilla[][] casillas = new Casilla[gridSize][gridSize];
	private BufferedImage bufferedImage = null;
	private Border border;
	private Escucha escucha;
	//Barcos
	private Portaaviones portaaviones;
	private Submarino submarino1, submarino2;
	private Destructor destructor1, destructor2, destructor3;
	private Fragata fragata1, fragata2, fragata3, fragata4;
	
	
	//Constructor 
	public TableroPosicion() {
		//Layout
		this.setLayout(new GridLayout(gridSize, gridSize));
		border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize);
		
		//Escucha
		escucha = new Escucha();
		
		//Barcos
		/*
		portaaviones = new Portaaviones();
		submarino1 = new Submarino();
		submarino1 = new Submarino();
		destructor1 = new Destructor();
		destructor2 = new Destructor();
		destructor3 = new Destructor();
		fragata1 = new Fragata();
		fragata2 = new Fragata();
		fragata3 = new Fragata();
		fragata4 = new Fragata();
		*/
		//JPanel configuration
		pintarCasillas();
		
		
		
	}
	//Pinta las casillas inicialmente con agua
	private void pintarCasillas() {
		//Pintar casillas
		int id = 0;
		for(int row = 0; row < gridSize; row++) {
			for(int col = 0; col < gridSize; col++ ) {
				casillas[row][col] = new Casilla(id, row, col);
				casillas[row][col].addActionListener(escucha);
				add(casillas[row][col]);
				id++;
			}
		}
	}
	
	//Poner barco
	public void pintarBarco(Casilla casillaSeleccionada) {
		if(barcoSeleccionado != null) {
			int clicksDisponibles = 0;
			bufferedImage = barcoSeleccionado.getBufferedImage();
			switch(barcoSeleccionado.getTamanho()) {
			case Portaaviones.numeroCasillas:
				clicksDisponibles = Portaaviones.numeroCasillas - 1;
				break;
			case Submarino.numeroCasillas:
				clicksDisponibles = Submarino.numeroCasillas - 1;
				break;
			case Destructor.numeroCasillas:
				clicksDisponibles = Destructor.numeroCasillas - 1;
				break;
			case Fragata.numeroCasillas:
				//Fragata se pinta 
				break;
			}
		}
	}
	
	private class Escucha implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			
			casillaSeleccionada = (Casilla)eventAction.getSource();
			pintarBarco(casillaSeleccionada);
			System.out.println(casillaSeleccionada.getIdCasilla());
		}
	}	
	
	
	
}
