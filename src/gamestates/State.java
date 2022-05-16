package gamestates;

import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class State {

	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	
	//indicates if the mouse in inside the bounds of the menu button
	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}
	
	
	public Game getGame() {
		return game;
	}
}
