package screens;

import net.chemicalstudios.snake.SnakeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IntroScreen implements Screen {
	
	private Texture introTexture;
	private SpriteBatch batch;
	
	double alpha = 1;
	
	
	SnakeGame game;
	
	public IntroScreen(SnakeGame game) {
		this.game = game;
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (alpha > 0) {
			alpha -= 0.01;
		} else {
			game.setScreen(new GameScreen(game));
			return;
		}
		
		batch.begin();
		
		batch.setColor(1, 1, 1, (float) alpha);
		
		batch.draw(introTexture, 0, 0);
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		introTexture = new Texture("intro.png");
		batch = new SpriteBatch();
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
