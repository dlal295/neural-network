import java.util.*;

import World.*;
import ANN.*;
import GenAlg.GenAlg;
import GenAlg.Genome;


public class Controller {

	public static final int NUM_TICKS = 1000;
	int ticks;
	int numEntities;
	ArrayList<Entity> entities;
	World world;
	int generationCount;
	GenAlg genAlg;
	boolean displayWorld = true;
	
	public Controller(int popSize, World _world){
		genAlg = new GenAlg(popSize, 0.015, 0.7);
		world = _world;
	}
	
	
	public void epoch(ArrayList<Entity> entities){
		ArrayList<Genome> genomes = new ArrayList<Genome>();
		for (Entity entity : entities)
			genomes.add(entity.getGenome());
		genAlg.epoch(genomes);
		for (int i = 0; i < entities.size(); i++)
			entities.get(i).setGenome(genomes.get(i));
	}
	
	public boolean update(){
		if (ticks++ < NUM_TICKS){
			world.update();
			if (displayWorld)
				world.render();
		}
		else {
			generationCount++;
			System.out.println("GENERATION: " + generationCount);
			ticks = 0;
			epoch(world.critters);
			world.reset();
		}
		return false;
	}
}
