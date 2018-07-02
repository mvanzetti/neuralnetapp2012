package com.rand;

import java.util.Random;

public class ExponentialDeviates extends Random {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2896973097346948212L;
	
	private UniformDeviates uniformDeviates;
	
	public ExponentialDeviates() {
		this(System.nanoTime());
		uniformDeviates = new UniformDeviates();
	}

	public ExponentialDeviates(long seed) {
		uniformDeviates = new UniformDeviates(seed);
	}
	
	/** 
	 * Adapted from "Numerical Recipes in C: The Art of Scientific Computing".
	 * Returns an exponentially distributed, positive, random deviate of unit mean, using
	 * {@link UniformDeviates} as the source of uniform deviates.
	 */
	public double expdev() {
		
		double dum;
		
		do {
			dum = uniformDeviates.ran1();
		} while(dum == 0.);
				
		return - Math.log(dum);
	}
}
