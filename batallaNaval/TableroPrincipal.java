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
	// Para quitar
	private ImageIcon imagen;

	// Constructor
	public TableroPrincipal(Barco[] pcBarcos) {
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

	private void acomodarBarcos() {

		for (int barco = 0; barco < barcos.length; barco++) {

			// Dentro del ciclo de los barcos
			barcoSeleccionado = barcos[barco];
			// Generar casilla aleatoria que funcione. Mientras tenga un barco, genera otra
			// casilla aleatoria.
			do {
				randomCasilla();
			} while (casillaSeleccionada.getHasBarco() || !cabeElBarco());
			// Acomodar solo el portaaviones

			imagen = new ImageIcon(barcoSeleccionado.getBufferedImage());
			Casilla casillaActual = null;
			switch (orientacion) {
			case 1:
				// Por la derecha
				for (int i = 0; i < barcoSeleccionado.getTamanho(); i++) {
					casillaActual = casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + i];
					casillaActual.setImagen(imagen);
					casillaActual.setHasBarco();
					barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
				}
				break;
			case 2:
				// Por la izquierda
				for (int i = 0; i < barcoSeleccionado.getTamanho(); i++) {
					casillaActual = casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - i];
					casillaActual.setImagen(imagen);
					casillaActual.setHasBarco();
					barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
				}
				break;
			case 3:
				// Por abajo
				for (int i = 0; i < barcoSeleccionado.getTamanho(); i++) {
					casillaActual = casillas[casillaSeleccionada.getRow() + i][casillaSeleccionada.getCol()];
					casillaActual.setImagen(imagen);
					casillaActual.setHasBarco();
					barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
				}
				break;
			case 4:
				// Por arriba
				for (int i = 0; i < barcoSeleccionado.getTamanho(); i++) {
					casillaActual = casillas[casillaSeleccionada.getRow() - i][casillaSeleccionada.getCol()];
					casillaActual.setImagen(imagen);
					casillaActual.setHasBarco();
					barcoSeleccionado.setCasillasDondeEstoy(casillaActual);
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
