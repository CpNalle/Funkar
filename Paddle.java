package funkar;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Shape{
	
	public boolean isMine;
	public Paddle(client pong, int x, int y, boolean isMine){
		super(pong, x,y);
		this.isMine=isMine;
		width = 10;
		height = 100;
		ySpeed = 5;
		boundingBox.x=x;
		boundingBox.y=y;
		boundingBox.width=width;
		boundingBox.height=height;
	}
	public void tick(){
		if(isMine){
			if((pong.getKeyManager().down) && (y + height <pong.getHeight())){
				y +=ySpeed;
				boundingBox.y+=ySpeed;
			}else if((pong.getKeyManager().up) && (y>0)){
				y -=ySpeed;
				boundingBox.y-=ySpeed;
			}else{
				return;
			}
			if(y<0) y=0;
		}
	}
	public void render(Graphics g){
		//g.fillRect((int)x, (int)y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect((int)(boundingBox.x),
				(int)(boundingBox.y),
				boundingBox.width, boundingBox.height);
		
		
	}
}
