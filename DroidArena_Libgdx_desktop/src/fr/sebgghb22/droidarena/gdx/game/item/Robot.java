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
package fr.sebgghb22.droidarena.gdx.game.item;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.joints.Joint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import fr.sebgghb22.droidarena.gdx.game.arena.Arena;
import fr.sebgghb22.droidarena.gdx.game.arena.MyWorld;
import fr.sebgghb22.droidarena.gdx.utils.Option;

/**
 * The Class Robot.
 */
public class Robot extends Bloc  {

	/** The life. */
	private int life=100;
	
	/** The alive. */
	private boolean alive;
	
	/** The player. */
	private Properties player;
	
	/** The speed. */
	private int speed;
	
	/** The rotate speed. */
	float rotateSpeed=0.3f;
	
	/** The arena. */
	private Arena arena;
	
	/** The is finish. */
	private boolean isFinish = false;
	
	/** The joints. */
	private ArrayList<Joint> joints;

	private float initialLife=100;

	private boolean hurting=true;

	public float getInitialLife() {
		return initialLife;
	}


	/**
	 * Instantiates a new robot.
	 *
	 * @param p the p
	 * @param x the x
	 * @param y the y
	 * @param arena the arena
	 */
	public Robot(Properties p,float x,float y, Arena arena){
		super(p,x,y);
		this.alive = true;
		this.player = p;
		this.arena=arena;
		this.joints=new ArrayList<Joint>();
	}


	/* (non-Javadoc)
	 * @see fr.umlv.urobot.items.Bloc#setBody(org.jbox2d.dynamics.Body)
	 */
	@Override
	public void setBody(Body createBody) {
		this.body=createBody;
		CircleShape shape = new CircleShape();
		shape.m_type=ShapeType.CIRCLE;
		shape.m_radius=(17.5f)*Option.SCALING;
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density=p.getResistance();
		fd.friction=0.1f;
		this.body.createFixture(fd); 
		this.body.setFixedRotation(p.isFixedRotation());
		this.body.setUserData(this);
		this.body.getFixtureList().setSensor(p.isSensor());
	}


	/**
	 * Gets the life.
	 *
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	
	/**
	 * Checks if is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() {
		if(this.getScreenPosition().x<0||this.getScreenPosition().y<0||this.getScreenPosition().x>arena.getWidth()||this.getScreenPosition().y>arena.getHeight()){
			alive=false;
		}
		if(life>0)
			return alive;
		else
			alive=false;;
		return alive;
	}

	/**
	 * Sets the alive.
	 *
	 * @param alive the new alive
	 */
	public void setAlive(boolean alive) {
		if(!alive){
			this.alive = alive;
			this.life=0;
			img=Sprite.RIP.getImg();
			body.setTransform(body.getWorldCenter(), 0);
			for(Joint j : joints){
				MyWorld.getWorld().destroyJoint(j);
			}
		}
		p=Properties.ICE;
	}


	
	public void move(Vec2 vec2){
		body.applyLinearImpulse(vec2, getBody().getPosition());
	}
	/**
	 * Right.
	 */


	/**
	 * Sets the damage.
	 *
	 * @param lifeLost the new damage
	 */
	public void setDamage(float lifeLost) {
		if(life - lifeLost <= 0){
			setAlive(false);
			return;
		}
		life = (int) (life - lifeLost);
	}

	/**
	 * Sets the finish.
	 *
	 * @param b the new finish
	 */
	public void setFinish(boolean b) {
		isFinish = b;
	}

	/**
	 * Gets the arena.
	 *
	 * @return the arena
	 */
	public Arena getArena() {
		return this.arena;
	}


	/**
	 * Gets the joint list.
	 *
	 * @return the joint list
	 */
	public ArrayList<Joint> getJointList() {
		return joints;
	}
	
	@Override
	public float getAngle(){
		return body.getAngle();
	}


	public void setHurtable(boolean hurt) {
		hurting=hurt;
		
	}


	public boolean isHurtable() {
		return hurting;
	}
	
} 