package screens;

import java.util.ArrayList;

import net.chemicalstudios.snake.SnakeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {

	private SnakeGame game;
	private SpriteBatch batch;
	
	private Texture body;
	private ArrayList<Vector2> coords;
	private int length = 7;
	
	private static String direction;
	
	private OrthographicCamera cmaera;
	
	
	
	public GameScreen(SnakeGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
}
