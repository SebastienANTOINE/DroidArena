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
package com.sebgghb22.android.game;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;

import com.sebgghb22.android.game.item.Bloc;

// TODO: Auto-generated Javadoc
/**
 * The Class Level.
 */
public class Level {

	/** The free space. */
	private final ArrayList<Vec2> freeSpace;
	
	/** The blocs. */
	private final ArrayList<Bloc> blocs;
	
	/** The width. */
	private int height,width;
	
	/** The firstbloc. */
	private final Bloc firstbloc;

	/**
	 * Gets the free space.
	 *
	 * @return the free space
	 */
	public ArrayList<Vec2> getFreeSpace() {
		return freeSpace;
	}

	/**
	 * Gets the blocs.
	 *
	 * @return the blocs
	 */
	public ArrayList<Bloc> getBlocs() {
		return blocs;
	}

	/**
	 * Instantiates a new level.
	 *
	 * @param freeSpace the free space
	 * @param blocs the blocs
	 * @param width the width
	 * @param height the height
	 * @param firstBloc the first bloc
	 */
	public Level(ArrayList<Vec2> freeSpace, ArrayList<Bloc> blocs, int width, int height,Bloc firstBloc) {
		super();
		this.freeSpace = freeSpace;
		this.blocs = blocs;
		this.height=height;
		this.width=width;
		this.firstbloc=firstBloc;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the firstbloc.
	 *
	 * @return the firstbloc
	 */
	public Bloc getFirstbloc() {
		return firstbloc;
	}
}
