package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import utils.LoadSave;
import static utils.Constants.EnemyConstants.*;

public class EnemyManager {
	
	private Playing playing;
	private BufferedImage[][] crabbyA;
	private ArrayList<Crabby> crabbies = new ArrayList<>();
	
	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
		addEnemies();
	}
	
	private void addEnemies() {
		crabbies = LoadSave.GetCrabs();
		System.out.println("size of crabs: " + crabbies.size());
		
	}

	public void update(int[][] lvlData) {
		for(Crabby c : crabbies)
			c.update(lvlData);
	}
	
	public void draw(Graphics g, int xLvlOff) {
		drawCrabs(g,xLvlOff);
	}

	private void drawCrabs(Graphics g, int xLvlOff) {
		for(Crabby c : crabbies)
			g.drawImage(crabbyA[c.getEnemyState()][c.getAniIndex()], (int)(c.getHitbox().x - xLvlOff), (int)c.getHitbox().y, CRABBY_WIDTH, CRABBY_HEIGHT, null);
	}

	private void loadEnemyImgs() {
		crabbyA = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for(int j = 0; j < crabbyA.length; j++)
			for(int i = 0; i < crabbyA[j].length; i++)
				crabbyA[j][i] = temp.getSubimage(i * CRABBY_WIDTH_D, j * CRABBY_HEIGHT_D, CRABBY_WIDTH_D , CRABBY_HEIGHT_D);
				
		
	}

}
