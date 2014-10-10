package screens;

import net.chemicalstudios.snake.Snake;
import net.chemicalstudios.snake.SnakeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {

	private Snake snake;
	private SnakeGame game;
	
	
	public GameScreen(SnakeGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		snake = new Snake();
		
		Gdx.input.setInputProcessor(snake);
	}
	
	@Override
	public void render(float delta) {
		
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
