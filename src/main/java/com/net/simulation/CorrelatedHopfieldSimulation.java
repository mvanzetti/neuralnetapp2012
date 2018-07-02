package com.net.simulation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.net.IRandomPatternFactory;
import com.net.impl.BinaryHopfieldNetwork;
import com.net.impl.BipolarPattern;
import com.net.impl.RandomPatternFactory;
import com.net.utils.BipolarHelper;
import com.net.utils.FrameInfo;
import com.net.utils.NetWriter;

public class CorrelatedHopfieldSimulation {

    private String _filePath;
    private Random _rndGenerator;
    private int _networkSize;
    private double _networkLoadFactor;
    private double _patternNoise;
    private double _trainPatternNoise;

    private FrameInfo _frame;
    private BipolarPattern _basePattern;

    public CorrelatedHopfieldSimulation(
            String filePath,
            Random rndGenerator,
            int networkSize,
            double networkLoadFactor,
            double patternNoise,
            double trainPatternNoise) {

        _filePath = filePath;
        _rndGenerator = rndGenerator;
        _networkSize = networkSize;
        _networkLoadFactor = networkLoadFactor;
        _patternNoise = patternNoise;
        _trainPatternNoise = trainPatternNoise;
    }

    public void SetOutputFrame(int width, int height) {
        _frame = new FrameInfo(width, height);
    }

    public void SetBasePattern(BipolarPattern pattern) {
        _basePattern = pattern;
    }

    public void Launch() {

        NetWriter netWriter = new NetWriter(_filePath);
        IRandomPatternFactory patternFactory = new RandomPatternFactory();

        int size = _networkSize;
        double loadFactor = _networkLoadFactor;

        netWriter.Write();
        netWriter.Write("HOPFIELD NETWORK SIMULATION - " + Calendar.getInstance().getTime());

        netWriter.Write("[Correlated memories]");

        netWriter.Write("Instantiating Hopfield Network (size: " + size + ", load factor: " + loadFactor + ")");

        BinaryHopfieldNetwork net = new BinaryHopfieldNetwork(size, loadFactor);

        netWriter.Write("Maximum number of addressable patterns: " + net.GetPatternLimit());

        netWriter.Write("Randomly creating memory patterns:");

        ArrayList<BipolarPattern> patternList = new ArrayList<BipolarPattern>();

        if (_basePattern == null) {
            netWriter.Write("Creating a random base pattern");

            _basePattern = (BipolarPattern) patternFactory.UniformBipolarPattern(_rndGenerator, size);
        } else {
            netWriter.Write("Using the base pattern");
        }


        netWriter.Write(_basePattern.ToString());
        netWriter.Write(BipolarHelper.BipolarPatternToFrame(_basePattern, _frame));


        netWriter.Write("Creating correlated patterns adding a low noise (" + _trainPatternNoise * 100 + "%)");

        for (int i = 0; i < net.GetPatternLimit(); i++) {

            BipolarPattern p = (BipolarPattern) _basePattern.GetNoisyPattern(_rndGenerator, _trainPatternNoise);

            netWriter.Write(p.ToString());
            netWriter.Write(BipolarHelper.BipolarPatternToFrame(p, _frame));

            patternList.add(p);
        }

        netWriter.Write();
        netWriter.Write("Network training begins");

        long timeOffset = Calendar.getInstance().getTimeInMillis();

        for (BipolarPattern p : patternList) {
            net.Memorize(p);
        }

        long elapsed = Calendar.getInstance().getTimeInMillis() - timeOffset;

        netWriter.Write("Network training is finished");
        netWriter.Write("Elapsed time: " + elapsed + "ms");
        netWriter.Write();

        netWriter.Write("Asynch Update - Testing memory patterns:");

        for (BipolarPattern p : patternList) {

            netWriter.Write("Presented: " + p.ToString());
            netWriter.Write(BipolarHelper.BipolarPatternToFrame(p, _frame));

            BipolarPattern ret = (BipolarPattern) net.AsynchronousUpdate(new BipolarPattern(p.ToList()));
            netWriter.Write("Returned:  " + ret.ToString());
            netWriter.Write(BipolarHelper.BipolarPatternToFrame(ret, _frame));
            netWriter.Write();

            double countError = p.CompareWith(ret);

            netWriter.Write("Convergence error memory at " + countError * 100 + "%");
            netWriter.Write();

        }

        netWriter.Write("Asynch Update - Testing patterns convergence:");

        double noise = _patternNoise;

        for (BipolarPattern p : patternList) {

            BipolarPattern pClean = new BipolarPattern(p.ToList());

            netWriter.Write("Adding noise to memorized patterns: " + noise * 100 + "%");
            BipolarPattern pNoisy = (BipolarPattern) pClean.GetNoisyPattern(_rndGenerator, noise);

            netWriter.Write("Clean:         " + pClean.ToString());
            netWriter.Write(BipolarHelper.BipolarPatternToFrame(pClean, _frame));

            netWriter.Write("Noisy:         " + pNoisy.ToString());
            netWriter.Write(BipolarHelper.BipolarPatternToFrame(pNoisy, _frame));

            BipolarPattern pReturned = (BipolarPattern) net.AsynchronousUpdate(pNoisy);

            netWriter.Write("Converged to:  " + pReturned.ToString());
            netWriter.Write(BipolarHelper.BipolarPatternToFrame(pReturned, _frame));
            netWriter.Write();

            double countError = p.CompareWith(pReturned);

            netWriter.Write("Convergence error memory at " + countError * 100 + "%");
            netWriter.Write();

        }
    }
}
