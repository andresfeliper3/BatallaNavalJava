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
