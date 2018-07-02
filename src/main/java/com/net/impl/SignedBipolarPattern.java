package com.net.impl;

import java.util.ArrayList;
import java.util.List;

import com.net.consts.Bipolar;
import com.net.utils.BipolarHelper;

public class SignedBipolarPattern extends Pattern<Bipolar>{

	public SignedBipolarPattern(int size) {
		super(size);
	}

	public SignedBipolarPattern(List<Bipolar> source) {
		super(source);
	}
	
	public SignedBipolarPattern(Bipolar[] source) {
		super(source);
	}
	
	public Bipolar GetSign() {
		return super.ToList().get(0);
	}
	
	public SignedBipolarPattern Switch() {
		
		List<Bipolar> source = super.ToList();
		List<Bipolar> ret = new ArrayList<Bipolar>();
		
		for(Bipolar p : source) {
			ret.add(BipolarHelper.Switch(p));
		}
		
		return new SignedBipolarPattern(ret);
	}
	
	public double CompareWith(SignedBipolarPattern comparePattern) {
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
