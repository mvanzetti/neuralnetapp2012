package com.net;

import java.util.List;

public interface IPattern<T> {

	public int GetSize();
	public List<T> ToList();
	public T GetElement(int index);
	
	public void SetElement(int index, T element);
	
	public void Clear();
}
