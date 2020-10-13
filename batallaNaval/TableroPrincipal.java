package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

// TODO: Auto-generated Javadoc
/**
 * The Class TableroPrincipal.
 */
public class TableroPrincipal extends JPanel {

	private Barco[] barcos;
	private Casilla[][] casillas;
	private BufferedImage bufferImage = null;
	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private int casillaSize = 50;
	private int gridSize = 11;
	private Escucha escucha;
	int clicksDisponibles;
	int particionImagen = 0;
	boolean puedoArriba = false;
	boolean puedoAbajo = false;
	boolean puedoIzq = false;
	boolean puedoDer = false;
	private BatallaNaval referenciaBatallaNaval;
	private boolean visible = true;
	private Timer timer;
	private boolean puedoDisparar = false;

	/**
	 * Instantiates a new tablero principal.
	 *
	 * @param pcBarcos               the array de Barcos
	 * @param referenciaBatallaNaval the referencia de la clase BatallaNaval
	 */
	public TableroPrincipal(Barco[] pcBarcos, BatallaNaval referenciaBatallaNaval) {

		this.referenciaBatallaNaval = referenciaBatallaNaval;
		this.barcos = pcBarcos;

		// ESCUCHA
		escucha = new Escucha();
		// Layout
		this.setLayout(new GridLayout(gridSize, gridSize));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 7);
		this.setBorder(border);

