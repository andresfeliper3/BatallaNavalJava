package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Muelle extends JFrame {
	
	private JButton ready;
	private JLabel titulo, portaaviones,submarinos,destructores,fragatas;
	private Escucha escucha;
	
	private Portaaviones portaavion;
	private Submarino submarino1,submarino2;
	private Destructor destructor1,destructor2,destructor3;
	private Fragata fragata1,fragata2,fragata3,fragata4;
	private JFrame batallaNaval;
	private JPanel zonaPortaaviones,zonaSubmarinos,zonaDestructores,zonaFragatas;
	
	
	public Muelle(JFrame batallaNaval) {

		this.batallaNaval = batallaNaval;
		initGUI();
	//default config
		this.setTitle("Organiza tus barcos");
		this.pack();
		this.setLocation(batallaNaval.getLocation().x,batallaNaval.getLocation().y);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initGUI() {
		// TODO Auto-generated method stub
		//set up container - layout
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		//escucha 
		escucha = new Escucha();
		
		//set up GUIComponents add to window
		//Título
		titulo = new JLabel("ORGANICE SUS BARCOS");
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.CENTER;
		add(titulo,constraints);
		
		//Botón
		ready = new JButton("Ready");
		ready.addActionListener(escucha);
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		add(ready,constraints);
		
		//Titulos
		portaaviones = new JLabel("Portaaviones");
		portaaviones.setBorder(border);
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(portaaviones,constraints);
		
		submarinos = new JLabel("Submarinos");
		submarinos.setBorder(border);
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(submarinos,constraints);
		
		destructores = new JLabel("Destructores");
		destructores.setBorder(border);
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(destructores,constraints);
		
		fragatas = new JLabel("Fragatas");	
		fragatas.setBorder(border);
		constraints.gridx=3;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(fragatas,constraints);
		
		zonaPortaaviones = new JPanel();
		zonaPortaaviones.setBorder(border);
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaPortaaviones,constraints);
		
		zonaSubmarinos = new JPanel();
		zonaSubmarinos.setBorder(border);
		constraints.gridx=1;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaSubmarinos,constraints);
		
		zonaDestructores = new JPanel();
		zonaDestructores.setBorder(border);
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaDestructores,constraints);
		
		zonaFragatas = new JPanel();
		zonaFragatas.setBorder(border);
		constraints.gridx=3;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaFragatas,constraints);
		
	}

	private class Escucha extends MouseAdapter implements ActionListener{
		
		public void mousePressed(MouseEvent eventMouse) {
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
