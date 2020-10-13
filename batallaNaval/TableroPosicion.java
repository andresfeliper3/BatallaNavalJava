/*
 * 
 */
package batallaNaval;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

// TODO: Auto-generated Javadoc
/**
 * The Class TableroPosicion.
 */
public class TableroPosicion extends JPanel {

	private Barco[] misBarcos;
	private Casilla[][] casillas;
	private BufferedImage bufferImage = null;
	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private int casillaSize = 50;
	private int gridSize = 11;
	private Escucha escucha;
	int clicksDisponibles;
	int particionImagen = 0;
	private BatallaNaval referenciaBatallaNaval;
	private Timer timer;
	private boolean puedoPonerBarco = true;

	/**
	 * Constructor
	 * Instantiates a new tablero posicion.
	 *
	 * @param misBarcos              the mis barcos
	 * @param referenciaBatallaNaval the referencia batalla naval
	 */
	public TableroPosicion(Barco[] misBarcos, BatallaNaval referenciaBatallaNaval) {

		this.referenciaBatallaNaval = referenciaBatallaNaval;
		this.misBarcos = misBarcos;

		// ESCUCHA
		escucha = new Escucha();
		// Layout
		this.setLayout(new GridLayout(gridSize, gridSize));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 7);
		this.setBorder(border);

