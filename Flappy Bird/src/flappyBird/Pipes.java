package flappyBird;
import java.util.concurrent.ThreadLocalRandom;


public class Pipes {

	int width = 20;
	int height,xPos;
	
	public Pipes() {
		int randomNum = ThreadLocalRandom.current().nextInt(0,380);
		this.height = randomNum;
		this.xPos = 640;
	}
	public void move(int a) {
		this.xPos = this.xPos - a;
	}
}
