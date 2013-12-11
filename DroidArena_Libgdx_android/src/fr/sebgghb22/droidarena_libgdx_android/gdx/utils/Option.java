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
package fr.sebgghb22.droidarena_libgdx_android.gdx.utils;

import org.jbox2d.common.Vec2;

import com.esotericsoftware.tablelayout.Toolkit;

// TODO: Auto-generated Javadoc
/**
 * The Class Option.
 */
public class Option {
	
	/** The MAXVELOCITY. */
	public static int MAXVELOCITY = 20;
	
	/** The BOMBRADIUS. */
	public static int BOMBRADIUS = 8;
	
	/** The SNAPRADIUS. */
	public static int SNAPRADIUS = 16;
	
	/** The VIEWRADIUS. */
	public static int VIEWRADIUS = 8;
	
	/** The MAXSPEED. */
	public static int MAXSPEED = 30;
	
	/** The MAXBONUS. */
	public static int MAXBONUS = 15;
	
	/** The DAMAGERATIO. */
	public static float DAMAGERATIO = 0.001f;
	
	/** The MAXBOT. */
	public static int MAXBOT = 10;
	
	/** The UNIT. */
	public static int UNIT=64;
	
	/** The GRAVITY. */
	public static Vec2 GRAVITY = new Vec2(0,0);
	
	/** The RATE. */
	public static int RATE=17;
	
	public static float SENSIVITY=0.7f;
	
	/** The IASPEED. */
	public static int IASPEED=30;
	
	/** The SCALING. */
	public static float SCALING=0.1f;
	
	/** The TIMESEC. */
	public static int TIMESEC = 2000;
	
	public final static String SPRITESFOLDER="sprites/";
	
	/*Get the dimension of you screen computer*/
	/** The SCREENWIDTH. */
	public static int SCREENWIDTH;
	
	/** The SCREENHEIGHT. */
	public static int SCREENHEIGHT;
	
	private static final float MAX_HORIZONTAL_SPEED = 250 ;
	private static final float MAX_VERTICAL_SPEED = 250;
}
