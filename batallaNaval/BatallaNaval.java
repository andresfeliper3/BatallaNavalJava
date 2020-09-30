package batallaNaval;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BatallaNaval extends JFrame {

	//estadoDelJuego 0 Organizar, 1 Jugar, 2 Ganar y 3 perder
	//turno true Jugador, false Máquina
	private int estadoDelJuego;
	private boolean turno;
	private Escucha escucha;
	private JFrame	ZonaDeJuego;
	private TableroPosicion tableroPosicion;
	private TableroPrincipal tableroPrincipal;
	private Muelle muelle;
	private JLabel titulo;
	
	//Mi muelle
	private Portaaviones miPortaaviones;
	private Submarino miSubmarino1,miSubmarino2;
	private Destructor miDestructor1,miDestructor2,miDestructor3;
	private Fragata miFragata1,miFragata2,miFragata3,miFragata4;
	
	//muelle computador
	private Portaaviones pcPortaaviones;
	private Submarino pcSubmarino1,pcSubmarino2;
	private Destructor pcDestructor1,pcDestructor2,pcDestructor3;
	private Fragata pcFragata1,pcFragata2,pcFragata3,pcFragata4;
	
	
	public BatallaNaval() {
			
			initGUI();
	//Window default config
	this.setTitle("Batalla Naval");
	this.pack();
	this.setLocationRelativeTo(null);
	//this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);

	}
	
	private void initGUI() {
		//Set up Container BorderLayout
		
		//escucha, referencia y control
		escucha = new Escucha();
		
		//set up GUIComponents add to window
		titulo = new JLabel("Batalla Naval");
		add(titulo,BorderLayout.NORTH);
		
		tableroPosicion = new TableroPosicion();
		add(tableroPosicion,BorderLayout.CENTER);
		
		tableroPrincipal = new TableroPrincipal();
		add(tableroPrincipal,BorderLayout.EAST);
		
		//Muelle
		muelle = new Muelle();
		add(muelle,BorderLayout.WEST);

	}

	private class Escucha implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			Casilla casillaSeleccionada = (Casilla)eventAction.getSource();
			tableroPosicion.pintarBarco(casillaSeleccionada);
			System.out.println(casillaSeleccionada.getIdCasilla());
			
		}
		
	}
	
}
