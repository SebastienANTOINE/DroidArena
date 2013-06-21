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
package com.sebgghb22.android.game.util;

import static java.lang.Math.sqrt;

import org.jbox2d.common.Vec2;

// TODO: Auto-generated Javadoc
/**
 * The Class Operations.
 */
public class Operations {
	
	/**
	 * Max.
	 *
	 * @param v1 the v1
	 * @param v2 the v2
	 * @return the int
	 */
	public static int max(int v1,int v2){
		if(v1>v2)return v1;
		return v2;
	}
	
	/**
	 * Distance.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the double
	 */
	public static double distance(Vec2 a, Vec2 b){
		return sqrt(((b.x - a.x)*(b.x-a.x))+((b.y - a.y)*(b.y - a.y)));
	}
	
}
