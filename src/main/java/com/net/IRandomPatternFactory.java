package com.net;

import java.util.Random;

import com.net.consts.Bipolar;

public interface IRandomPatternFactory {
	
	public IPattern<Bipolar> UniformBipolarPattern(Random generator, int size);
	
	public IPattern<Boolean> CreateBooleanPattern(Random generator, int size);
	public IPattern<Double> CreateDoublePattern(Random generator, int size);
	

}
