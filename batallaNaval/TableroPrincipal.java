package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
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

	private Barco[] barcos;
	private Casilla[][] casillas;
	private BufferedImage bufferImage = null;
	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private Casilla casilla2;
	private int casillaSize = 50;
	private int gridSize = 11;
	private Escucha escucha;
	int clicksDisponibles;
	int particionImagen=0;
	boolean puedoArriba=false;
	boolean puedoAbajo=false;
	boolean puedoIzq=false;
	boolean puedoDer=false;
	
	
	public TableroPrincipal(Barco[] pcBarcos) {
		
		this.barcos = pcBarcos;
		//ESCUCHA
		escucha = new Escucha();
		//Layout
		this.setLayout(new GridLayout(gridSize,gridSize));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);

		casillas = new Casilla[casillaSize][casillaSize];
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize);
		
		pintarCasillas();
		seleccionarBarcosYCasillas();
		
	}
	//Pinta todas las casillas del tablero principal
	private void pintarCasillas() {
		// TODO Auto-generated method stub
		int id = 0;
		for(int row=0;row<gridSize;row++){
			for(int col=0;col<gridSize;col++) {
				casillas[row][col] = new Casilla(id,row,col);
				casillas[row][col].addActionListener(escucha);
				add(casillas[row][col]);
				id++;
			}
		}
	}
	
	//Genera un número al azar de 1 a 4
	private int randomDirection() {
		
		Random random = new Random();
		int direction = random.nextInt(4)+1;
		
		return direction;
		
	}
	//Genera un número al azar de columna entre 1 y 10
	private int randomCol() {
		
		Random random = new Random();
		int col = random.nextInt(10)+1;
		
		return col;
	}
	//Genera un número al azar de fila entre 1 y 10
	private int randomRow() {
		
		Random random = new Random();
		int row = random.nextInt(10)+1;
		
		return row;
	}
	
	//pintar los barcos
	private void seleccionarBarcosYCasillas() {
		for(int i=0;i < barcos.length;i++) {
				
				//Selecciona el barco
				setBarcoSeleccionado(barcos[i]);
				
					casillaSeleccionada = generarClick();
				if(clicksDisponibles >0 && barcoSeleccionado != null) {
					examinarOrientacionBarco();
				}
				else {
						i--;
			}
		}
		
	}
	
	/*
	 * Simula un click en la primera casilla para poner el barco
	 */
	private Casilla generarClick() {
		
		casillaSeleccionada = casillas[randomRow()][randomCol()];
		
		if(!casillaSeleccionada.isHasBarco() && puedoPonerBarco()) {
			return casillaSeleccionada;
		}else {
		
			return generarClick();
		}
	}
	
	private void examinarOrientacionBarco() {
		// TODO Auto-generated method stub
			
			int row = casillaSeleccionada.getRow();
			int col = casillaSeleccionada.getCol();
			
			if(barcoSeleccionado.getCasillasDondeEstoy()[0] == null) {
				//primer click
				
				puedoPonerBarco();
				pintarBarcos(0,row,col);


				if(puedoDer) {//RIGHT

					pintarBarcos(4,row,col);
				}
				else if(puedoArriba) {//UP

					pintarBarcos(1,row,col);
				}
				else if(puedoAbajo) {//DOWN

					pintarBarcos(2,row,col);
				}
				else if(puedoIzq) {//LEFT

					pintarBarcos(3,row,col);
				}
				else if(puedoDer) {//RIGHT

					pintarBarcos(4,row,col);
				}

			}
	}

	private boolean puedoPonerBarco() {
		
		// TODO Auto-generated method stub
		puedoArriba=true;
		puedoAbajo=true;
		puedoIzq=true;
		puedoDer=true;
			
		
			for(int i=0;i<barcoSeleccionado.getTamanho();i++) {
				//mira por arriba
				if(!casillas[casillaSeleccionada.getRow()-i][casillaSeleccionada.getCol()].isWater() || casillas[casillaSeleccionada.getRow()-i][casillaSeleccionada.getCol()].isHasBarco()) {
					
					puedoArriba = false;
					
					break;
				}
			}
			for(int i=0;i<barcoSeleccionado.getTamanho();i++) {
				//mira por abajo
				if(casillas[casillaSeleccionada.getRow()+i][casillaSeleccionada.getCol()].getRow()+1 == 11 || casillas[casillaSeleccionada.getRow()+i][casillaSeleccionada.getCol()].isHasBarco()) {
					puedoAbajo = false;

					break;
				}
			}
			for(int i=0;i<barcoSeleccionado.getTamanho();i++) {
				//mira por izquierda
				if(!casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol()-i].isWater() || casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol()-i].isHasBarco()) {
					puedoIzq = false;

					break;
				}
			}
			for(int i=0;i<barcoSeleccionado.getTamanho();i++) {
				//mira por derecha
				if(casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol()+i].getCol()+1 == 11 || casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol()+i].isHasBarco()) {
					puedoDer = false;

					break;
				}
			}
			if(!puedoArriba && !puedoAbajo && !puedoIzq && !puedoDer) {
					
				return false;
			}

		return true;
	}
	
	private void pintarBarcos(int caso,int fila, int columna) {

		particionImagen = 0;
		int col = columna;
		int row = fila;
		bufferImage = barcoSeleccionado.getBufferedImage();
		BufferedImage subImage = bufferImage.getSubimage(particionImagen, 0, casillaSize, casillaSize);
		ImageIcon casillaImage = new ImageIcon(subImage);

		//pone la primera partición de la imágen del barco
		if(caso == 0) {
			
			casillaSeleccionada.setImagen(casillaImage);
			casillaSeleccionada.setHasBarco();
			barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
			
			clicksDisponibles--;
			particionImagen = particionImagen + 50;
			
		}else if(clicksDisponibles > 0 ) {
			for(int cualCasilla=0;cualCasilla<barcoSeleccionado.getTamanho();cualCasilla++) {
				subImage = bufferImage.getSubimage(particionImagen, 0, casillaSize, casillaSize);
				casillaImage = new ImageIcon(subImage);
						switch(caso) {
							case 1://UP
								if(barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {
									
									barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(),RotatedIcon.Rotate.UP));
									casillaSeleccionada.setImagen(new RotatedIcon(casillaImage,RotatedIcon.Rotate.UP));
									row--;
								}else{

									casillaSeleccionada = casillas[row][col];
									casillaSeleccionada.setImagen(new RotatedIcon(casillaImage,RotatedIcon.Rotate.UP));
									row--;
								}
									break;
							case 2://Down
								if(barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {
									barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(),RotatedIcon.Rotate.DOWN));
									casillaSeleccionada.setImagen(new RotatedIcon(casillaImage,RotatedIcon.Rotate.DOWN));
									row++;
								}else {
									casillaSeleccionada = casillas[row][col];
									casillaSeleccionada.setImagen(new RotatedIcon(casillaImage,RotatedIcon.Rotate.DOWN));
									row++;
								}
									break;
							case 3://Left
								if(barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {	
									barcoSeleccionado.getCasillasDondeEstoy()[0].setImagen(new RotatedIcon(barcoSeleccionado.getCasillasDondeEstoy()[0].getImagen(),RotatedIcon.Rotate.REFLECT));
									casillaSeleccionada.setImagen(new RotatedIcon(casillaImage,RotatedIcon.Rotate.REFLECT));
									col--;
								}else {
									casillaSeleccionada = casillas[row][col];
									casillaSeleccionada.setImagen(new RotatedIcon(casillaImage,RotatedIcon.Rotate.REFLECT));
									col--;
								}
									break;
							case 4://Right
								if(barcoSeleccionado.getCasillasDondeEstoy()[1] == null) {
									casillaSeleccionada.setImagen(casillaImage);
									col++;
								}else {
									casillaSeleccionada = casillas[row][col];
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
			
		}if(clicksDisponibles == 0) {

				//barcoSeleccionado = null;
				particionImagen=0;
		}

	}
	
	public void setBarcoSeleccionado(Barco barco) {
		barcoSeleccionado = barco;
		clicksDisponibles = barcoSeleccionado.getTamanho();

	}
	
	public void ocultarOMostrar(int orden) {
		
		//OCULTA LOS BARCOS
		for(int row=0;row<gridSize;row++) {
			for(int col=0;col<gridSize;col++) {
				
				if(casillas[row][col].getImagen() !=null && row>0 && col>0 && orden == 0) {

					casillaSeleccionada = casillas[row][col];
					casillaSeleccionada.setIcon(null);
		//MOSTRAR BARCOS
				}if(orden == 1) {

					casillaSeleccionada = casillas[row][col];
					casillaSeleccionada.setImagen(casillaSeleccionada.getImagen());
						
					}
			}
		}
	}
	
	
	
	public class Escucha implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			casillaSeleccionada = (Casilla)eventAction.getSource();
			
			System.out.println(casillaSeleccionada.isHasBarco());
			casillaSeleccionada.setIcon(null);
			
			if(casillaSeleccionada == casillas[0][0]) {

				ocultarOMostrar(0);
				
			}if(casillaSeleccionada == casillas[0][1]) {
				
				ocultarOMostrar(1);

			}
		}
	}
}
