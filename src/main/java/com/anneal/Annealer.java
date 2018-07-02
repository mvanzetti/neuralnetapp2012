package com.anneal;

import java.util.Random;

public class Annealer {
	
	private Random _gen;
	
	private double _coolingFactor = 0.999;
	private double _initialTemperature = 400.;
	private double _minTemperature = 0.001;
	
	private double[] _state;
	private double _energy;
	
	public Annealer(double[] initialState){
		
		_gen = new Random();
		
		_state = initialState;
		_energy = Energy(initialState);
	}
	
	public Annealer(double coolingFactor, double initialTemperature, double minTemperature) {
		_coolingFactor = coolingFactor;
		_initialTemperature = initialTemperature;
		_minTemperature = minTemperature;
	}
	
	// simulation of energy function
	private double Energy(double[] state) {
		double sum = 0;
		
		for(double s : state) {
			sum += s;
		}
		return Math.abs(sum) * _gen.nextDouble();
	}
	
	public double[] GetBest() {
		
		double[] bestState = _state;
		double bestEnergy = _energy;
		
		double temperature = _initialTemperature;
		
		while(temperature > _minTemperature) {
			
			double[] newState = NextState(bestState);
			double newEnergy = Energy(newState);
			
			if(AcceptanceProbability(_energy, newEnergy, temperature) > _gen.nextDouble()) {
				_state = newState;
				_energy = newEnergy;
			}
			
			if(_energy < bestEnergy) {
				bestState = newState;
				bestEnergy = newEnergy;
				
				System.out.println(bestEnergy);
			}
			
			temperature *= _coolingFactor;
		}
		
		return bestState;
	}
	
	private double AcceptanceProbability(double oldEnergy, double newEnergy, double temperature) {
		
		if(newEnergy < oldEnergy){
			return 1.;
			} else {
				return Math.exp((oldEnergy - newEnergy)/temperature);
			}
			
	}
	
	private double[] NextState(double[] state) {
		
		double[] next = new double[state.length];
		
		for(int i = 0; i < state.length; i++) {
			next[i] = _gen.nextDouble();
		}
		
		return next;
	}


}
