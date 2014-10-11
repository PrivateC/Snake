package screens;

import net.chemicalstudios.snake.Food;
import net.chemicalstudios.snake.Snake;
import net.chemicalstudios.snake.SnakeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {

	//TODO http://pastebin.com/9dVhMj5U
	
	private SnakeGame game;
	private SpriteBatch batch;
	
	private Snake snake;
	private Food food;
	
	public enum GameState {
		PLAYING, PAUSE, OVER;
	}
	
	GameState currentState = GameState.PLAYING;
	
	public GameScreen(SnakeGame game) {
		this.game = game;
	}
	
	FreeTypeFontGenerator ftfGen;
	FreeTypeFontParameter parameter;
	BitmapFont bmFont;
	
	private Vector2 scoreCoord;
	String pauseMessage;
	private Vector2 pauseMsgCoord;
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		snake = new Snake(this);
		Gdx.input.setInputProcessor(snake);
		food = new Food();
		
		ftfGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 32;
		bmFont = ftfGen.generateFont(parameter);
		
		scoreCoord = new Vector2(Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(String.valueOf(score)).width),  bmFont.getCapHeight() * 2);
		
		pauseMessage = new String("Paused! P or Spacebar to unpause!");
		
		pauseMsgCoord = new Vector2(Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(pauseMessage).width / 2), -100);
	}
	
	int timer = 0;
	int score = 0;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.5f, 0.2f, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		if (currentState.equals(GameState.PLAYING)) {
			if (scoreCoord.y >  bmFont.getCapHeight() * 2) {
				scoreCoord.y -= (scoreCoord.y -  bmFont.getCapHeight() * 2) * 0.1;
			} else {
				scoreCoord.y =  bmFont.getCapHeight() * 2;
			}
			if (pauseMsgCoord.y > -100) {
				pauseMsgCoord.y -= (pauseMsgCoord.y - -100) * 0.1;
			} else {
				pauseMsgCoord.y = -100;
			}
			
			batch.draw(food.getTexture(), food.getX(), food.getY());
			for (int i = 0; i < snake.getSprites().size(); i++) {
				snake.getSprites().get(i).draw(batch);
			}
			
			if (timer >= 4) {
				timer = 0;
				snake.update(food);
				score = snake.getSprites().size() - 6;
			}
			
			bmFont.draw(batch, String.valueOf(score), scoreCoord.x, scoreCoord.y);
			bmFont.draw(batch, pauseMessage, pauseMsgCoord.x, pauseMsgCoord.y);

			timer++;
		} else if (currentState.equals(GameState.PAUSE)) {
			
			if (scoreCoord.y < Gdx.graphics.getHeight() / 2 + (bmFont.getCapHeight() * 4)) {
				scoreCoord.y += (Gdx.graphics.getHeight() / 2 + (bmFont.getCapHeight() * 4) - scoreCoord.y) * 0.1;
			} else {
				scoreCoord.y = Gdx.graphics.getHeight() / 2 + (bmFont.getCapHeight() * 4);
			}
			if (pauseMsgCoord.y < Gdx.graphics.getHeight() / 2 - (bmFont.getCapHeight() / 2)) {
				pauseMsgCoord.y += (Gdx.graphics.getHeight() / 2 - (bmFont.getCapHeight() / 2) - pauseMsgCoord.y) * 0.1;
			} else {
				pauseMsgCoord.y = Gdx.graphics.getHeight() / 2 - (bmFont.getCapHeight() / 2);
			}
			
			bmFont.draw(batch, pauseMessage, pauseMsgCoord.x, pauseMsgCoord.y);
			bmFont.draw(batch, String.valueOf(score), scoreCoord.x, scoreCoord.y);
		} else if (currentState.equals(GameState.OVER)) {
			
		}
		batch.end();
	}

	public void setState(GameState newState) {
		currentState = newState;
	}
	
	public GameState getState() {
		return currentState;
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
		batch.dispose();
		bmFont.dispose();
		ftfGen.dispose();
		snake.dispose();
		food.dispose();
	}
}
