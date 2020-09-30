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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;

public class Muelle extends JPanel {
	private JButton ready;
	private JLabel titulo;
	private JLabel textoPortaaviones, textoSubmarinos, textoFragatas, textoDestructores;
	private Escucha escucha;
	private JFrame batallaNaval;
	private JPanel zonaPortaaviones, zonaSubmarinos, zonaDestructores, zonaFragatas;
	private boolean visible; //true si es visible, false en caso contrario
	private GridBagConstraints constraints;
	private ImageIcon imagen;
	private JLabel miPortaaviones, miSubmarino1, miSubmarino2, miDestructor1, miDestructor2, miDestructor3,  miFragata1, miFragata2, miFragata3, miFragata4;
	
	
	//Constructor

	public Muelle() {
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
		/*//Icono rotado 90 grados (down = rota hacia la derecha, up = rota hacia la izquierda)
        label2 = new JLabel(new RotatedIcon(imageIcon));
        //Icono rotado 45 grados
        label3 = new JLabel(new RotatedIcon(imageIcon, 45));
        , RotatedIcon.Rotate.DOWN
		 * */
		
		imagen = new ImageIcon(Portaaviones.rutaFile);
		miPortaaviones = new JLabel(new RotatedIcon(imagen, RotatedIcon.Rotate.DOWN));
		miPortaaviones.addMouseListener(escucha);
		zonaPortaaviones.add(miPortaaviones);
		JPanel panelSeleccionado = new JPanel();
		JLabel[] misBarcos = {miSubmarino1, miSubmarino2, miDestructor1, miDestructor2, miDestructor3,  miFragata1, miFragata2, miFragata3, miFragata4};
		for(int i = 0; i < misBarcos.length; i++) {	
			if(i < 2) {
				imagen = new ImageIcon(Submarino.rutaFile);
				panelSeleccionado = zonaSubmarinos;
			}
			else if(i > 1 && i < 5) {
				imagen = new ImageIcon(Destructor.rutaFile);
				panelSeleccionado = zonaDestructores;
			}
			else if(i > 4 && i < 9) {
				imagen = new ImageIcon(Fragata.rutaFile);
				panelSeleccionado = zonaFragatas;
			}
			//imagen
			misBarcos[i] = new JLabel(imagen);
			misBarcos[i].addMouseListener(escucha);
			panelSeleccionado.add(misBarcos[i]);
		}
		

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
