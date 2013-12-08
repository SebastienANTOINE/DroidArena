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
package fr.sebgghb22.droidarena.gdx;

import java.util.concurrent.ConcurrentLinkedQueue;

import fr.sebgghb22.droidarena.gdx.game.arena.Level;

/**
 * The Class Game.
 */
public class Game {

	private ConcurrentLinkedQueue<Level> levels;
	
	
	public Game(ConcurrentLinkedQueue<Level> levels2) {
		this.levels=levels2;
	}


	public ConcurrentLinkedQueue<Level> getLevels() {
		return levels;
	}

}
