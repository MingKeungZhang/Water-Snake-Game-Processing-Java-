package snake;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * 
 * @author Ming Keung Zhang
 *create the snake
 */
public class SnakeObject extends GraphicObject {

	//the length and side length
	protected int length, sidelength;
	//the direction
	protected String direction;
	//arraylist of x and y position
	protected ArrayList<Integer> xpos, ypos;
	
	/**
	 * the constructor
	 * @param x the x position
	 * @param y	the y position
	 * @param width	the width
	 * @param height the height
	 * @param grid the frame grid
	 */
	SnakeObject(int x, int y, int width, int height, int grid)
	{
		super(x, y, width, height, grid);

		//initialize the length and side length
		length = 1;
		sidelength = grid;
		
		//initialize the direction
		direction = "right";

		//initialize the arraylist
		xpos = new ArrayList<Integer>();
		ypos = new ArrayList<Integer>();

		//add the x and y position to the arraylist
		xpos.add(x);
		ypos.add(y);
	}

	/**
	 * update the snake / direction
	 */
	public void update() {

		for(int i = length - 1; i > 0; i = i -1 ){ 
			xpos.set(i, xpos.get(i - 1)); 
			ypos.set(i, ypos.get(i - 1));   
		}  
		if(direction == "left"){ 
			xpos.set(0, xpos.get(0) - sidelength); 
		} 
		if(direction == "right"){ 
			xpos.set(0, xpos.get(0) + sidelength); 
		} 
		if(direction == "up"){ 
			ypos.set(0, ypos.get(0) - sidelength); 
		} 
		if(direction == "down"){ 
			ypos.set(0, ypos.get(0) + sidelength); 
		} 
		xpos.set(0, (xpos.get(0) + width) % width); 
		ypos.set(0, (ypos.get(0) + height) % height); 

		// check if hit itself and if so cut off the tail 
		if( collision() == true){ 
			length = 1; 
			int xtemp = xpos.get(0); 
			int ytemp = ypos.get(0); 
			xpos.clear(); 
			ypos.clear(); 
			xpos.add(xtemp); 
			ypos.add(ytemp); 
		} 
	}

	/**
	 * add chain
	 */
	public void addLink(){ 
		xpos.add( xpos.get(length-1) + sidelength); 
		ypos.add( ypos.get(length-1) + sidelength); 
		length++; 
	} 

	/**
	 * check if snake hit itself
	 * @return snake collision
	 */
	public boolean collision(){ 
		for(int i = 1; i < length; i++){
			double distance = Math.sqrt((xpos.get(0)-xpos.get(i))*(xpos.get(0)-xpos.get(i))
					+ (ypos.get(0)-ypos.get(i))*(ypos.get(0)-ypos.get(i)));
			if(distance < sidelength)
			{
				return true; 
			}  
		}  
		return false; 
	} 

	/**
	 * draw the snake
	 */
	@Override
	public
	void draw(PApplet applet) {
		// TODO Auto-generated method stub
		for(int i = 0; i <length; i++){ 
			applet.stroke(179, 140, 198); 
			applet.fill(100, 0, 100); 
			applet.rect(xpos.get(i), ypos.get(i), sidelength, sidelength); 
		} 
	}
}
