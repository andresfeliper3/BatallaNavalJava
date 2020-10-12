package batallaNaval;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Muelle extends JPanel {
	
	private JButton ready;
	private JLabel titulo, portaaviones,submarinos,destructores,fragatas;
	private Escucha escucha;
	private JPanel zonaPortaaviones,zonaSubmarinos,zonaDestructores,zonaFragatas;
	private ImageIcon imagen;
	private JLabel miPortaaviones,miSubmarino1,miSubmarino2,miDestructor1,miDestructor2,
	   miDestructor3,miFragata1,miFragata2,miFragata3,miFragata4;
	private JLabel[] barcos = new JLabel[10];;
	private JPanel[] zonas = new JPanel[4];
	private int indexOfBarcoSeleccionado;
	private BatallaNaval referenciaBatallaNaval;
	private int barcosRestantes;
	
	public Muelle(BatallaNaval refBatallaNaval) {

			this.setBackground(Color.WHITE);
			initGUI();
			referenciaBatallaNaval=refBatallaNaval;
	}
	
	private void initGUI() {
		// TODO Auto-generated method stub
		//set up container - layout
		setLayout(new GridBagLayout());
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
		
		//zona de los Portaaviones
		zonaPortaaviones = new JPanel();
		zonaPortaaviones.setBackground(Color.WHITE);
		zonaPortaaviones.setPreferredSize(new Dimension(150,470));
		zonaPortaaviones.setBorder(new TitledBorder("Portaaviones"));
				
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaPortaaviones,constraints);
		//zona de los Submarinos
		zonaSubmarinos = new JPanel();
		zonaSubmarinos.setBackground(Color.WHITE);
		zonaSubmarinos.setBorder(new TitledBorder("Submarinos"));
		zonaSubmarinos.setPreferredSize(new Dimension(150,470));
		
		constraints.gridx=1;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaSubmarinos,constraints);
		//Zona de los destructores
		zonaDestructores = new JPanel();
		zonaDestructores.setBackground(Color.WHITE);
		zonaDestructores.setBorder(new TitledBorder("Destructores"));
		zonaDestructores.setPreferredSize(new Dimension(150,470));
				
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaDestructores,constraints);
		//Zona de las fragatas
		zonaFragatas = new JPanel();
		zonaFragatas.setBackground(Color.WHITE);
		zonaFragatas.setPreferredSize(new Dimension(150,470));
		zonaFragatas.setBorder(new TitledBorder("Fragatas"));
				
		constraints.gridx=3;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaFragatas,constraints);
		
		//Lista de zonas
		zonas[0] = zonaPortaaviones;
		zonas[1] = zonaSubmarinos;
		zonas[2] = zonaDestructores;
		zonas[3] = zonaFragatas;		
		
		//Lista de barcos iniciales en el muelle
		barcos[0] = miPortaaviones;
		barcos[1] = miSubmarino1;
		barcos[2] = miSubmarino2;
		barcos[3] = miDestructor1;
		barcos[4] = miDestructor2;
		barcos[5] = miDestructor3;
		barcos[6] = miFragata1;
		barcos[7] = miFragata2;
		barcos[8] = miFragata3;
		barcos[9] = miFragata4;
		
	
		pintarBarcos();
	}

	private void pintarBarcos() {
		// TODO Auto-generated method stub
		int j = 0;
			for(int i = 0; i< barcos.length;i++) {
				if(i == 0) {
					imagen = new ImageIcon(Portaaviones.rutaFile);
					barcos[i] = new JLabel(new RotatedIcon(imagen,RotatedIcon.Rotate.DOWN));
					
				}
				else if(i >0 && i <=2) {
					imagen = new ImageIcon(Submarino.rutaFile);
					barcos[i] = new JLabel(imagen);
					j=1;
				}
				else if(i > 2 && i <=5) {
					imagen = new ImageIcon(Destructor.rutaFile);
					barcos[i] = new JLabel(imagen);
					j=2;
				}
				else if(i >5){
					imagen = new ImageIcon(Fragata.rutaFile);
					barcos[i] = new JLabel(imagen);
					j=3;
				}

				barcos[i].addMouseListener(escucha);
				barcos[i].setCursor(new Cursor(Cursor.MOVE_CURSOR));
				barcos[i].addMouseMotionListener(escucha);
				zonas[j].add(barcos[i]);
		}
			barcosRestantes = barcos.length;
	}
    
	private void indexOfBarco(JLabel barcoSeleccionado) {

		for(int i =0;i<barcos.length;i++) {
			if(barcoSeleccionado == barcos[i]) {
				indexOfBarcoSeleccionado = i;
			}
		}
	}

	private class Escucha extends MouseAdapter implements ActionListener, MouseMotionListener{
		
		public void mousePressed(MouseEvent eventMouse) {
			//System.out.println(indexOfBarcoSeleccionado);

			if(referenciaBatallaNaval.hayBarcoSeleccionado() == false) {
				
				JLabel barcoSeleccionado = (JLabel)eventMouse.getSource();
				barcoSeleccionado.setVisible(false);
				indexOfBarco(barcoSeleccionado);
				referenciaBatallaNaval.pasarBarco(indexOfBarcoSeleccionado);
				barcosRestantes--;
			}
		}

		
		public void mouseEntered(MouseEvent eventMouse) {
			
		}
		
		public void mouseExited(MouseEvent eventMouse) {
			
		}
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			
			if(eventAction.getSource() == ready && barcosRestantes==0) {
				
				referenciaBatallaNaval.setEstadoDelJuego(1);
				referenciaBatallaNaval.setTurno(true); //dispara primero el usuario
				setVisible(false);
				referenciaBatallaNaval.pack();
				referenciaBatallaNaval.setLocationRelativeTo(null);
				referenciaBatallaNaval.playSound("musicaFondo");
				referenciaBatallaNaval.partidaEnCurso();
		

			}
		}
	}
}
