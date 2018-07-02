package com.math.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.math.IMatrix;

public class DoubleMatrix implements IMatrix<Double> {

	public static DoubleMatrix CreateColumnMatrix(Double input[]) {
		Double result[][] = new Double[input.length][1];
		for (int r = 0; r < result.length; r++) {
			result[r][0] = input[r];
		}
		return new DoubleMatrix(result);
	}

	public static DoubleMatrix CreateRowMatrix(Double input[]) {
		Double result[][] = new Double[1][input.length];
		System.arraycopy(input, 0, result[0], 0, input.length);
		return new DoubleMatrix(result);
	}
	
	public static DoubleMatrix ColumnToMatrix(Double column[]) {
		
		DoubleMatrix a = CreateColumnMatrix(column);
		DoubleMatrix b = (DoubleMatrix) MatrixHelper.Transpose(a);
		
		return (DoubleMatrix) MatrixHelper.Multiply(a, b);
	}
	
	private Double _matrixSource[][];
	
	public DoubleMatrix(Double matrixSource[][]) {
		
		_matrixSource = new Double[matrixSource.length][matrixSource[0].length];
		Clear();

		for (int r = 0; r < GetNumberOfRows(); r++) {
			for (int c = 0; c < GetNumberOfColumns(); c++) {
				this.SetElement(r, c, matrixSource[r][c]);
			}
		}
		
	}
	
	public DoubleMatrix(int numberOfRows, int numberOfColumns) {
		
		_matrixSource = new Double[numberOfRows][numberOfColumns];
		Clear();
	}
	
	@Override
	public void Clear() {
		for(int r = 0; r < GetNumberOfRows(); r++) {
			for(int c = 0; c < GetNumberOfColumns(); c++) {
				SetElement(r, c, 0.);
			}
		}
	}
	
	@Override
	public int GetNumberOfColumns() {
		return _matrixSource[0].length;
	}
	
	@Override
	public int GetNumberOfRows() {
		return _matrixSource.length;
	}
	
	@Override
	public Double GetElement(int rowIndex, int columnIndex) {
		return _matrixSource[rowIndex][columnIndex];
	}
	
	@Override
	public IMatrix<Double> GetColumn(int columnIndex) {
		
		Double column[][] = new Double[GetNumberOfRows()][1];

		for (int r = 0; r < GetNumberOfRows(); r++) {
			column[r][0] = _matrixSource[r][columnIndex];
		}

		return new DoubleMatrix(column);
	}
	
	@Override
	public IMatrix<Double> GetRow(int rowIndex) {
		
		Double row[][] = new Double[1][GetNumberOfColumns()];

		for (int c = 0; c < GetNumberOfColumns(); c++) {
			row[0][c] = _matrixSource[rowIndex][c];
		}

		return new DoubleMatrix(row);
	}
	
	@Override
	public List<Double> ToArrayList() {
		Double result[] = new Double[GetNumberOfRows() * GetNumberOfColumns()];

		int index = 0;
		for (int r = 0; r < GetNumberOfRows(); r++) {
			for (int c = 0; c < GetNumberOfColumns(); c++) {
				result[index++] = _matrixSource[r][c];
			}
		}

		return new ArrayList<Double>(Arrays.asList(result));
	}
	
	@Override
	public boolean IsZero() {

		for(int r = 0; r < GetNumberOfRows(); r++) {
			for(int c = 0; c < GetNumberOfColumns(); c++) {
				
				if(GetElement(r, c) != 0.)
					return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean IsVector() {
		if (GetNumberOfRows() == 1) {
			return true;
		} else {
			return GetNumberOfColumns() == 1;
		}
	}
	
	@Override
	public boolean IsSquare() {
		return GetNumberOfColumns() == GetNumberOfRows() ? true : false;
	}
	
	@Override
	public boolean IsValidElement(int rowIndex, int columnIndex) {
		
		if ((rowIndex >= GetNumberOfRows()) || (rowIndex < 0)) {
			throw new RuntimeException("The row:" + rowIndex + " is out of range:" + GetNumberOfRows());
		}

		if ((columnIndex >= GetNumberOfColumns()) || (columnIndex < 0)) {
			throw new RuntimeException("The col:" + columnIndex + " is out of range:" + GetNumberOfColumns());
		}
		
		return true;
	}

	@Override
	public void SetElement(int rowIndex, int columnIndex, Double value) {

		if(IsValidElement(rowIndex, columnIndex))
			_matrixSource[rowIndex][columnIndex] = value;
		
	}
	@Override
	public void SetColumn(int columnIndex, Double[] value) {
		
		if(value.length != GetNumberOfRows())
			throw new RuntimeException("Input column is out of range");
		
		for (int r = 0; r < GetNumberOfRows(); r++) {
			_matrixSource[r][columnIndex] = value[r];
		}
	}
	@Override
	public void SetRow(int rowIndex, Double[] value) {
		
		if(value.length != GetNumberOfColumns())
			throw new RuntimeException("Input row is out of range");
		
		for (int c = 0; c < GetNumberOfColumns(); c++) {
			_matrixSource[rowIndex][c] = value[c];
		}
	}

	@Override
	public void SetDiagonal(Double value) {
		
		if(!IsSquare())
			throw new UnsupportedOperationException(
					"Impossible to set elements in diagonal: the matrix is not square");
		
		for(int d = 0; d < _matrixSource.length; d++) {
			_matrixSource[d][d] = value;
		}
		
	}


}
