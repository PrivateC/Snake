package net.chemicalstudios.snake;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class MathSnake {
	public void checkBounds(ArrayList<Vector2> coords) {
		int temp1=0;
		int temp2=0;
		for(int i=coords.size()-1;i>0;i--){

			if(coords.get(i).x>=800)
			{
				temp1=0;
				temp2=(int) coords.get(0).y;
				coords.get(i).set(temp1,temp2);
			}
			if(coords.get(i).x<0)
			{
				temp1=800-20;
				temp2=(int) coords.get(0).y;
				coords.get(0).set(temp1,temp2);
			}
			if(coords.get(i).y>=480)
			{
				temp2=0;
				temp1=(int) coords.get(0).x;
				coords.get(0).set(temp1,temp2);
			}
			if(coords.get(i).y<0)
			{
				temp2=480-20;
				temp1=(int) coords.get(0).x;
				coords.get(0).set(temp1,temp2);
			}
		}
	}
	
	public void right(ArrayList<Vector2> coords) {
		for (int i = coords.size() - 1; i > 0; i--) {
			coords.get(i).set(coords.get(i-1).x, coords.get(i-1).y);
		}
		coords.get(0).add(20, 0);
		checkBounds(coords);
	}
	
	public void left(ArrayList<Vector2> coords) {
		for (int i = coords.size() - 1; i > 0; i--) {
			coords.get(i).set(coords.get(i-1).x, coords.get(i-1).y);
		}
		coords.get(0).sub(20, 0);
		checkBounds(coords);
	}
	public void down(ArrayList<Vector2> coords) {
		for (int i = coords.size() - 1; i > 0; i--) {
			coords.get(i).set(coords.get(i-1).x, coords.get(i-1).y);
		}
		coords.get(0).sub(0, 20);
		checkBounds(coords);
	}
	
	public void up(ArrayList<Vector2> coords) {
		for (int i = coords.size() - 1; i > 0; i--) {
			coords.get(i).set(coords.get(i-1).x, coords.get(i-1).y);
		}
		coords.get(0).add(0, 20);
		checkBounds(coords);
	}
	
	public void nextCoord(ArrayList<Vector2> coords, String direction) {
		if (direction.equals("right")) {
			if (coords.get(0).y != coords.get(1).y) { // the snake is traveling up or down
				right(coords);
			} else if (coords.get(0).x > coords.get(1).x) { // already traveling right 
				right(coords);
			} else if (coords.get(0).x < coords.get(1).x) { // traveling left 
				left(coords);
			}
		}
		if (direction.equals("left")) {
			if (coords.get(0).y != coords.get(1).y) { // the snake is traveling up or down
				left(coords);
			} else if (coords.get(0).x > coords.get(1).x) { // already traveling right 
				right(coords);
			} else if (coords.get(0).x < coords.get(1).x) { // traveling left 
				left(coords);
			}
		}
		if (direction.equals("up")) {
			if(coords.get(0).y>coords.get(1).y) {
				up(coords);
			} else if(coords.get(0).y<coords.get(1).y) {
				down(coords);
			} else if(coords.get(0).x>coords.get(1).x) {
				up(coords);
			} else if(coords.get(0).x<coords.get(1).x){
				up(coords);
			}
		}
		
		if (direction.equals("down")) {
			if(coords.get(0).y > coords.get(1).y) {
				up(coords);
			} else if(coords.get(0).y < coords.get(1).y) {
				down(coords);
			} else if(coords.get(0).x > coords.get(1).x) {
				down(coords);
			} else if(coords.get(0).x < coords.get(1).x){
				down(coords);
			}
		}
	}
	
	
}
