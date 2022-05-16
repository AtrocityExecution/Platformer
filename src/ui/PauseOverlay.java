package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;
import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.UrmButtons.*;
import static utils.Constants.UI.VolumeButtons.*;

public class PauseOverlay {
	
	private Playing playing;
	private BufferedImage background;
	private int bgX,bgY,bgW,bgH;
	private SoundButton musicB, sfxB;
	private UrmButton menuB,replayB,resumeB;
	private VolumeButton volumeButton;

	public PauseOverlay(Playing playing) {
		this.playing = playing;
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeButton();
	}
	
	private void createVolumeButton() {
		int vX = (int)(309 * Game.SCALE);
		int vY = (int)(278 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_W, VOLUME_H);
		
	}

	private void createUrmButtons() {
		int menuX = (int) (313 * Game.SCALE);
		int replayX = (int) (387 * Game.SCALE);
		int resumeX = (int) (462 * Game.SCALE);
		int bY = (int) (325 * Game.SCALE);
		
		menuB = new UrmButton (menuX, bY, URM_SIZE, URM_SIZE, 2);
		replayB = new UrmButton (replayX, bY, URM_SIZE, URM_SIZE, 1);
		resumeB = new UrmButton (resumeX, bY, URM_SIZE, URM_SIZE, 0);
		
	}

	private void createSoundButtons() {
		int soundX = (int)(450 * Game.SCALE);
		int musicY = (int)(140 * Game.SCALE);
		int sfxY = (int)(186 * Game.SCALE);
		musicB = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxB = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
		
	}

	private void loadBackground() {
		background = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int)(background.getWidth() * Game.SCALE);
		bgH = (int)(background.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int)(25 * Game.SCALE);
		
	}

	public void update() {
		 musicB.update();
		 sfxB.update();
		 
		 menuB.update();
		 replayB.update();
		 resumeB.update();
		 
		 volumeButton.update();
		 
	}
	
	public void draw(Graphics g) {
		//Background
		g.drawImage(background, bgX, bgY, bgW, bgH, null);
		
		//Sound Buttons
		musicB.draw(g);
		sfxB.draw(g);
		
		//Urm Buttons
		menuB.draw(g);
		replayB.draw(g);
		resumeB.draw(g);
		
		//VolumeButton/Slider
		volumeButton.draw(g);
	}
	

	
	public void mouseDragged(MouseEvent e) {
		if(volumeButton.isMousePressed()) {
			volumeButton.changeX(e.getX());
		}
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		if (isIn(e, musicB))
			musicB.setMousePressed(true);
		else if (isIn(e, sfxB))
			sfxB.setMousePressed(true);
		else if(isIn(e,menuB))
			menuB.setMousePressed(true);
		else if(isIn(e,replayB))
			replayB.setMousePressed(true);
		else if(isIn(e,resumeB))
			resumeB.setMousePressed(true);
		else if(isIn(e,volumeButton))
			volumeButton.setMousePressed(true);
	}


	
	public void mouseReleased(MouseEvent e) {
		
		if (isIn(e, musicB)) {
			if(musicB.isMousePressed()) 
				musicB.setMuted(!musicB.isMuted());
			
		} else if (isIn(e, sfxB)) {
			if(sfxB.isMousePressed())
				sfxB.setMuted(!sfxB.isMuted());
		} else if (isIn(e, menuB)) {
			if(menuB.isMousePressed()) {
				Gamestate.state = Gamestate.MENU;
				playing.resumeGame();
			}
		} else if (isIn(e, replayB)) {
				if(replayB.isMousePressed())
					System.out.println("Replay lvl!");	
		} else if (isIn(e, resumeB)) {
			if(resumeB.isMousePressed())
				playing.resumeGame();
		}

			musicB.resetBools();
			sfxB.resetBools();
			menuB.resetBools();
			replayB.resetBools();
			resumeB.resetBools();	
			volumeButton.resetBools();
			
	}
		
		
		
	


	
	public void mouseMoved(MouseEvent e) {
		
		musicB.setMouseOver(false);
		sfxB.setMouseOver(false);
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		resumeB.setMouseOver(false);
		volumeButton.setMouseOver(false);
		
		
		if (isIn(e, musicB)) {
			musicB.setMouseOver(true);
		} else if (isIn(e, sfxB))
			sfxB.setMouseOver(true);
		  else if (isIn(e, menuB))
			menuB.setMouseOver(true);
		  else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		  else if (isIn(e, resumeB))
			resumeB.setMouseOver(true);
		  else if (isIn(e, volumeButton))
			  volumeButton.setMouseOver(true);
		
		
	}
	
	private boolean isIn(MouseEvent e, PauseButton b) {
		return (b.getBounds().contains(e.getX(), e.getY()));
	}
	
	
}
