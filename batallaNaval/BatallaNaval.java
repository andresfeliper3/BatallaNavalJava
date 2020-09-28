package batallaNaval;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	
	public BatallaNaval() {
			
			initGUI();
	//Window default config
	this.setTitle("Batalla Naval");
	this.pack();
	this.setLocationRelativeTo(null);
	//this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
	muelle = new Muelle(this);
	}
	
	private void initGUI() {
		//Set up Container BorderLayout
		
		//escucha, referencia y control
		escucha = new Escucha();
		
		//set up GUIComponents add to window
		titulo = new JLabel("Batalla Naval");
		add(titulo,BorderLayout.NORTH);
		
		tableroPosicion = new TableroPosicion();
		add(tableroPosicion,BorderLayout.WEST);
		
		tableroPrincipal = new TableroPrincipal();
		add(tableroPrincipal,BorderLayout.EAST);
		
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
