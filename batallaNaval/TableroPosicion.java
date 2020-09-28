package batallaNaval;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class TableroPosicion extends JPanel {
	
	private Barco[] barcos;
	private Casilla[][] casillas;
	private BufferedImage bufferImage = null;
	private Barco barcoSeleccionado;
	private Casilla casillaSeleccionada;
	private int casillaSize = 30;
	private int gridSize = 10;
	private int cantidadBarcos;
	private Escucha escucha;
	private Portaaviones portaavion;
	private Submarino submarino1,submarino2;
	private Destructor destructor1,destructor2,destructor3;
	private Fragata fragata1,fragata2,fragata3,fragata4;
	
	
	public TableroPosicion() {
		//Layout
		escucha = new Escucha();
		
		//Crear barcos
		//1 Portaavion
		portaavion = new Portaaviones();
		//2 Submarinos
		submarino1 = new Submarino();
		submarino2 = new Submarino();
		//3 Destructores
		destructor1 = new Destructor();
		destructor2 = new Destructor();
		destructor3 = new Destructor();
		//4 fragatas
		fragata1 = new Fragata();
		fragata2 = new Fragata();
		fragata3 = new Fragata();
		fragata4 = new Fragata();
		
		
		this.setLayout(new GridLayout(gridSize,gridSize));
		System.out.println("HOLI");
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
	
	public void pintarBarco(Casilla casillaSeleccionado) {
		
		int clicksDisponibles = 0;
		
		if(barcoSeleccionado != null) {
			
			bufferImage = barcoSeleccionado.getBufferImage();
	
			switch(barcoSeleccionado.getTamanho()){
				
			case Portaaviones.numeroCasillas: //Portaaviones
				
				clicksDisponibles = Portaaviones.numeroCasillas-1;
					
					break;
			case Submarino.numeroCasillas: //Submarinos
					
				clicksDisponibles = Submarino.numeroCasillas-1;
					break;
			case Destructor.numeroCasillas: //Destructores
					
				clicksDisponibles = Destructor.numeroCasillas-1;
					break;
			case Fragata.numeroCasillas: //Fragatas
					
				clicksDisponibles = Fragata.numeroCasillas-1; //fragata se pinta de una vez
					break;
			}
			
			
		}
	}
	public class Escucha implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			casillaSeleccionada = (Casilla)eventAction.getSource();
			pintarBarco(casillaSeleccionada);
			System.out.println(casillaSeleccionada.getIdCasilla());
			
		}
		
	}
	
	
	
}
