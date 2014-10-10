package net.chemicalstudios.snake;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SnakeGame extends ApplicationAdapter {

	private SnakeGame game;
	private SpriteBatch batch;
	
	private Texture body;
	private ArrayList<Vector2> coords;
	private int length = 19;
	
	private static String direction;
	
	private OrthographicCamera camera;
	
	private int WIDTH;
	private int HEIGHT;
	
	int timer = 0;
	
	@Override
	public void create() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		coords = new ArrayList<Vector2>();
		for (int i = 0; i < length; i++) {
			coords.add(new Vector2(WIDTH / 2 - ((i) * 20), Gdx.graphics.getHeight() / 2));
		}
		
		direction = "right";
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		body = new Texture("body.png");
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		MathSnake mathSnake = new MathSnake();
		
		if (timer >= 5) {
			mathSnake.nextCoord(coords, direction);
			timer = 0;
		} else {
			timer++;
		}
		
		batch.begin();
		
		for (int i = 0; i < coords.size(); i++) {
			batch.draw(body, coords.get(i).x, coords.get(i).y);
		}
		
		batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			direction = "left";
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			direction = "right";
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			direction = "down";
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			direction = "up";
		}
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}
}
