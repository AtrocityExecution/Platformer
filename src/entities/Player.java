package entities;

import static utils.Constants.playerConstants.*;
import static utils.HelpMethods.*;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import utils.LoadSave;

public class Player extends Entity {

	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 15;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 1.0f * Game.SCALE;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	
	// Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	//player sprite
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y,(int) (20*Game.SCALE),(int) (27*Game.SCALE));
		
	}
	
	public void update() {
		
		updatePos();
		updateAnimationTick();
		setAnimation();	
	}
	
	/* paintComponent(Graphics g) method:
	 * Allows to "draw" within the panel of the object with the graphics 
	 * being able to draw
	 * - need both to draw!!
	 */
	public void render(Graphics g, int lvlOff) {
		g.drawImage(animations[playerAction][aniIndex], (int)(hitbox.x - xDrawOffset) - lvlOff, (int)(hitbox.y - yDrawOffset), width, height, null);// draws the sprite
		//drawHitbox(g, lvlOff); // draws hitbox for the sprite
	}
	


	//enables animation in the sprite
	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}
				
		}
		
	}
	
	private void setAnimation() {
			
		int startAni = playerAction;
		
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
		
		if(inAir)
			if(airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
			
		if (attacking) 
			playerAction = ATTACK_1;
		
		if(startAni != playerAction)
			resetAniTick();
	}
		
	//resets animation properties to get the full animation in game
	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
		
	}

	private void updatePos() {
		
		moving = false;
		
		if(jump)
			jump();
		
//		if(!left && !right && !inAir)
//			return;
		if(!inAir)
			if((!left && !right) || (right && left))
				return;
		
		float xSpeed = 0, ySpeed = 0;
		
		
		if (left) 
			xSpeed -= playerSpeed;
		
		if (right) 
			xSpeed += playerSpeed;
			
		if (!inAir) 
			if(!IsEntityOnFloor(hitbox, lvlData)) 
				inAir = true;
			
		
		if(inAir) {
			
			if(CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData))
			{
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox,airSpeed);
				if(airSpeed > 0)
					resetInAir();
				else 
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}
			
		} else 
			updateXPos(xSpeed);
		
		moving = true;
		
		
	}
	
	//checks if player is not in the air and applies speed to the jump
	private void jump() {
		if(inAir)
			return;
		
		inAir = true;
		airSpeed = jumpSpeed;
		
	}
	
	//resets the air speed
	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
		
	}

	private void updateXPos(float xSpeed) {
		
		  if(CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width,hitbox.height, lvlData)) 
		  { 
			 hitbox.x += xSpeed;
			 
		  } else {
			 hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
		  }
		 
		
	}

	private void loadAnimations() {
		
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
			
			animations = new BufferedImage[9][6];
			for(int j = 0; j < animations.length; j++)	
				for( int i = 0; i < animations[j].length; i++)
					animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
			
		
		}
		
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!IsEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	//stops moving the character if the window loses focus
	public void resetDBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	
	//Getters for the input control for easier code management
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
		
	}
	
	
	
	
}
