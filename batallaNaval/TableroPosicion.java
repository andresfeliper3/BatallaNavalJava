package batallaNaval;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
	private int gridSize = 10;
	private Escucha escucha;
	int clicksDisponibles;

	
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
	
	private void pintarBarco() {
		
		int x = clicksDisponibles*casillaSize-casillaSize;
		
		if(clicksDisponibles >0 && barcoSeleccionado != null && casillaSeleccionada.isHasBarco()==false) {
			
			bufferImage = barcoSeleccionado.getBufferedImage();
			BufferedImage subImage = bufferImage.getSubimage(x, 0, casillaSize, casillaSize);
			ImageIcon casillaImage = new ImageIcon(subImage);
			barcoSeleccionado.setCasillasDondeEstoy(casillaSeleccionada);
			
			casillaSeleccionada.cambiarImagen(casillaImage);
			casillaSeleccionada.setHasBarco();
			clicksDisponibles--;

		}else {
			barcoSeleccionado = null;
		}
		
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
			pintarBarco();
			
		}
	}
}
