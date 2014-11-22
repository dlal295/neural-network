package ANN;

import java.util.ArrayList;

public class NeuronLayer {
	
	
	int numNeurons;
	ArrayList<Neuron> neurons;
	
	public NeuronLayer(int _numNeurons, int numInputs){
		neurons = new ArrayList<Neuron>();
		numNeurons = _numNeurons;
		for (int i = 0; i < numNeurons; i++)
			addNeuron(new Neuron(numInputs));
	}
	
	public void addNeuron(Neuron neuron){
		neurons.add(neuron);
	}
}
