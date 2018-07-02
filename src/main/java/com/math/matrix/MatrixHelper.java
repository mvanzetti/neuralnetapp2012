package com.math.matrix;

import java.util.List;

import com.math.IMatrix;

public class MatrixHelper {
	
	public static IMatrix<Double> Add(IMatrix<Double> a, IMatrix<Double> b) {
			
		if(a.GetNumberOfRows() != b.GetNumberOfRows() || a.GetNumberOfColumns() != b.GetNumberOfColumns())
			throw new RuntimeException("Cannot add matrices with different number of rows or columns");
		
		Double result[][] = new Double[a.GetNumberOfRows()][a.GetNumberOfColumns()];
		
		for(int r = 0; r < a.GetNumberOfRows(); r++) {
			for(int c = 0; c < a.GetNumberOfColumns(); c++) {
				result[r][c] = a.GetElement(r, c) + b.GetElement(r, c);
			}
		}
		
		return new DoubleMatrix(result);
	}

	public static IMatrix<Double> Subtract(IMatrix<Double> a, IMatrix<Double> b) {
		
		if(a.GetNumberOfRows() != b.GetNumberOfRows() || a.GetNumberOfColumns() != b.GetNumberOfColumns())
			throw new RuntimeException("Cannot add matrices with different number of rows or columns");
		
		Double result[][] = new Double[a.GetNumberOfRows()][a.GetNumberOfColumns()];
		
		for(int r = 0; r < a.GetNumberOfRows(); r++) {
			for(int c = 0; c < a.GetNumberOfColumns(); c++) {
				result[r][c] = a.GetElement(r, c) - b.GetElement(r, c);
			}
		}
		
		return new DoubleMatrix(result);
	}

	public static void Copy(IMatrix<Double> source, IMatrix<Double> target) {
		
		if(source.GetNumberOfRows() != target.GetNumberOfRows() || 
				source.GetNumberOfColumns() != target.GetNumberOfColumns())
			throw new RuntimeException(
					"Cannot copy a matrix to another with a different number of rows or columns");
		
		for(int r = 0; r < source.GetNumberOfRows(); r++) {
			for(int c = 0; c < source.GetNumberOfColumns(); c++) {
				target.SetElement(r, c, source.GetElement(r, c));
			}
		}
	}

	public static IMatrix<Double> DeleteColumn(IMatrix<Double> matrix, int columnIndex) {
		
		if(columnIndex > matrix.GetNumberOfColumns())
			throw new RuntimeException(
					"Cannot delete a column of index " + columnIndex + 
					"while the matrix has " + matrix.GetNumberOfColumns() + "columns");

		Double result[][] = new Double[matrix.GetNumberOfRows()][matrix.GetNumberOfColumns() - 1];
		
		for(int r = 0; r < matrix.GetNumberOfRows(); r++) {
			
			int resultColIndex = 0;
			
			for(int c = 0; c < matrix.GetNumberOfColumns(); c++) {
				
				if(c != columnIndex) {
					result[r][resultColIndex] = matrix.GetElement(r, c);
					resultColIndex++;
				}
			}
		}
		
		return new DoubleMatrix(result);
	}

	public static IMatrix<Double> DeleteRow(IMatrix<Double> matrix, int rowIndex) {
		
		if(rowIndex > matrix.GetNumberOfRows())
			throw new RuntimeException(
					"Cannot delete a row of index " + rowIndex + 
					"while the matrix has " + matrix.GetNumberOfRows() + "rows");

		Double result[][] = new Double[matrix.GetNumberOfRows() - 1][matrix.GetNumberOfColumns()];
		
		int resultRowIndex = 0;
		
		for(int r = 0; r < matrix.GetNumberOfRows(); r++) {
			
			if(r != rowIndex) {
				for(int c = 0; c < matrix.GetNumberOfColumns(); c++) {
					
					result[resultRowIndex][c] = matrix.GetElement(r, c);
				}
			}
			resultRowIndex++;
		}
		
		return new DoubleMatrix(result);
	}

	public static IMatrix<Double> MultiplyByScalar(IMatrix<Double> matrix, Double scalar) {
		
		Double result[][] = new Double[matrix.GetNumberOfRows()][matrix.GetNumberOfColumns()];
		
		for(int r = 0; r < matrix.GetNumberOfRows(); r++) {
			for(int c = 0; c < matrix.GetNumberOfColumns(); c++) {
				
				result[r][c] = matrix.GetElement(r, c) * scalar;
			}
		}
		
		return new DoubleMatrix(result);
	}

	public static IMatrix<Double> DivideByScalar(IMatrix<Double> matrix, Double scalar) {
		
		Double result[][] = new Double[matrix.GetNumberOfRows()][matrix.GetNumberOfColumns()];
		
		for(int r = 0; r < matrix.GetNumberOfRows(); r++) {
			for(int c = 0; c < matrix.GetNumberOfColumns(); c++) {
				
				result[r][c] = matrix.GetElement(r, c) / scalar;
			}
		}
		
		return new DoubleMatrix(result);
	}

	public static IMatrix<Double> Identity(int size) {

		if(size < 1)
			throw new RuntimeException("Size must be a real integer positive number");
		
		DoubleMatrix identity = new DoubleMatrix(size, size);
		
		for(int d = 0; d < size; d++) {
			identity.SetElement(d, d, 1.);
		}
		
		return identity;
	}

	public static IMatrix<Double> Multiply(IMatrix<Double> a, IMatrix<Double> b) {

		if(a.GetNumberOfRows() != b.GetNumberOfColumns())
			throw new RuntimeException(
					"In order to multiply matrices the number of rows of the first matrix " +
					"must be the same of the number of columns of the second matrix");
		
		Double result[][] = new Double[a.GetNumberOfRows()][b.GetNumberOfColumns()];
		
		for(int resultRow = 0; resultRow < a.GetNumberOfRows(); resultRow++) {
			for(int resultColumn = 0; resultColumn < b.GetNumberOfColumns(); resultColumn++) {
				
				double value = 0;
				
				for(int i = 0; i < a.GetNumberOfColumns(); i++) {
					value += a.GetElement(resultRow, i) * b.GetElement(i, resultColumn);
				}
				
				result[resultRow][resultColumn] = value;
			}
		}
		
		return new DoubleMatrix(result);
	}

	public static IMatrix<Double> Transpose(IMatrix<Double> matrix) {

		Double transposedMatrix[][] = new Double[matrix.GetNumberOfColumns()][matrix.GetNumberOfRows()];
		
		for(int r = 0; r < matrix.GetNumberOfRows(); r++) {
			for(int c = 0; c < matrix.GetNumberOfColumns(); c++) {
				transposedMatrix[c][r] = matrix.GetElement(r, c);
			}
		}
		
		return new DoubleMatrix(transposedMatrix);
	}

	public static Double DotProduct(IMatrix<Double> a, IMatrix<Double> b) {

		if(!a.IsVector() || !b.IsVector())
			throw new RuntimeException("Dot product can be only performed between vector matrices");
			
		List<Double> aList = a.ToArrayList();
		List<Double> bList = b.ToArrayList();
		
		if(aList.size() != bList.size())
			throw new RuntimeException("Cannot perform dot product between vectors of different length");
		
		Double result = 0.;
		for(int i = 0; i < aList.size(); i++) {
			result += aList.get(i) * bList.get(i);
		}
		
		return result;
	}
}
