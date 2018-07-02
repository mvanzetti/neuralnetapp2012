package com.net.impl;

import java.util.ArrayList;
import java.util.List;

import com.net.IPattern;

public class Pattern<T> implements IPattern<T> {

	private int _size;
	private List<T> _pattern;
	
	public Pattern(int size) {
		_size = size;
		_pattern = new ArrayList<T>(size);
	}
	
	public Pattern(T source[]) {
		_size = source.length;
		_pattern = new ArrayList<T>();
		
		for(T el : source) {
			_pattern.add(el);
		}
	}
	
	public Pattern(List<T> source) {
		_size = source.size();
		_pattern = new ArrayList<T>(source);
	}
	
	@Override
	public int GetSize() {
		return _size;
	}
	
	@Override
	public List<T> ToList() {
		return _pattern;
	}
	
	
	@Override
	public T GetElement(int index) {
		if(index > _size)
			throw new NullPointerException("Element of index " + index + " doesn't exist");
		
		return _pattern.get(index);
	}
	
	@Override
	public void SetElement(int index, T element) {
		if(index > _size)
			throw new NullPointerException("Index " + index + " is out of bound");
		
		_pattern.set(index, element);
	}
	
	@Override
	public void Clear() {
		_size = 0;
		_pattern = new ArrayList<T>();
	}
	
	public String ToString() {
		
		String str = "{";
		
		for(T el : _pattern) {
			str += el + ", ";
		}
		
		str += "}";
		
		return str;
		
	}
}
