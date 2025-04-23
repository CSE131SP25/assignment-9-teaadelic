package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		segments = new LinkedList<>();
		// Start with one segment in the center of the screen
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));

		// Start moving to the right by default
		deltaX = MOVEMENT_SIZE;
		deltaY = 0;
	}
	
	/**
	 * Updates direction of snake based on input
	 */
	public void changeDirection(int direction) {
		if(direction == 1) { // up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { // down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { // left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { // right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake forward in the current direction.
	 * Shifts all segments so each one follows the one in front.
	 */
	//
	public void move() {
		// Move tail segments to the position of the one before it
		for (int i = segments.size() - 1; i > 0; i--) {
			BodySegment prev = segments.get(i - 1);
			segments.get(i).setPosition(prev.getX(), prev.getY());
		}

		// Move head in current direction
		BodySegment head = segments.get(0);
		double newX = head.getX() + deltaX;
		double newY = head.getY() + deltaY;
		head.setPosition(newX, newY);
	}
	
	/**
	 * Draws all segments of the snake
	 */
	public void draw() {
		for (BodySegment segment : segments) {
			segment.draw();
		}
	}
	
	/**
	 * If head touches food, grow the snake and return true.
	 */
	//Q
	public boolean eatFood(Food f) {
		BodySegment head = segments.get(0);
		double dx = head.getX() - f.getX();
		double dy = head.getY() - f.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);

		if (distance <= SEGMENT_SIZE + Food.FOOD_SIZE) {
			// Add a new segment at the position of the last segment, added to segments list
			BodySegment tail = segments.get(segments.size() - 1);
			segments.add(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the snake's head is within screen boundaries
	 */
	//
	public boolean isInbounds() {
		BodySegment head = segments.get(0);
		double x = head.getX();
		double y = head.getY();
		return x >= 0 && x <= 1 && y >= 0 && y <= 1;
	}
}




