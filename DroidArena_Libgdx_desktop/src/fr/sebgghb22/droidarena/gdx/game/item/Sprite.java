/*
 * Project : JAVA: YouRobot
 * ESIPE-MLV
 * 
 * VERNEAU Julien
 * ANTOINE Sébastien
 * 
 * 
 * IR2 - DECEMBER 2011
 * 
 */
package fr.sebgghb22.droidarena.gdx.game.item;


// TODO: Auto-generated Javadoc
/**
 * The Enum Skin.
 */
public enum Sprite {

	/** The MULTI. */
	MULTI("MULTI.png"),/** The GHOST. */
GHOST("GHOST.png"),/** The SOLO. */
SOLO("SOLO.png"),/** The CONCRETE. */
CONCRETE("concrete.png"),/** The GAMEOVER. */
GAMEOVER("gameover.png"),/** The SCONCRETE. */
SCONCRETE("limit.png"),/** The ICE. */
ICE("ice.png"),/** The UNKNOWN. */
UNKNOWN("UNKNOWN.png"),/** The ICEBOMB. */
ICEBOMB("ICEBOMB.png"),/** The WOOD. */
WOOD("wood.png"),/** The ENEMY. */
ENEMY("enemy.png"),/** The PLAYE r1. */
PLAYER1("player1.png"),/** The PLAYE r2. */
PLAYER2("player2.png"),/** The START. */
START("start.png"),/** The FINISH. */
FINISH("finish.png"),/** The RIP. */
RIP("rip.png"), /** The WOODBOMB. */
 WOODBOMB("WOODBOMB.png"), /** The CONCRETEBOMB. */
 CONCRETEBOMB("CONCRETEBOMB.png"),/** The SNAP. */
SNAP("magnet.png"), /** The CLOUD. */
 CLOUD("cloud.png"), /** The LEVELUP. */
 LEVELUP("levelup.png"), /** The ENEMYOFF. */
 ENEMYOFF("enemyOff.png");

/** The img. */
String img;
String assetFolder="assets/";

	/**
	 * Instantiates a new skin.
	 *
	 * @param img the img
	 */
	Sprite(String img){
			this.img=img;   
	}

	/**
	 * Gets the img.
	 *
	 * @return the img
	 */
	public String getImg(){
		  
		return img;
		
	}
}
