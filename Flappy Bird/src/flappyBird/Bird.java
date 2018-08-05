package flappyBird;

import java.awt.Color;
import java.awt.Graphics;

public class Bird{
	
	private static final long serialVersionUID = 1L;
	protected int x,y;
	protected int velocity,acceleration =0;
	
	public Bird() {	
		this.x = 20;
		this.y = 640/9;
		this.velocity = 1;
	}
	
	/*public abstract void tick();
	public abstract void render(Graphics g);*/
	
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int a) {
		this.y = a;
	}
	public void setX(int a) {
		this.x = a;
	}	
	public int getVel() {
		return this.velocity;
	}
	void updatePos() {
		this.y = this.y + this.velocity;
		this.velocity ++;
	}
	void spacePressed(int a) {
		this.velocity = -12;
	}
	
	
	

	
}
