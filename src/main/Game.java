package main;

import java.awt.Graphics;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;


public class Game implements Runnable {
	
	//objects created
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	/* FPS: - draws the game scene(level, player, enemies, etc.)
	 * 		- Can be changed
	 */
	private final int UPS_SET = 200;
	/* UPS: - takes care of the logic(player movements, events, etc.)
	 *  	- stays constant among all PCs
	 *  	- In case on lag, a way to "catch up"
	 */
	
	private Playing playing;
	private Menu menu;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	/* Constructor: "Head method of the class" when game object is created,
	 * can directly call any code inside
	 */
	public Game() 
	{
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel); 
		gamePanel.requestFocus(); // enables to accept inputs from the keyboard
		
		startGameLoop();
		
	}

	private void initClasses() {
	
		menu = new Menu(this);
		playing = new Playing(this);
	}

	private void startGameLoop()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
		
		switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case OPTIONS:
		case QUIT:
		default:
			System.exit(0);
			break;
		
		}
		
	}
	
	public void render(Graphics g) {
		
		switch(Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
		
		}
		
		
	}
	
	//Game Engine
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) 
		{
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if(deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
				
			}
			
	
			if (System.currentTimeMillis() - lastCheck >= 1000)
			{
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
			
		}
			
	}
	
	// resets the direction of the character if window loses focus
	public void windowFocusLost() {
		
		if(Gamestate.state == Gamestate.PLAYING) {
			playing.getPlayer().resetDBooleans();
		}
			
	}
	
	public Menu getMenu() {
		return menu;
	}
			
	public Playing getPlaying() {
		return playing;
	}
	
}
