package com.net.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.net.IPattern;
import com.net.IRandomPatternFactory;
import com.net.consts.Bipolar;

public class RandomPatternFactory implements IRandomPatternFactory {

	@Override
	public IPattern<Bipolar> UniformBipolarPattern(Random generator, int size) {
		
		List<Bipolar> pattern = new ArrayList<Bipolar>();
		
		for(int i = 0; i < size; i++) {
			
			// for gaussian distribution generator.nextGaussian()
			Boolean rand = generator.nextBoolean();

			if(rand)
				pattern.add(Bipolar.Up);
			else 
				pattern.add(Bipolar.Dw);
		}
		
		return new BipolarPattern(pattern);
	}
	
	@Override
	public IPattern<Boolean> CreateBooleanPattern(Random generator, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPattern<Double> CreateDoublePattern(Random generator, int size) {
		// TODO Auto-generated method stub
		return null;
	}


}
