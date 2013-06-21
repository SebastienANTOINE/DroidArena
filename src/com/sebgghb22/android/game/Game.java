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

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The Class Game.
 */
public class Game {




	
	private ConcurrentLinkedQueue<Level> levels;
	
	
	public Game(ConcurrentLinkedQueue<Level> levels2) {
		// TODO Auto-generated constructor stub
		this.levels=levels2;
	}


	public ConcurrentLinkedQueue<Level> getLevels() {
		// TODO Auto-generated method stub
		return levels;
	}

}
