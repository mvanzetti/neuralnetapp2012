package com.app;

import java.util.Random;

import com.net.consts.Bipolar;
import com.net.impl.BipolarPattern;
import com.net.simulation.UncorrelatedHopfieldSimulation;
import com.rand.UniformDeviates;

public class TestMemoryApp {

	public static void main(String[] args ) {
		
		Random rndGen = new UniformDeviates();
				
		// memory patterns
		Bipolar[] b1 = 
			{
				Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
				Bipolar.Dw, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Dw,
				Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
				Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
				Bipolar.Dw, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Dw
			};
		
		Bipolar[] b2 = 
			{
				Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up,
				Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
				Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up,
				Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
				Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up,
			};
		
		Bipolar[] b3 = 
			{
				Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
				Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up,
				Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
				Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up,
				Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
			};
		
		// test patterns
		Bipolar[] t1 = 
			{
				Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up,
				Bipolar.Up, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Up,
				Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up,
				Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up,
				Bipolar.Up, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Up
			};
		
		Bipolar[] t2 =
			{
				Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
				Bipolar.Dw, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Dw,
				Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
				Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
				Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw
			};
		
		String filePathTest = "output/test.txt";
		
		UncorrelatedHopfieldSimulation hopfieldTest = 
				new UncorrelatedHopfieldSimulation(
						filePathTest, 
						rndGen, 
						25, 
						0.138, 
						0.20);
		
		hopfieldTest.AddBasePattern(new BipolarPattern(b1));
		hopfieldTest.AddBasePattern(new BipolarPattern(b2));
		hopfieldTest.AddBasePattern(new BipolarPattern(b3));
		
		hopfieldTest.AddTestPattern(new BipolarPattern(t1));
		hopfieldTest.AddTestPattern(new BipolarPattern(t2));
		
		hopfieldTest.SetOutputFrame(5, 5);
		hopfieldTest.Launch();
		
	}
	
}
