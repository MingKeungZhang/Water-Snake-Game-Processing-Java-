package snake;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * 
 * @author Ming Keung Zhang
 * the main class / create the snake game
 */
public class Snake extends PApplet {

	//the snake / food object
	private FoodObject food;
	private SnakeObject snake;
	
	//the size and the grid of the frame
	private final static int WIDTH = 800, HEIGHT = 600;
	private final int GRID = 20;

	//the highscore
	private int highscore;

	//the image and object
	private PImage img;
	private BackgroundObject background;

	//the controls
	private boolean up, down, left, right;

	/**
	 * create the frame
	 */
	public void settings()
	{
		size(WIDTH, HEIGHT);
	}

	/**
	 * set up the game
	 */
	public void setup() {

		//initialize food / snake object
		food = new FoodObject((int) (Math.random()*((WIDTH/GRID) - 10) + 5)*GRID, (int) (Math.random()*((HEIGHT/GRID) - 10) + 5)*GRID, WIDTH, HEIGHT, GRID);
		snake = new SnakeObject((int) (Math.random()*((WIDTH/GRID) - 10) + 5)*GRID, (int) (Math.random()*((HEIGHT/GRID) - 10) + 5)*GRID, WIDTH, HEIGHT, GRID);

		//initialize control
		up = true;
		down = true;
		left = true;
		right = true;

		//initialize highscore
		highscore = 0;

		//initialize image
		img = loadImage("Water.jpg");
		background = new BackgroundObject(snake.xpos.get(0),snake.ypos.get(0),WIDTH, HEIGHT, GRID, img);	
		
		//the frame rate
		frameRate(20);
	}

	/**
	 * draw the game
	 */
	public void draw() {
		//draw image
		loadPixels();
		img.loadPixels();
		background.draw(this);
		//get pixels
		for(int loc = 0; loc < WIDTH*HEIGHT; loc++)
		{		
			pixels[loc] = background.col[loc];
		}
		//update image
		updatePixels();
		background.update();
		//draw scorebroad
		scoreboard();
		//draw food / snake and updates
		snake.draw(this);
		food.draw(this);
		snake.update();
		food.update();
		//get new snake position for background
		background.newPos(snake.xpos.get(0), snake.ypos.get(0));

		//add chain if snake is inside food / reset food
		double distance = Math.sqrt((food.x-snake.xpos.get(0))*(food.x-snake.xpos.get(0))
				+ (food.y-snake.ypos.get(0))*(food.y-snake.ypos.get(0)));
		if(distance < snake.sidelength)
		{
			food.reset();
			snake.addLink();
		}
		
		//update highscore
		if(snake.length > highscore)
		{
			highscore = snake.length-1;
		}
	}
	/**
	 * user interaction. use up / down / left / right arrow key to control snake
	 */
	public void keyPressed() {
	
		if(key == CODED){ 
			if(keyCode == LEFT && left == true){ 
				snake.direction = "left"; 
				if(snake.length > 1)
				{
					up = true;
					down = true;
					left = true;
					right = false;
				}
				else
				{
					up = true;
					down = true;
					left = true;
					right = true;						
				}
			} 
			if(keyCode == RIGHT && right == true){ 
				snake.direction = "right"; 
				if(snake.length > 1)
				{
					up = true;
					down = true;
					left = false;
					right = true;	
				}
				else
				{
					up = true;
					down = true;
					left = true;
					right = true;						
				}
			} 
			if(keyCode == UP && up == true){ 
				snake.direction = "up"; 
				if(snake.length > 1)
				{
					up = true;
					down = false;
					left = true;
					right = true;
				}
				else
				{
					up = true;
					down = true;
					left = true;
					right = true;						
				}
			} 
			if(keyCode == DOWN && down == true){ 
				snake.direction = "down";
				if(snake.length > 1)
				{
					up = false;
					down = true;
					left = true;
					right = true;
				}
				else
				{
					up = true;
					down = true;
					left = true;
					right = true;						
				}
			} 
		} 
	}

	/**
	 * the scorebroad. draw the text for the score and highscore
	 */
	public void scoreboard() {

		stroke(179, 140, 198); 
		fill(118, 22, 167); 
		textSize(20); 
		text( "Score: " + (snake.length-1), 20, 20); 
		fill(218, 100, 57); 
		textSize(20); 
		text( "High Score: " + highscore, 20, 40); 

	}
	
	/**
	 * the main method
	 * @param _args
	 */
	public static void main(String _args[]) {
		PApplet.main(new String[] { snake.Snake.class.getName() });
	}
}
