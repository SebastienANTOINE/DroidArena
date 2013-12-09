package fr.sebgghb22.droidarena.gdx;

import java.util.HashMap;

import org.jbox2d.dynamics.World;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.sebgghb22.droidarena.gdx.game.arena.Arena;
import fr.sebgghb22.droidarena.gdx.game.arena.Level;
import fr.sebgghb22.droidarena.gdx.game.arena.MyWorld;
import fr.sebgghb22.droidarena.gdx.game.item.Item;
import fr.sebgghb22.droidarena.gdx.game.item.Properties;
import fr.sebgghb22.droidarena.gdx.game.item.Robot;
import fr.sebgghb22.droidarena.gdx.utils.Option;

public class DroidArena implements ApplicationListener {

	private static final float TIMESTEP = 10.0f / 60;
	private static final int VELOCITY_ITERATIONS = 10;
	private static final int POSITION_ITERATIONS = 10;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Game g;
	private World world;
	private Arena arena;
	private Robot player;
	private final HashMap<String, Texture> textureManager;

	public DroidArena(Game g) {
		this.g = g;
		textureManager = new HashMap<>();
	}

	@Override
	public void create() {

		Level level1 = g.getLevels().poll();
		arena = new Arena(level1.getWidth(), level1.getHeight());
		arena.setScreenSizeHeight((int) 480);
		arena.setScreenSizeWidth((int) 800);
		arena.addAll(level1.getBlocs());

		player = new Robot(Properties.PLAYER1, arena.getStartPosition().x, arena.getStartPosition().y, arena);
		arena.addBloc(player);
		arena.setPlayer(player);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		this.world = MyWorld.getWorld();

		/**
		 * 
		 * Loading  a texture only one time using an hashmap
		 * 
		 */
		
		for (Item i : arena.getItems()) {
			System.out.println("Init bloc : "+i.getProperties().name()+" at pos : " + i.getScreenPosition().x + ";" + i.getScreenPosition().y);
			String img = i.getImg();
			if (textureManager.containsKey(img)) {
				i.setTexture(textureManager.get(img));
			} else {
				Texture texture = new Texture(Gdx.files.internal(Option.SPRITESFOLDER + img));
				textureManager.put(img, texture);
				i.setTexture(texture);
			}
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		batch.begin();

		for (Item i : arena.getItems()) {
			batch.draw(i.getTexture(), i.getScreenPosition().x, i.getScreenPosition().y);
			i.update();
		}
		batch.end();
		world.step(TIMESTEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
	}

	@Override
	public void resize(int arg0, int arg1) {

	}

	@Override
	public void resume() {

	}

}
