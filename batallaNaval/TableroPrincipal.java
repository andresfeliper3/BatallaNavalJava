package batallaNaval;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
	private boolean disparoHabilitado = false, mostrarBarcos = false, puedoDisparar = true;
	private BatallaNaval ventana;
	private int clicksDisponibles;
	private Timer timer;
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
		// timer
		timer = new Timer("Timer");
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
		// Creaci�n de barcos y casillas

		// Pintar casillas
		int id = 0;
		for (int row = 0; row < gridSize; row++) {
			for (int col = 0; col < gridSize; col++) {
				casillas[row][col] = new Casilla(id, row, col);
				casillas[row][col].addActionListener(escucha);
				casillas[row][col].addMouseListener(escucha);
				add(casillas[row][col]);
				id++;
			}
		}
	}

	// Random de posici�n inicial para un barco
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
	// Genera una orientaci�n aleatoria y la revisa. Si no funciona, genera otra. Si
	// despu�s de X giros, no ha funcionado ninguna direcci�n
	// entonces retorna false.

	private boolean cabeElBarco() {
		// Generar direcci�n u orientaci�n aleatoria y la revisa
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

	// revisa una orientaci�n espec�fica
	private boolean revisarDireccion(int orientacion) {

		int row = casillaSeleccionada.getRow();
		int col = casillaSeleccionada.getCol();
		switch (orientacion) {
		case 1:
			// por derecha
			for (int cambioEnCasilla = 0; cambioEnCasilla < barcoSeleccionado.getTamanho(); cambioEnCasilla++) {
				// L�mite por derecha
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
				// L�mite por izquierda
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
				// L�mite por abajo
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
				// Otro barco presente o l�mites por derecha
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

			// Primera parte del barco
			if (clicksDisponibles == barcoSeleccionado.getTamanho()) {

				BufferedImage subImagen = bufferedImage.getSubimage(counter, 0, casillaSize, casillaSize);
				imagen = new ImageIcon(subImagen);
				casillaSeleccionada.setImagen(imagen);
				casillaSeleccionada.setIcon(null);
				casillaSeleccionada.setHasBarco(); // Decirle a la casilla que tiene un barco
				barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
				counter += 50;
				clicksDisponibles--;
			}
			// Si es la segunda parte del barco
			if (clicksDisponibles == barcoSeleccionado.getTamanho() - 1 && clicksDisponibles != 0) {
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
					barcoSeleccionado.getCasillasDondeEstoy()[0].setIcon(null);
					break;
				case 3:
					// Por abajo
					detallesBarco(3);
					// Rota el primero
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
							barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.DOWN));
					barcoSeleccionado.getCasillasDondeEstoy()[0].setIcon(null);
					break;
				case 4:
					// Por arriba
					detallesBarco(4);
					// Rota el primero
					barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
							barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.UP));
					barcoSeleccionado.getCasillasDondeEstoy()[0].setIcon(null);
					break;

				}
			}
			// Si ya puse todos las partes del barco, se borra el barco seleccionado para
			// que pueda escoger otro
			if (clicksDisponibles == 0) {
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
			casillaActual.setIcon(null);
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

	// Analiza si la casilla clickeada tiene un barco, e internamente el barco mira
	// si est� hundido
	private void analizarDisparo(Casilla casillaClickeada) {
		puedoDisparar = true;
		if (casillaClickeada.getHasBarco()) {
			// Ejecuci�n del timer
			System.out.println("empezando disparo");
			
			ventana.playSound("disparoAcertado");
			System.out.println("dentro del timer");
			for (int barco = 0; barco < barcos.length; barco++) {
				barcos[barco].disparoAcertado(casillaClickeada);
			}

			if(casillaClickeada.isNaufragado()) {
				ventana.playSound("naufragado");
			}
			// Revisar si el computador perdi�
			if (ventana.revisarDerrota(barcos)) {
				System.out.println("revisando derrota del computador");
				ventana.setEstado(2); // usuario gana
			}
			// Mantiene el turno del usuario
			System.out.println("antes de setTurno");
			ventana.setTurno(true);
		} else {

			// Cambia al turno del computador
			// Ejecuci�n del timer
			

			casillaClickeada.setZonaDestruida(true);
			ventana.playSound("disparoAlAgua");
			

			ventana.setTurno(false);

		}
	}

	// Muestra los barcos o los oculta
	public void mostrarOcultarBarcos() {
		// Si est� mostrando los barcos, los oculta
		if (mostrarBarcos) {
			for (int row = 1; row < gridSize; row++) {
				for (int col = 1; col < gridSize; col++) {
					// Si la zona no est� destruida, quita el icon
					if (!casillas[row][col].isZonaDestruida()) {
						casillas[row][col].setIcon(null);
					}
				}
			}
			mostrarBarcos = false;
			ventana.setTextMostrarOcultar("Ver barcos");
		}
		// Si no est� mostrando los barcos, los muestra
		else {
			for (int row = 1; row < gridSize; row++) {
				for (int col = 1; col < gridSize; col++) {
					if (!casillas[row][col].isZonaDestruida()) {
						casillas[row][col].setIcon(casillas[row][col].getImagen());
					}
				}
			}

			mostrarBarcos = true;
			ventana.setTextMostrarOcultar("Ocultar barcos");
		}
	}

	private class Escucha implements ActionListener, MouseListener {
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			Casilla casillaClickeada = (Casilla) eventAction.getSource();
			// Si la casilla tiene agua, analiza el disparo
			if (casillaClickeada.getHasWater() && !casillaClickeada.isZonaDestruida() && ventana.getEstado() == 1 && puedoDisparar) {
				puedoDisparar = false;
				ventana.playSound("disparo");
				timer = new Timer("Timer");
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						analizarDisparo(casillaClickeada);
					}				
				};
				timer.schedule(task, 1500);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			if (ventana.getEstado() == 1 && puedoDisparar) {
				ventana.playSound("pop");
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
