package ui;



import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;
import static utils.Constants.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton {
	
	private BufferedImage[] imgs;
	private BufferedImage slider;
	private int index = 0;
	private boolean mouseOver, mousePressed;
	private int bX, minX, maxX;

	public VolumeButton(int x, int y, int w, int h) {
		super(x + w/2, y, VOLUME_W, h);
		bounds.x -= VOLUME_W / 2;
		bX = x + w/2;
		this.x = x;
		this.w = w;
		minX = x + VOLUME_W / 2;
		maxX = x + w - VOLUME_W / 2;
		loadImgs();
	}

	private void loadImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
		imgs = new BufferedImage[3];
		for(int i = 0; i < imgs.length; i++)
			imgs[i] = temp.getSubimage(i * VOLUME_DEFAULT_W, 0, VOLUME_DEFAULT_W, VOLUME_DEFAULT_H);
		
		slider = temp.getSubimage(3 * VOLUME_DEFAULT_W, 0, SLIDER_DEFAULT_W, VOLUME_DEFAULT_H);
		
		
	}

	public void update() {
		index = 0;
		if(mouseOver)
			index = 1;
		if(mousePressed)
			index = 2;
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(slider, x, y, w, h, null);
		g.drawImage(imgs[index], bX - VOLUME_W/2, y, VOLUME_W, h, null);
			
	}
	
	public void changeX(int x) {
		if(x < minX)
			bX = minX;
		else if (x > maxX)
			bX = maxX;
		else
			bX = x;
		
		bounds.x = bX - VOLUME_W/2;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
}
