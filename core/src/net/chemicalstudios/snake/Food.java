package net.chemicalstudios.snake;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Food {
	private Vector2 location;
	private Texture food;
	int width, height;
	Random rng;
	
	public Food() {
		location = new Vector2(-50, 50);
		food = new Texture("food.png");
		width = food.getWidth();
		height = food.getHeight();
		rng = new Random();
	}
	
	public void generate(ArrayList<Sprite> body) {
		int newX = rng.nextInt(Gdx.graphics.getWidth());
		int newY = rng.nextInt(Gdx.graphics.getHeight());
		boolean done = true;
		
			if (newX <= body.get(0).getX() + body.get(0).getWidth() && newX + width >= body.get(0).getX()) {
				if (newY <= body.get(0).getY() + body.get(0).getHeight() && newX + width >= body.get(0).getY()) {
					done = false;
					generate(body);
				}	
			}
		if (done) {
			location.set(new Vector2(newX, newY));	
			System.out.println(location);
		}
	}
	
	public void generate() {
		int newX = rng.nextInt(Gdx.graphics.getWidth());
		int newY = rng.nextInt(Gdx.graphics.getHeight());
		
		location.set(new Vector2(newX, newY));
		System.out.println(location);
	}
	
	public int getX() {
		return (int) location.x;
	}
	
	public int getY() {
		return (int) location.y;
	}
	
	public Rectangle getBoundingRectangle() {
		return new Sprite(food).getBoundingRectangle();
	}
	
	public Texture getTexture() {
		return food;
	}
}
