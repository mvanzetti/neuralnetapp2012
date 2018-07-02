package com.anneal;

import java.util.Arrays;

public class TestAnnealer {
	
	public static void main(String[] args) {
		
		double[] state = {0.0, -0.1, 0.2, 0.0, 0.9, 0.2, 0.1, 0.0, 0.2};
		
		Annealer anneal = new Annealer(state);
		
		double[] result = anneal.GetBest();
		
		System.out.println(Arrays.toString(result));
	}

}
