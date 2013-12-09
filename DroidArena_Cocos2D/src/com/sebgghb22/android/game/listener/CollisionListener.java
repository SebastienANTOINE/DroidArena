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
package com.sebgghb22.android.game.listener;

import org.jbox2d.collision.WorldManifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.Contact;

import com.sebgghb22.android.game.item.Bloc;
import com.sebgghb22.android.game.item.Enemy;
import com.sebgghb22.android.game.item.Robot;

// TODO: Auto-generated Javadoc
/**
 * The listener interface for receiving collision events.
 * The class that is interested in processing a collision
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCollisionListener<code> method. When
 * the collision event occurs, that object's appropriate
 * method is invoked.
 *
 * @see CollisionEvent
 */
public class CollisionListener extends GameListener {

	/* (non-Javadoc)
	 * @see fr.umlv.urobot.listener.GameListener#beginContact(org.jbox2d.dynamics.contacts.Contact)
	 */
	@Override
	public void beginContact(Contact c) {
		if(c==null)return;

		/*Recherche d'un bloc robot et d'un bloc enemy*/
		Bloc figA = (Bloc) c.getFixtureA().getBody().getUserData();
		Bloc figB = (Bloc) c.getFixtureB().getBody().getUserData();


		if(figA != null && figB != null){
			Robot rFig = null;
			Enemy eFig = null;

			if((figA.getClass() == Robot.class || figB.getClass() == Robot.class)){

				if(figA.getClass() == Robot.class)rFig = (Robot) figA;
				if(figB.getClass() == Robot.class)rFig = (Robot) figB;

				//rFig.setSpeed(0);

				if(figB.getClass() == Enemy.class){
					eFig = (Enemy) figB;
				}
				else if(figA.getClass() == Enemy.class){
					eFig = (Enemy) figA;
				}
				if(rFig != null && eFig != null){
					WorldManifold worldManifold = new WorldManifold();
					try{
						c.getWorldManifold(worldManifold);
					}catch( NullPointerException e){
						return;
					}
					if(eFig.isActive()&&rFig.isHurtable()){
						eFig.disable();
						Vec2 point = worldManifold.points[0];
						Vec2 vB = eFig.getBody().getLinearVelocityFromWorldPoint(point);

						if(vB.length() > 0 && vB.length()<15){
							rFig.setDamage(rFig.getInitialLife()/3);
						}else{
							rFig.setDamage(rFig.getInitialLife()/2);
						}
					}
					return;
				}
			}  
		}
	}
}
