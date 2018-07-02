package com.net.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.net.IPattern;
import com.net.consts.Bipolar;

public class BipolarRandomPattern implements IPattern<Bipolar> {

	private int _size;
	private List<Bipolar> _pattern;

	public BipolarRandomPattern(Random generator, int size) {
		
		_size = size;
		_pattern = new ArrayList<Bipolar>(size);
		
		for(int i = 0; i < size; i++) {
			
			// for gaussian distribution generator.nextGaussian()
			Boolean rand = generator.nextBoolean();

			if(rand)
				_pattern.add(i, Bipolar.Up);
			else 
				_pattern.add(i, Bipolar.Dw);
			}
	}
	
	@Override
	public int GetSize() {
		return _size;
	}
	
	@Override
	public List<Bipolar> ToList() {
		return _pattern;
	}
	
	@Override
	public Bipolar GetElement(int index) {
		if(index > _size)
			throw new NullPointerException("Element of index " + " doesn't exist");
		
		return _pattern.get(index);
	}
	
	@Override
	public void SetElement(int index, Bipolar element) {
		if(index > _size)
			throw new NullPointerException("Index " + " is out of bound");
		
		_pattern.set(index, element);
	}
	
	@Override
	public void Clear() {
		_size = 0;
		_pattern = new ArrayList<Bipolar>();
	}
	
	public String ToString() {
		
		String str = "{";
		
		for(Bipolar el : _pattern) {
			str += el + ", ";
		}
		
		str += "}";
		
		return str;
		
	}

}
