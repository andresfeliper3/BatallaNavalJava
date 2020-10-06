package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Icon;
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
	boolean orientacion = true; //true si es horizontal, false si es vertical
	public void pintarBarco(Casilla casillaSeleccionada) {
		//Revisa que haya un barco seleccionado, que no se hayan hecho todos los clicks disponibles y que la casilla no tenga ya un barco
		if(barcoSeleccionado != null && clicksDisponibles > 0 && !casillaSeleccionada.getHasBarco()) {
			//Cargar imagen
			bufferedImage = barcoSeleccionado.getBufferedImage();
			BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
			imagen = new ImageIcon(subImagen);

			//Si ya está la primera parte del barco puesta, se decide la orientación total con el segundo click
			if(clicksDisponibles == barcoSeleccionado.getTamanho() - 1) {
				//por la derecha
				if(casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() && casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() + 1) {
					casillaSeleccionada.setImagen(imagen);
					casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
					barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
					counter += 50;	
					clicksDisponibles--;
				}
				//por abajo
				else if(casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() && casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() + 1) {
					casillaSeleccionada.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.DOWN));
					casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
					barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
					counter += 50;	
					clicksDisponibles--;
					orientacion = false; //vertical
					//Rota anterior
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.DOWN));
				}
				//por arriba
				else if(casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() && casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() - 1) {
					casillaSeleccionada.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.UP));
					casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
					barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
					counter += 50;	
					clicksDisponibles--;
					orientacion = false; //vertical
					//Rota anterior
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.UP));
				}
			} 
			//Primer click para un barco, puede ser en cualquier lugar
			else if(clicksDisponibles == barcoSeleccionado.getTamanho()){
				casillaSeleccionada.setImagen(imagen);
				casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
				barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
				counter += 50;	
				clicksDisponibles--;
			}
			//Resto de clicks para poner el barco, a partir del tercero.
			else {
				//El barco se está poniendo en posición horizontal
				if(orientacion) {
					if(casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[barcoSeleccionado.getTamanho() - clicksDisponibles - 1].getRow() && casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[barcoSeleccionado.getTamanho() - clicksDisponibles - 1].getCol() + 1) {
						casillaSeleccionada.setImagen(imagen);
						casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
						barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
						counter += 50;	
						clicksDisponibles--;
					}
				}
				//El barco se está poniendo en posición vertical
				else {
					if(casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[barcoSeleccionado.getTamanho() - clicksDisponibles - 1].getCol() && casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[barcoSeleccionado.getTamanho() - clicksDisponibles - 1].getRow() + 1)  {
						casillaSeleccionada.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.DOWN));
						casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
						barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
						counter += 50;	
						clicksDisponibles--;
					}
					else if(casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[barcoSeleccionado.getTamanho() - clicksDisponibles - 1].getCol() && casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[barcoSeleccionado.getTamanho() - clicksDisponibles - 1].getRow() - 1) {
						casillaSeleccionada.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.UP));
						casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
						barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
						counter += 50;	
						clicksDisponibles--;
					}
				}
			}
			
		}		
		//Si ya puse todos las partes del barco, se borra el barco seleccionado para quem pueda escoger otro
		if(clicksDisponibles == 0) {
			barcoSeleccionado = null;
			counter = 0;
		}
		
	}
	
	//Indica el barco seleccionado y e inicia el conteo de los clicks disponibles restantes
	public void setBarcoSeleccionado(Barco barco) {
		barcoSeleccionado = barco;
		clicksDisponibles = barcoSeleccionado.getTamanho();
	}
	
	public Barco getBarcoSeleccionado() {
		return barcoSeleccionado;
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
