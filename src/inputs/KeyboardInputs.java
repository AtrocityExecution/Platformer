package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.Game;
import main.GamePanel;
import static utils.Constants.Directions.*;

/* extends: extends a class (can only extend one class)
 * implements: implements a interface (can use in more than one interface)
 */
	
public class KeyboardInputs implements KeyListener{

	private GamePanel gamePanel; //gamePanel access inside the Keyboard Inputs
	
	public KeyboardInputs(GamePanel gamePanel) //allows to change a value in the GamePanel class
	{
		this.gamePanel = gamePanel;
	}
	
	
	
	public void keyTyped(KeyEvent e) {
		
		
	}

	public void keyReleased(KeyEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyReleased(e);
			break;
		default:
			break;
		
		}
	}

	public void keyPressed(KeyEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().keyPressed(e);
			break;
		case PLAYING:
			gamePanel.getGame().getPlaying().keyPressed(e);
			break;
		default:
			break;
		
		}
		
		
		
	}

	
	

}
