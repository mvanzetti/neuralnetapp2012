package com.math;

import java.util.List;

public interface IMatrix<T> {
	
	public void Clear();
	public int GetNumberOfColumns();
	public int GetNumberOfRows();
	
	public T GetElement(int rowIndex, int columnIndex);
	public IMatrix<T> GetColumn(int columnIndex);
	public IMatrix<T> GetRow(int rowIndex);
	
	public List<T> ToArrayList();
	
	public boolean IsZero();
	public boolean IsVector();
	public boolean IsSquare();
	public boolean IsValidElement(int rowIndex, int columnIndex);
	
	public void SetElement(int rowIndex, int columnIndex, T value);
	public void SetColumn(int columnIndex, T value[]);
	public void SetRow(int rowIndex, T value[]);
	public void SetDiagonal(T value);
		
}
