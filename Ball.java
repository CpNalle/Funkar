package funkar;

import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Shape{
int speedCounter = 0;	
	
	public Ball(client pong, int x, int y) {
		super(pong, x/2, y/2);
		width = 10;
		height = 10;
		xSpeed = 2;
		//xSpeed= (int)(Math.random()*10+1);
		ySpeed= (int)(Math.random()*2+1);
		boundingBox.x=x/2 -5;
		boundingBox.y=y/2 -5;
		boundingBox.width=width;
		boundingBox.height=height;
	
		
	}

	@Override
	public void tick() {
		if(boundingBox.x+boundingBox.width<0){
			//player 2 får poäng
		}else if(boundingBox.x>pong.getWidth()){
			//player1 får poäng
			boundingBox.x=boundingBox.x-(int)xSpeed;
			
		}else{
			move();
		}
		

	}
	public void move(){
	
		boundingBox.x-=xSpeed;
		boundingBox.y-=ySpeed;
		bounceVertical();
		
	}

	public void bounceHorizontal(){		
		xSpeed = -xSpeed;
		speedCounter++;
		System.out.println("studs" +speedCounter);
		System.out.println("speed" + Math.abs(ySpeed));
		if(speedCounter==2 && Math.abs(xSpeed)<15){
			goFasterplz();
		}
		

	}
	public void bounceVertical(){
		if((boundingBox.y-ySpeed<=0)){
			ySpeed =-ySpeed;
		}else if(boundingBox.y+ySpeed+boundingBox.height>=pong.getHeight()){
			boundingBox.y=pong.getHeight()-boundingBox.width-1;
			ySpeed=-ySpeed;
		}
	}

	public void goFasterplz(){
		if(xSpeed<0){
			xSpeed-=0.5;
		}
		else if(xSpeed>0){
			xSpeed+=0.5;
		}
		if(ySpeed<0){
			ySpeed-=0.2;
		}
		else if(ySpeed>0){
			ySpeed+=0.2;
		}
		speedCounter=0;

	}

	@Override
	public void render(Graphics g) {
	//	g.drawOval((int)x, (int)y, width, height);
		g.setColor(Color.RED);
		g.fillRect((int)(boundingBox.x),
				(int)(boundingBox.y),
				boundingBox.width, boundingBox.height);
		
	}

}
