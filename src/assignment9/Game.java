package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {

	private Snake snake;
	private Food food;
	private int score;

	public Game() {
		StdDraw.enableDoubleBuffering();
		startNewGame();
	}

	private void startNewGame() {
		snake = new Snake();
		food = new Food();
		score = 0;
		play();
	}

	public void play() {
		while (snake.isInbounds()) {
			int dir = getKeypress();
			if (dir != -1) {
				snake.changeDirection(dir);
			}

			snake.move();

			if (snake.eatFood(food)) {
				food = new Food();
				score++;
			}

			updateDrawing();
		}

		showGameOverScreen();
	}

	private int getKeypress() {
		if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
			return 4;
		} else {
			return -1;
		}
	}

	private void updateDrawing() {
		StdDraw.clear();

		snake.draw();
		food.draw();

		// Show current score
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.textLeft(0.02, 0.98, "Score: " + score);

		StdDraw.pause(50);
		StdDraw.show();
	}

	private void showGameOverScreen() {
		while (true) {
			StdDraw.clear();

			// Game Over text
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.5, 0.6, "Game Over!");
			StdDraw.text(0.5, 0.55, "Final Score: " + score);

			// Button rectangle
			double buttonX = 0.4, buttonY = 0.4, width = 0.2, height = 0.07;
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.filledRectangle(buttonX + width / 2, buttonY + height / 2, width / 2, height / 2);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(buttonX + width / 2, buttonY + height / 2, "Play Again");

			StdDraw.show();

			if (StdDraw.isMousePressed()) {
				double mouseX = StdDraw.mouseX();
				double mouseY = StdDraw.mouseY();

				if (mouseX >= buttonX && mouseX <= buttonX + width &&
					mouseY >= buttonY && mouseY <= buttonY + height) {
					StdDraw.pause(200); // Debounce click
					startNewGame();
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		new Game(); // Game starts automatically
	}
}
