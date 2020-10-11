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
	private int gridSize = 11;
	private int limiteInferior = 1;
	private Casilla[][] casillas = new Casilla[gridSize][gridSize];
	private BufferedImage bufferedImage = null;
	private ImageIcon imagen;
	private Border border;
	private Escucha escucha;
	private int clicksDisponibles;
	private BatallaNaval ventana;
	private Barco[] misBarcos;
	//Constructor 
	public TableroPosicion(Barco[] misBarcos, BatallaNaval ventana) {
		//Layout
		this.setLayout(new GridLayout(gridSize, gridSize));
		border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize, gridSize);
		
		//Escucha
		escucha = new Escucha();
		
		//Mis barcos
		this.misBarcos = misBarcos;
			
		//JPanel configuration
		this.ventana = ventana;
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
	int counter = 0;
	int orientacion = 1; //0: derecha, 1: izquierda, 2: abajo, 3: arriba
	private void pintarBarco(Casilla casillaSeleccionada) {
		//Revisa que haya un barco seleccionado, que no se hayan hecho todos los clicks disponibles y que la casilla no tenga ya un barco
		if(barcoSeleccionado != null && clicksDisponibles > 0 && !casillaSeleccionada.getHasBarco()) {
			//Cargar imagen
			bufferedImage = barcoSeleccionado.getBufferedImage();
			//Si ya está la primera parte del barco puesta, se decide la orientación total con el segundo click		
			if(clicksDisponibles == barcoSeleccionado.getTamanho() - 1) {
				Casilla casillaActual;
				//por la derecha
				if(casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() && casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() + 1) {
					for(int i = 0; i < barcoSeleccionado.getTamanho() - 1; i++) {
						BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
						imagen = new ImageIcon(subImagen);
						casillaActual = casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + i];
						casillaActual.setImagen(imagen);
						casillaActual.setHasBarco();
						barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
						counter += 50;
						clicksDisponibles--;
					}
				}
				//por la izquierda
				else if(casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() && casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() - 1) {
					for(int i = 0; i < barcoSeleccionado.getTamanho() - 1; i++) {
						BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
						imagen = new ImageIcon(subImagen);
						casillaActual = casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - i];
						casillaActual.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.REFLECT));
						casillaActual.setHasBarco();
						barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
						counter += 50;
						clicksDisponibles--;
					}	
					//Rota el primero
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.REFLECT));		
				}
				//por abajo
				else if(casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() && casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() + 1) {
					for(int i = 0; i < barcoSeleccionado.getTamanho() - 1; i++) {
						BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
						imagen = new ImageIcon(subImagen);
						casillaActual = casillas[casillaSeleccionada.getRow() + i][casillaSeleccionada.getCol()];
						casillaActual.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.DOWN));
						casillaActual.setHasBarco();
						barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
						counter += 50;
						clicksDisponibles--;
					}	
					//Rota el primero
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.DOWN));		
				}
				//por arriba
				else if(casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() && casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() - 1) {
					for(int i = 0; i < barcoSeleccionado.getTamanho() - 1; i++) {
						BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
						imagen = new ImageIcon(subImagen);
						casillaActual = casillas[casillaSeleccionada.getRow() - i][casillaSeleccionada.getCol()];
						casillaActual.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.UP));
						casillaActual.setHasBarco();
						barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
						counter += 50;
						clicksDisponibles--;
					}	
					//Rota el primero
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.UP));		
				}
			} 
			//Primer click para un barco, puede ser en cualquier lugar
			else if(clicksDisponibles == barcoSeleccionado.getTamanho() && casillaSeleccionada.getHasWater()){
				BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
				imagen = new ImageIcon(subImagen);
				casillaSeleccionada.setImagen(imagen);
				casillaSeleccionada.setHasBarco(); //Decirle a la casilla que tiene un barco
				barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
				counter += 50;	
				clicksDisponibles--;
				cambiarMensajeMuelle("Escoja una casilla adyacente a la casilla seleccionada");
			}		
		}
		
		//Si ya puse todos las partes del barco, se borra el barco seleccionado para quem pueda escoger otro
		if(clicksDisponibles == 0) {
			barcoSeleccionado = null;
			counter = 0;
			cambiarMensajeMuelle("Haga click en un barco");
		}	
		
		
		
	}
	//Retorna true si hay al menos una dirección posible para poner el barco (arriba, abajo, izq, der). Retorna false si el barco en ninguna dirección cabe.
	boolean porDerecha, porIzquierda, porArriba, porAbajo;
	private boolean cabeElBarco() {
		//Siempre que se cambie de barco (se acaben los clicksDisponibles), los booleanos vuelven a ser true
		int row = casillaSeleccionada.getRow();
		int col = casillaSeleccionada.getCol();		
		//La primera casilla para poner el barco.
		if(clicksDisponibles == barcoSeleccionado.getTamanho()) {
			porDerecha = true;
			porIzquierda = true;
			porArriba = true;
			porAbajo = true;
			//Por derecha
			for(int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				//Límite por derecha
				if(col + cambioEnCasilla >= gridSize) {
					porDerecha = false;
					break;
				}
				//Revisar 
				if(casillas[row][col + cambioEnCasilla].getHasBarco()) {
					porDerecha = false;
					break;
				}
			}
			//Por abajo
			for(int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				//Límite por abajo
				if(row + cambioEnCasilla >= gridSize) {
					porAbajo = false;
					break;
				}		
				if(casillas[row + cambioEnCasilla][col].getHasBarco()) {
					porAbajo = false;
				}
			}
			//Por izquierda
			for(int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				//Límite por izquierda
				if(col - cambioEnCasilla < limiteInferior) {
					porIzquierda = false;
					break;
				}
				if(casillas[row][col - cambioEnCasilla].getHasBarco()) {
					porIzquierda = false;
				}
			}
			//Por arriba
			for(int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				//Límite por arriba
				if(row - cambioEnCasilla < limiteInferior) {
					porArriba = false;
					break;
				}
				if(casillas[row - cambioEnCasilla][col].getHasBarco()) {
					porArriba = false;
				}
			}	
			if(porDerecha || porArriba || porIzquierda || porAbajo) {
				return true;
			}
			return false;
		}
		//El segundo click en una casilla donde se elige la dirección del barco
		else if(clicksDisponibles == barcoSeleccionado.getTamanho() - 1) {
			//Si se escogió la orientación derecha
			if(casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() && casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() + 1) {
				if(porDerecha) {
					return true;
				}
				return false;
			}
			//Si se escogió la orientación superior
			if(casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() - 1 && casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol()) {
				if(porArriba) {
					return true;
				}
				return false;
			}
			//Si se escogió la orientación izquierdo
			if(casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() && casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() - 1) {
				if(porIzquierda) {
					return true;
				}
				return false;
			}
			//Si se escogió la orientación inferior
			if(casillaSeleccionada.getRow() == barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() + 1 && casillaSeleccionada.getCol() == barcoSeleccionado.getCasillasDondeEstoy()[0].getCol()) {
				if(porAbajo) {
					return true;
				}
				return false;
			}
		}	
		return true;	
	}
	//Indica el barco seleccionado y e inicia el conteo de los clicks disponibles restantes
	public void setBarcoSeleccionado(Barco barco) {
		barcoSeleccionado = barco;
		clicksDisponibles = barcoSeleccionado.getTamanho();
	}
	
	public Barco getBarcoSeleccionado() {
		return barcoSeleccionado;
	}
	//Cambiar mensaje muelle
	private void cambiarMensajeMuelle(String mensaje) {
		ventana.setMensajeMuelle(mensaje);
	}
	
	//Genera un disparo del computador. Retorna true si logró disparar correctamente, false en caso contrario.
	public boolean generarDisparo(int row, int col) {
		casillaSeleccionada = casillas[row][col];	
		if(!casillaSeleccionada.isZonaDestruida()) {
			if(casillaSeleccionada.getHasBarco()) {
				for(int barco = 0; barco < misBarcos.length; barco++) {
					misBarcos[barco].disparoAcertado(casillaSeleccionada);
				}
				//Mantiene el turno del computador
				ventana.setTurno(false);
			}
			else {
				casillaSeleccionada.setZonaDestruida(true);
				//Cambia al turno del usuario
				ventana.setTurno(true);
			}
			
			return true;
		}
		return false;
	}
	private class Escucha implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			
			casillaSeleccionada = (Casilla)eventAction.getSource();
			//Si el barco se puede poner en al menos una orientación de las 4 (arriba, abajo, izq, der).
			if(cabeElBarco()) {
				pintarBarco(casillaSeleccionada);
			}		
		}
	}	
}
