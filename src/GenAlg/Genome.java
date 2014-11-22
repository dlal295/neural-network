package GenAlg;
import java.util.*;

import ANN.NeuronNet;


public class Genome implements Comparable{
	public static int GenomeIDCount = 0;
	public static double maxFitness = 0;
	NeuronNet network;
	double fitness;
	int genomeID;
	
	public Genome(){
		fitness = 0;
		genomeID = GenomeIDCount;
		GenomeIDCount++;
		network = new NeuronNet();
	}
	
	public Genome(NeuronNet network, double f){
		this.network = network;
		fitness = f;
	}
	
	
	public int compareTo(Object o) {
		Genome other = (Genome) o;
		return Double.compare(fitness, other.fitness);
	}

	public void increaseFitness() {
		fitness++;
		if (fitness > maxFitness)
		{
			maxFitness = fitness;
			System.out.println(maxFitness);
		}
	}

	public NeuronNet getNetwork() {
		return network;
	}
	
}
