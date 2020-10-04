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
	private BatallaNaval referenciaBatallaNaval = this;
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
	
	private Barco[] misBarcos;
	private Barco[] pcBarcos = new Barco[10];
	private Barco barcoSeleccionado;
	
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
		muelle = new Muelle(referenciaBatallaNaval);
		add(muelle,BorderLayout.WEST);
		
		//Barcos
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
		
		pcBarcos = new Barco[10];
		pcBarcos[0] = pcPortaaviones;
		pcBarcos[1] = pcSubmarino1;
		pcBarcos[2] = pcSubmarino2;
		pcBarcos[3] = pcDestructor1;
		pcBarcos[4] = pcDestructor2;
		pcBarcos[5] = pcDestructor3;
		pcBarcos[6] = pcFragata1;
		pcBarcos[7] = pcFragata2;
		pcBarcos[8] = pcFragata3;
		pcBarcos[9] = pcFragata4;
		
		muelle = new Muelle(referenciaBatallaNaval);
	}
	
	public void pasarBarco(int index) {
		
		barcoSeleccionado = misBarcos[index];
		tableroPosicion.setBarcoSeleccionado(barcoSeleccionado);

	}

	private class Escucha implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub

			
		}
		
	}
	
}
