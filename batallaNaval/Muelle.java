package batallaNaval;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class Muelle.
 */
public class Muelle extends JPanel {
	
	private JButton ready;
	private JLabel mensaje,ayudaReady,hundido,tocado,agua;
	private JTextArea aguaInfo,hundidoInfo,tocadoInfo;
	private Escucha escucha;
	private JPanel zonaPortaaviones,zonaSubmarinos,zonaDestructores,zonaFragatas,zonaInformacion;
	private ImageIcon imagen;
	private JLabel miPortaaviones,miSubmarino1,miSubmarino2,miDestructor1,miDestructor2,
	   miDestructor3,miFragata1,miFragata2,miFragata3,miFragata4;
	private JLabel[] barcos = new JLabel[10];
	private JPanel[] zonas = new JPanel[4];
	private int indexOfBarcoSeleccionado;
	private BatallaNaval referenciaBatallaNaval;
	private int barcosRestantes;
	
	/**
	 * Constructor
	 * Instantiates a new muelle.
	 *
	 * @param refBatallaNaval the ref batalla naval
	 */
	public Muelle(BatallaNaval refBatallaNaval) {
			this.setBackground(Color.WHITE);
			initGUI();
			referenciaBatallaNaval=refBatallaNaval;
	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		// TODO Auto-generated method stub
		//set up container - layout
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		//escucha 
		escucha = new Escucha();
		
		//set up GUIComponents add to window
		//mensaje
		mensaje = new JLabel("ESCOGE UN BARCO PARA PONERLO EN EL TABLERO DE LA IZQUIERDA");
		mensaje.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		mensaje.setBorder(border);
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.CENTER;
		add(mensaje,constraints);
		//Botón
		ready = new JButton("Ready");
		ready.addActionListener(escucha);
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.SOUTH;
		add(ready,constraints);
		//ayudaReady
		ayudaReady = new JLabel("Cuando termine de posicionar todos sus barcos presione 'Ready'");
		ayudaReady.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		constraints.gridx=0;
		constraints.gridy=6;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.CENTER;
		add(ayudaReady,constraints);
		//zona de los Portaaviones
		zonaPortaaviones = new JPanel();
		zonaPortaaviones.setBackground(Color.WHITE);
		zonaPortaaviones.setPreferredSize(new Dimension(150,240));
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
		zonaSubmarinos.setPreferredSize(new Dimension(150,240));
		
		constraints.gridx=1;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaSubmarinos,constraints);
		//Zona de los destructores
		zonaDestructores = new JPanel();
		zonaDestructores.setBackground(Color.WHITE);
		zonaDestructores.setBorder(new TitledBorder("Destructores"));
		zonaDestructores.setPreferredSize(new Dimension(150,240));
				
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaDestructores,constraints);
		//Zona de las fragatas
		zonaFragatas = new JPanel();
		zonaFragatas.setBackground(Color.WHITE);
		zonaFragatas.setPreferredSize(new Dimension(150,240));
		zonaFragatas.setBorder(new TitledBorder("Fragatas"));
				
		constraints.gridx=3;
		constraints.gridy=3;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaFragatas,constraints);
		//Zona de Información
		zonaInformacion = new JPanel(new GridBagLayout());
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaInformacion,constraints);

		zonaInformacion.setBackground(Color.WHITE);
		zonaInformacion.setPreferredSize(new Dimension(600,250));
		
		//Imagen de la X
		agua = new JLabel(new ImageIcon("src/imagenes/agua.gif"));
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		zonaInformacion.add(agua,constraints);
		//Imagen de la bomba
		tocado = new JLabel(new ImageIcon("src/imagenes/tocado.gif"));
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		zonaInformacion.add(tocado,constraints);
		//Imagen del fuego
		hundido = new JLabel(new ImageIcon("src/imagenes/hundido.gif"));
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		zonaInformacion.add(hundido,constraints);
		//Información de la X
		aguaInfo = new JTextArea("Agua: Cuando se dispara sobre una casilla donde no está colocado ningún barco enemigo.En el tablero aparecerá una X. Pasa el turno a tu oponente.");
		aguaInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		aguaInfo.setPreferredSize(new Dimension(500,70));
		aguaInfo.setWrapStyleWord(true);
		aguaInfo.setLineWrap(true);
		aguaInfo.setEditable(false);
		aguaInfo.setBorder(null);
		aguaInfo.setOpaque(true);
		aguaInfo.setBackground(Color.WHITE);
		
		JPanel zonaAguaInfo = new JPanel();
		zonaAguaInfo.setPreferredSize(new Dimension(590,62));
		zonaAguaInfo.setBackground(Color.WHITE);
		zonaAguaInfo.add(aguaInfo);
		
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		zonaInformacion.add(zonaAguaInfo,constraints);
		//Información de la bomba
		tocadoInfo = new JTextArea("Tocado: Cuando se dispara en una casilla en la que está ubicado un barco enemigo que ocupa 2 o más casillas; se destruye sólo una parte del barco. En el tablero del jugador aparecerá esa parte del barco con una marca indicativa de que ha sido tocado. El jugador vuelve a disparar.");
		tocadoInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		tocadoInfo.setPreferredSize(new Dimension(500,70));
		tocadoInfo.setWrapStyleWord(true);
		tocadoInfo.setLineWrap(true);
		tocadoInfo.setEditable(false);
		tocadoInfo.setBorder(null);
		tocadoInfo.setOpaque(true);
		tocadoInfo.setBackground(Color.WHITE);
		
		JPanel zonaTocadoInfo = new JPanel();
		zonaTocadoInfo.setPreferredSize(new Dimension(590,62));
		zonaTocadoInfo.add(tocadoInfo);
		zonaTocadoInfo.setBackground(Color.WHITE);
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		zonaInformacion.add(zonaTocadoInfo,constraints);
		//Información del fuego
		hundidoInfo = new JTextArea("Hundido: Si se dispara en una casilla en la que está ubicado una fragata (1 casilla) u otro barco con el resto de casillas tocadas, se habrá hundido, es decir, se ha eliminado ese barco del juego. Aparecerá en el tablero principal del jugador, el barco completo con la marca indicativa de que ha sido hundido. El jugador puede volver a disparar, siempre y cuando no hayas hundido toda la flota de su enemigo, en cuyo caso habrá ganado. ");
		hundidoInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		hundidoInfo.setPreferredSize(new Dimension(500,100));
		hundidoInfo.setWrapStyleWord(true);
		hundidoInfo.setLineWrap(true);
		hundidoInfo.setEditable(false);
		hundidoInfo.setBorder(null);
		hundidoInfo.setOpaque(true);
		hundidoInfo.setBackground(Color.WHITE);
		
		JPanel zonaHundidoInfo = new JPanel();
		zonaHundidoInfo.setPreferredSize(new Dimension(590,62));
		zonaHundidoInfo.add(hundidoInfo);
		zonaHundidoInfo.setBackground(Color.WHITE);
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=4;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		zonaInformacion.add(zonaHundidoInfo,constraints);
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
	
	/**
	 * Pintar barcos.
	 * Pinta todos los barcos al inicio del juego en el muelle
	 * para que el usuario los pueda seleccionar.
	 */
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
    
	/**
	 * Index of barco.
	 *
	 * @param barcoSeleccionado the barco seleccionado
	 * Identifica el índice del barco seleccionado en el array de barcos.
	 */
	private void indexOfBarco(JLabel barcoSeleccionado) {

		for(int i =0;i<barcos.length;i++) {
			if(barcoSeleccionado == barcos[i]) {
				indexOfBarcoSeleccionado = i;
			}
		}
	}
	
	/**
	 * Cambiar mensaje muelle.
	 *
	 * @param caso the caso
	 * Cambia el texto del mensaje según la situación.
	 */
	public void cambiarMensajeMuelle(int caso) {
		mensaje.setForeground(Color.BLACK);
		mensaje.setBackground(null);
		mensaje.setOpaque(false);
		switch(caso) {
		case 0:	mensaje.setText("HAGA CLICK EN UN BARCO PARA PONERLO EN EL TABLERO DE LA IZQUIERDA");
				break;
		case 1:
				mensaje.setText("HAGA CLICK EN LA CASILLA DONDE DESEA POSICIONAR EL BARCO SELECCIONADO");
				break;
		case 2:
				mensaje.setText("ESCOJA UNA CASILLA ADYACENTE A LA CASILLA SELECCIONADA");
				break;
		case 3:
				mensaje.setText("DEBES ACOMODAR TODOS LOS BARCOS EN EL TABLERO ANTES DE INICIAR");
				mensaje.setForeground(Color.WHITE);
				mensaje.setBackground(Color.RED);
				mensaje.setOpaque(true);
				break;
		}
	}
	
	/**
	 * The Class Escucha.
	 */
	private class Escucha extends MouseAdapter implements ActionListener, MouseMotionListener{
		
		/**
		 * Mouse pressed.
		 *
		 * @param eventMouse the event mouse
		 */
		public void mousePressed(MouseEvent eventMouse) {
			if(!referenciaBatallaNaval.hayBarcoSeleccionado()) {
				
				JLabel barcoSeleccionado = (JLabel)eventMouse.getSource();
				barcoSeleccionado.setVisible(false);
				indexOfBarco(barcoSeleccionado);
				referenciaBatallaNaval.setMensajeMuelle(1);
				referenciaBatallaNaval.pasarBarco(indexOfBarcoSeleccionado);
				barcosRestantes--;
			}
		}
		
		/**
		 * Action performed.
		 *
		 * @param eventAction the event action
		 */
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
			else {
				referenciaBatallaNaval.setMensajeMuelle(3);
			}
		}
	}
}
