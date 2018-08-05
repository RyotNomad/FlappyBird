package flappyBird;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Scanner;
import flappyBird.Bird;
import flappyBird.Pipes;

public class FlappyBird extends Canvas implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7642046876350889793L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH/12*9;
	private Thread thread;
	private boolean running = true;
	Bird bird = new Bird();
	Pipes pipe = new Pipes();
	ArrayList<Pipes> pipes = new ArrayList<Pipes>();
	int gameSpeed = 5;
	boolean buttonPressed = false;
	int count = 0;
	
	
	public FlappyBird() {
		new Window(WIDTH, HEIGHT, "Main",this);
		bird.setX(20);
		bird.setY(640/9);	
		for(int i=0;i<4;i++) {
			Pipes temp = new Pipes();
			pipes.add(temp);
		}
		addKeyListener(this);
	}
	
	public static void main(String[] args) {
		new FlappyBird();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			//System.out.println(now);
			lastTime = now;
			while(delta >= 1) {
				count++;
				tick();
				
			
				pipes.get(0).move(gameSpeed);
				if(pipes.get(1).xPos - pipes.get(0).xPos > 280) {
					pipes.get(1).move(gameSpeed);
				}
				if(pipes.get(2).xPos - pipes.get(1).xPos > 280) {
					pipes.get(2).move(gameSpeed);
				}
				if(pipes.get(3).xPos - pipes.get(2).xPos > 280) {
					pipes.get(3).move(gameSpeed);
				}
				//Make sure to delete passed pipes
				for(int i=0;i<pipes.size();i++) {
					Pipes temp = pipes.get(i);
					if(temp.xPos < 0) {
						pipes.remove(i);
						//Add new pipe to replace old
						Pipes newPipe = new Pipes();
						pipes.add(newPipe);
					}
				}
				delta--;
			}
			//For single lines in if statement, {} can be ommitted!!!
			if(running) {				
				render();
				}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		bird.updatePos();
		if(buttonPressed) {
			buttonPressed = false;
			bird.spacePressed(5);
		}
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == 32) {
			System.out.println("SPACE");
			buttonPressed = true;
		}
	}
	
	private void render() {		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.blue);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.white);
		g.drawOval(bird.getX(), bird.getY(), 20, 20);
		g.setColor(Color.black);
		for(int i=0;i<pipes.size();i++) {
				Pipes temp = pipes.get(i);
				g.drawRect(temp.xPos, 0, 60, temp.height);
				g.drawRect(temp.xPos, temp.height+100, 60, 480);
		}
		
		
		g.dispose();
		bs.show();
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
