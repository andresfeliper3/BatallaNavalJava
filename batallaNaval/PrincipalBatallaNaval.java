/* Autores: Jose David Barona Hernández - 1727590
 *                  Andrés Felipe Rincón    - 1922840
 * Correos: jose.david.barona@correounivalle.edu.co 
 *             andres.rincon.lopez@correounivalle.edu.co
 * Mini proyecto 2: Batalla Naval
 * Fecha: 13/10/2020
 * 
 * */
package batallaNaval;

import java.awt.EventQueue;

import javax.swing.UIManager;

// TODO: Auto-generated Javadoc
/**
 * The Class PrincipalBatallaNaval.
 */
public class PrincipalBatallaNaval {

		/**
		 * The main method.
		 *
		 * @param args the arguments
		 */
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			try {
				String className = UIManager.getCrossPlatformLookAndFeelClassName();
				UIManager.setLookAndFeel(className);
			}
			catch(Exception e) {}
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					BatallaNaval myWindow = new BatallaNaval();
				}
			});
	}
}