		casillas = new Casilla[casillaSize][casillaSize];
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize * gridSize);

		pintarCasillas();
	}

	/**
	 * Pintar casillas.
	 *  Pinta todas las casillas del tablero posicion y agrega las escuchas a cada casilla.
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
	 * Pintar barco.
	 *
	 * @param caso    the caso
	 * @param fila    the fila
	 * @param columna the columna
	 * 
	 * Este metodo se encarga de pintar todo el barco una vez ya se haya analizado
	 * las direcciones posibles.
	 * Caso 0: primera particion de la imagen del barco
	 * Caso 1: dirección hacia arriba.
	 * Caso 2: dirección hacia abajo.
	 * Caso 3: dirección hacia la izquierda.
	 * Caso 4: dirección hacia la derecha.
	 */
	private void pintarBarco(int caso, int fila, int columna) {
		int col = columna;
		int row = fila;
		bufferImage = barcoSeleccionado.getBufferedImage();
		BufferedImage subImage = bufferImage.getSubimage(particionImagen, 0, casillaSize, casillaSize);
		ImageIcon casillaImage = new ImageIcon(subImage);

		//Pone la primera particion de la imágen del barco
		if (caso == 0) {
			referenciaBatallaNaval.setMensajeMuelle(2);
			casillaSeleccionada.setImagen(casillaImage);
			casillaSeleccionada.setHasBarco();
			barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
			clicksDisponibles--;
			particionImagen = particionImagen + 50;

		} else if (clicksDisponibles > 0) {

			for (int cualCasilla = 0; cualCasilla < barcoSeleccionado.getTamanho() - 1; cualCasilla++) {

				subImage = bufferImage.getSubimage(particionImagen, 0, casillaSize, casillaSize);
				casillaImage = new ImageIcon(subImage);
				switch (caso) {
				case 1:// UP
					if (barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {
						barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(
								barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(), RotatedIcon.Rotate.UP));
						casillaSeleccionada.setImagen(new RotatedIcon(casillaImage, RotatedIcon.Rotate.UP));

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

					} else {
						casillaSeleccionada = casillas[row][col - 1];
						casillaSeleccionada.setImagen(new RotatedIcon(casillaImage, RotatedIcon.Rotate.REFLECT));
						col--;
					}
					break;
				case 4:// Right
					if (barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {
						casillaSeleccionada.setImagen(casillaImage);

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
		}
		if (clicksDisponibles == 0) {

			barcoSeleccionado = null;
			particionImagen = 0;
			referenciaBatallaNaval.setMensajeMuelle(0);
		}
	}

	/**
	 * Examinar orientacion barco.
	 * Determina la orientacion del barco determinado, es decir, en que direccion
	 * puede pintarse todo el barco completo sin que encuentre un extremo de la matriz
	 *  o que otro barco ya esté ocupando las casillas.
	 */
	private void examinarOrientacionBarco() {
		// TODO Auto-generated method stub
		int row = casillaSeleccionada.getRow();
		int col = casillaSeleccionada.getCol();

		if (barcoSeleccionado.getCasillasDondeEstoy()[0] == null) {
			// primer click
			if (puedoPonerBarco(0)) {
				pintarBarco(0, row, col);
			}
		} else {
			// UP = 1
			// DOWN = 2
			// LEFT = 3
			// RIGHT = 4
			// segundo click
			if (barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() == row + 1
					&& barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() == col) {
				// UP = 1
				if (puedoPonerBarco(1)) {
					pintarBarco(1, row, col);
				}
			} else if (barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() == row - 1
					&& barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() == col) {
				// DOWN = 2
				if (puedoPonerBarco(2)) {
					pintarBarco(2, row, col);
				}
			} else if (barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() == row
					&& barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() == col + 1) {
				// LEFT = 3
				if (puedoPonerBarco(3)) {
					pintarBarco(3, row, col);
				}
			} else if (barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() == row
					&& barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() == col - 1) {
				// RIGHT = 4
				if (puedoPonerBarco(4)) {
					pintarBarco(4, row, col);
				}
			}
		}
	}

	/**
	 * Puedo poner barco.
	 *
	 * @param direccion the direccion
	 * @return true, if successful
	 * Determina a traves de booleanos si el barco se puede pintar en cualquiera de
	 * las cuatro direcciones. Basta con que se pueda pintar en una de las cuatro direcciones 
	 * para poder pintar el barco. En el caso en el que las cuatro direcciones estén bloqueadas,
	 * no permite poner el barco.
	 */
	
	private boolean puedoPonerBarco(int direccion) {
		// TODO Auto-generated method stub
		boolean puedoArriba = true;
		boolean puedoAbajo = true;
		boolean puedoIzq = true;
		boolean puedoDer = true;

		switch (direccion) {

		case 0: // Evalua el primer click
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
			break;

		case 1: // Up
			for (int i = 0; i < barcoSeleccionado.getTamanho() - 1; i++) {
				if (!casillas[casillaSeleccionada.getRow() - i][casillaSeleccionada.getCol()].isWater()
						|| casillas[casillaSeleccionada.getRow() - i][casillaSeleccionada.getCol()].isHasBarco()) {
					return false;
				}
			}
			break;
		case 2:// Down
			for (int i = 1; i < barcoSeleccionado.getTamanho() - 1; i++) {
				if (casillas[casillaSeleccionada.getRow() + i][casillaSeleccionada.getCol()].getRow() + 1 == 12
						|| casillas[casillaSeleccionada.getRow() + i][casillaSeleccionada.getCol()].isHasBarco()) {
					return false;
				}
			}
			break;
		case 3:// Left
			for (int i = 0; i < barcoSeleccionado.getTamanho() - 1; i++) {
				if (!casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - i].isWater()
						|| casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() - i].isHasBarco()) {
					return false;
				}
			}
			break;
		case 4:// Right
			for (int i = 0; i < barcoSeleccionado.getTamanho() - 1; i++) {
				if (casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + i].getCol() + 1 == 12
						|| casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol() + i].isHasBarco()) {
					return false;
				}
			}
			break;
		}

		return true;
	}

	/**
	 * Sets the barco seleccionado.
	 *
	 * @param barco the new barco seleccionado
	 * Asigna un barco seleccionado y le da el valor inicial a clicksDisponibles.
	 */
	
	public void setBarcoSeleccionado(Barco barco) {
		barcoSeleccionado = barco;
		clicksDisponibles = barcoSeleccionado.getTamanho();
	}
	
	/**
	 * Generar disparo.
	 *
	 * @param row the row
	 * @param col the col
	 * @return true, if successful
	 * Simula un disparo del computador y determina si le dio a un barco o no. 
	 * Retorna true si le da a un barcon y false en caso contrario.
	 */
	public boolean generarDisparo(int row, int col) {

		casillaSeleccionada = casillas[row][col];

		if (!casillaSeleccionada.isZonaDestruida()) {
			referenciaBatallaNaval.playSound("disparo");
			timer = new Timer("Timer");
			TimerTask task = new TimerTask() {
				public void run() {

					if (casillaSeleccionada.isHasBarco()) {
						for (int i = 0; i < misBarcos.length; i++) {
							misBarcos[i].disparoAcertado(casillaSeleccionada);
						}

						if (referenciaBatallaNaval.revisarDerrota(misBarcos)) {
							referenciaBatallaNaval.setEstadoDelJuego(3);// Usuario perdió
							referenciaBatallaNaval.setTurno(false);
						} else if (casillaSeleccionada.isNaufragado()) {

							referenciaBatallaNaval.playSound("naufragado");
							referenciaBatallaNaval.setTurno(false);
						}
						// Computador dispara otra vez
						else {
							System.out.println("REPITO PORQUE TE DI EN: " + casillaSeleccionada.getRow() + " "
									+ casillaSeleccionada.getCol());
							referenciaBatallaNaval.playSound("disparoAcertado");
							referenciaBatallaNaval.setTurno(false);
						}

					} else {
						referenciaBatallaNaval.playSound("disparoAlAgua");
						casillaSeleccionada.setZonaDestruida(true);
						referenciaBatallaNaval.setTurno(true);
						cambiarBorde(Color.BLACK);
					}
				}
			};

			timer.schedule(task, 1500);
			return true;

		}
		return false;
	}

	/**
	 * Cambiar borde.
	 *
	 * @param color the color
	 * Cambia el color del borde del tablero.
	 */
	public void cambiarBorde(Color color) {
		Border border = BorderFactory.createLineBorder(color, 7);
		this.setBorder(border);
	}

	/**
	 * Sets the puedo poner barco.
	 * Cambia el booleano a falso para poder usarlo y parar el sonido de las
	 * casillas al pasar el mouse.
	 */
	public void setPuedoPonerBarco() {
		this.puedoPonerBarco = false;
	}

	/**
	 * Gets the barco seleccionado.
	 *
	 * @return the barco seleccionado
	 * Retorna el barco seleccionado
	 */
	
	public Barco getBarcoSeleccionado() {
		return barcoSeleccionado;
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
			casillaSeleccionada = (Casilla) eventAction.getSource();
			if (clicksDisponibles > 0 && barcoSeleccionado != null && casillaSeleccionada.isHasBarco() == false
					&& casillaSeleccionada.isWater()) {
				examinarOrientacionBarco();
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
			if (puedoPonerBarco) {
				referenciaBatallaNaval.playSound("pop");
			}
		}
	}
}
