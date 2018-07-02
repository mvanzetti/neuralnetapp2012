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

public class UncorrelatedHopfieldSimulation {
	
	private String _filePath;
	private Random _rndGenerator;
	private int _networkSize;
	private double _networkLoadFactor;
	private double _patternNoise;
		
	private FrameInfo _frame;
	private ArrayList<BipolarPattern> _basePatternList;
	private ArrayList<BipolarPattern> _testPatternList;
	
	public UncorrelatedHopfieldSimulation(
			String filePath, 
			Random rndGenerator,
			int networkSize,
			double networkLoadFactor,
			double patternNoise) {
		
		_filePath = filePath;
		_rndGenerator = rndGenerator;
		_networkSize = networkSize;
		_networkLoadFactor = networkLoadFactor;
		_patternNoise = patternNoise;
		
		_basePatternList = new ArrayList<BipolarPattern>();
		_testPatternList = new ArrayList<BipolarPattern>();
	}
	
	public void SetOutputFrame(int width, int height) {
		_frame = new FrameInfo(width, height);
	}
	
	public void AddBasePattern(BipolarPattern pattern) {
		_basePatternList.add(pattern);
	}
	
	public void AddTestPattern(BipolarPattern pattern) {
		_testPatternList.add(pattern);
	}
	
	public void Launch() {
		
		NetWriter netWriter = new NetWriter(_filePath);
		IRandomPatternFactory patternFactory = new RandomPatternFactory();
		
		int size = _networkSize;
		double loadFactor = _networkLoadFactor;
		
		netWriter.Write();
		netWriter.Write("HOPFIELD NETWORK SIMULATION - " + Calendar.getInstance().getTime());
		
		netWriter.Write("[Uncorrelated memories]");
		
		netWriter.Write("Instantiating Hopfield Network (size: " + size + ", load factor: " + loadFactor + ")");
		
		BinaryHopfieldNetwork net = new BinaryHopfieldNetwork(size, loadFactor);
		
		netWriter.Write("Maximum number of addressable patterns: " + net.GetPatternLimit());
		
		netWriter.Write("Memory patterns:");
		
		ArrayList<BipolarPattern> patternList = new ArrayList<BipolarPattern>();
		
		if(_basePatternList.size() == 0) {
			
			netWriter.Write("Randomly creating memories");
			
			for(int i = 0; i < net.GetPatternLimit(); i ++) {
				
				BipolarPattern p = (BipolarPattern) patternFactory.UniformBipolarPattern(_rndGenerator, size);

				netWriter.Write(p.ToString());
				netWriter.Write(BipolarHelper.BipolarPatternToFrame(p, _frame));
				
				patternList.add(p);
			}
		} else {
			
			netWriter.Write("Selected memories:");
			
			for(int i = 0; i < net.GetPatternLimit(); i ++) {
				
				BipolarPattern p = _basePatternList.get(i);
				
				netWriter.Write(p.ToString());
				netWriter.Write(BipolarHelper.BipolarPatternToFrame(p, _frame));
				
				patternList.add(p);
			}
		}
			
		netWriter.Write();
		netWriter.Write("Network training begins");
		
		long timeOffset = Calendar.getInstance().getTimeInMillis();
		
		for(BipolarPattern p : patternList) {
			net.Memorize(p);
		}
		
		long elapsed = Calendar.getInstance().getTimeInMillis() - timeOffset;
		
		netWriter.Write("Network training is finished");
		netWriter.Write("Elapsed time: " + elapsed + " ms");
		netWriter.Write();
		
		netWriter.Write("Asynch Update - Testing memory patterns:");
		
		for(BipolarPattern p : patternList) {
			
			netWriter.Write("Presented: " + p.ToString());
			netWriter.Write(BipolarHelper.BipolarPatternToFrame(p, _frame));
			
			BipolarPattern ret = (BipolarPattern) net.AsynchronousUpdate(new BipolarPattern(p.ToList()));
			//Pattern<Bipolar> ret = (Pattern<Bipolar>) net.AsynchronousUpdate(p, p);
			netWriter.Write("Returned:  " + ret.ToString());
			netWriter.Write(BipolarHelper.BipolarPatternToFrame(ret, _frame));
			netWriter.Write();
			
			double countError = p.CompareWith(ret);
			
			netWriter.Write("Convergence error memory at " + countError * 100 + "%");
			netWriter.Write();
			
		}
		
		if(_testPatternList.size() > 0) {
			
			netWriter.Write("Asynch Update - presenting test patterns");
			
			for(BipolarPattern p : _testPatternList) {
				
				netWriter.Write("Presented:     " + p.ToString());
				netWriter.Write(BipolarHelper.BipolarPatternToFrame(p, _frame));
				
				//Pattern<Bipolar> pReturned = (Pattern<Bipolar>) net.AsynchronousUpdate(p, p);
				BipolarPattern pReturned = (BipolarPattern) net.AsynchronousUpdate(new BipolarPattern(p.ToList()));
				netWriter.Write("Returned:      " + pReturned.ToString());
				netWriter.Write(BipolarHelper.BipolarPatternToFrame(pReturned, _frame));
				
			}
		}
		else {
			netWriter.Write("Asynch Update - Testing patterns convergence:");
			
			double noise = _patternNoise;
			
			for(BipolarPattern p : patternList) {
				
				BipolarPattern pClean = new BipolarPattern(p.ToList());
				
				netWriter.Write("Adding noise to memorized patterns: " + noise * 100 + "%");
				BipolarPattern pNoisy = pClean.GetNoisyPattern(_rndGenerator, noise);
				
				netWriter.Write("Clean:         " + pClean.ToString());
				netWriter.Write(BipolarHelper.BipolarPatternToFrame(pClean, _frame));
				
				netWriter.Write("Noisy:         " + pNoisy.ToString());
				netWriter.Write(BipolarHelper.BipolarPatternToFrame(pNoisy, _frame));
				
				BipolarPattern pReturned = (BipolarPattern) net.AsynchronousUpdate(pNoisy);
				//Pattern<Bipolar> pReturned = (Pattern<Bipolar>) net.AsynchronousUpdate(pClean, pNoisy);
				
				netWriter.Write("Converged to:  " + pReturned.ToString());
				netWriter.Write(BipolarHelper.BipolarPatternToFrame(pReturned, _frame));
				
				double countError = p.CompareWith(pReturned);
				
				netWriter.Write("Convergence error memory at " + countError * 100 + "%");
				netWriter.Write();
				
			}
		}
			
	}

}
