/*
s * Project : JAVA: YouRobot
 * ESIPE-MLV
 * 
 * VERNEAU Julien
 * ANTOINE Sébastien
 * 
 * 
 * IR2 - DECEMBER 2011
 * 
 */
package com.sebgghb22.android.game.arena;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.cocos2d.types.CGPoint;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import com.sebgghb22.android.game.item.Bloc;
import com.sebgghb22.android.game.item.Item;
import com.sebgghb22.android.game.item.Properties;
import com.sebgghb22.android.game.item.Robot;
import com.sebgghb22.android.game.util.Operations;
import com.sebgghb22.android.game.util.Option;



// TODO: Auto-generated Javadoc
/**
 * The Class Arena.
 */
public class Arena{

	/**
	 * Gets the start position p1.
	 *
	 * @return the start position p1
	 */
	public CGPoint getStartPosition() {
		return startPosition;
	}

	/** The width. */
	private final int width;
	
	/** The height. */
	private final int height;
	
	/** The world. */
	private World world;
	
	/** The items on screen. */
	private ArrayList<Item> items,itemsOnScreen;
	
	/** The free space. */
	private ArrayList<Vec2> freeSpace;	
	
	/** The start position p1. */
	private CGPoint startPosition;
	
	/** The finish position. */
	private CGPoint finishPosition;	
	
	/** The r. */
	private Random r;
	
	/** The screen size width. */
	private  int screenSizeWidth=0;
	
	/** The screen size height. */
	private  int screenSizeHeight=0;
	
	/** The element pos xmax. */
	private int elementPosXmax=0;
	
	/** The decaling x. */
	private int decalingX=0;
	
	/** The decaling y. */
	private int decalingY=0;
	
	/** The element pos ymax. */
	private int elementPosYmax=0;
	
	/** The resize screen. */
	private boolean resizeScreen=true;
	
	/** The first. */
	private Bloc first;
	
	private Robot player;
	

	/**
	 * Instantiates a new arena.
	 *
	 * @param width the width
	 * @param height the height
	 * @param game the game
	 */
	public Arena(int width, int height){
		this.width=width;
		this.height=height;
		this.items= new ArrayList<Item>();
		this.itemsOnScreen= new ArrayList<Item>();
		
		this.world=MyWorld.getWorld();
		this.r=new Random();
		freeSpace=new ArrayList<Vec2>();
		
	}


	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width*Option.UNIT;
	}

	
	public void setPlayer(Robot r){
	  player=r;
	}
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height*Option.UNIT;
	}

	/**
	 * Gets the world.
	 *
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public ArrayList<Item> getItems(){
		
		if(elementPosXmax>screenSizeWidth||elementPosYmax>screenSizeHeight){
			resizeScreen=true;		
		}
		else{
			if(decalingX>-1&&(first.getScreenPosition().x-decalingX>0)){
				decalingX--;
			}
			if(decalingY>-1&&first.getScreenPosition().y-decalingY>0){
				decalingY--;
				decalingY--;
			}
			resizeScreen=false;
		}
		return itemsOnScreen();
	}

	/**
	 * Items on screen.
	 *
	 * @return the concurrent linked deque
	 */
	private ArrayList<Item> itemsOnScreen() {
		
		if(player!=null){
			if(resizeScreen){
				decalingX=(int)(player.getScreenPosition().x-first.getScreenPosition().x-screenSizeWidth/2);
				decalingY=(int)(player.getScreenPosition().y-first.getScreenPosition().y-screenSizeHeight/2);
			}
			itemsOnScreen.removeAll(itemsOnScreen);
			for(Item b : items){
				if(b.getScreenPosition().x-decalingX>0||b.getScreenPosition().y-decalingY>0){
					itemsOnScreen.add(b);
				}
			}
			return itemsOnScreen;
		}
		return itemsOnScreen;
		
	}

	/**
	 * Gets the finish position.
	 *
	 * @return the finish position
	 */
	public CGPoint getFinishPosition() {
		return finishPosition;
	}

	/**
	 * Adds the bloc.
	 *
	 * @param b the b
	 */
	public void addBloc(Bloc b){
		if(first==null)first=b;
		b.setBody(world.createBody(b.getBodyDef()));

		if(b.getProperties()==Properties.START){
			if(startPosition==null){
				startPosition=b.getScreenPosition();
				items.add(b);
			}
		}else{
			items.add(b);
		}
		if(b.getProperties()==Properties.FINISH)finishPosition=b.getScreenPosition();
		elementPosXmax=Operations.max(elementPosXmax,(int)b.getScreenPosition().x);
	}

	/**
	 * Adds the all.
	 *
	 * @param elements the elements
	 */
	public void addAll(List<Bloc> elements){
		for(Bloc b : elements){
			this.addBloc(b);
		}
	}

	/**
	 * Removes the bloc.
	 *
	 * @param b the b
	 */
	public void removeBloc(Bloc b){
		items.remove(b);
		world.destroyBody(b.getBody());
	}
	
	
	/**
	 * Removes the item.
	 *
	 * @param i the i
	 */
	public void removeItem(Item i){
		items.remove(i);
	}
	
	/**
	 * Adds the item.
	 *
	 * @param i the i
	 */
	public void addItem(Item i){
		items.add(i);
	}


	/**
	 * Gets the free space.
	 *
	 * @return the free space
	 */
	public Vec2 getFreeSpace() {
		int size=freeSpace.size();
		if(size<1)return null;
		int randomValue = r.nextInt(size);
		Vec2 vec = freeSpace.get(randomValue);
		try{
			return vec;
		}finally{
			freeSpace.remove(randomValue);
		}
	}

	/**
	 * Gets the screen size width.
	 *
	 * @return the screen size width
	 */
	public int getScreenSizeWidth() {
		return screenSizeWidth;
	}

	/**
	 * Gets the screen size height.
	 *
	 * @return the screen size height
	 */
	public int getScreenSizeHeight() {
		return screenSizeHeight;
	}


	/**
	 * Sets the screen size width.
	 *
	 * @param screenSizeWidth the new screen size width
	 */
	public void setScreenSizeWidth(int screenSizeWidth) {
		this.screenSizeWidth = screenSizeWidth;
	}

	/**
	 * Sets the screen size height.
	 *
	 * @param screenSizeHeight the new screen size height
	 */
	public void setScreenSizeHeight(int screenSizeHeight) {
		this.screenSizeHeight = screenSizeHeight;
	}
	
	/**
	 * Decaling x.
	 *
	 * @return the int
	 */
	public int decalingX(){
		return decalingX;
	}
	
	/**
	 * Decaling y.
	 *
	 * @return the int
	 */
	public int decalingY(){
		return decalingY;
	}
}
