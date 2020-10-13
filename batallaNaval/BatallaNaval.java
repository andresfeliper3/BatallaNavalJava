/*
 * 
 */
package batallaNaval;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



// TODO: Auto-generated Javadoc
/**
 * The Class BatallaNaval.
 */
public class BatallaNaval extends JFrame {

	/** The estado del juego. */
	private int estadoDelJuego =0;
	
	/** The barcos en juego. */
	private int barcosEnJuego =10;
	
	/** The turno. */
	private boolean turno;
	
	/** The escucha. */
	private Escucha escucha;
	
	/** The tablero posicion. */
	private TableroPosicion tableroPosicion;
	
	/** The tablero principal. */
	private TableroPrincipal tableroPrincipal;
	
	/** The referencia batalla naval. */
	private BatallaNaval referenciaBatallaNaval=this;
	
	/** The muelle. */
	private Muelle muelle;
	
	/** The contador barcos. */
	private JLabel titulo,contadorBarcos;
	
	/** The clip 2. */
	private Clip clip, clip2;
	
	/** The zona titulo. */
	private JPanel zonaBotones,zonaTitulo;
	
	/** The mostrar Y ocultar. */
	private JButton reset, mostrarYOcultar;
	
	/** The timer. */
	private Timer timer;


	/** The mi portaaviones. */
	//Mi muelle
	private Portaaviones miPortaaviones;
	
	/** The mi submarino 2. */
	private Submarino miSubmarino1,miSubmarino2;
	
	/** The mi destructor 3. */
	private Destructor miDestructor1,miDestructor2,miDestructor3;
	
	/** The mi fragata 4. */
	private Fragata miFragata1,miFragata2,miFragata3,miFragata4;
	
	/** The pc portaaviones. */
	//muelle computador
	private Portaaviones pcPortaaviones;
	
	/** The pc submarino 2. */
	private Submarino pcSubmarino1,pcSubmarino2;
	
	/** The pc destructor 3. */
	private Destructor pcDestructor1,pcDestructor2,pcDestructor3;
	
	/** The pc fragata 4. */
	private Fragata pcFragata1,pcFragata2,pcFragata3,pcFragata4;
	
	/** The mis barcos. */
	private Barco[] misBarcos;
	
	/** The pc barcos. */
	private Barco[] pcBarcos = new Barco[10];
	
	/** The barco seleccionado. */
	private Barco barcoSeleccionado;

	/**
	 * Instantiates a new batalla naval.
	 */
	public BatallaNaval() {
			
		
	initGUI();
	
	//Window default config
	//this.referenciaBatallaNaval = referenciaBatallaNaval;
	this.setTitle("Batalla Naval");
	this.pack();
	this.setLocationRelativeTo(null);
	//this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);

	}
	
	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		//ImageIcon battleShip = new ImageIcon(new ImageIcon("src/imagenes/battleship.jpg").getImage().getScaledInstance(500,500,Image.SCALE_SMOOTH));
		escucha = new Escucha();
		
		//Set up Container - GridBagLayout
		Container container = getContentPane();
		container.setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		zonaTitulo = new JPanel();
		zonaTitulo.setBackground(Color.WHITE);
		titulo = new JLabel(new ImageIcon(new ImageIcon("src/imagenes/battleship_banner.png").getImage().getScaledInstance(600, 100, Image.SCALE_SMOOTH)));
		zonaTitulo.add(titulo);
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=3;
		constraints.fill=GridBagConstraints.CENTER;
		add(zonaTitulo,constraints);
		
		//Título
		titulo = new JLabel("ORGANICE SUS BARCOS");
		titulo.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=3;
		constraints.fill=GridBagConstraints.CENTER;
		add(titulo,constraints);
		//Zona de botones
		zonaBotones = new JPanel();
		zonaBotones.setBackground(Color.WHITE);
		//Contador de Barcos restantes
		contadorBarcos = new JLabel("Barcos restantes: 10");
		zonaBotones.add(contadorBarcos);
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=2;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		add(zonaBotones,constraints);
		//botón de reset
		reset = new JButton("Reiniciar Juego");
		reset.addActionListener(escucha);
		zonaBotones.add(reset);
		//botón de mostrar y/u ocultar los barcos
		mostrarYOcultar = new JButton("Mostrar Barcos");
		mostrarYOcultar.addActionListener(escucha);
		zonaBotones.add(mostrarYOcultar);
		
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		add(zonaBotones,constraints);
		
