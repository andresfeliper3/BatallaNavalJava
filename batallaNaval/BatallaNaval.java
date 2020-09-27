package batallaNaval;

import java.awt.BorderLayout;
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
	private Escucha escucha;
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() {
		//Container & layout
		
		//Escucha
		escucha = new Escucha();
		
		//Componentes gráficos
		//Título
		titulo = new JLabel("Batalla Naval");
		add(titulo, BorderLayout.NORTH);
		//Tablero posición
		tableroPosicion = new TableroPosicion();
		add(tableroPosicion, BorderLayout.WEST);
		//Tablero principal
		tableroPrincipal = new TableroPrincipal();
		add(tableroPrincipal, BorderLayout.EAST);
		
		
		
	}

	
	private class Escucha implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			
		}
	}	
}
