package batallaNaval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;

public class Muelle extends JPanel {
	private JButton botonReady;
	private JLabel titulo, mensaje;
	private JLabel textoPortaaviones, textoSubmarinos, textoFragatas, textoDestructores;
	private Escucha escucha;
	private JFrame batallaNaval;
	private JPanel zonaPortaaviones, zonaSubmarinos, zonaDestructores, zonaFragatas;
	private boolean visible; //true si es visible, false en caso contrario
	private GridBagConstraints constraints;
	private ImageIcon imagen;
	private JLabel miPortaaviones, miSubmarino1, miSubmarino2, miDestructor1, miDestructor2, miDestructor3,  miFragata1, miFragata2, miFragata3, miFragata4;
	private int indexOfBarcoSeleccionado;
	private JLabel[] misBarcos = {miPortaaviones, miSubmarino1, miSubmarino2, miDestructor1, miDestructor2, miDestructor3,  miFragata1, miFragata2, miFragata3, miFragata4};
	private BatallaNaval ventana;
	private JLabel barcoSeleccionado;
	private int barcosRestantes; //barcos que faltan por poner
	//Constructor

	public Muelle(BatallaNaval ventana) {
		
		//Layout
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		//Escucha
		escucha = new Escucha();
		//Visibilidad
		visible = true;
		setVisible(visible);
		setBackground(Color.WHITE);
		//Ventana batalla naval
		this.ventana = ventana;
		//Componentes
		//Título
		titulo = new JLabel("Organice sus barcos");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		add(titulo, constraints);
		
		//Mensaje de ayuda
		mensaje = new JLabel("Haga click sobre un barco");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		add(mensaje, constraints);
		//Botón ready
		botonReady = new JButton("Ready");
		botonReady.addActionListener(escucha);
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		add(botonReady, constraints);
		//Botón reset
		//Barcos
		zonaPortaaviones = new JPanel();
		zonaPortaaviones.setPreferredSize(new Dimension(100,500));
		zonaPortaaviones.setBackground(Color.WHITE);
		zonaPortaaviones.setBorder(new TitledBorder("Portaaviones"));
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(zonaPortaaviones, constraints);
		zonaSubmarinos = new JPanel();
		zonaSubmarinos.setBackground(Color.WHITE);
		zonaSubmarinos.setPreferredSize(new Dimension(100,500));
		zonaSubmarinos.setBorder(new TitledBorder("Submarinos"));
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(zonaSubmarinos, constraints);
		zonaDestructores = new JPanel();
		zonaDestructores.setBackground(Color.WHITE);
		zonaDestructores.setPreferredSize(new Dimension(100,500));
		zonaDestructores.setBorder(new TitledBorder("Destructores"));
		constraints.gridx = 2;
		constraints.gridy = 3;
		add(zonaDestructores, constraints);
		zonaFragatas = new JPanel();
		zonaFragatas.setBackground(Color.WHITE);
		zonaFragatas.setPreferredSize(new Dimension(100,500));
		zonaFragatas.setBorder(new TitledBorder("Fragatas"));
		constraints.gridx = 3;
		constraints.gridy = 3;
		add(zonaFragatas, constraints);
		
		pintarBarcos();
	}
	
	private void pintarBarcos() {
		
		JPanel panelSeleccionado = new JPanel();
	
		for(int i = 0; i < misBarcos.length; i++) {	
			if(i == 0) {
				imagen = new ImageIcon(Portaaviones.rutaFile);
				misBarcos[i] = new JLabel(new RotatedIcon(imagen, RotatedIcon.Rotate.DOWN));
				panelSeleccionado = zonaPortaaviones;

			}
			else if(i < 3) {
				imagen = new ImageIcon(Submarino.rutaFile);
				panelSeleccionado = zonaSubmarinos;
				misBarcos[i] = new JLabel(imagen);
			}
			else if(i > 2 && i < 6) {
				imagen = new ImageIcon(Destructor.rutaFile);
				panelSeleccionado = zonaDestructores;
				misBarcos[i] = new JLabel(imagen);
			}
			else if(i > 5 && i < 10) {
				imagen = new ImageIcon(Fragata.rutaFile);
				panelSeleccionado = zonaFragatas;
				misBarcos[i] = new JLabel(imagen);
			}
			//imagen
			misBarcos[i].addMouseListener(escucha);
			panelSeleccionado.add(misBarcos[i]);
		}
		//Barcos restantes adquiere su valor inicial
		barcosRestantes = misBarcos.length;

	}
	
	//Retorna index de los barcos del array. Retorna -1 si no tiene posicion
	private int indexOfBarco(JLabel barco) {
		for(int i = 0; i < misBarcos.length; i++) {
			if(barco == misBarcos[i]) {
				return i;
			}
		}	
		return -1;		
	}
	
	//editar mensaje de ayuda
	public void setMensaje(String texto) {
		mensaje.setText(texto);
	}
	private class Escucha implements MouseListener, ActionListener {
		@Override
		public void mouseClicked(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			//Revisa que el usuario no tenga un barco seleccionado. Esto asegura que termine de poner las partes de un barco.
			if(!ventana.hayBarcoSeleccionado()) {
				barcoSeleccionado = (JLabel)eventMouse.getSource();
				indexOfBarcoSeleccionado = indexOfBarco(barcoSeleccionado);	
				ventana.pasarBarcoSeleccionado(indexOfBarcoSeleccionado);
				barcoSeleccionado.setVisible(false);
				barcosRestantes--;
				setMensaje("Haga click en una casilla");		
			}
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
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			//Botón para empezar el juego
			if(eventAction.getSource() == botonReady && barcosRestantes == 0) {
				//Cambia el estado de juego a jugar
				ventana.setEstado(1);
				//Turno del usuario
				ventana.setTurno(true);
				setVisible(false);
				ventana.pack();
				ventana.setLocationRelativeTo(null);
				//Mensaje
				
			}
			/*
			if(eventAction.getSource() == botonReset) {
				ventana.dispose();
				ventana = new BatallaNaval();
			}*/
		}

		
		
	}
}
