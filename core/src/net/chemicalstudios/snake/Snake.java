package net.chemicalstudios.snake;

import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Snake implements InputProcessor {
	ArrayList<Sprite> body;
	
	private Texture bodyTexture;
	
	enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	Direction currentDirection = Direction.RIGHT;
	
	private int moveAmount;
	
	public Snake() {
		body = new ArrayList<Sprite>();
		
		bodyTexture = new Texture("body.png");
		
		moveAmount = bodyTexture.getWidth();
		
		body.add(new Sprite(bodyTexture));
	}
	
	public void update() {
		for (int i = body.size() - 1; i > 0; i--) {
			body.set(i, body.get(i - 1));
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
	
	public void grow() {
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
	
	public ArrayList<Sprite> getSprites() {
		return body;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.A:
		case Keys.LEFT:
			currentDirection = Direction.LEFT;
			break;
		case Keys.D:
		case Keys.RIGHT:
			currentDirection = Direction.RIGHT;
			break;
		case Keys.W:
		case Keys.UP:
			currentDirection = Direction.UP;
			break;
		case Keys.S:
		case Keys.DOWN:
			currentDirection = Direction.DOWN;
			break;
		case Keys.SPACE:
			grow();
			break;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
