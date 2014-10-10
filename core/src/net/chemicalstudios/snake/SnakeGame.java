package net.chemicalstudios.snake;

import screens.GameScreen;

import com.badlogic.gdx.Game;

public class SnakeGame extends Game {
	
	GameScreen gameScreen;
	
	@Override
	public void create() {
		
		gameScreen = new GameScreen(this);
		this.setScreen(gameScreen);
	}
	
	@Override
	public void render() {
		super.render();
	}
}
