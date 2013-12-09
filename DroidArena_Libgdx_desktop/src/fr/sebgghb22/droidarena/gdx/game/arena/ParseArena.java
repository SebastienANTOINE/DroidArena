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
package fr.sebgghb22.droidarena.gdx.game.arena;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.jbox2d.common.Vec2;

import fr.sebgghb22.droidarena.gdx.game.item.Bloc;
import fr.sebgghb22.droidarena.gdx.game.item.Properties;
import fr.sebgghb22.droidarena.gdx.utils.Option;


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


		ConcurrentLinkedQueue<Level> levels = new ConcurrentLinkedQueue<>();		
		ArrayList<Bloc> elements= new ArrayList<>();
		ArrayList<Vec2> freeBlocs = new ArrayList<>();
		String line;
		int width=0;
		int height=0;
		int startCounter=0;
		int finishCounter=0;
		BufferedReader bf = new BufferedReader(new InputStreamReader(arena));
		int i;
		while((line = bf.readLine()) != null){

			if(width<line.length()){
				width=line.length();
			}
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
					elements.add(new Bloc(Properties.SCONCRETE,(float)i*Option.UNIT,(float)height*Option.UNIT));
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
					elements= new ArrayList<>();
					freeBlocs = new ArrayList<>();
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
