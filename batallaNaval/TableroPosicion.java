package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class TableroPosicion extends JPanel {

	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private int casillaSize = 50;
	private int gridSize = 10;
	private int cantidadBarcos = 10;
	private Barco[] barcos = new Barco[cantidadBarcos];
	private Casilla[][] casillas = new Casilla[gridSize][gridSize];
	private BufferedImage bufferedImage = null;
	private ImageIcon imagen;
	private Border border;
	private Escucha escucha;
	private int clicksDisponibles;
	
	//Constructor 
	public TableroPosicion() {
		//Layout
		this.setLayout(new GridLayout(gridSize, gridSize));
		border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize);
		
		//Escucha
		escucha = new Escucha();
		
		
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
	
	//NO SE HA TERMINADO
	//Poner barco
	int counter = 0;
	public void pintarBarco(Casilla casillaSeleccionada) {
		if(barcoSeleccionado != null && clicksDisponibles > 0 && !casillaSeleccionada.getHasBarco()) {
			bufferedImage = barcoSeleccionado.getBufferedImage();
			BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
			imagen = new ImageIcon(subImagen);
			casillaSeleccionada.cambiarImagen(imagen);
			casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
			barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
			counter += 50;	
			clicksDisponibles--;
		}		
		//Si ya puse todos las partes del barco, se borra el barco seleccionado
		if(clicksDisponibles == 0) {
			barcoSeleccionado = null;
			counter = 0;
		}
	
		
	}
	
	public void setBarcoSeleccionado(Barco barco) {
		barcoSeleccionado = barco;
		clicksDisponibles = barcoSeleccionado.getTamanho();
	}
	
	private class Escucha implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			
			casillaSeleccionada = (Casilla)eventAction.getSource();
			pintarBarco(casillaSeleccionada);
			
		}
	}	
	
	
	
}
