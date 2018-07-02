package com.rand;

import java.util.Random;

public class GaussianDeviates extends Random {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1994543051041435702L;

	private long idum;
	private UniformDeviates uniformDeviates;

	public GaussianDeviates() {
		this(System.nanoTime());
		uniformDeviates = new UniformDeviates();
	}

	public GaussianDeviates(long seed) {
		idum = seed;
		uniformDeviates = new UniformDeviates(seed);
	}

	/**
	 * Adapted from "Numerical Recipes in C: The Art of Scientific Computing".
	 * Returns a normally distributed deviate with zero mean and unit variance,
	 * using {@link UniformDeviates} as the source of uniform deviates
	 */
	public double gasdev() {

		int iset = 0;
		double gset = 0.;
		double fac, rsq, v1, v2;

		if (idum < 0)
			iset = 0;

		if (iset == 0) {
			do {
				v1 = 2.0 * uniformDeviates.ran1() - 1.0;
				v2 = 2.0 * uniformDeviates.ran1() - 1.0;
				rsq = v1 * v1 + v2 * v2;
			} while (rsq >= 1.0 || rsq == 0.0);

			fac = Math.sqrt(-2.0 * Math.log(rsq) / rsq);

			gset = v1 * fac;
			iset = 1;

			return v2 * fac;
		} else {
			iset = 0;
			return gset;
		}
	}
}
