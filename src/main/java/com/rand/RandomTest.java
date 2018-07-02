package com.rand;

public class RandomTest {

	public static void main(String[] args) {

		long idum = -1234837646;

		System.out.println("--- Uniform ---");
		
		UniformDeviates rGen = new UniformDeviates(idum);
		
		for(int i = 0; i < 100; i++) {
		
			double r = rGen.ran1();
			System.out.println(r);
		}
		
		System.out.println("--- Gaussian ---");

		GaussianDeviates rGenGauss = new GaussianDeviates(idum);
		
		for(int i = 0; i < 100; i++) {
		
			double r = rGenGauss.gasdev();
			System.out.println(r);
		}
		
		System.out.println("--- Exponential ---");

		ExponentialDeviates rGenExp = new ExponentialDeviates(idum);
		
		for(int i = 0; i < 100; i++) {
		
			double r = rGenExp.expdev();
			System.out.println(r);
		}
	}

}
