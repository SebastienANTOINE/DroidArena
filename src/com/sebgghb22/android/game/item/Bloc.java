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
package com.sebgghb22.android.game.item;

import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import com.sebgghb22.android.game.util.Option;

/**
 * The Class Bloc permit to create an object in the arena
 */
public class Bloc extends CCNode implements Item  {

	/** The image of the bloc*/
	protected CCSprite img;

	/** The body of the bloc */
	protected Body body;

	/** The definition of the body */
	private BodyDef bd;

	/** The properties of the bloc */
	protected Properties p;

	/**
	 * Instantiates a new bloc.
	 *
	 * @param p the properties of the new blocs
	 * @param x the abscissa position of the new bloc
	 * @param y the ordinate position of the new bloc
	 */
	public Bloc(Properties p ,float x, float y){
		bd = new BodyDef();
		this.p = p;
		
		bd.type = p.getBodyType();
		bd.position.set(new Vec2((x+(25))*Option.SCALING, (y+(25))*Option.SCALING));
		/*to scale image and body on the frame*/
	}

	/* (non-Javadoc)
	 * @see fr.umlv.urobot.items.Item#getCenter()
	 */
	@Override
	public Vec2 getCenter() {
		Vec2 center = body.getPosition();
		return new Vec2((center.x/Option.SCALING)+Option.UNIT/2,(center.y/Option.SCALING)+Option.UNIT/2);
	}

	/* (non-Javadoc)
	 * @see fr.umlv.urobot.items.Item#getScreenPosition()
	 */
	@Override
	public CGPoint getScreenPosition() {
		Vec2 position = body.getPosition();
		CGPoint p = new CGPoint();
		p.set(position.x/Option.SCALING, position.y/Option.SCALING);
		return p;
	}


	/* (non-Javadoc)
	 * @see fr.umlv.urobot.items.Item#getBody()
	 */
	@Override
	public Body getBody() {
		return body;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.urobot.items.Item#getBodyDef()
	 */
	@Override
	public BodyDef getBodyDef() {
		return bd;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.urobot.items.Item#setBody(org.jbox2d.dynamics.Body)
	 */
	@Override
	public void setBody(Body createBody) {
		this.body = createBody;
		PolygonShape box = new PolygonShape();
		box.setAsBox((Option.UNIT/2)*Option.SCALING, (Option.UNIT/2)*Option.SCALING);
		FixtureDef fd = new FixtureDef();
		fd.shape = box;
		fd.density = p.getResistance();
		fd.friction = p.getResistance();
		this.body.createFixture(fd); 
		this.body.setFixedRotation(p.isFixedRotation());
		this.body.setUserData(this);
		this.body.getFixtureList().setSensor(p.isSensor());
		this.body.setUserData(this);

	}

	/* (non-Javadoc)
	 * @see fr.umlv.urobot.items.Item#getAngle()
	 */
	@Override
	public float getAngle() {
		if(body == null)return 0;
		return body.getAngle();
	}

	/**
	 * Gets the properties of the bloc.
	 *
	 * @return the {@link Properties}
	 */
	public Properties getProperties() {
		return this.p;
	}

	/* (non-Javadoc)
	 * @see fr.umlv.urobot.items.Item#update()
	 */
	@Override
	public void update() {
		if(body.getLinearVelocity().length() < 3 && body.getJointList() == null){
			body.setType(BodyType.STATIC);
		}

	}

	@Override
	public CCSprite getSprite() {
		// TODO Auto-generated method stub
		return img;
	}

	@Override
	public void load() {
		this.img = CCSprite.sprite(p.getImg());
		
	}


	
}
