package com.net.simulation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


import com.net.IRandomPatternFactory;
import com.net.impl.BinaryHopfieldNetwork;
import com.net.impl.BipolarPattern;
import com.net.impl.RandomPatternFactory;
import com.net.utils.NetWriter;

public class HopfieldSizeSimulation {

	private String _filePath;
	private Random _rndGenerator;
	private int _minSize, _maxSize;
	private double _networkLoadFactor;
	private double _patternNoise;
	
	public HopfieldSizeSimulation(
			String filePath, 
			Random rndGenerator,
			int minSize,
			int maxSize,
			double networkLoadFactor,
			double patternNoise) {
		
		_filePath = filePath;
		_rndGenerator = rndGenerator;
		_minSize = minSize;
		_maxSize = maxSize;
		_networkLoadFactor = networkLoadFactor;
		_patternNoise = patternNoise;
	
	}
	
	public void Launch() {
		
		NetWriter netWriter = new NetWriter(_filePath);
		IRandomPatternFactory patternFactory = new RandomPatternFactory();
		
		double loadFactor = _networkLoadFactor;
		
		netWriter.Write();
		netWriter.Write("HOPFIELD NETWORK SIZE SIMULATION - " + Calendar.getInstance().getTime());
		netWriter.Write("[Uncorrelated memories]");
		netWriter.Write("Load factor: " + _networkLoadFactor);
		netWriter.Write("Test pattern noise: " + _patternNoise);
		netWriter.Write();
		
		
		for(int size = _minSize; size < _maxSize + 1; size++) {
			
			BinaryHopfieldNetwork net = new BinaryHopfieldNetwork(size, loadFactor);
			
			ArrayList<BipolarPattern> patternList = new ArrayList<BipolarPattern>();
	
			for(int i = 0; i < net.GetPatternLimit(); i ++) {
					
				BipolarPattern p = (BipolarPattern) patternFactory.UniformBipolarPattern(_rndGenerator, size);					
				patternList.add(p);
			}
			
			for(BipolarPattern p : patternList) {
				net.Memorize(p);
			}
			
			double meanError = 0.;
			
			for(BipolarPattern p : patternList) {
				
				BipolarPattern ret = (BipolarPattern) net.AsynchronousUpdate(new BipolarPattern(p.ToList()));
				
				double countError = p.CompareWith(ret);
				
				meanError += countError;
			}
			
			meanError /= patternList.size();
			
			double noise = _patternNoise;
			
			double meanErrorConv = 0.;
			
			for(BipolarPattern p : patternList) {
					
				BipolarPattern pClean = new BipolarPattern(p.ToList());
					
				BipolarPattern pNoisy = pClean.GetNoisyPattern(_rndGenerator, noise);
				BipolarPattern pReturned = (BipolarPattern) net.AsynchronousUpdate(pNoisy);
				
				double countError = p.CompareWith(pReturned);
				meanErrorConv += countError;	
			}
			
			meanErrorConv /= patternList.size();

			netWriter.Write(String.format("%4d %4.2f %4.2f", size, meanError * 100, meanErrorConv * 100));
			
		}		
				
	}	
		
	
	
}
