package fr.sebgghb22.droidarena.gdx;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.sebgghb22.droidarena.gdx.game.arena.Level;
import fr.sebgghb22.droidarena.gdx.game.arena.ParseArena;

public class Launch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "DroidArena";
//		cfg.width = Option.SCREENWIDTH;
//		cfg.height = Option.SCREENHEIGHT;
		cfg.width = 400;
		cfg.height = 300;
		InputStream ims = Launch.class.getClass().getResourceAsStream(
				"/fr/sebgghb22/droidarena/gdx/arena.txt");
		ConcurrentLinkedQueue<Level> levels = null;
		try {
			levels = ParseArena.parse(ims);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Game game = new Game(levels);

		new LwjglApplication(new DroidArena(game), cfg);
	}

}
