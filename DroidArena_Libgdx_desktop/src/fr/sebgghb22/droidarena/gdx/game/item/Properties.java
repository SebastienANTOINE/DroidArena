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

import org.jbox2d.dynamics.BodyType;


public enum Properties {


	CONCRETE(Sprite.CONCRETE,BodyType.STATIC,10,false,false),SCONCRETE(Sprite.SCONCRETE,BodyType.STATIC,10,false,false),PLAYER1(Sprite.PLAYER1,BodyType.DYNAMIC,0,true,true),PLAYER2(Sprite.PLAYER2,BodyType.DYNAMIC,0,true,false),
	ENEMY(Sprite.ENEMY,BodyType.DYNAMIC,0,true,false),ICE(Sprite.ICE,BodyType.STATIC,10,false,false),WOOD(Sprite.WOOD,BodyType.STATIC,10,true,false),START(Sprite.START,BodyType.STATIC,0,true,true),FINISH(Sprite.FINISH,BodyType.STATIC,0,true,true),
	BONUS(Sprite.UNKNOWN,BodyType.DYNAMIC,80,false,true),TRACE(Sprite.CLOUD,BodyType.STATIC,10,false,true);


	private String img;
	private int resistance;
	private BodyType b;
	private boolean isFixedRotation;
	private boolean sensor;
	
	Properties(Sprite s, BodyType b, int resistance, boolean isFixedRotation, boolean sensor){
		this.img=s.getImg();
		this.b=b;
		this.resistance=resistance;
		this.isFixedRotation=isFixedRotation;
		this.sensor = sensor;
	}

	public String getImg() {
		return img;
	}

	public int getResistance() {
		return resistance;
	}

	public BodyType getBodyType() {
		return b;
	}

	public boolean isFixedRotation() {
		return isFixedRotation;
	} 
	public boolean isSensor() {
		return sensor;
	}
}
