package World;


import org.newdawn.slick.Color;

import Display.Screen;
import Utils.Vec;

public class Food {
	
	Vec pos;
	double radius = 5;
	
	public Food(double _x, double _y){
		setPos(_x,_y);
	}
	
	public void setPos(double _x, double _y){
		pos = new Vec(_x,_y);
	}
	
	public void render(){
		Screen.setColor(Color.orange);
		Screen.fillCircle(pos.x, pos.y, radius);
	}
	
	
}
