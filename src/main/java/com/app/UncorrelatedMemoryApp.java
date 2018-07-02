package com.app;

import java.util.Random;

import com.net.simulation.UncorrelatedHopfieldSimulation;
import com.rand.UniformDeviates;

public class UncorrelatedMemoryApp {

	public static void main(String[] args) {
		
		Random rndGen = new UniformDeviates();
		
		String filePath = "output/uncorrelated.txt";
		
		UncorrelatedHopfieldSimulation hopfieldUncorrelated = 
				new UncorrelatedHopfieldSimulation(
						filePath, 
						rndGen, 
						5*5,
						0.138, 
						0.20);
		
		hopfieldUncorrelated.SetOutputFrame(5, 5);
		hopfieldUncorrelated.Launch();

	}

}
