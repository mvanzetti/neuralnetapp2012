package com.gellmann;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexFormat;
import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;

public class GellMannCalc {

    public static void main(String[] args) {

        ComplexFormat format = new ComplexFormat();

        Complex zero = new Complex(0, 0);
        Complex one = new Complex(1, 0);
        Complex one_minus = new Complex(-1, 0);
        Complex i = new Complex(0, 1);
        Complex i_minus = new Complex(0, -1);
        Complex two_minus = new Complex(-2, 0);

        Complex[][] l1 = new Complex[][]{{zero, one, zero},
                {one, zero, zero}, {zero, zero, zero}};

        Complex[][] l2 = new Complex[][]{{zero, i_minus, zero},
                {i, zero, zero}, {zero, zero, zero}};

        Complex[][] l3 = new Complex[][]{{one, zero, zero},
                {zero, one_minus, zero}, {zero, zero, zero}};

        Complex[][] l4 = new Complex[][]{{zero, zero, one},
                {zero, zero, zero}, {one, zero, zero}};

        Complex[][] l5 = new Complex[][]{{zero, zero, i_minus},
                {zero, zero, zero}, {i, zero, zero}};

        Complex[][] l6 = new Complex[][]{{zero, zero, zero},
                {zero, zero, one}, {zero, one, zero}};

        Complex[][] l7 = new Complex[][]{{zero, zero, zero},
                {zero, zero, i_minus}, {zero, i, zero}};

        Complex[][] l8 = new Complex[][]{
                {one.divide(Math.sqrt(3)), zero, zero},
                {zero, one.divide(Math.sqrt(3)), zero},
                {zero, zero, two_minus.divide(Math.sqrt(3))}};

        FieldMatrix<Complex> lambda1 = new Array2DRowFieldMatrix<Complex>(l1);
        FieldMatrix<Complex> lambda2 = new Array2DRowFieldMatrix<Complex>(l2);
        FieldMatrix<Complex> lambda3 = new Array2DRowFieldMatrix<Complex>(l3);
        FieldMatrix<Complex> lambda4 = new Array2DRowFieldMatrix<Complex>(l4);
        FieldMatrix<Complex> lambda5 = new Array2DRowFieldMatrix<Complex>(l5);
        FieldMatrix<Complex> lambda6 = new Array2DRowFieldMatrix<Complex>(l6);
        FieldMatrix<Complex> lambda7 = new Array2DRowFieldMatrix<Complex>(l7);
        FieldMatrix<Complex> lambda8 = new Array2DRowFieldMatrix<Complex>(l8);

        List<FieldMatrix<Complex>> list = new ArrayList<FieldMatrix<Complex>>();
        list.add(lambda1);
        list.add(lambda2);
        list.add(lambda3);
        list.add(lambda4);
        list.add(lambda5);
        list.add(lambda6);
        list.add(lambda7);
        list.add(lambda8);

        Complex nullMatrix[][] = {{zero, zero, zero}, {zero, zero, zero},
                {zero, zero, zero}};

        FieldMatrix<Complex> result = new Array2DRowFieldMatrix<Complex>(
                nullMatrix);

        for (FieldMatrix<Complex> lambda : list) {

            result = result.add(lambda.multiply(lambda));
        }

        // print the result
        for (int row = 0; row < result.getRowDimension(); row++) {

            for (int col = 0; col < result.getColumnDimension(); col++) {
                System.out.println(format.format(result.getEntry(row, col)));

            }

        }

    }
}
