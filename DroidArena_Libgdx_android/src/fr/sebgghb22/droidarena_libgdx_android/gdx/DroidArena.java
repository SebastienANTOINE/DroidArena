package fr.sebgghb22.droidarena_libgdx_android.gdx;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Display;
import fr.sebgghb22.droidarena_libgdx_android.gdx.game.arena.Level;
import fr.sebgghb22.droidarena_libgdx_android.gdx.game.arena.ParseArena;

public class DroidArena extends AndroidApplication {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = getWindowManager().getDefaultDisplay();
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useGL20 = true;
		AssetManager assetManager = getAssets();

		try {
			InputStream ims = assetManager.open("arena.txt");

			ConcurrentLinkedQueue<Level> levels = null;
			levels = ParseArena.parse(ims);
			Game game = new Game(levels);

			initialize(new DroidArenaApplicationListener(game,display.getWidth(),display.getHeight(),assetManager), cfg);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
