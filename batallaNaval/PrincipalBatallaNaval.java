package batallaNaval;

import java.awt.EventQueue;

import javax.swing.UIManager;

public class PrincipalBatallaNaval {

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
