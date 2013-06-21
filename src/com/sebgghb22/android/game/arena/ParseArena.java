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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jbox2d.common.Vec2;

import android.util.Log;

import com.sebgghb22.android.game.Level;
import com.sebgghb22.android.game.item.Bloc;
import com.sebgghb22.android.game.item.Properties;
import com.sebgghb22.android.game.util.Option;


// TODO: Auto-generated Javadoc
/**
 * The Class ParseArena.
 */
public class ParseArena {
	
	/**
	 * Parses the.
	 *
	 * @param arena the arena
	 * @return the concurrent linked queue
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ConcurrentLinkedQueue<Level> parse(InputStream arena) throws IOException{
		
		ConcurrentLinkedQueue<Level> levels = new ConcurrentLinkedQueue<Level>();		
		ArrayList<Bloc> elements= new ArrayList<Bloc>();
		ArrayList<Vec2> freeBlocs = new ArrayList<Vec2>();
		String lineRead;
		int width=0;
		int height=0;
		int startCounter=0;
		int finishCounter=0;
		BufferedReader bf = new BufferedReader(new InputStreamReader(arena));
		int i;
		ArrayList<String> lines= new ArrayList<String>();
		while((lineRead = bf.readLine()) != null){
			Log.d("Line", lineRead);
			lines.add(0, lineRead);
			if(width<lineRead.length()){
				width=lineRead.length();
			}
		}
		for(String line : lines){
			
			i=0;
			for(char c : line.toCharArray()){
				switch(c){
				case 'w' :{
					
					elements.add(new Bloc(Properties.WOOD,(float)i*Option.UNIT,(float)height*Option.UNIT));
					break;
				}
				case 'W' :{
					elements.add(new Bloc(Properties.WOOD,(float)i*Option.UNIT,(float)height*Option.UNIT));
					break;
				}
				case 'i' :{
					elements.add(new Bloc(Properties.ICE,(float)i*Option.UNIT,(float)height*Option.UNIT));
					break;
				}
				case 'I' :{
					elements.add(new Bloc(Properties.ICE,(float)i*Option.UNIT,(float)height*Option.UNIT));
					break;
				}
				case 'c' :{
					elements.add(new Bloc(Properties.CONCRETE,(float)i*Option.UNIT,(float)height*Option.UNIT));
					break;
				}
				case 'C' :{
					elements.add(new Bloc(Properties.SCONCRETE,i*Option.UNIT,height*Option.UNIT));
					break;
				}

				case 's' :{
					if(startCounter<2){
						elements.add(new Bloc(Properties.START,(float)i*Option.UNIT,(float)height*Option.UNIT));
						startCounter++;
						break;
					}

				}
				case 'S' :{
					if(startCounter<2){
						elements.add(new Bloc(Properties.START,(float)i*Option.UNIT,(float)height*Option.UNIT));
						startCounter++;
						break;
					}
				}

				case 'f' :{
					elements.add(new Bloc(Properties.FINISH,(float)i*Option.UNIT,(float)height*Option.UNIT));
					finishCounter++;
					break;
				}
				case 'F' :{
					finishCounter++;
					elements.add(new Bloc(Properties.FINISH,(float)i*Option.UNIT,(float)height*Option.UNIT));
					break;
				}

				case  '-' :{
					if(startCounter==2&&finishCounter>0){
					levels.offer(new Level(freeBlocs, elements,width,height,elements.get(0)));
					elements= new ArrayList<Bloc>();
					freeBlocs = new ArrayList<Vec2>();
					height=-1;
					width=0;
					startCounter=0;
					finishCounter=0;
					break;
					}else{
						throw new IllegalArgumentException("Arena is not valid check arena file or see readme!");
					}
				}

				default :{
					freeBlocs.add(new Vec2(i*Option.UNIT,height*Option.UNIT ));
					break;	
				}
				}

				i++;
			}
			height++;
		}

		return levels;
	}
}
