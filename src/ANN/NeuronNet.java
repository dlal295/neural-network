package ANN;

import java.util.ArrayList;
import java.util.LinkedList;

import org.lwjgl.input.Mouse;

import Display.Screen;

public class NeuronNet {

	private static final double BIAS = 1;
	private static final double ACTIVATION_RESPONSE = 1;
	
	
	
	
	int numInputs = 2;
	int numOutputs = 2;
	int numHiddenLayers = 3;
	int numNeuronsPerLayer = 3;
	ArrayList<NeuronLayer> layers;
	
	
	public NeuronNet(){
		layers = new ArrayList<NeuronLayer>();
		for (int i = 0; i < numHiddenLayers; i++){
			layers.add(new NeuronLayer(numNeuronsPerLayer, numInputs));
		}
	}
	
	
	public ArrayList<Double> update(ArrayList<Double> inputs){
		
		
		
		
		
		ArrayList<Double> outputs = new ArrayList<Double>();
		
		if (1 != 1){
			outputs.add(Mouse.getX()/(Screen.width*1.0)-0.5);
			outputs.add(-(Mouse.getY()/(Screen.height*1.0)-0.5));
			return outputs;
		}
		if (inputs.size() != numInputs){
			System.out.println("PSYCH!");
			return outputs;
		}
		
		int inputIndex = 0;
		for (int i = 0; i < numHiddenLayers; i++){
			if (i > 0)
				inputs.addAll(outputs);
			outputs.clear();
			
			for (int j = 0; j < layers.get(i).numNeurons; j++){
				double netInput = 0;
				
				Neuron neuron = layers.get(i).neurons.get(j);
				int numInputs = neuron.numInputs;
				for (int k = 0; k < numInputs-1; k++){
					
					netInput += neuron.weights.get(k)*inputs.get(inputIndex++);
				}
				//System.out.println(numInputs);
				netInput += neuron.weights.get(numInputs-1)*BIAS;
				outputs.add(sigmoid(netInput,ACTIVATION_RESPONSE));
				inputIndex = 0;
			}
			
		}
		
		
		return outputs;
	}
	
	public LinkedList<Double> getWeights(){
		LinkedList<Double> weights = new LinkedList<Double>();
		for (NeuronLayer layer : layers)
			for (Neuron neuron : layer.neurons)
				for (Double weight : neuron.weights)
					weights.add(new Double(weight));
		
		return weights;
	}
	
	public void setWeights(LinkedList<Double> weights){
		for (NeuronLayer layer : layers){
			for (Neuron neuron : layer.neurons){
				neuron.weights.clear();
				for (int i = 0; i < neuron.numInputs; i++){
					neuron.weights.add(weights.pop());
				}
			}
		}
				
				
		
	}
	
	
	public static double sigmoid(double activation, double response){
		return 1/(1+Math.pow(Math.E,-activation/response));
	}
	
	
}
