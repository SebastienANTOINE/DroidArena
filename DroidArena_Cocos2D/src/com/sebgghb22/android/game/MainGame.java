package com.sebgghb22.android.game;

import java.util.ArrayList;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import android.util.Log;

import com.sebgghb22.android.game.arena.Arena;
import com.sebgghb22.android.game.arena.MyWorld;
import com.sebgghb22.android.game.item.Enemy;
import com.sebgghb22.android.game.item.Item;
import com.sebgghb22.android.game.item.Properties;
import com.sebgghb22.android.game.item.Robot;
import com.sebgghb22.android.game.util.Option;

public final class MainGame extends CCLayer {
	private static final float TIMESTEP = 10.0f / GameActivity.TARGET_FPS;
	private static final int VELOCITY_ITERATIONS = 10;
	private static final int POSITION_ITERATIONS = 10;
	private float initX, initY;
	private boolean init=false;

	private World world;
	private Arena a;
	private Robot player;
	private Vec2 accel;
	private ArrayList<Enemy> army = new ArrayList<Enemy>();


	public MainGame(Game g) {
		setUpWorld(g);
		setUpBodies();
		setIsAccelerometerEnabled(true);
		schedule("tick");
	}

	private void setUpWorld(Game g) {

		CGSize size = CCDirector.sharedDirector().winSize();
		this.world=MyWorld.getWorld();
		Level level1 = g.getLevels().poll();
		a=new Arena(level1.getWidth(), level1.getHeight());
		a.setScreenSizeHeight((int)size.height);
		a.setScreenSizeWidth((int)size.width);
		a.addAll(level1.getBlocs());
		CGPoint startPosition = a.getStartPosition();

		player = new Robot(Properties.PLAYER1,startPosition.x, startPosition.y,a);

		for(int i = 0; i < Option.MAXBOT; i++){
			Enemy cpu = new Enemy(Properties.ENEMY,a.getFinishPosition().x,a.getFinishPosition().y,a);
			a.addBloc(cpu);
			army.add(cpu);
		}

		a.addBloc(player);
		a.setPlayer(player);

		Log.d("info","arena size: " + a.getScreenSizeHeight() +" ; " + a.getScreenSizeWidth());

	}

	private void setUpBodies() {
		for(Item i : a.getItems()){
			i.load();
			addChild(i.getSprite(),0);
		}

	}

	public void tick(float dt) {
		// Update Physics World
		synchronized (world) {
			world.step(TIMESTEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}

		// Update sprites
		for(Item i : a.getItems()){

			CCSprite s =i.getSprite();
			s.setRotation(i.getAngle());
			CGPoint newPos= new CGPoint();
			newPos.set(i.getScreenPosition().x-a.decalingX(), i.getScreenPosition().y-a.decalingY());
			s.setPosition(newPos);

		}

		player.move(accel);
		for(Enemy enemy : army){
			enemy.update();
		}
	}

	public void ccAccelerometerChanged(float x, float y, float z) {
		if(init){
			synchronized (world) {
				accel=new Vec2(y*Option.SENSIVITY-initY, -x*Option.SENSIVITY*2+initX);
			}
		}else{
			initX=x;
			initY=y;
			init=true;
		}

	}


}
