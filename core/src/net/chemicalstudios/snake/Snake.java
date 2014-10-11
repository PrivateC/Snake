package net.chemicalstudios.snake;

import java.util.ArrayList;

import screens.GameScreen;
import screens.GameScreen.GameState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Snake implements InputProcessor {
	ArrayList<Sprite> body;
	
	private Texture bodyTexture;

	int startLength = 5;
	
	enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	Direction currentDirection = Direction.RIGHT;
	
	private int moveAmount;
	
	GameScreen game;
	
	public Snake(GameScreen game) {
		this.game = game;
		body = new ArrayList<Sprite>();
		
		bodyTexture = new Texture("body.png");
		
		for(int i = 0; i < startLength; i++){
			body.add(new Sprite(bodyTexture));
			body.get(i).setPosition(Gdx.graphics.getWidth() / 2- ((i) * bodyTexture.getWidth()), Gdx.graphics.getHeight() / 2);
		}
		
		moveAmount = bodyTexture.getWidth();
		
		body.add(new Sprite(bodyTexture));
	}
	
	public void update(Food food) {
		for (int i = body.size() - 1; i > 0; i--) {
			body.get(i).setPosition(body.get(i-1).getX(), body.get(i - 1).getY());
			
			if (body.get(0).getX() < body.get(i).getX() + body.get(i).getWidth() && body.get(0).getX() + body.get(0).getWidth() > body.get(i).getX()) {
				if (body.get(0).getY() < body.get(i).getY() + body.get(i).getHeight() && body.get(0).getY() + body.get(0).getHeight() > body.get(i).getY()) {
					if (i > 1) {
						game.setState(GameState.PAUSE);
					}
				}
			}
		}		
		if (body.get(0).getX() < food.getX() + food.getWidth() && body.get(0).getX() + body.get(0).getWidth() > food.getX()) {
			if (body.get(0).getY() < food.getY() + food.getHeight() && body.get(0).getY() + body.get(0).getHeight() > food.getY()) {
				food.generate(body);
				grow(1);
			}
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
		switch (keycode) {
		case Keys.A:
		case Keys.LEFT:
			if (currentDirection != Direction.RIGHT) currentDirection = Direction.LEFT;
			break;
		case Keys.D:
		case Keys.RIGHT:
			if (currentDirection != Direction.LEFT) currentDirection = Direction.RIGHT;
			break;
		case Keys.W:
		case Keys.UP:
			if (currentDirection != Direction.DOWN) currentDirection = Direction.UP;
			break;
		case Keys.S:
		case Keys.DOWN:
			if (currentDirection != Direction.UP) currentDirection = Direction.DOWN;
			break;
		case Keys.P:
		case Keys.SPACE:
			if (game.getState().equals(GameState.PAUSE)) {
				game.setState(GameState.PLAYING);
			} else if (game.getState().equals(GameState.PLAYING)) {
				game.setState(GameState.PAUSE);
			}
			break;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
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
}
