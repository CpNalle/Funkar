package funkar;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;



public class client implements Runnable
{
	double x,y;
	private Window window;
	private static int width;
	private static int height;
	public String title;
	
	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;
	int p;
	//Input
	private keyManager keyManager;
	public static Paddle paddle;
	public static Paddle paddle2;
	public static Ball ball;
	
	public static ArrayList<Shape> poop;
	
	public client(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new keyManager();
		paddle = new Paddle(this, 5, height/2 -75/2,true);
		//paddle2 = new Paddle(this, width-15, height/2 -75/2, false);
		paddle2 = new Paddle(this, width-15, 0 -75/2, false);
		ball = new Ball(this, width, height);
		poop = new ArrayList<Shape>();
		
			
		
	}
	
	private void init(){
		window = new Window(title, width, height);
		window.getFrame().addKeyListener(keyManager);
		poop.add(ball);
		poop.add(paddle);
		poop.add(paddle2);
	}
	
	private void tick(){
		keyManager.tick();
		
		
		
		for(Shape s : poop){
			s.tick();
			if(s!= ball){
				if(ball.boundingBox.intersects(s.boundingBox)){
					ball.bounceHorizontal();
				}
			}
		}
		

	}
	
	private void render(){
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null){
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		for(Shape s : poop){
			s.render(g);
		}
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				
				
				
				
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
		//		System.out.println(ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
		
	}
	
	
	public keyManager getKeyManager(){
		return keyManager;
	}
	
	public static int getWidth(){
		return width;
	}
	public static int getHeight(){
		return height;
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static ArrayList<Shape> getShapes() {
		return poop;
	}

	public static void main(String[] args){
		
		client game = new client("Värdelös Pong", 400, 200);
		game.start();
		
		
		

		try { 
				Socket clientSocket = new Socket("172.16.0.1", 8000);
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				while(true)
				{
					out.println(paddle.getBoundingBox().y);
					String userInput = in.readLine();
					int yPos = Integer.parseInt(userInput);
					System.out.println(userInput);
					paddle2.boundingBox.y=yPos;
				}
		} catch (Exception e) {
			System.out.println("Clientside error");
		}
	}
}
