package com.net.impl;

import java.util.ArrayList;
import java.util.Random;

import com.math.IMatrix;
import com.math.matrix.DoubleMatrix;
import com.math.matrix.MatrixHelper;
import com.net.IActivationFunction;
import com.net.IHopfieldNetwork;
import com.net.IPattern;
import com.net.consts.Bipolar;
import com.net.utils.BipolarHelper;

public class BinaryHopfieldNetwork implements IHopfieldNetwork<Bipolar> {

    private static int MAX_STEP_UNCHANGED = 1000;

    private int _size;
    private double _storageCapacity;
    private int _patternLimit;

    private IMatrix<Double> _weightMatrix;
    private IActivationFunction<Bipolar> _activationFunction;

    public BinaryHopfieldNetwork(int size, double storageCapacity) {
        _size = size;
        _storageCapacity = storageCapacity;
        _patternLimit = (int) (storageCapacity * size);

        _weightMatrix = new DoubleMatrix(size, size);
        _weightMatrix.Clear();

        _activationFunction = new BipolarActivationFunction();
    }

    @Override
    public int GetSize() {
        return _size;
    }

    @Override
    public double GetStorageCapacity() {
        return _storageCapacity;
    }

    @Override
    public int GetPatternLimit() {
        return _patternLimit;
    }

    @Override
    public IMatrix<Double> GetWeightMatrix() {
        return _weightMatrix;
    }

    @Override
    public void Memorize(IPattern<Bipolar> pattern) {

        if (pattern.GetSize() != _weightMatrix.GetNumberOfRows()) {
            throw new RuntimeException(
                    "Cannot train a pattern of size " + pattern.GetSize() +
                            " on a hopfield network of size " + _weightMatrix.GetNumberOfRows());
        }

        Bipolar bArray[] = BipolarHelper.BipolarListToArray(pattern.ToList());
        DoubleMatrix patternMatrix = DoubleMatrix.ColumnToMatrix(BipolarHelper.BipolarToDouble(bArray));

        patternMatrix.SetDiagonal(0.);

        // normalize
        Double size = new Double((double) GetSize());
        MatrixHelper.DivideByScalar(patternMatrix, size);

        _weightMatrix = (DoubleMatrix) MatrixHelper.Add(_weightMatrix, patternMatrix);
    }

    @Override
    public IPattern<Bipolar> SynchronousUpdate(IPattern<Bipolar> pattern) {

        ArrayList<Bipolar> output = new ArrayList<Bipolar>(pattern.GetSize());

        Bipolar bArray[] = BipolarHelper.BipolarListToArray(pattern.ToList());
        DoubleMatrix inputVector = DoubleMatrix.CreateRowMatrix(BipolarHelper.BipolarToDouble(bArray));

        for (int c = 0; c < inputVector.GetNumberOfColumns(); c++) {

            DoubleMatrix weightColumn = (DoubleMatrix) MatrixHelper.Transpose(_weightMatrix.GetColumn(c));

            // Synchronous update: updating each unit at the same time step
            Double dotProduct = MatrixHelper.DotProduct(inputVector, weightColumn);

            output.add(c, _activationFunction.Calculate(dotProduct));
        }
        return new Pattern<Bipolar>(output);

    }

    @Override
    public IPattern<Bipolar> AsynchronousUpdate(IPattern<Bipolar> pattern) {

        Random generator = new Random();

        // initialize
        IPattern<Bipolar> outputPattern = pattern;

        // loop until a stable configuration is obtained
        int patternUnchangedStepCount = 0;

        while (patternUnchangedStepCount < MAX_STEP_UNCHANGED) {

            // randomly select a neuron
            int j = generator.nextInt(_size);
            Double sum = 0.;

            for (int i = 0; i < outputPattern.GetSize(); i++) {
                if (i != j) {
                    sum += BipolarHelper.BipolarToDouble(outputPattern.GetElement(i)) * _weightMatrix.GetElement(j, i);
                }
            }

            outputPattern.SetElement(j, _activationFunction.Calculate(sum));

            boolean isChanged = false;
            for (int i = 0; i < outputPattern.GetSize(); i++) {
                if (outputPattern.GetElement(i) != pattern.GetElement(i)) {
                    isChanged = true;
                    break;
                }
            }

            if (!isChanged)
                patternUnchangedStepCount++;
            else
                patternUnchangedStepCount = 0;

            // update the previous pattern state
            pattern = outputPattern;
        }

        return outputPattern;
    }

}
