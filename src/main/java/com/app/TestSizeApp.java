package com.app;

import java.util.Random;

import com.net.simulation.HopfieldSizeSimulation;
import com.rand.UniformDeviates;

public class TestSizeApp {

	public static void main(String[] args) {
		
		Random rndGen = new UniformDeviates();
		
		String filePath = "output/test_size.txt";
		
		HopfieldSizeSimulation hopfieldUncorrelated = 
				new HopfieldSizeSimulation(
						filePath, 
						rndGen, 
						25,
						200,
						0.100, 
						0.20);

		hopfieldUncorrelated.Launch();
	}

}
