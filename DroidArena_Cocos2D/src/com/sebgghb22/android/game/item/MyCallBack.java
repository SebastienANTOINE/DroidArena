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

import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;

/**
 * The Class MyCallBack is a class to have a callback to doing a {@link RayCast}.
 */
public class MyCallBack implements RayCastCallback {

	/** The position of the first object. */
	private Vec2 point1;
	
	/** The view is set if there is an object forward the robot
	 * if there is an object, the enemy can't see the player robot*/
	private int view;
	
	/* (non-Javadoc)
	 * @see org.jbox2d.callbacks.RayCastCallback#reportFixture(org.jbox2d.dynamics.Fixture, org.jbox2d.common.Vec2, org.jbox2d.common.Vec2, float)
	 */
	@Override
	public float reportFixture(Fixture arg0, Vec2 arg1, Vec2 arg2, float arg3) {
		Bloc b = (Bloc) arg0.getBody().getUserData();
		if(b.getClass() != Robot.class){
			if(b.getProperties() == Properties.WOOD || b.getProperties() == Properties.CONCRETE || b.getProperties()==Properties.SCONCRETE
					|| b.getProperties() == Properties.ICE){
				view = -1;
				return arg3;
			}
		}
		point1 = arg1;
		view = 1;
		return -1;
	}

	/**
	 * Gets the state of the vision of the enemy.
	 *
	 * @return the view, -1 if an object exist between the enemy and the player, else 1
	 */
	public int getEtat(){
		return view;
	}

	/**
	 * Gets the robot position.
	 *
	 * @return the robot position
	 */
	public Vec2 getRobotPosition() {
		return point1;
	}
}
