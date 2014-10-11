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
	
	ArrayList<Vector2> possibleLocations;
	
	public Food() {
		location = new Vector2();
		food = new Texture("food.png");
		width = food.getWidth();
		height = food.getHeight();
		rng = new Random();
		
		possibleLocations = new ArrayList<Vector2>();
		for (int x = 0; x < Gdx.graphics.getWidth(); x += food.getWidth()) {
			for (int y = 0; y < Gdx.graphics.getHeight(); y += food.getHeight()) {
				possibleLocations.add(new Vector2(x, y));
			}
		}
		System.out.println(possibleLocations.size());
		int newLocation = rng.nextInt(possibleLocations.size());
		location = possibleLocations.get(newLocation);
	}
	
	public void generate(ArrayList<Sprite> body) {
		int newLocation = rng.nextInt(possibleLocations.size());
		location = possibleLocations.get(newLocation);
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
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
