package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
	
	/* JFrame
	 * - backbone of the window
	 * - the "frame" of a painting
	 * - part of larger collections of GUI components
	 * - helps create windows
	 */
	
	private JFrame jframe;
	
	public GameWindow(GamePanel gamePanel)
	{
		jframe = new JFrame();
		
		
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //E_ON_C: integer that closes the program when the compiler closes (3)
		jframe.add(gamePanel); //adds panel into the window
		jframe.setResizable(false);
		jframe.pack();//fits the size of the window to the preferred size of the components
		jframe.setLocationRelativeTo(null); //sets window to the middle
		jframe.setVisible(true);
		jframe.addWindowFocusListener(new WindowFocusListener() { //adds an indicator to see if the window loses focus(clicked out)

			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
				
			}
			
		});
		
	}
	
}
