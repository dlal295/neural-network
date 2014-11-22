
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

import World.World;
import Display.Screen;


public class Main {

    public static int MAX_FPS = 60;
    static Screen screen;
    private boolean end;
    Controller control;

    static int popSize = 10;
    
    
    protected Main(int width, int height) {
        screen = new Screen(width, height);
        screen.setBGColor(Color.white);
		init();
		start();
    }
    
    public void init(){
    	World world = new World(screen.width,screen.height,popSize);
    	control = new Controller(popSize,world);
    }

    protected void setFPS(int fps) {
        MAX_FPS = fps;
    }

    protected void start() {

        while (!Display.isCloseRequested() && !end) {
            Screen.render();
            update();
            Display.update();
            Display.sync(MAX_FPS);
        }


        // Release the resources of the wood texture
        Display.destroy();
        System.exit(0);
    }

    protected void end() {
        end = true;
    }

	public void update(){
		control.update();
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN))
			MAX_FPS = 2000;
		else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			MAX_FPS = 60;
		//System.out.println(Mouse.getX() + ", " + (Screen.height - Mouse.getY()));
	}
	
	
	public static void main(String[] args) {
		Main main = new Main(640,480);
	}

}

