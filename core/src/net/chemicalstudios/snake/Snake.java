package net.chemicalstudios.snake;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Snake implements InputProcessor {
	public enum direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	private ArrayList<Sprite> body;
	private Texture bodyTexture;
	private Sprite bodySprite;
	private Vector2 location;
	private int jumpAmount;
	private Queue<direction> movementDirection;
	
	public Snake() {
		body = new ArrayList<Sprite>();
		
		bodyTexture = new Texture("sprites/body.png");
		bodySprite = new Sprite(bodyTexture);

		jumpAmount = (int) bodySprite.getWidth();
		
		body.add(bodySprite);
		
		location = new Vector2(0, 0);
		
		movementDirection = new ArrayDeque<direction>();
	}
	
	public void update() {
		location.x += jumpAmount;
		location.y += jumpAmount;
	}
	
	public Vector2 getLocation() {
		return location;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
