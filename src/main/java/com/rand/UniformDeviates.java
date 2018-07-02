package com.rand;

import java.util.Random;

public class UniformDeviates extends Random {

    /**
     *
     */
    private static final long serialVersionUID = -1679481716777709922L;

    private static final long IA = 16807;
    private static final long IM = 2147483647;
    private static final double AM = 1.0 / IM;
    private static final long IQ = 127773;
    private static final long IR = 2836;
    private static final int NTAB = 32;
    private static final long NDIV = (long) (1 + (IM - 1) / NTAB);

    private static final double EPS = 1.2e-7;
    private static final double RNMX = (1.0 - EPS);

    private static long iy = 0;
    private static long[] iv = new long[NTAB];

    private long idum;

    public UniformDeviates() {
        this(System.nanoTime());
    }

    public UniformDeviates(long seed) {
        idum = seed;
    }


    public double NextDouble() {
        return this.ran1();
    }

    /**
     * Adapted from Numerical Recipes in C
     * Minimal random number generator of Park and Miller with Bays-Durham shuffle and added
     * safeguards. Returns a uniform random deviate between 0.0 and 1.0 (exclusive of the endpoint
     * values). Call with idum a negative integer to initialize; thereafter, do not alter idum between
     * successive deviates in a sequence. RNMX should approximate the largest floating value that is
     * less than 1.
     */
    public double ran1() {

        int j;
        long k;

        double temp;
        if (idum <= 0 || iy == 0) {

            if (-(idum) < 1) {
                idum = 1;
            } else {
                idum = -(idum);
            }

            for (j = NTAB + 7; j >= 0; j--) {

                k = (idum) / IQ;
                idum = IA * (idum - k * IQ) - IR * k;
                if (idum < 0)
                    idum += IM;
                if (j < NTAB)
                    iv[j] = idum;
            }
            iy = iv[0];
        }
        k = (idum) / IQ;
        idum = IA * (idum - k * IQ) - IR * k;
        if (idum < 0)
            idum += IM;
        j = (int) (iy / NDIV);
        iy = iv[j];
        iv[j] = idum;
        if ((temp = AM * iy) > RNMX)
            return RNMX;
        else
            return temp;
    }
}
