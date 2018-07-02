package com.net.utils;

import java.util.Iterator;
import java.util.List;

import com.net.IPattern;
import com.net.consts.Bipolar;

public class BipolarHelper {

	public static Bipolar Switch(Bipolar value) {
		switch(value) {
			default:
			case Up:
				return Bipolar.Dw;
			case Dw:
				return Bipolar.Up;
		}	
	}
	
	public static double BipolarToDouble(Bipolar value) {
		switch(value) {
			default:
			case Up:
				return 1.;
			case Dw:
				return -1.;
		}
	}
	
	public static boolean BipolarToBoolean(Bipolar value) {
		switch(value) {
			default:
			case Up:
				return true;
			case Dw:
				return false;
		}
	}
	
	public static Bipolar DoubleToBipolar(double value) {
		switch((int)value) {
			default:
			case 1:
				return Bipolar.Up;
			case -1:
				return Bipolar.Dw;
				
		}
	}
	
	public static Bipolar DoubleToBoolean(boolean value) {
		if(value)
			return Bipolar.Up;
		else
			return Bipolar.Dw;
	}
	
	public static Bipolar[] DoubleToBipolar(Double[] array) {
		Bipolar[] bipolar = new Bipolar[array.length];
		for(int i = 0; i < array.length; i++) {
			bipolar[i] = DoubleToBipolar(array[i]);
		}
		return bipolar;
	}
	
	public static Double[] BipolarToDouble(Bipolar[] bipolar) {
		Double[] array = new Double[bipolar.length];
		for(int i = 0; i < bipolar.length; i++) {
			array[i] = BipolarToDouble(bipolar[i]);
		}
		return array;
	}
	
	public static Bipolar[][] DoubleToBipolar(Double[][] matrix) {
		Bipolar[][] bipolar = new Bipolar[matrix.length][matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				bipolar[i][j] = DoubleToBipolar(matrix[i][j]);	
			}
		}
		return bipolar;
	}
	
	public static Double[][] BipolarToDouble(Bipolar[][] bipolar) {
		Double[][] matrix = new Double[bipolar.length][bipolar[0].length];
		for(int i = 0; i < bipolar.length; i++) {
			for(int j = 0; j < bipolar[0].length; j++) {
				matrix[i][j] = BipolarToDouble(bipolar[i][j]);	
			}
		}
		return matrix;
	}
	
	public static String BipolarToString(Bipolar value) {
		switch(value) {
			default:
			case Up:
				return "U";
			case Dw:
				return "D";
		}
	}
	
	public static Bipolar[] BipolarListToArray(List<Bipolar> bipolar) {
		Bipolar b[] = new Bipolar[bipolar.size()];
		for(int i = 0; i < bipolar.size(); i++) {
			b[i] = bipolar.get(i);
		}
		return b;
	}

	public static String BipolarPatternToFrame(IPattern<Bipolar> pattern, FrameInfo frameInfo) 
			throws IllegalArgumentException {
		
		if(pattern.GetSize() != frameInfo.GetWidth() * frameInfo.GetHeight())
			throw new IllegalArgumentException("Pattern size must fit the frame");
		
		String str = "";
		
		List<Bipolar> bipolar = pattern.ToList();
		Iterator<Bipolar> itr = bipolar.iterator(); 
		
		for(int i = 0; i < frameInfo.GetHeight(); i++) {
			
			str += "| ";
			
			for(int j = 0; j < frameInfo.GetWidth(); j++) {
				
				Bipolar el = (Bipolar) itr.next();
				
				switch(el) {
					case Up:
						str += " O ";
						break;
					case Dw:
						str += "   ";
						break;
				}
			}	
			str += " |\n";
		}	
		return str;
	}
	
	public static String SignedBipolarPatternToFrame(IPattern<Bipolar> pattern, FrameInfo frameInfo) 
			throws IllegalArgumentException {
		
		if(pattern.GetSize() - 1 != frameInfo.GetWidth() * frameInfo.GetHeight())
			throw new IllegalArgumentException("Pattern size must fit the frame");
		
		String str = "";
		
		List<Bipolar> bipolar = pattern.ToList();
		Iterator<Bipolar> itr = bipolar.iterator(); 
		
		// the first position is the sign
		itr.next();
		
		for(int i = 0; i < frameInfo.GetHeight(); i++) {
			
			str += "| ";
			
			for(int j = 0; j < frameInfo.GetWidth(); j++) {
				
				Bipolar el = (Bipolar) itr.next();
				
				switch(el) {
					case Up:
						str += " O ";
						break;
					case Dw:
						str += "   ";
						break;
				}
			}	
			str += " |\n";
		}	
		return str;
	}
}
