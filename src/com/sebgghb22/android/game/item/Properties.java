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
package com.sebgghb22.android.game.item;

import org.jbox2d.dynamics.BodyType;

import com.sebgghb22.android.game.skin.Skin;


public enum Properties {


	CONCRETE(Skin.CONCRETE,BodyType.STATIC,10,false,false),SCONCRETE(Skin.SCONCRETE,BodyType.STATIC,10,false,false),PLAYER1(Skin.PLAYER1,BodyType.DYNAMIC,0,true,false),PLAYER2(Skin.PLAYER2,BodyType.DYNAMIC,0,true,false),
	ENEMY(Skin.ENEMY,BodyType.DYNAMIC,0,true,false),ICE(Skin.ICE,BodyType.STATIC,10,false,false),WOOD(Skin.WOOD,BodyType.STATIC,10,true,false),START(Skin.START,BodyType.STATIC,0,true,true),FINISH(Skin.FINISH,BodyType.STATIC,0,true,true),
	BONUS(Skin.UNKNOWN,BodyType.DYNAMIC,80,false,true),TRACE(Skin.CLOUD,BodyType.STATIC,10,false,true);


	private String img;
	private int resistance;
	private BodyType b;
	private boolean isFixedRotation;
	private boolean sensor;
	
	Properties(Skin s, BodyType b, int resistance, boolean isFixedRotation, boolean sensor){
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
