package snake;

import processing.core.PApplet;

/**
 * 
 * @author Ming Keung Zhang
 *Create the food
 */
public class FoodObject extends GraphicObject{
	// the x and y position
	private int xpos, ypos;
	// the side length
	private int sidelength;

	//number of pivots for kinematic chain
	private final int PIVOTS = 8;
	//number of kinematic chains
	private final int CHAINS = 4;
	//the chain angle
	private float chainAngle = 0.5f;

	/**
	 * the constructor
	 * @param x the x position
	 * @param y	the y position
	 * @param width the width
	 * @param height the height
	 * @param grid the frame grid
	 */
	FoodObject(int x, int y, int width, int height, int grid)
	{
		super(x, y, width, height, grid);

		// initialize side length
		sidelength = grid;
		
		// initialize x and y position
		xpos = width / sidelength;
		ypos = height / sidelength;
	}

	/**
	 * draw the food
	 */
	@Override
	public
	void draw(PApplet applet) {
		// TODO Auto-generated method stub
		applet.noStroke();
		applet.noFill();
		applet.rect(x, y, 20, 20);

		//create the kinematic chain
		applet.translate (x+sidelength/2, y+sidelength/2);
		for(int i =0; i < PIVOTS;i++)
		{		
			applet.rotate((float)Math.PI/(PIVOTS/2));
			applet.pushMatrix();	

			for(int j = 0; j < CHAINS; j++)
			{
				applet.rotate(chainAngle);
				applet.stroke(179, 140, 198); 
				applet.strokeWeight(5);
				applet.fill(100, 0, 100);
				applet.line(0, 0, 5, 0);
				applet.translate(5, 0);
			}
			applet.popMatrix();
		}
	}

	/**
	 * reset the food
	 */
	public void reset() {
		x = (int) (Math.random()*(xpos - 12) + 6)*sidelength;
		y = (int) (Math.random()*(ypos - 12) + 6)*sidelength;
	}

	/**
	 * update the kinematic chain angles / create effect
	 */
	public void update(){
		chainAngle+=.25f;
	}
}
