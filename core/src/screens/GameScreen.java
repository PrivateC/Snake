package screens;

import net.chemicalstudios.snake.Food;
import net.chemicalstudios.snake.Snake;
import net.chemicalstudios.snake.SnakeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	private SnakeGame game;
	private SpriteBatch batch;
	
	private Snake snake;
	private Food food;
	
	public enum GameState {
		PLAYING, PAUSE, OVER;
	}
	
	private int score;
	private int length;
	
	public GameScreen(SnakeGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		snake = new Snake();
		Gdx.input.setInputProcessor(snake);
		food = new Food();
	}
	
	int timer = 0;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(food.getTexture(), food.getX(), food.getY());
		for (int i = 0; i < snake.getSprites().size(); i++) {
			snake.getSprites().get(i).draw(batch);
		}
		
		batch.end();
		
		if (timer >= 4) {
			timer = 0;
			snake.update(food);
		}
		timer++;
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
