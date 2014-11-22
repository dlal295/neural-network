package World;

import java.awt.Dimension;
import java.util.ArrayList;

import org.newdawn.slick.Color;

import Utils.Vec;
import ANN.NeuronNet;
import Display.Screen;
import GenAlg.Genome;

public class Entity {
	
	Vec pos;
	double radius = 10;
	Genome genome;
	Dimension bounds;
	Food targetFood;
	Vec targetDir;
	Color color;
	double speed = 10;

	public Entity(double _x, double _y, Dimension _bounds) {

		setPos(_x,_y);
		bounds = _bounds;
		genome = new Genome();
		targetFood = null;
		targetDir = null;
		color = Color.green;
	}
	
	public void setColor(Color col){
		color = col;
	}
	
	public void setTarget(Food food, Vec dir){
		targetFood = food;
		targetDir = dir;
	}
	
	
	public void update(){
		ArrayList<Double> inputs = new ArrayList<Double>();
		inputs.add(targetDir.x);
		inputs.add(targetDir.y);
		/*for (Entity critter : critters){
			if (critter != this)
			{
				inputs.add(critter.pos.x);
				inputs.add(critter.pos.y);
			}
		}*/
		ArrayList<Double> outputs = getBrain().update(inputs);
		
		move((outputs.get(0)-0.5)*speed,(outputs.get(1)-0.5)*speed);
		
		
	}
	
	
	
	public void move(double dx, double dy){
		if (pos.x+dx < bounds.width && pos.x+dx > 0)
			pos.x += dx;
		if (pos.y+dy < bounds.height && pos.y+dy > 0)
			pos.y += dy;
	}
	
	
	public void render(){
		Screen.setColor(color);
		Screen.fillCircle(pos.x, pos.y, radius);
	}

	public NeuronNet getBrain() {
		return genome.getNetwork();
	}

	public boolean foundFood() {
		return false;
	}

	public Genome getGenome() {
		return genome;
	}

	public void setPos(double _x, double _y){
		pos = new Vec(_x,_y);
	}

	public void setGenome(Genome _genome) {
		genome = _genome;
	}

}
