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
	private JFrame referencia = this;
	private Muelle muelle;
	//Barcos
	private Portaaviones miPortaaviones, pcPortaaviones;
	private Submarino miSubmarino1, miSubmarino2, pcSubmarino1, pcSubmarino2;
	private Destructor miDestructor1, miDestructor2, miDestructor3, pcDestructor1, pcDestructor2, pcDestructor3;
	private Fragata miFragata1, miFragata2, miFragata3, miFragata4, pcFragata1, pcFragata2, pcFragata3, pcFragata4;
	private JLabel titulo;
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
		muelle = new Muelle();
		
	}
	
	private void initGUI() {
		//Container & layout
		
		//Escucha
	
		
		//Componentes gráficos
		//Título
		//titulo = new JLabel("Batalla Naval");
		//add(titulo, BorderLayout.NORTH);
		//Muelle
		muelle = new Muelle();
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
		miSubmarino1 = new Submarino();
		miDestructor1 = new Destructor();
		miDestructor2 = new Destructor();
		miDestructor3 = new Destructor();
		miFragata1 = new Fragata();
		miFragata2 = new Fragata();
		miFragata3 = new Fragata();
		miFragata4 = new Fragata();
		
		
		
	}

	
	
}
