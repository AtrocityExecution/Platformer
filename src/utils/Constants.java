package utils;

import main.Game;

public class Constants {
	
	public static class EnemyConstants {
		public static final int CRABBY = 0;
		
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int ATTACK = 2;
		public static final int HIT = 3;
		public static final int DEAD = 4;

		public static final int CRABBY_WIDTH_D = 72;
		public static final int CRABBY_HEIGHT_D = 32;
		
		public static final int CRABBY_WIDTH = (int)(CRABBY_WIDTH_D *Game.SCALE);
		public static final int CRABBY_HEIGHT = (int)(CRABBY_HEIGHT_D *Game.SCALE);
		
		public static final int CRABBY_DRAWOFFSET_X = (int)(26 * Game.SCALE);
		public static final int CRABBY_DRAWOFFSET_Y = (int)(9 * Game.SCALE);
		
		public static int GetSpriteAmount(int enemy_t, int enemy_s) {
			
			switch(enemy_t) {
			case CRABBY:
				switch(enemy_s) {
				case IDLE:
					return 9;
				case RUNNING:
					return 6;
				case ATTACK:
					return 7;
				case HIT:
					return 4;
				case DEAD:
					return 5;	
				}
			}
		
			return 0;
		
		}
	}		
	
	public static class Environment{
		public static final int BIG_CLOUDS_WIDTH_D = 448;
		public static final int BIG_CLOUDS_HEIGHT_D = 101;
		public static final int SMALL_CLOUDS_WIDTH_D = 74;
		public static final int SMALL_CLOUDS_HEIGHT_D = 24;
		
		public static final int BIG_CLOUDS_WIDTH = (int)(BIG_CLOUDS_WIDTH_D * Game.SCALE);
		public static final int BIG_CLOUDS_HEIGHT = (int)(BIG_CLOUDS_HEIGHT_D * Game.SCALE);
		public static final int SMALL_CLOUDS_WIDTH = (int)(SMALL_CLOUDS_WIDTH_D * Game.SCALE);
		public static final int SMALL_CLOUDS_HEIGHT = (int)(SMALL_CLOUDS_HEIGHT_D * Game.SCALE);
	}
	
	public static class UI{
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
		}
		
		public static class PauseButtons{
			public static final int SOUND_SIZE_D = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_D * Game.SCALE);
		}
		
		public static class UrmButtons{
			public static final int URM_SIZE_D = 56;
			public static final int URM_SIZE = (int) (URM_SIZE_D * Game.SCALE);
		}
		
		public static class VolumeButtons{
			public static final int VOLUME_DEFAULT_W = 28;
			public static final int VOLUME_DEFAULT_H = 44;
			public static final int SLIDER_DEFAULT_W = 215;
			
			public static final int VOLUME_W = (int) (VOLUME_DEFAULT_W * Game.SCALE);
			public static final int VOLUME_H = (int) (VOLUME_DEFAULT_H * Game.SCALE);
			public static final int SLIDER_W = (int) (SLIDER_DEFAULT_W * Game.SCALE);
		}
	}
	
	//initializing directions for sprite
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
		
	}
	
	//initializing animations for each action
	public static class playerConstants{
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int GROUND = 4;
		public static final int HIT = 5;
		public static final int ATTACK_1 = 6;
		public static final int ATTACK_JUMP_1 = 7;
		public static final int ATTACK_JUMP_2 = 8;
		
		public static int GetSpriteAmount(int player_action) {
			switch(player_action) {
			//allows animations for each action	
			case RUNNING:
				return 6;
			case IDLE:
				return 5;
			case HIT:
				return 4;
			case JUMP:
			case ATTACK_1:
			case ATTACK_JUMP_1:
			case ATTACK_JUMP_2:
				return 3;
			case GROUND:
				return 2;
			case FALLING:
			default:
				return 6;
			}
		}
	}
}
