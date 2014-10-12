package net.chemicalstudios.snake;

import java.util.ArrayList;

import screens.GameScreen;
import screens.GameScreen.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


public class Snake implements InputProcessor {
	ArrayList<Sprite> body;

	private Texture bodyTexture;

	int startLength = 5;

	enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	boolean movedThisFrame = false;

	Direction currentDirection = Direction.RIGHT;

	private int moveAmount;

	GameScreen game;

	public Snake(GameScreen game) {
		this.game = game;
		bodyTexture = new Texture("body.png");
		reset();
	}

	public void update(Food food) {
		
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (currentDirection != Direction.RIGHT && !movedThisFrame) {
				currentDirection = Direction.LEFT;
			}
		} else if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (currentDirection != Direction.LEFT && !movedThisFrame) {
				currentDirection = Direction.RIGHT;
			}
		} else if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			if (currentDirection != Direction.UP && !movedThisFrame) {
				currentDirection = Direction.DOWN;
			}
		} else if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			if (currentDirection != Direction.DOWN && !movedThisFrame) {
				currentDirection = Direction.UP;
			}
		}
		
		for (int i = body.size() - 1; i > 0; i--) {
			body.get(i).setPosition(body.get(i-1).getX(), body.get(i - 1).getY());

			if (body.get(0).getX() < body.get(i).getX() + body.get(i).getWidth() && body.get(0).getX() + body.get(0).getWidth() > body.get(i).getX()) {
				if (body.get(0).getY() < body.get(i).getY() + body.get(i).getHeight() && body.get(0).getY() + body.get(0).getHeight() > body.get(i).getY()) {
					if (i > 1) {
						game.setState(GameState.OVER);
					}
				}
			}
		}		
		if (body.get(0).getX() < food.getX() + food.getWidth() && body.get(0).getX() + body.get(0).getWidth() > food.getX()) {
			if (body.get(0).getY() < food.getY() + food.getHeight() && body.get(0).getY() + body.get(0).getHeight() > food.getY()) {
				game.setScoreFade(true, new Vector2(food.getX(), food.getY()));
				food.generate(body);
				grow(1);
			}
		}

		switch (currentDirection) {
		case UP:
			body.get(0).translateY(moveAmount);
			break;
		case DOWN:
			body.get(0).translateY(-moveAmount);
			break;
		case LEFT:
			body.get(0).translateX(-moveAmount);
			break;
		case RIGHT:
			body.get(0).translateX(moveAmount);
			break;
		}

		if (body.get(0).getX() + body.get(0).getWidth() > Gdx.graphics.getWidth()) {
			body.get(0).setX(0);
			currentDirection = Direction.RIGHT;
		} else if (body.get(0).getX() < 0) {
			body.get(0).setX(Gdx.graphics.getWidth());
			currentDirection = Direction.LEFT;
		} else if (body.get(0).getY() < 0) {
			body.get(0).setY(Gdx.graphics.getHeight());
			currentDirection = Direction.DOWN;
		} else if (body.get(0).getY() > Gdx.graphics.getHeight()) {
			body.get(0).setY(0);
			currentDirection = Direction.UP;
		}

		
		movedThisFrame = false;
	}

	public void grow(int amount) {
		for (int i = 0; i < amount; i++) {
			body.add(new Sprite(bodyTexture));
			body.get(body.size() - 1).setPosition(body.get(body.size() - 2).getX(), body.get(body.size() - 2).getY());

			switch (currentDirection) {
			case UP:
				body.get(body.size() - 1).translateY(-moveAmount);
				break;
			case DOWN:
				body.get(body.size() - 1).translateY(moveAmount);
				break;
			case LEFT:
				body.get(body.size() - 1).translateX(moveAmount);
				break;
			case RIGHT:
				body.get(body.size() - 1).translateX(-moveAmount);
				break;
			}
		}
	}

	public ArrayList<Sprite> getSprites() {
		return body;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.P || keycode == Keys.SPACE) {
			switch (game.getState()) {
			case PAUSE:
				game.setState(GameState.PLAYING);
				break;
			case PLAYING:
				game.setState(GameState.PAUSE);
				break;
			case OVER:
				game.setState(GameState.PLAYING);
				game.reset();
				break;
			}
			game.scoreCoordMove = true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public void dispose() {
		bodyTexture.dispose();
	}

	public void reset() {
		body = new ArrayList<Sprite>();

		currentDirection = Direction.RIGHT;

		for(int i = 0; i < startLength; i++){
			body.add(new Sprite(bodyTexture));
			body.get(i).setPosition(Gdx.graphics.getWidth() / 2- ((i) * bodyTexture.getWidth()), Gdx.graphics.getHeight() / 2);
		}

		moveAmount = bodyTexture.getWidth();

		body.add(new Sprite(bodyTexture));
	}
}
