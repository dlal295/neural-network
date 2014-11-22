package GenAlg;
import java.util.*;

import World.Entity;

public class GenAlg {

	public static final int NUM_COPIES_ELITE = 1;
	public static final int NUM_ELITE = 1;
	public static final int tournamentSize = 5;
	
	ArrayList<Genome> population;
	int popSize;
	double totalFitness;
	double bestFitness;
	double avgFitness;
	double worstFitness;
	int fittestGenome;
	double mutRate = 0.015;
	double crossRate = 0.7;
	int genCount;
	
	public GenAlg(int popSize, double mutRate, double crossRate){
		this.popSize = popSize;
		this.mutRate = mutRate;
		this.crossRate = crossRate;
	}
	
	public void crossover(LinkedList<Double> mum, LinkedList<Double> dad, LinkedList<Double> baby1, LinkedList<Double> baby2){
		if (Math.random() > crossRate || mum == dad){
			baby1.addAll(mum);
			baby2.addAll(dad);
			return;
		}
		int totalWeights = mum.size();
		int divider = (int) (Math.random()*totalWeights);
		
		for (int i = 0; i < totalWeights; i++){
			if (i < divider){
				baby1.add(mum.get(i));
				baby2.add(dad.get(i));
			} else {
				baby1.add(dad.get(i));
				baby2.add(mum.get(i));
			}
		}
		
		
	}
	
	public void mutate(LinkedList<Double> baby){
		ListIterator iter = baby.listIterator();
		while (iter.hasNext())
		{
			iter.next();
			if (Math.random() < mutRate)
			{
				iter.set(Math.random());
			}
		}
		
		
	}
	
	public Genome getChromoRoulette(ArrayList<Genome> genomes){
		
		Genome fittest = null;
		for (int i = 0; i < tournamentSize; i++){
			int index = (int) (Math.random()*genomes.size());
			if (fittest == null || genomes.get(index).fitness > fittest.fitness)
				fittest = genomes.get(index);
		}
		return fittest;
		
	}
	
	public void grabNBest(int nBest, int numCopies, ArrayList<Genome> nextGen){
		
	}
	
	public void reset(){
	
	}
	
	
	public ArrayList<Genome> getChromos(){
		return population;
	}
	
	public void epoch(ArrayList<Genome> genomes){
		ArrayList<Genome> oldPop = genomes;
		ArrayList<Genome> nextGen = new ArrayList<Genome>();
		
		if (NUM_ELITE > 0){
			grabNBest(NUM_ELITE,NUM_COPIES_ELITE,nextGen);
		}
		
		while (nextGen.size() < popSize){
			Genome mum = getChromoRoulette(genomes);
			Genome dad = getChromoRoulette(genomes);
			Genome baby1 = new Genome();
			Genome baby2 = new Genome();
			
			LinkedList<Double> mumWeights = mum.getNetwork().getWeights();
			LinkedList<Double> dadWeights = dad.getNetwork().getWeights();
			
			LinkedList<Double> baby1Weights = new LinkedList<Double>();
			LinkedList<Double> baby2Weights = new LinkedList<Double>();
			
			crossover(mumWeights,dadWeights,baby1Weights,baby2Weights);
			

			mutate(baby1Weights);
			mutate(baby2Weights);
			
			
			baby1.network.setWeights(baby1Weights);
			baby2.network.setWeights(baby2Weights);
			nextGen.add(baby1);
			nextGen.add(baby2);
			
			
			
			
			
		}
		
		genomes.clear();
		genomes.addAll(nextGen);
		
		genCount++;
		
	}
	

	public double averageFitness(){
		return totalFitness/popSize;
	}
	
	public double bestFitness(){
		return bestFitness;
	}

	
	
	
	
}
