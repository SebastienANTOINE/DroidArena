package com.sebgghb22.android.game;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.sebgghb22.android.game.arena.ParseArena;

public final class GameActivity extends Activity {
	public static final int TARGET_FPS = 50;

	private CCGLSurfaceView surface;
	private CCScene scene;
	private Game game;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		//
		AssetManager assetManager = getAssets();
		try {
			InputStream ims = assetManager.open("arena.txt");
			ConcurrentLinkedQueue<Level> levels = ParseArena.parse(ims);
			game=new Game(levels);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		surface = new CCGLSurfaceView(this);

		setContentView(surface);
	}

	@Override
	public void onStart() {
		super.onStart();

		// Attach the OpenGL view to a window
		CCDirector.sharedDirector().attachInView(surface);
		// Show FPS, set false to disable FPS display
		CCDirector.sharedDirector().setDisplayFPS(true);
		// Frames per second
		CCDirector.sharedDirector().setAnimationInterval(1.0f / TARGET_FPS);
		scene = MainScene.scene(game);
		CCDirector.sharedDirector().runWithScene(scene);
	}

	@Override
	public void onPause() {
		super.onPause();
		CCDirector.sharedDirector().pause();

		
	}

	@Override
	public void onResume() {
		super.onResume();
		CCDirector.sharedDirector().resume();
	}

	@Override
	public void onStop() {
		super.onStop();
		CCDirector.sharedDirector().end();
	}
	
	
}
