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
	private int orientacion; // 1: derecha, 2: izquierda, 3: abajo, 4: arriba
	private Barco barcoSeleccionado;
	private int limiteInferior = 1;
	private boolean disparoHabilitado = false;
	private BatallaNaval ventana;
	private int clicksDisponibles;
	// Para quitar
	private ImageIcon imagen;

	// Constructor
	public TableroPrincipal(Barco[] pcBarcos, BatallaNaval ventana) {
		// Layout
		this.setLayout(new GridLayout(gridSize, gridSize));
		border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize * gridSize, gridSize);
		// Escucha
		escucha = new Escucha();
		// casillas
		casillas = new Casilla[gridSize][gridSize];
		// barcos
		barcos = new Barco[cantidadBarcos];
		this.barcos = pcBarcos;
		// referencia
		this.ventana = ventana;

		// JPanel configuration
		pintarCasillas();
		acomodarBarcos();
	}

	// Pinta las casillas inicialmente con agua
	private void pintarCasillas() {
		// Creación de barcos y casillas

		// Pintar casillas
		int id = 0;
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				casillas[row][col] = new Casilla(id, row, col);
				casillas[row][col].addActionListener(escucha);
				add(casillas[row][col]);
				id++;
			}
		}
	}

	// Random de posición inicial para un barco
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

	// Retorna true si es posible poner el barco.
	// Genera una orientación aleatoria y la revisa. Si no funciona, genera otra. Si
	// después de X giros, no ha funcionado ninguna dirección
	// entonces retorna false.

	private boolean cabeElBarco() {
		// Generar dirección u orientación aleatoria y la revisa
		int acum = 0;
		do {
			acum++;
			randomDirection();
		} while (!revisarDireccion(orientacion) && acum <= 8);

		if (revisarDireccion(orientacion)) {
			return true;
		} else {
			return false;
		}
	}

	// revisa una orientación específica
	private boolean revisarDireccion(int orientacion) {

		int row = casillaSeleccionada.getRow();
		int col = casillaSeleccionada.getCol();
		switch (orientacion) {
		case 1:
			// por derecha
			for (int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				// Límite por derecha
				if (col + cambioEnCasilla >= gridSize) {
					return false;
				}
				// Otro barco presente
				if (casillas[row][col + cambioEnCasilla].getHasBarco()) {
					return false;
				}
			}
			return true;
		case 2:
			// por izquierda
			for (int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				// Límite por izquierda
				if (col - cambioEnCasilla < limiteInferior) {
					return false;
				}
				// Otro barco presente
				if (casillas[row][col - cambioEnCasilla].getHasBarco()) {
					return false;
				}
			}
			return true;
		case 3:
			// por abajo
			for (int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				// Límite por abajo
				if (row + cambioEnCasilla >= gridSize) {
					return false;
				}
				// Otro barco presente
				if (casillas[row + cambioEnCasilla][col].getHasBarco()) {
					return false;
				}
			}
			return true;
		case 4:
			// por arriba
			for (int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				// Otro barco presente o límites por derecha
				if (row - cambioEnCasilla < limiteInferior) {
					return false;
				}
				if (casillas[row - cambioEnCasilla][col].getHasBarco()) {
					return false;
				}
			}
			return true;
		}
		return false;

	}

	int counter = 0;
	Casilla casillaActual;

	private void acomodarBarcos() {

		for (int barco = 0; barco < barcos.length; barco++) {

			// Dentro del ciclo de los barcos
			barcoSeleccionado = barcos[barco];
			clicksDisponibles = barcoSeleccionado.getTamanho();
			// Cargar imagen
			bufferedImage = barcoSeleccionado.getBufferedImage();
			// Generar casilla aleatoria que funcione. Mientras tenga un barco, genera otra
			// casilla aleatoria.
			do {
				randomCasilla();
			} while (casillaSeleccionada.getHasBarco() || !cabeElBarco());		
			
			
			//Primera parte del barco
			if (clicksDisponibles == barcoSeleccionado.getTamanho()) {
				
				BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
				imagen = new ImageIcon(subImagen);
				casillaSeleccionada.setImagen(imagen);
				casillaSeleccionada.setHasBarco(); // Decirle a la casilla que tiene un barco
				barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
				counter += 50;
				clicksDisponibles--;
			}		
			//Si es la segunda parte del barco
			if(clicksDisponibles == barcoSeleccionado.getTamanho() - 1 && clicksDisponibles != 0) {
				switch (orientacion) {
				case 1:
					// Por la derecha
					detallesBarco(1);
					
					break;
				case 2:
					// Por la izquierda
					detallesBarco(2);
					// Rota el primero
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
							barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.REFLECT));

					break;
				case 3:
					// Por abajo
					detallesBarco(3);
					// Rota el primero
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
							barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.DOWN));
					break;
				case 4:
					// Por arriba
					detallesBarco(4);
					// Rota el primero
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
							barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.UP));
					break;

				}
			}
			// Si ya puse todos las partes del barco, se borra el barco seleccionado para que pueda escoger otro
			if(clicksDisponibles == 0) {
				barcoSeleccionado = null;
				counter = 0;
			}
			
			
		}
	}

	// Configuraciones de cada barco a acomodar
	private void detallesBarco(int orientacion) {
		for (int i = 1; i < barcoSeleccionado.getTamanho(); i++) {
			BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
			imagen = new ImageIcon(subImagen);
			switch (orientacion) {
			// Derecha
			case 1:
				casillaActual = casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + i];
				casillaActual.setImagen(imagen);
				break;
			// Izquierda
			case 2:
				casillaActual = casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - i];
				casillaActual.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.REFLECT));
				break;
			// Abajo	
			case 3:
				casillaActual = casillas[casillaSeleccionada.getRow() + i][casillaSeleccionada.getCol()];
				casillaActual.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.DOWN));
				break;
			// Arriba
			case 4:
				casillaActual = casillas[casillaSeleccionada.getRow() - i][casillaSeleccionada.getCol()];
				casillaActual.setImagen(new RotatedIcon(imagen, RotatedIcon.Rotate.UP));
				break;
			}
			casillaActual.setHasBarco();
			barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
			counter += 50;
			clicksDisponibles--;
			
		}

	}

	// Habilita o deshabilita el disparo en las casilla
	public void setDisparoHabilitado(boolean disponible) {
		this.disparoHabilitado = disponible;
		for (int row = 1; row < gridSize; row++) {
			for (int col = 1; col < gridSize; col++) {
				if (!casillas[row][col].isZonaDestruida()) {
					casillas[row][col].setEnabled(disponible);
				}

			}
		}
	}

	// Analizaz si la casilla clickeada tiene un barco, e internamente el barco mira
	// si está hundido
	private void analizarDisparo(Casilla casillaClickeada) {
		if (casillaClickeada.getHasBarco()) {
			for (int barco = 0; barco < barcos.length; barco++) {
				barcos[barco].disparoAcertado(casillaClickeada);
			}
			// Revisar si el computador perdió
			if (ventana.revisarDerrota(barcos)) {
				ventana.setEstado(2); // usuario gana
			}
			// Mantiene el turno del usuario
			ventana.setTurno(true);
		} else {
			casillaClickeada.setZonaDestruida(true);
			// Cambia al turno del computador
			ventana.setTurno(false);
		}
	}

	private class Escucha implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			Casilla casillaClickeada = (Casilla) eventAction.getSource();
			// Si la casilla tiene agua, analiza el disparo
			if (casillaClickeada.getHasWater() && !casillaClickeada.isZonaDestruida() && ventana.getEstado() == 1) {
				analizarDisparo(casillaClickeada);
			}
		}
	}
}
