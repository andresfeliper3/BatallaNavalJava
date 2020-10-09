package batallaNaval;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class TableroPosicion extends JPanel {
	
	private Casilla[][] casillas;
	private BufferedImage bufferImage = null;
	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private int casillaSize = 50;
	private int gridSize = 11;
	private Escucha escucha;
	int clicksDisponibles;
	int particionImagen=0;
	
	public TableroPosicion() {
		//Layout
		escucha = new Escucha();
		
		this.setLayout(new GridLayout(gridSize,gridSize));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
		this.setBorder(border);

		casillas = new Casilla[casillaSize][casillaSize];
		Casilla.setCasillaSizeMaxCasillas(casillaSize, gridSize*gridSize);
	
		pintarCasillas();
	}

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
	
	private void pintarBarco(int caso,int fila,int columna) {
		
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
			for(int cualCasilla=1;cualCasilla<barcoSeleccionado.getTamanho();cualCasilla++) {
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

				barcoSeleccionado = null;
				particionImagen=0;
		}
	}

	private  void examinarOrientacionBarco() {
		// TODO Auto-generated method stub
		int row = casillaSeleccionada.getRow();
		int col = casillaSeleccionada.getCol();
		
		if(barcoSeleccionado.getCasillasDondeEstoy()[0] == null) { 
			//primer click
			if(puedoPonerBarco(0)) {
				pintarBarco(0,row,col);
			}
		}else {
			//UP = 1
			//DOWN = 2
			//LEFT = 3
			//RIGHT = 4	
				//segundo click
				if(barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() == row+1 && barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() == col) {
					//UP = 1
						if(puedoPonerBarco(1)) {
							pintarBarco(1,row,col);
						}
				}else if(barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() == row-1 && barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() == col) {
					//DOWN = 2
					if(puedoPonerBarco(2)) {
						pintarBarco(2,row,col);
					}
				}else if(barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() == row && barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() == col+1) {
					//LEFT = 3
					if(puedoPonerBarco(3)) {
						pintarBarco(3,row,col);
					}
				}else if(barcoSeleccionado.getCasillasDondeEstoy()[0].getRow() == row && barcoSeleccionado.getCasillasDondeEstoy()[0].getCol() == col-1) {
					//RIGHT = 4
					if(puedoPonerBarco(4)) {
						pintarBarco(4,row,col);
					}
				}
		}
	}
	
	private boolean puedoPonerBarco(int direccion) {
		// TODO Auto-generated method stub
		boolean puedoArriba =true;
		boolean puedoAbajo=true;
		boolean puedoIzq=true;
		boolean puedoDer=true;
		
		switch(direccion) {
		
			case 0:	//Evalua el primer click
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
				break;
					
			case 1:	//Up
					for(int i=0;i<barcoSeleccionado.getTamanho()-1;i++) {
						if(!casillas[casillaSeleccionada.getRow()-i][casillaSeleccionada.getCol()].isWater() || casillas[casillaSeleccionada.getRow()-i][casillaSeleccionada.getCol()].isHasBarco()) {
							return false;
						}
					}	
					break;
			case 2://Down
				for(int i=1;i<barcoSeleccionado.getTamanho()-1;i++) {
					if(casillas[casillaSeleccionada.getRow()+i][casillaSeleccionada.getCol()].getRow()+1 == 12 || casillas[casillaSeleccionada.getRow()+i][casillaSeleccionada.getCol()].isHasBarco()) {
						return false;
					}
				}	
					break;
			case 3://Left
				for(int i=0;i<barcoSeleccionado.getTamanho()-1;i++) {
					if(!casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol()-i].isWater() || casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol()-i].isHasBarco()) {
						return false;
					}
				}
				break;
			case 4://Right
				for(int i=0;i<barcoSeleccionado.getTamanho()-1;i++) {
					if(casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol()+i].getCol()+1 == 12 || casillas[casillaSeleccionada.getRow()][casillaSeleccionada.getCol()+i].isHasBarco()) {
						return false;
					}
				}
				break;
		}

		return true;
	}

	public void setBarcoSeleccionado(Barco barco) {
		barcoSeleccionado = barco;
		clicksDisponibles = barcoSeleccionado.getTamanho();

	}
	
	public class Escucha implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			casillaSeleccionada = (Casilla)eventAction.getSource();
			if(clicksDisponibles >0 && barcoSeleccionado != null && casillaSeleccionada.isHasBarco()==false && casillaSeleccionada.isWater()) {

					examinarOrientacionBarco();		
			}
			
		}
	}
}
