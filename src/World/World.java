package World;
import java.awt.Dimension;
import java.util.*;

import org.newdawn.slick.Color;

import Utils.Vec;
import ANN.NeuronNet;

public class World {

	public HashSet<Food> foods;
	public ArrayList<Entity> critters;
	int maxFood = 50;
	int maxCritters;
	Dimension size;
	
	
	public World(int sizeX, int sizeY, int popSize){
		size = new Dimension(sizeX, sizeY);
		foods = new HashSet<Food>();
		maxCritters = popSize;
		critters = new ArrayList<Entity>();
		
		for (int i = 0; i < maxFood; i++)
			genFood();

		for (int i = 0; i < maxCritters; i++)
			critters.add(new Entity(Math.random()*size.width,Math.random()*size.height,size));
		critters.get(0).setColor(Color.red);
	}
	
	
	
	public void genFood(){
		foods.add(new Food(Math.random()*size.width,Math.random()*size.height));
	}


	public void reset() {
		for (Food food : foods)
			food.setPos(Math.random()*size.width,Math.random()*size.height);
		for (Entity critter : critters)
			critter.setPos(Math.random()*size.width,Math.random()*size.height);
		
	}



	public void update() {
		for (Entity critter : critters){
			Food closestFood = null;
			Vec target = null;
			double lowestLength = Integer.MAX_VALUE;
			if (true || critter.targetFood == null || !foods.contains(critter.targetFood)){
				for (Food food : foods){
					Vec dif = food.pos.sub(critter.pos);
					if (dif.lengthSquared() < lowestLength)
					{
						target = dif;
						closestFood = food;
						lowestLength = dif.lengthSquared();
					}
					
				}

				target.normalize();
				critter.setTarget(closestFood, target);
			}
			
			critter.update();
			if (critters.get(0) == critter){
				//System.out.println("Distance: " + Math.sqrt(((critter.targetFood.pos.sub(critter.pos)).lengthSquared())));
				//System.out.println("Total Size: " + Math.sqrt((critter.radius + critter.targetFood.radius)*(critter.radius + critter.targetFood.radius)));
			}
			boolean foodFound = ((critter.targetFood.pos.sub(critter.pos)).lengthSquared()) < (critter.radius + critter.targetFood.radius)*(critter.radius + critter.targetFood.radius);
			if (foodFound){
				critter.getGenome().increaseFitness();
				foods.remove(critter.targetFood);
				genFood();
			}
			
		}
		
	}



	public void render() {
		
		for (Food food : foods)
			food.render();
		for (Entity critter : critters)
			critter.render();
		
	}
}
