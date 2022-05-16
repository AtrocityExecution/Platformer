package utils;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {

	// determines if sprite can move within the bounds of the hitbox 
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData ) {
		
		if(!IsSolid(x, y, lvlData))//top-left bound/corner
			if(!IsSolid(x + width, y + height, lvlData))//bottom-right bound/corner
				if(!IsSolid(x + width, y, lvlData))//top-right bound/corner
					if(!IsSolid(x, y + height, lvlData))//bottom-left bound/corner
						return true;
		return false;
		
	}
	
	/*checks whether the sprite touching the background is a tile and checking
	 if the position is in the game window */
	private static boolean IsSolid(float x, float y, int[][] lvlData) {
		int maxW = lvlData[0].length * Game.TILES_SIZE;
		if( x < 0 || x >= maxW) {
			return true;
		}
		if( y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}
		
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		
		int value = lvlData[(int) yIndex][(int) xIndex];
		
		if(value >= 48 || value < 0 || value != 11 )//checks the outsideSprite file
			return true;
		return false;
	}
	
	// allows the player to move next to a wall with their hitbox
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		
		int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
		
		if(xSpeed > 0 ) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset - 1;
		}else {
			// Left
			return currentTile * Game.TILES_SIZE;
		}
			
	}
	
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		
		int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
		if(airSpeed > 0) {
			//Falling - touching floor
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		} else {
			//Jumping
			return currentTile * Game.TILES_SIZE;
		}
		
	}
	
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		
		//Check the pixel below bottom-left and bottom-right
		if(!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
			if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
				return false;
		return true;
	}
	
	public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
		return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
	}
}
