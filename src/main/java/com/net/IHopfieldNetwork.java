package com.net;

import com.math.IMatrix;

public interface IHopfieldNetwork<T> {

	public int GetSize();
	public double GetStorageCapacity();
	public int GetPatternLimit();
	public IMatrix<Double> GetWeightMatrix();
	
	public void Memorize(IPattern<T> pattern);
	
	public IPattern<T> SynchronousUpdate(IPattern<T> pattern);
	public IPattern<T> AsynchronousUpdate(IPattern<T> pattern);
}
