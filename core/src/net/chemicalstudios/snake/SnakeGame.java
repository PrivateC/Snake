package net.chemicalstudios.snake;

import screens.GameScreen;
import screens.IntroScreen;

import com.badlogic.gdx.Game;

public class SnakeGame extends Game {
	
	IntroScreen introScreen;
	GameScreen gameScreen;
	
	@Override
	public void create() {
		
		introScreen = new IntroScreen(this);
		gameScreen = new GameScreen(this);
		this.setScreen(introScreen);
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		introScreen.dispose();
		gameScreen.dispose();
	}
}
