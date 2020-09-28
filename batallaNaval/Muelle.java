package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

public class Muelle extends JFrame {
	private JButton ready;
	private JLabel titulo;
	private JLabel textoPortaaviones, textoSubmarinos, textoFragatas, textoDestructores;
	private Escucha escucha;
	private JFrame batallaNaval;
	private JPanel zonaPortaaviones, zonaSubmarinos, zonaDestructores, zonaFragatas;
	//Constructor
	public Muelle(JFrame batallaNaval) {
		initGUI();
		this.batallaNaval = batallaNaval;
		this.pack();
		this.setResizable(false);
		this.setLocation(batallaNaval.getLocation().x, batallaNaval.getLocation().y);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initGUI() {
		//Layout
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//Escucha
		escucha = new Escucha();
		//Componentes
		//Título
		titulo = new JLabel("Organice sus barcos");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		add(titulo, constraints);
		//Botón 
		ready = new JButton("Ready");
		ready.addActionListener(escucha);
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		add(ready, constraints);
		//Textos de barcos
		textoPortaaviones = new JLabel("Portaaviones");
		textoPortaaviones.setBorder(border);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		add(textoPortaaviones, constraints);
		textoSubmarinos = new JLabel("Submarinos");
		textoSubmarinos.setBorder(border);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		add(textoSubmarinos, constraints);
		textoDestructores = new JLabel("Destructores");
		textoDestructores.setBorder(border);
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		add(textoDestructores, constraints);
		textoFragatas = new JLabel("Fragatas");
		textoFragatas.setBorder(border);
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		add(textoFragatas, constraints);
		//Barcos
		zonaPortaaviones = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(zonaPortaaviones, constraints);
		zonaSubmarinos = new JPanel();
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(zonaSubmarinos, constraints);
		zonaDestructores = new JPanel();
		constraints.gridx = 2;
		constraints.gridy = 3;
		add(zonaDestructores, constraints);
		zonaFragatas = new JPanel();
		constraints.gridx = 3;
		constraints.gridy = 3;
		add(zonaFragatas, constraints);
		
		
		
	}
	private class Escucha implements MouseListener, ActionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
