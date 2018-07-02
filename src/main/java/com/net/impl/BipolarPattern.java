package com.net.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.net.consts.Bipolar;
import com.net.utils.BipolarHelper;

public class BipolarPattern extends Pattern<Bipolar>{

	public BipolarPattern(int size) {
		super(size);
	}

	public BipolarPattern(List<Bipolar> source) {
		super(source);
	}
	
	public BipolarPattern(Bipolar[] source) {
		super(source);
	}
		
	public BipolarPattern GetNoisyPattern(Random generator, double noise) {
			
		List<Bipolar> noisyPattern = new ArrayList<Bipolar>(super.GetSize());
		List<Bipolar> source = super.ToList();
		
		for(Bipolar el : source) {
			if(generator.nextDouble() < noise) {
				noisyPattern.add(BipolarHelper.Switch(el));
			} 
			else {
				noisyPattern.add(el);
			}
		}
		
		return new BipolarPattern(noisyPattern);
		
	}
		
	public double CompareWith(BipolarPattern comparePattern) {
		double countError = 0.;
		
		for(int i = 0; i < this.GetSize(); i ++) {
			if(comparePattern.GetElement(i) != this.GetElement(i))
			{
				countError++;
			}
		}
		
		countError /= (double) this.GetSize();

		return countError;
	}
}
