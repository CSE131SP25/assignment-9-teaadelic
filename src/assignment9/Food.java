package assignment9;

import java.util.Random;
import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.02;
	private double x, y;
	
	/**
	 * Creates a new Food at a random location
	 */
	public Food() {
		Random r = new Random();
		// Keep food within 0.05 and 0.95 to avoid edges
		this.x = 0.05 + r.nextDouble() * 0.9;
		this.y = 0.05 + r.nextDouble() * 0.9;
	}
	
	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(x, y, FOOD_SIZE);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
