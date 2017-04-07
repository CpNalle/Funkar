package funkar;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Shape {
	
	public double x,y, ySpeed, xSpeed;
	public int width, height;
	client pong;
	public Rectangle boundingBox;
	
	public Shape(client pong, int x, int y){
		this.pong=pong;
		this.x=x;
		this.y=y;
		boundingBox = new Rectangle(0,0,width,height);
	}
	public abstract void tick ();
	public abstract void render(Graphics g);
	
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}
	public Rectangle getBoundingBox(float xOffset, float yOffset){
		return new Rectangle((int) (x + boundingBox.x + xOffset), (int) (y + boundingBox.y + yOffset), boundingBox.width, boundingBox.height);
	}
}
