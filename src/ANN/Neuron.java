package ANN;
import java.util.*;

public class Neuron {
	
	int numInputs;
	ArrayList<Double> weights;
	
	public Neuron(int inputs){
		numInputs = inputs+1;
		weights = new ArrayList<Double>();
		
		for (int i = 0; i < numInputs ; i++){
			
			weights.add(Math.random()-0.5);
			
		}
	}
	
	
}
