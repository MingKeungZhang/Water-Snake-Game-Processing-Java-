package snake;

import processing.core.PApplet;

/**
 * 
 * @author Ming Keung Zhang
 *the abstract class. use to create other objects
 */

public abstract class GraphicObject {

	//the x and y position
	int x, y;
	//the width and height
	int width, height;
	//the frame grid
	int grid;

	/**
	 * the constructor
	 * @param x the x position
	 * @param y the y position
	 * @param width the width
	 * @param height the height
	 * @param grid the grid
	 */
	GraphicObject(int x, int y, int width, int height, int grid)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * the abstract draw method
	 * @param app the applet
	 */
	public abstract void draw(PApplet app);
	
	/**
	 * the abstract update method
	 */
	public abstract void update();

}