		//Barcos del usuario
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
		//Barcos del computador
		pcPortaaviones = new Portaaviones();
		pcSubmarino1 = new Submarino();
		pcSubmarino2 = new Submarino();
		pcDestructor1 = new Destructor();
		pcDestructor2 = new Destructor();
		pcDestructor3 = new Destructor();
		pcFragata1 = new Fragata();
		pcFragata2 = new Fragata();
		pcFragata3 = new Fragata();
		pcFragata4 = new Fragata();
		//Lista de barcos del usuario
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
		//Lista de barcos del computador
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
		
		//escucha
		escucha = new Escucha();		
		//Muelle
		muelle = new Muelle(referenciaBatallaNaval);
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(muelle,constraints);
		
		//Tablero Posición	
		tableroPosicion = new TableroPosicion(misBarcos, referenciaBatallaNaval);
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(tableroPosicion,constraints);
		//tablero Principal
		tableroPrincipal = new TableroPrincipal(pcBarcos, referenciaBatallaNaval);
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.CENTER;
		add(tableroPrincipal,constraints);
		tableroPrincipal.ocultarOMostrar();

	}
	
	/**
	 * Sets the turno.
	 *
	 * @param turno the new turno
	 */
	//cambia el turno y ejecuta partidaEnCurso para que establezca a quién le toca disparar
	public void setTurno(boolean turno) {
		this.turno = turno;
		
		partidaEnCurso();
	}
	
	/**
	 * Pasar barco.
	 *
	 * @param index the index
	 */
	//método que se encarga de pasarle barco seleccionado en el muelle al tablero posicion
	public void pasarBarco(int index) {
		barcoSeleccionado = misBarcos[index];
		tableroPosicion.setBarcoSeleccionado(barcoSeleccionado);
	}
	
	/**
	 * Hay barco seleccionado.
	 *
	 * @return true, if successful
	 */
	//Revisa en el tablero de posicion si tengo un barco seleccionado por poner
	public boolean hayBarcoSeleccionado() {
		return tableroPosicion.getBarcoSeleccionado() == null ? false : true;
	}
	
	/**
	 * Sets the estado del juego.
	 *
	 * @param estado the new estado del juego
	 */
	//Cambia el estado del juego: 0 Organizar, 1 Jugar, 2 Usuario Gana y 3 Usuario pierde
	public void setEstadoDelJuego(int estado) {
		this.estadoDelJuego = estado;
	}
	
	/**
	 * Partida en curso.
	 */
	//se encarga de controlar el juego, determinar si el usuario perdió o ganó y asigna los turnos al usuario y al computador
	public void partidaEnCurso() {
		tableroPosicion.setPuedoPonerBarco(); //cambia el boleano a falso para dejar de reproducir el pop de las casillas
	
		//Ejecución del timer
		timer = new Timer("Timer");
		TimerTask task = new TimerTask(){
			public void run() {
				
				if(estadoDelJuego==1) {
					
					if(turno) {
						//USUARIO
						reset.setEnabled(true);
						mostrarYOcultar.setEnabled(true);
						tableroPrincipal.permisoParaDisparar(true);
						tableroPrincipal.cambiarBorde(Color.GREEN);
						titulo.setText("¡ES TU TURNO DE ATACAR!");
						titulo.setForeground(Color.GREEN);
						tableroPrincipal.setDisparoHabilitado(true);//activa el tablero principal para que el usuario ejecute los disparos
						
					}else {
						//COMPUTADOR	

						tableroPosicion.cambiarBorde(Color.RED);
						titulo.setText("¡TE ESTÁN ATACANDO!");
						titulo.setForeground(Color.RED);
						tableroPrincipal.setDisparoHabilitado(false);//desactiva el tablero principal para que el usuario no pueda ejecutar los disparos
						while(!tableroPosicion.generarDisparo(randomPosition(),randomPosition())) {};
						
					}
				}else if(estadoDelJuego==2) {
					
					titulo.setText("¡ES TU TURNO DE ATACAR!");
					titulo.setForeground(Color.GREEN);
					playSound("victoria");
					int option = JOptionPane.showConfirmDialog(tableroPrincipal, "¿Quieres jugar otra ronda?","GANASTE",JOptionPane.YES_NO_OPTION);
					if(option == JOptionPane.YES_OPTION) {
						removeAll();
						clip2.stop();
						clip.stop();
						dispose();
					}else if(option == JOptionPane.NO_OPTION){
						System.exit(0);
					}
					
				}else if(estadoDelJuego==3) {
					reset.setEnabled(true);
					mostrarYOcultar.setEnabled(true);
					playSound("derrota");
					int option = JOptionPane.showConfirmDialog(tableroPosicion, "¿Quieres jugar otra ronda?","PERDISTE",JOptionPane.YES_NO_OPTION);
					if(option == JOptionPane.YES_OPTION) {
						removeAll();
						clip2.stop();
						clip.stop();
						dispose();
					}else if(option == JOptionPane.NO_OPTION){
						System.exit(0);
					}
				}
			}
		};
		timer.schedule(task,1500);
		

	}
	
	/**
	 * Revisar derrota.
	 *
	 * @param barcos the barcos
	 * @return true, if successful
	 */
	//Función que revisa si el usuario perdió
	public boolean revisarDerrota(Barco[] barcos) {
		
		for(int cualBarco = 0;cualBarco < barcos.length;cualBarco++) {
			
			if(!barcos[cualBarco].getEstadoBarco()) {
				return false; //Aún quedan barcos no naufragados del usuario
			}
		}
		return true; //todos los barcos del usuario han sido naufragados
	}
	
	/**
	 * Random position.
	 *
	 * @return the int
	 */
	//Genera un número al azar de columna entre 1 y 10
	private int randomPosition() {
		
		Random random = new Random();
		int col = random.nextInt(10)+1;
		
		return col;
	}
	
	/**
	 * Play sound.
	 *
	 * @param cualSonido the cual sonido
	 */
	//método que ejecuta los sonidos dependiendo del nombre del sonido
		public void playSound(String cualSonido) { 
			File soundFile = new File("");
			float volumen = 0;
				
			try {
				if(cualSonido=="musicaFondo") {
	
					soundFile = new File("src/sounds/musicaBatallaNaval.wav");
					volumen = -15.0f;
				}else if(cualSonido == "disparo") {
					soundFile = new File("src/sounds/disparo.wav");
					volumen = -7.0f;
				}else if(cualSonido == "disparoAcertado") {
					soundFile = new File("src/sounds/disparoAcertado.wav");
					volumen = -5.0f;
				}else if(cualSonido == "disparoAlAgua") {
					soundFile = new File("src/sounds/disparoAlAgua.wav");
					volumen = -5.0f;
				}else if(cualSonido == "naufragado") {
					soundFile = new File("src/sounds/naufragado.wav");
					volumen = -10.0f;
				}else if(cualSonido == "pop") {
					soundFile = new File("src/sounds/pop.wav");
					volumen = -15.0f;
				}else if(cualSonido == "victoria") {
					soundFile = new File("src/sounds/victoria.wav");
					volumen = -5.0f;
					clip2.stop();
				}else if(cualSonido == "derrota") {
					soundFile = new File("src/sounds/derrota.wav");
					volumen = -5.0f;
					clip2.stop();
				}
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
				clip = AudioSystem.getClip();
				clip.open(audioIn);

				
				FloatControl gainControl1 = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl1.setValue(volumen); // Reduce volume by 10 decibels.
			
				if(cualSonido=="musicaFondo" || cualSonido == "victoria" || cualSonido == "derrota") {
					clip2 = clip;
					clip2.loop(Clip.LOOP_CONTINUOUSLY);
					clip2.start();
				}
				else {
					clip.start();
				}
				
			}catch(Exception ex){
				System.err.println(ex.getMessage());
			}
		}
		
		/**
		 * Sets the text mostrar ocultar.
		 *
		 * @param texto the new text mostrar ocultar
		 */
		//cambia el texto del boton de ocultar o mostrar texto
		public void setTextMostrarOcultar(String texto) {
			this.mostrarYOcultar.setText(texto);
		}
		
		/**
		 * Sets the mensaje muelle.
		 *
		 * @param caso the new mensaje muelle
		 */
		//Le pasa el texto al muelle para que cambie su mensaje
		public void setMensajeMuelle(int caso) {
			
			muelle.cambiarMensajeMuelle(caso);
			
		}
		
		/**
		 * Sets the barcos restantes.
		 */
		//Método que define los barcos restantes del computador 
		public void setBarcosRestantes() {
			
			barcosEnJuego--;
			contadorBarcos.setText("Barcos restantes: "+ barcosEnJuego);
		}

	/**
	 * The Class Escucha.
	 */
	private class Escucha implements ActionListener{

		/**
		 * Action performed.
		 *
		 * @param eventAction the event action
		 */
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			if(eventAction.getSource()==reset) {
				//BOTON REST EN BATALLA NAVAL:
				if(estadoDelJuego == 0) {
						removeAll();
						dispose();
				}else {
					
					clip2.stop();
					clip.stop();
					dispose();
				}

				referenciaBatallaNaval = new BatallaNaval();
			}if(eventAction.getSource()== mostrarYOcultar) {
				tableroPrincipal.ocultarOMostrar();
			}
		}
	}
}
