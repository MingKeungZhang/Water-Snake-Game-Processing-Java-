package snake;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * @author Ming Keung Zhang
 *create the background and its effect
 */
public class BackgroundObject extends GraphicObject{

	//the variables
	private PImage img;
	private int i, a, b;
	private int oldind, newind, mapind;
	//the height map
	private short ripplemap[];
	//the pixels
	protected int col[];
	private int riprad;
	private int rwidth, rheight;
	protected int texture[];
	private int size;
	
	/**
	 * the constructor
	 * @param x the x position
	 * @param y	the y position
	 * @param width the width
	 * @param height the height
	 * @param grid the frame grid
	 * @param img the image
	 */
	BackgroundObject(int x, int y, int width, int height, int grid, PImage img) {
		super(x, y, width, height, grid);
		// TODO Auto-generated constructor stub
		//initialize the variables
		this.img = img;
		riprad = 5; 
		rwidth = width >> 1; 
		rheight = height >> 1; 
		size = width * (height + 2) * 2; 
		ripplemap = new short[size]; 
		col = new int[width * height]; 
		texture = new int[width * height]; 
		oldind = width; 
		newind = width * (height + 2); 
	}

	/**
	 * draw the background and its effect
	 * @param app the applet
	 */
	@Override
	public void draw(PApplet app) {
		// TODO Auto-generated method stub
		for (int j = y - riprad; j < y + riprad; j++) { 
			for (int k = x - riprad; k < x + riprad; k++) { 
				if (j >= 0 && j < height && k>= 0 && k < width) { 
					ripplemap[oldind + (j * width) + k] += 512; 
				} 
			} 
		} 
	}

	/**
	 * update the background and its effect
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		i = oldind; 
		oldind = newind; 
		newind = i; 
		
		i = 0; 
		mapind = oldind; 
		for (int j = 0; j < height; j++) { 
			for (int k = 0; k < width; k++) { 
				short data = (short)((ripplemap[mapind - width] + ripplemap[mapind + width] +  
						ripplemap[mapind - 1] + ripplemap[mapind + 1]) >> 1); 
				data -= ripplemap[newind + i]; 
				data -= data >> 5; 
				
				//avoid the wraparound effect 
				if (k == 0 || j == 0)
					ripplemap[newind + i] = 0; 
				else 
					ripplemap[newind + i] = data;  
				//where data = 0 then still, where data > 0 then wave 
				data = (short)(1024 - data); 

				//offsets 
				a = ((k - rwidth) * data / 1024) + rwidth; 
				b = ((j - rheight) * data / 1024) + rheight; 

				//bounds check 
				if (a >= width)  
					a = width - 1; 
				if (a < 0)  
					a = 0; 
				if (b >= height)  
					b = height-1; 
				if (b < 0)  
					b=0; 
				col[i] = img.pixels[a + (b * height)]; 
				mapind++; 
				i++; 
			} 
		} 
	} 
	
	/**
	 * get the new x and y position
	 * @param x the x position
	 * @param y the y position
	 */
	public void newPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

}
