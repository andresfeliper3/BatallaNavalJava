package batallaNaval;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class BatallaNaval extends JFrame {
	/*	Estado del juego
	 * 0: organizar
	 * 1: jugar
	 * 2: ganar
	 * 3: perder
	 * */
	private int estado;	
	/* Turno en juego
	 * true: jugador
	 * false: computador
	 * */
	private int cantidadBarcos = 10;
	private boolean turno;
	private TableroPosicion tableroPosicion;
	private TableroPrincipal tableroPrincipal;
	private BatallaNaval referencia = this;
	private Muelle muelle;
	private JPanel zonaBotones;
	private JButton botonReset, botonMostrarOcultar;
	private Escucha escucha;
	//Barcos
	private Portaaviones miPortaaviones, pcPortaaviones;
	private Submarino miSubmarino1, miSubmarino2, pcSubmarino1, pcSubmarino2;
	private Destructor miDestructor1, miDestructor2, miDestructor3, pcDestructor1, pcDestructor2, pcDestructor3;
	private Fragata miFragata1, miFragata2, miFragata3, miFragata4, pcFragata1, pcFragata2, pcFragata3, pcFragata4;
	private JLabel titulo;
	private Barco[] misBarcos, pcBarcos;
	private Barco barcoSeleccionado;
	//Sonido
	private Clip clip;
	//Constructor
	public BatallaNaval() {
		this.estado = 0;
		initGUI();
		
		//Configuraci�n de ventana
		
		this.setTitle("Batalla Naval");
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void initGUI() {
		//Container & layout
		Container container = getContentPane();
		container.setLayout(new GridBagLayout());
		container.setBackground(Color.WHITE);
		GridBagConstraints constraints = new GridBagConstraints();
		//Escucha
		escucha = new Escucha();
		
		//Objetos 
		
		//Mis barcos
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
		
		//Barcos del pc
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
		//Array de barcos del usuario
		misBarcos = new Barco[cantidadBarcos];
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
		
		//Array de barcos del computador
		
		pcBarcos = new Barco[cantidadBarcos];
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
		
		//Componentes gr�ficos
		
		//T�tulo
		titulo = new JLabel("Batalla Naval");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		add(titulo, constraints);
		//Zona botones
		zonaBotones = new JPanel();
		zonaBotones.setBackground(Color.WHITE);
		botonMostrarOcultar = new JButton("Ver barcos");
		botonMostrarOcultar.addActionListener(escucha);
		zonaBotones.add(botonMostrarOcultar);
		botonReset = new JButton("Reset");
		botonReset.addActionListener(escucha);
		zonaBotones.add(botonReset);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.EAST;
		add(zonaBotones, constraints);
		
		//Muelle
		muelle = new Muelle(referencia);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		add(muelle, constraints);
		//Tablero posici�n
		tableroPosicion = new TableroPosicion(misBarcos, referencia);
		constraints.gridx = 1;
		constraints.gridy = 2;
		add(tableroPosicion, constraints);
		//Tablero principal
		tableroPrincipal = new TableroPrincipal(pcBarcos, referencia);
		constraints.gridx = 2;
		constraints.gridy = 2;
		add(tableroPrincipal, constraints);

		
	}

	public void pasarBarcoSeleccionado(int index) {
		barcoSeleccionado = misBarcos[index];
		tableroPosicion.setBarcoSeleccionado(barcoSeleccionado);
	}
	
	//Retorna true si en el muelle se ha seleccionado un barco
	public boolean hayBarcoSeleccionado() {
		return tableroPosicion.getBarcoSeleccionado() == null ? false : true;	
	}
	public void setMensajeMuelle(String mensaje) {
		muelle.setMensaje(mensaje);	
	}
	
	//Recibe el estado
	/*	Estado del juego
	 * 0: organizar
	 * 1: jugar
	 * 2: ganar
	 * 3: perder
	 * */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
		partidaEnCurso();
	}
	
	private void partidaEnCurso() {
		switch(estado) {
		case 1:
			//Turno del jugador
			if(turno) {
				//Habilitar los disparos del usuario
				tableroPrincipal.setDisparoHabilitado(true);
					
			}	
			//Turno del computador
			else {
				//Deshabilitar los disparos del usuario
				tableroPrincipal.setDisparoHabilitado(false);
				//Disparo del computador	
				while(!tableroPosicion.generarDisparo(randomPosition(), randomPosition())) {
					//Si el disparo no es exitoso, el ciclo se sigue repitiendo hasta que dispara exitosamente en una casilla.
				}
			}
			break;
		//Usuario gana
		case 2:
			//USUARIO GANA
			JOptionPane.showMessageDialog(null, "Ganaste");
			break;
		//Usuario pierde
		case 3:
			//USUARIO PIERDE
			JOptionPane.showMessageDialog(null, "Perdiste");
			break;
			
		}
	}
	
	private int randomPosition() {
		Random rand = new Random();
		return rand.nextInt(10) + 1;
	}
	
	//Retorna true si todos los barcos del array est�n naufragados, y false en caso contrario.
	public boolean revisarDerrota(Barco[] barcos) {
		System.out.println("entr� a revisar derrota");
		//Derrota
		for(int barco = 0; barco < misBarcos.length; barco++) {
			if(!barcos[barco].isNaufragado()) {
				return false;
			}
		}
		return true;
	}
	
	//Editar texto del botonMostrarOcultar
	public void setTextMostrarOcultar(String text) {
		botonMostrarOcultar.setText(text);
	}
	//Sonidos del juego
    public void playSound(String sonido) { 

        File soundFile = new File("");
        float volumen = 0;

        try {
            if(sonido=="musicaFondo") {

                soundFile = new File("src/sonidos/musicaBatallaNaval.wav");
                volumen = -15.0f;
            }else if(sonido == "disparo") {
                soundFile = new File("src/sonidos/disparo.wav");
                volumen = -5.0f;
            }else if(sonido == "disparoAcertado") {
                soundFile = new File("src/sonidos/disparoAcertado.wav");
                volumen = -5.0f;
            }else if(sonido == "disparoAlAgua") {
                soundFile = new File("src/sonidos/disparoAlAgua.wav");
                volumen = -5.0f;
            }else if(sonido == "naufragado") {
                soundFile = new File("src/sonidos/naufragado.wav");
                volumen = -0.0f;
            }else if(sonido == "pop") {
                soundFile = new File("src/sonidos/pop.wav");
                volumen = -10.0f;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = 
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volumen); // Reduce volume by 10 decibels.
            clip.start();

        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }

    }
    
    private class Escucha implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent eventAction) {
			// TODO Auto-generated method stub
			if(eventAction.getSource() == botonReset) {
				
			} else if(eventAction.getSource() == botonMostrarOcultar) {
				tableroPrincipal.mostrarOcultarBarcos();
			}
		}
    	
    }
}