		casillas = new Casilla[casillaSize][casillaSize];
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize * gridSize);

		pintarCasillas();
		seleccionarBarcosYCasillas();
	}

	/**
	 * Pinta todas las casillas del tablero principal y agrega las escuchas a cada
	 * casilla
	 */
	private void pintarCasillas() {
		// TODO Auto-generated method stub
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

	/**
	 * Random col. Genera un número al azar de columna entre 1 y 10
	 * 
	 * @return the int Columna
	 */
	private int randomCol() {

		Random random = new Random();
		int col = random.nextInt(10) + 1;
		return col;
	}

	/**
	 * Random row. Genera un número al azar de fila entre 1 y 10
	 * 
	 * @return the int Row
	 */
	private int randomRow() {

		Random random = new Random();
		int row = random.nextInt(10) + 1;
		return row;
	}

	/**
	 * selecciona cada uno de los barcos que están en la lista de barcos del
	 * computador y los pinta en el tablero principal
	 */
	private void seleccionarBarcosYCasillas() {
		for (int i = 0; i < barcos.length; i++) {

			// Selecciona el barco
			setBarcoSeleccionado(barcos[i]);
			casillaSeleccionada = generarClick();
			if (clicksDisponibles > 0 && barcoSeleccionado != null) {
				examinarOrientacionBarco();
			} else {
				i--;
			}
		}
	}

	/**
	 * Generar click. Simula un click en la primera casilla para poner el barco
	 * 
	 * @return the casilla
	 */
	private Casilla generarClick() {
		// Se genera una casilla al azar
		casillaSeleccionada = casillas[randomRow()][randomCol()];
		// Si la casilla al azar ya fue una que se generó anteriormente o hay un barco,
		// genera otra casilla
		if (!casillaSeleccionada.isHasBarco() && puedoPonerBarco()) {
			return casillaSeleccionada;
		} else {

			return generarClick();
		}
	}

	/**
	 * Examinar orientacion barco. Determina la orientación del barco determinando,
	 * es decir; en qué dirección puede pintarse todo el barco completo sin que
	 * hayan límites de casillas u otro barco ya esté ocupando las casillas
	 */
	private void examinarOrientacionBarco() {
		// TODO Auto-generated method stub
		int row = casillaSeleccionada.getRow();
		int col = casillaSeleccionada.getCol();

		if (barcoSeleccionado.getCasillasDondeEstoy()[0] == null) {
			// primer click
			puedoPonerBarco();
			pintarBarcos(0, row, col);

		}
		if (puedoDer && barcoSeleccionado.getTamanho() != 1) {// RIGHT

			casillaSeleccionada = casillas[row][col + 1];
			pintarBarcos(4, row, col);
		} else if (puedoArriba && barcoSeleccionado.getTamanho() != 1) {// UP
			casillaSeleccionada = casillas[row - 1][col];
			pintarBarcos(1, row, col);
		} else if (puedoAbajo && barcoSeleccionado.getTamanho() != 1) {// DOWN
			casillaSeleccionada = casillas[row + 1][col];
			pintarBarcos(2, row, col);
		} else if (puedoIzq && barcoSeleccionado.getTamanho() != 1) {// LEFT
			casillaSeleccionada = casillas[row][col - 1];
			pintarBarcos(3, row, col);
		}
	}

	/**
	 * Puedo poner barco. Determina a través de booleanos si el barco se puede
	 * pintar en cualquiera de las cuatro direcciones
	 * 
	 * @return true, if successful
	 */
	private boolean puedoPonerBarco() {
		// TODO Auto-generated method stub
		puedoArriba = true;
		puedoAbajo = true;
		puedoIzq = true;
		puedoDer = true;

		for (int i = 0; i < barcoSeleccionado.getTamanho(); i++) {
			// mira por arriba
			if (!casillas[casillaSeleccionada.getRow() - i][casillaSeleccionada.getCol()].isWater()
					|| casillas[casillaSeleccionada.getRow() - i][casillaSeleccionada.getCol()].isHasBarco()) {
				puedoArriba = false;
				break;
			}
		}
		for (int i = 0; i < barcoSeleccionado.getTamanho(); i++) {
			// mira por abajo
			if (casillas[casillaSeleccionada.getRow() + i][casillaSeleccionada.getCol()].getRow() + 1 == 11
					|| casillas[casillaSeleccionada.getRow() + i][casillaSeleccionada.getCol()].isHasBarco()) {
				puedoAbajo = false;
				break;
			}
		}
		for (int i = 0; i < barcoSeleccionado.getTamanho(); i++) {
			// mira por izquierda
			if (!casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - i].isWater()
					|| casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - i].isHasBarco()) {
				puedoIzq = false;
				break;
			}
		}
		for (int i = 0; i < barcoSeleccionado.getTamanho(); i++) {
			// mira por derecha
			if (casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + i].getCol() + 1 == 11
					|| casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + i].isHasBarco()) {
				puedoDer = false;
				break;
			}
		}
		if (!puedoArriba && !puedoAbajo && !puedoIzq && !puedoDer) {
			return false;
		}

		return true;
	}

	/**
	 * Pintar barcos. Este método se encarga de pintar todo el barco una vez ya se
	 * haya analizado las direcciones posibles
	 * 
	 * @param caso    the caso
	 * @param fila    the fila
	 * @param columna the columna
	 * 
	 */
	private void pintarBarcos(int caso, int fila, int columna) {

		particionImagen = 0;
		int col = columna;
		int row = fila;
		bufferImage = barcoSeleccionado.getBufferedImage();
		BufferedImage subImage = bufferImage.getSubimage(particionImagen, 0, casillaSize, casillaSize);
		ImageIcon casillaImage = new ImageIcon(subImage);

		// pone la primera partición de la imágen del barco
		if (caso == 0) {
			casillaSeleccionada.setImagen(casillaImage);
			casillaSeleccionada.setHasBarco();
			barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
			clicksDisponibles--;
			particionImagen = particionImagen + 50;

		} else if (clicksDisponibles > 0) {
			particionImagen = 50;
			for (int cualCasilla = 0; cualCasilla < barcoSeleccionado.getTamanho() - 1; cualCasilla++) {
				subImage = bufferImage.getSubimage(particionImagen, 0, casillaSize, casillaSize);
				casillaImage = new ImageIcon(subImage);
				switch (caso) {
				case 1:// UP
					if (barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {

						barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
								barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.UP));
						casillaSeleccionada.setImagen(new RotatedIcon(casillaImage, RotatedIcon.Rotate.UP));
						row--;
					} else {
						casillaSeleccionada = casillas[row - 1][col];
						casillaSeleccionada.setImagen(new RotatedIcon(casillaImage, RotatedIcon.Rotate.UP));
						row--;
					}
					break;
				case 2:// Down
					if (barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {
						
						barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
								barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.DOWN));
						casillaSeleccionada.setImagen(new RotatedIcon(casillaImage, RotatedIcon.Rotate.DOWN));
						row++;
					} else {
						casillaSeleccionada = casillas[row + 1][col];
						casillaSeleccionada.setImagen(new RotatedIcon(casillaImage, RotatedIcon.Rotate.DOWN));
						row++;
					}
					break;
				case 3:// Left
					if (barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {
						
						barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
								barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.REFLECT));
						casillaSeleccionada.setImagen(new RotatedIcon(casillaImage, RotatedIcon.Rotate.REFLECT));
						col--;
					} else {
						casillaSeleccionada = casillas[row][col - 1];
						casillaSeleccionada.setImagen(new RotatedIcon(casillaImage, RotatedIcon.Rotate.REFLECT));
						col--;
					}
					break;
				case 4:// Right
					if (barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {
						casillaSeleccionada.setImagen(casillaImage);
						col++;
					} else {
						casillaSeleccionada = casillas[row][col + 1];
						casillaSeleccionada.setImagen(casillaImage);
						col++;
					}
					break;
				}
				casillaSeleccionada.setHasBarco();

				barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
				clicksDisponibles--;
				particionImagen = particionImagen + 50;
			}

		} else if (clicksDisponibles == 0) {

			casillaSeleccionada.setHasBarco();
			barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);

		}

	}

	/**
	 * Método que asigna un barco seleccionado
	 * 
	 * @param barco the new barco seleccionado
	 */
	public void setBarcoSeleccionado(Barco barco) {
		barcoSeleccionado = barco;
		clicksDisponibles = barcoSeleccionado.getTamanho();

	}

	/**
	 * Ocultar O mostrar. Método que se encarga de mostrar u ocultar los barcos del
	 * tablero principal
	 */
	public void ocultarOMostrar() {
		// Ocultar Barcos
		if (visible) {
			for (int row = 1; row < gridSize; row++) {
				for (int col = 1; col < gridSize; col++) {

					if (!casillas[row][col].isZonaDestruida()) {
						casillas[row][col].setIcon(null);
					}
				}
			}
			visible = false;
			referenciaBatallaNaval.setTextMostrarOcultar("Mostrar Barcos");
		}
		// MOSTRAR BARCOS
		else if (!visible) {
			for (int row = 1; row < gridSize; row++) {
				for (int col = 1; col < gridSize; col++) {
					casillas[row][col].setImagen(casillas[row][col].getImagen());
				}
			}
			visible = true;
			referenciaBatallaNaval.setTextMostrarOcultar("Ocultar Barcos");
		}
	}

	/**
	 * Sets the disparo habilitado.
	 *
	 * @param disponible the new disparo habilitado
	 */
	// habilita o deshabilita las casillas dependiendo del turno
	public void setDisparoHabilitado(boolean disponible) {

		for (int row = 1; row < gridSize; row++) {
			for (int col = 1; col < gridSize; col++) {

				if (!casillas[row][col].isZonaDestruida()) {
					casillas[row][col].setEnabled(disponible);
				}
			}
		}
	}

	/**
	 * Analizar disparo.
	 * 	 Método que se encarga de analizar el disparo del computador que se ejecuta 
	 *   desde la Clase de BatallaNaval y determinar si le pegó a un barco o si falló el disparo
	 */

	private void analizarDisparo() {

		if (casillaSeleccionada.isHasBarco()) {
			for (int i = 0; i < barcos.length; i++) {
				barcos[i].disparoAcertado(casillaSeleccionada);
			}
			if (referenciaBatallaNaval.revisarDerrota(barcos)) {
				referenciaBatallaNaval.setEstadoDelJuego(2);// Usuario Ganó

			}
			// Usuario repite turno de nuevo
			else if (casillaSeleccionada.isNaufragado()) {

				referenciaBatallaNaval.playSound("naufragado");
				referenciaBatallaNaval.setBarcosRestantes();
			} else {
				referenciaBatallaNaval.playSound("disparoAcertado");

			}
			referenciaBatallaNaval.setTurno(true);
		} else {
			referenciaBatallaNaval.playSound("disparoAlAgua");
			casillaSeleccionada.setZonaDestruida(true);
			referenciaBatallaNaval.setTurno(false);
			cambiarBorde(Color.BLACK);

		}
	}

	/**
	 * Permiso para disparar.
	 *	Método encargado de dar permiso para disparar al usuario, es decir; el
	 *	usuario no puede ejecutar un disparo si es false y sí puede disparar si es  
	 *	false
	 * @param fuego the fuego
	 */
	public void permisoParaDisparar(boolean fuego) {
		this.puedoDisparar = fuego;
	}
	/**
	 * Cambiar borde.
	 * cambiar el color del borde del tablero, recibe como parámetro un color
	 * @param color the color
	 */
	public void cambiarBorde(Color color) {
		Border border = BorderFactory.createLineBorder(color, 7);
		this.setBorder(border);
	}
	/**
	 * The Class Escucha.
	 */
	public class Escucha extends MouseAdapter implements ActionListener {

		/**
		 * Action performed.
		 *
		 * @param eventAction the event action
		 */
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			if (puedoDisparar) {
				cambiarBorde(Color.BLACK);
				puedoDisparar = false;
				casillaSeleccionada = (Casilla) eventAction.getSource();

				if (!casillaSeleccionada.isZonaDestruida() && casillaSeleccionada.isWater()) {
					// Ejecución del timer
					referenciaBatallaNaval.playSound("disparo");
					timer = new Timer("Timer");
					TimerTask task = new TimerTask() {
						public void run() {
							analizarDisparo();
						}
					};
					timer.schedule(task, 1500);
				}
			}
		}
		/**
		 * Mouse entered.
		 *
		 * @param eventMouse the event mouse
		 */
		@Override
		public void mouseEntered(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			if (puedoDisparar) {
				referenciaBatallaNaval.playSound("pop");
			}
		}
	}
}
