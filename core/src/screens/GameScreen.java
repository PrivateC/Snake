package screens;

import net.chemicalstudios.snake.Food;
import net.chemicalstudios.snake.Snake;
import net.chemicalstudios.snake.SnakeGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
	String gameOverMsg;
	private Vector2 gameOverCoord;
	int timer = 0;
	int score = 0;
	
	String resetMsg;
	private Vector2 resetCoord;
	
	SpriteBatch fade;
	Texture plusOne;
	
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
		
		gameOverMsg = new String("Game Over! Your final score is");
		
		gameOverCoord = new Vector2(Gdx.graphics.getWidth() * -1, Gdx.graphics.getHeight() / 2 - (bmFont.getCapHeight() / 2));
		
		resetMsg = new String("Spacebar to restart!");
		resetCoord = new Vector2(Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(resetMsg).width / 2), gameOverCoord.y - (bmFont.getCapHeight()));
		
		/* Score fade effect */
		fade = new SpriteBatch();
		fadeLocation = new Vector2(0, 0);
		fadeLocationEnd = new Vector2(0, 0);
		plusOne = new Texture("plusOne.png");
	}
	
	public boolean scoreCoordMove = false;
	
	public boolean scoreFade = false;
	private Vector2 fadeLocation;
	private Vector2 fadeLocationEnd;
	
	public void setScoreFade(boolean n, Vector2 location) {
		scoreFade = n;
		fadeLocation.set(location);
		fadeLocationEnd.set(fadeLocation.x, fadeLocation.y + 50);
	}
	
	double alpha = 1.0;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.5f, 0.2f, 0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		if (currentState.equals(GameState.PLAYING)) {
			/* Smoothly transition the text */
			if (scoreCoordMove) {
				if (scoreCoord.y > bmFont.getCapHeight() * 2) {
					scoreCoord.y -= (scoreCoord.y -  bmFont.getCapHeight() * 2) * 0.1;
				} else {
					scoreCoord.y =  bmFont.getCapHeight() * 2;
				}
				if (scoreCoord.x > (Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(String.valueOf(score)).width))) {
					scoreCoord.x -= (scoreCoord.x - (Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(String.valueOf(score)).width))) * 0.1; 
				} 
				if ((int) (scoreCoord.x) == (int) ((Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(String.valueOf(score)).width))) &&
						Math.round(scoreCoord.y) == Math.round(bmFont.getCapHeight() * 2)) {
					scoreCoordMove = false;
				}
			}
			
			if (pauseMsgCoord.y > -100) {
				pauseMsgCoord.y -= (pauseMsgCoord.y - -100) * 0.1;
			} else {
				pauseMsgCoord.y = -100;
			}
			
			if (gameOverCoord.x > Gdx.graphics.getWidth() * -1) {
				gameOverCoord.x -= (gameOverCoord.x - Gdx.graphics.getWidth() * -1) * 0.1;
			}
			if (resetCoord.x > Gdx.graphics.getWidth() * -1) {
				resetCoord.x -= (resetCoord.x - Gdx.graphics.getWidth() * -1) * 0.1;
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
			bmFont.draw(batch, gameOverMsg, gameOverCoord.x, gameOverCoord.y);
			
			timer++;
		} else if (currentState.equals(GameState.PAUSE)) {
			/* Smoothly transition the text */
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
			/* Smoothly transition the text */
			if (gameOverCoord.x < Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(gameOverMsg + score).width / 2)) {
				gameOverCoord.x += (Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(gameOverMsg + score).width / 2) - gameOverCoord.x) * 0.1;
			}
			if (resetCoord.x < Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(resetMsg).width / 2)) {
				resetCoord.x += (Gdx.graphics.getWidth() / 2 - (bmFont.getBounds(resetMsg).width / 2) - resetCoord.x) * 0.1;
			}
			
			if (scoreCoord.y < gameOverCoord.y) {
				scoreCoord.y += (gameOverCoord.y - scoreCoord.y) * 0.1;
			} else {
				scoreCoord.y = gameOverCoord.y;
			}
			if (scoreCoord.x < (gameOverCoord.x + bmFont.getBounds(gameOverMsg + score).width)) {
				scoreCoord.x += ((gameOverCoord.x + bmFont.getBounds(gameOverMsg + score).width) - scoreCoord.x)  * 0.1;
			} else {
				scoreCoord.x = gameOverCoord.x + bmFont.getBounds(gameOverMsg + score).width;
			}
			
			bmFont.draw(batch, resetMsg, resetCoord.x, resetCoord.y);
			bmFont.draw(batch, String.valueOf(score), scoreCoord.x, scoreCoord.y);
			bmFont.draw(batch, gameOverMsg, gameOverCoord.x, gameOverCoord.y);
		}
		batch.end();
		if (scoreFade && currentState.equals(GameState.PLAYING)) {
			
			fade.begin();
			fade.setColor(1, 1, 1, (float) alpha);
			
			fade.draw(plusOne, fadeLocation.x, fadeLocation.y);
			
			fade.end();
			
			if (fadeLocation.y < fadeLocationEnd.y) {
				fadeLocation.y += 1;
				if (alpha > 0.03) {
					alpha -= 0.03;
				} else {
					alpha = 0;
				}
			} else {
				alpha = 1.0;
				scoreFade = false;
			}
			
			
		}
		
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
	}
	
	public void reset() {
		timer = 0;
		score = 0;
	
		scoreCoordMove = true;
		
		snake.reset();
	}
}
