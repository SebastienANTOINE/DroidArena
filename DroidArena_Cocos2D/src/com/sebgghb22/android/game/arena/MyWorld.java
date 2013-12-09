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
package com.sebgghb22.android.game.arena;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.joints.Joint;

import com.sebgghb22.android.game.util.Option;

// TODO: Auto-generated Javadoc
/**
 * The Class MyWorld.
 */
public class MyWorld {

	/** The instance. */
	private static MyWorld instance;
	
	/** The world. */
	private World world=null;
	
	/**
	 * Instantiates a new my world.
	 */
	private MyWorld(){
		this.world= new World(Option.GRAVITY,true);
	}
	
	/**
	 * Gets the single instance of MyWorld.
	 *
	 * @return single instance of MyWorld
	 */
	public synchronized static MyWorld getInstance(){
		if(instance==null){
			instance= new MyWorld();
		}
		return instance;
	}
	
	/**
	 * Gets the world.
	 *
	 * @return the world
	 */
	public static synchronized World getWorld(){
		return getInstance().world;
	}
	
	/**
	 * Gets the contacts.
	 *
	 * @return the contacts
	 */
	public Collection<Contact> getContacts(){
		List<Contact> l = new ArrayList<Contact>();
		for(Contact c = world.getContactList();c!=null;c=c.getNext()){
			l.add(c);
		}
		return l;
	}
	
	/**
	 * Gets the joints.
	 *
	 * @return the joints
	 */
	public Collection<Joint> getJoints(){
		List<Joint> l = new ArrayList<Joint>();
		for(Joint j = world.getJointList();j!=null;j=j.getNext()){
			l.add(j);
		}
		return l;
	}
	
	/**
	 * Gets the body list.
	 *
	 * @return the body list
	 */
	public Collection<Body> getBodyList(){
		List<Body> l = new ArrayList<Body>();
		for(Body b = world.getBodyList();b!=null;b=b.getNext()){
			l.add(b);
		}
		return l;
	}

	/**
	 * Clear world.
	 */
	public void clearWorld() {
		for(Body b = world.getBodyList();b!=null;b=b.getNext()){
			world.destroyBody(b);
		}
	}
	
}
