package com.net.simulation;

import java.util.ArrayList;
import java.util.Calendar;

import com.net.consts.Bipolar;
import com.net.impl.BinaryHopfieldNetwork;
import com.net.impl.SignedBipolarPattern;
import com.net.utils.BipolarHelper;
import com.net.utils.FrameInfo;
import com.net.utils.NetWriter;

public class SignedHopfieldSimulation {

	private String _filePath;
	private int _networkSize;
	private double _networkLoadFactor;
		
	private FrameInfo _frame;
	private ArrayList<SignedBipolarPattern> _basePatternList;
	private ArrayList<SignedBipolarPattern> _testPatternList;
	
	public SignedHopfieldSimulation(
			String filePath,
			int networkSize,
			double networkLoadFactor) {
		
		_filePath = filePath;
		_networkSize = networkSize;
		_networkLoadFactor = networkLoadFactor;
		
		_basePatternList = new ArrayList<SignedBipolarPattern>();
		_testPatternList = new ArrayList<SignedBipolarPattern>();
	}
	
	public void SetOutputFrame(int width, int height) {
		_frame = new FrameInfo(width, height);
	}
	
	public void AddBasePattern(SignedBipolarPattern pattern) {
		_basePatternList.add(pattern);
	}
	
	public void AddTestPattern(SignedBipolarPattern pattern) {
		_testPatternList.add(pattern);
	}
	
	public void Launch() {
		
		NetWriter netWriter = new NetWriter(_filePath);
		
		int size = _networkSize;
		double loadFactor = _networkLoadFactor;
		
		netWriter.Write();
		netWriter.Write("HOPFIELD NETWORK SIMULATION - " + Calendar.getInstance().getTime());
		
		netWriter.Write("[Uncorrelated memories]");
		
		netWriter.Write("Instantiating Hopfield Network (size: " + size + ", load factor: " + loadFactor + ")");
		
		BinaryHopfieldNetwork net = new BinaryHopfieldNetwork(size, loadFactor);
		
		netWriter.Write("Maximum number of addressable patterns: " + net.GetPatternLimit());
		
		netWriter.Write("Memory patterns:");
		
		ArrayList<SignedBipolarPattern> patternList = new ArrayList<SignedBipolarPattern>();
			
		netWriter.Write("Selected memories:");
			
		for(int i = 0; i < net.GetPatternLimit(); i ++) {
				
			SignedBipolarPattern p = _basePatternList.get(i);
				
			netWriter.Write(p.ToString());
			netWriter.Write(BipolarHelper.SignedBipolarPatternToFrame(p, _frame));
				
			patternList.add(p);
		}
			
		netWriter.Write();
		netWriter.Write("Network training begins");
		
		long timeOffset = Calendar.getInstance().getTimeInMillis();
		
		for(SignedBipolarPattern p : patternList) {
			net.Memorize(p);
		}
		
		long elapsed = Calendar.getInstance().getTimeInMillis() - timeOffset;
		
		netWriter.Write("Network training is finished");
		netWriter.Write("Elapsed time: " + elapsed + " ms");
		netWriter.Write();
		
		netWriter.Write("Asynch Update - Testing memory patterns:");
		
		for(SignedBipolarPattern p : patternList) {
			
			netWriter.Write("Presented: " + p.ToString());
			netWriter.Write(BipolarHelper.SignedBipolarPatternToFrame(p, _frame));
			
			SignedBipolarPattern ret = (SignedBipolarPattern) net.AsynchronousUpdate(new SignedBipolarPattern(p.ToList()));
			netWriter.Write("Returned:  " + ret.ToString());
			netWriter.Write(BipolarHelper.SignedBipolarPatternToFrame(ret, _frame));
			netWriter.Write();
			
			double countError = p.CompareWith(ret);
			
			netWriter.Write("Convergence error memory at " + countError * 100 + "%");
			netWriter.Write();		
		}
		
		if(_testPatternList.size() > 0) {
			
			netWriter.Write("Asynch Update - presenting test patterns");
			
			for(SignedBipolarPattern p : _testPatternList) {
				
				netWriter.Write("Presented:     " + p.ToString());
				netWriter.Write(BipolarHelper.SignedBipolarPatternToFrame(p, _frame));
				
				SignedBipolarPattern pReturned = (SignedBipolarPattern) net.AsynchronousUpdate(new SignedBipolarPattern(p.ToList()));
				netWriter.Write("Returned:      " + pReturned.ToString());
				netWriter.Write(BipolarHelper.SignedBipolarPatternToFrame(pReturned, _frame));
				
				if(pReturned.GetSign() == Bipolar.Dw) {
					netWriter.Write("Found reversed state, switching:");
					SignedBipolarPattern switched = pReturned.Switch();
					netWriter.Write("Returned:  " + switched.ToString());
					netWriter.Write(BipolarHelper.SignedBipolarPatternToFrame(switched, _frame));
					
				}
				
			}
		}
			
	}

}
