package com.math.matrix;

import java.util.ArrayList;
import java.util.List;

import com.math.IMatrix;
import com.net.consts.Bipolar;
import com.net.utils.BipolarHelper;

public class BipolarMatrix implements IMatrix<Bipolar>{

	public static BipolarMatrix CreateColumnMatrix(Bipolar input[]) {
		
		DoubleMatrix source = DoubleMatrix.CreateColumnMatrix(BipolarHelper.BipolarToDouble(input));	
		
		return new BipolarMatrix((Bipolar[][])source.ToArrayList().toArray());
		
	}

	public static BipolarMatrix CreateRowMatrix(Bipolar input[]) {

		DoubleMatrix source = DoubleMatrix.CreateRowMatrix(BipolarHelper.BipolarToDouble(input));	
		
		return new BipolarMatrix((Bipolar[][])source.ToArrayList().toArray());
	}
	
	public static BipolarMatrix ColumnToMatrix(Bipolar column[]) {
		
		DoubleMatrix source = DoubleMatrix.ColumnToMatrix(BipolarHelper.BipolarToDouble(column));
		
		return new BipolarMatrix((Bipolar[][])source.ToArrayList().toArray());
	}
	
	private DoubleMatrix _matrix;
	
	public BipolarMatrix(int numberOfRows, int numberOfColumns) {
		
		_matrix = new DoubleMatrix(numberOfRows, numberOfColumns);
	}
	
	public BipolarMatrix(Bipolar[][] source) {
		_matrix = new DoubleMatrix(BipolarHelper.BipolarToDouble(source));
	}
	
	@Override
	public void Clear() {
		_matrix.Clear();
	}

	@Override
	public int GetNumberOfColumns() {
		return _matrix.GetNumberOfColumns();
	}

	@Override
	public int GetNumberOfRows() {
		return _matrix.GetNumberOfRows();
	}

	@Override
	public Bipolar GetElement(int rowIndex, int columnIndex) {
		return BipolarHelper.DoubleToBipolar(_matrix.GetElement(rowIndex, columnIndex));
	}

	@Override
	public IMatrix<Bipolar> GetColumn(int columnIndex) {
		Bipolar column[][] = new Bipolar[GetNumberOfRows()][1];

		for (int r = 0; r < GetNumberOfRows(); r++) {
			column[r][0] = BipolarHelper.DoubleToBipolar(_matrix.GetElement(r, columnIndex));
		}

		return new BipolarMatrix(column);
	}

	@Override
	public IMatrix<Bipolar> GetRow(int rowIndex) {
		Bipolar row[][] = new Bipolar[1][GetNumberOfColumns()];

		for (int c = 0; c < GetNumberOfColumns(); c++) {
			row[0][c] = BipolarHelper.DoubleToBipolar(_matrix.GetElement(c, rowIndex));
		}

		return new BipolarMatrix(row);
	}

	@Override
	public List<Bipolar> ToArrayList() {
		
		List<Bipolar> returnList = new ArrayList<Bipolar>();
		List<Double> list = _matrix.ToArrayList();
		
		for(Double el : list) {
			returnList.add(BipolarHelper.DoubleToBipolar(el));
		}
		
		return returnList;
	}

	@Override
	public boolean IsZero() {
		return _matrix.IsZero();
	}

	@Override
	public boolean IsVector() {
		return _matrix.IsVector();
	}
	
	@Override
	public boolean IsSquare() {
		return _matrix.IsSquare();
	}

	@Override
	public boolean IsValidElement(int rowIndex, int columnIndex) {
		return _matrix.IsValidElement(rowIndex, columnIndex);
	}

	@Override
	public void SetElement(int rowIndex, int columnIndex, Bipolar value) {
		_matrix.SetElement(rowIndex, columnIndex, BipolarHelper.BipolarToDouble(value));
	}

	@Override
	public void SetColumn(int columnIndex, Bipolar[] value) {
		_matrix.SetColumn(columnIndex, BipolarHelper.BipolarToDouble(value));
	}

	@Override
	public void SetRow(int rowIndex, Bipolar[] value) {
		_matrix.SetRow(rowIndex, BipolarHelper.BipolarToDouble(value));
	}

	@Override
	public void SetDiagonal(Bipolar value) {
		_matrix.SetDiagonal(BipolarHelper.BipolarToDouble(value));		
	}


}
