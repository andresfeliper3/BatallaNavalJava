package batallaNaval;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BatallaNaval extends JFrame {
	/*	Estado del juego
	 * 0: organizar
	 * 1: jugar
	 * 2: ganar
	 * 3: perder
	 * */
	private int estado;	
	/* Turno en juego
	 * true: jugador
	 * false: computador
	 * */
	private boolean turno;
	private TableroPosicion tableroPosicion;
	private TableroPrincipal tableroPrincipal;
	private BatallaNaval referencia = this;
	private Muelle muelle;
	//Barcos
	private Portaaviones miPortaaviones, pcPortaaviones;
	private Submarino miSubmarino1, miSubmarino2, pcSubmarino1, pcSubmarino2;
	private Destructor miDestructor1, miDestructor2, miDestructor3, pcDestructor1, pcDestructor2, pcDestructor3;
	private Fragata miFragata1, miFragata2, miFragata3, miFragata4, pcFragata1, pcFragata2, pcFragata3, pcFragata4;
	private JLabel titulo;
	private Barco[] misBarcos;
	private Barco barcoSeleccionado;
	//Constructor
	public BatallaNaval() {
		this.estado = 0;
		initGUI();
		
		//Configuración de ventana
		
		this.setTitle("Batalla Naval");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() {
		//Container & layout
	
		//Escucha
	
		
		//Componentes gráficos
		//Título
		//titulo = new JLabel("Batalla Naval");
		//add(titulo, BorderLayout.NORTH);
		//Muelle
		muelle = new Muelle(referencia);
		add(muelle, BorderLayout.WEST);
		//Tablero posición
		tableroPosicion = new TableroPosicion();
		add(tableroPosicion, BorderLayout.CENTER);
		//Tablero principal
		tableroPrincipal = new TableroPrincipal();
		add(tableroPrincipal, BorderLayout.EAST);
		
		//Mis barcos
		miPortaaviones = new Portaaviones();
		miSubmarino1 = new Submarino();
		miSubmarino2 = new Submarino();
		miDestructor1 = new Destructor();
		miDestructor2 = new Destructor();
		miDestructor3 = new Destructor();
		miFragata1 = new Fragata();
		miFragata2 = new Fragata();
		miFragata3 = new Fragata();
		miFragata4 = new Fragata();		
		
		//Array de barcos
		misBarcos = new Barco[10];
		misBarcos[0] = miPortaaviones;
		misBarcos[1] = miSubmarino1;
		misBarcos[2] = miSubmarino2;
		misBarcos[3] = miDestructor1;
		misBarcos[4] = miDestructor2;
		misBarcos[5] = miDestructor3;
		misBarcos[6] = miFragata1;
		misBarcos[7] = miFragata2;
		misBarcos[8] = miFragata3;
		misBarcos[9] = miFragata4;
		
	}

	public void pasarBarcoSeleccionado(int index) {
		barcoSeleccionado = misBarcos[index];
		tableroPosicion.setBarcoSeleccionado(barcoSeleccionado);
	}
	
	//Retorna true si en el muelle se ha seleccionado un barco
	public boolean hayBarcoSeleccionado() {
		boolean hayBarco = tableroPosicion.getBarcoSeleccionado() == null ? false : true;
		return hayBarco;	
	}


}
