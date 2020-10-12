package batallaNaval;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Titulos extends JLabel {
	//atributos
		
	
	//métodos
	public Titulos(String texto, int tamano,Color colorFont)
	{
		this.setText(texto); //Recibe la variable texto
		Font font = new Font(Font.SERIF,Font.BOLD+Font.ITALIC,tamano);
		this.setFont(font); 
		this.setBackground(colorFont); //Color Fondo
		this.setForeground(Color.WHITE); //Color Texto
		this.setHorizontalAlignment(JLabel.CENTER); //Permite alinear el texto
		this.setOpaque(true); //permite ver el background por encima
		
	}
}
