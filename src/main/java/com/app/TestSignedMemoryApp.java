package com.app;

import com.net.consts.Bipolar;
import com.net.impl.SignedBipolarPattern;
import com.net.simulation.SignedHopfieldSimulation;

public class TestSignedMemoryApp {

    public static void main(String[] args) {

        // memory patterns
        Bipolar[] b1 =
                {
                        Bipolar.Up,    // sign
                        Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Dw,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Dw
                };

        Bipolar[] b2 =
                {
                        Bipolar.Up, // sign
                        Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up,
                        Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
                        Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up,
                        Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
                        Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up,
                };

        Bipolar[] b3 =
                {
                        Bipolar.Up, // sign
                        Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
                        Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
                        Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
                };

        // test patterns
        Bipolar[] t1 =
                {
                        Bipolar.Up, // sign
                        Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Up,
                        Bipolar.Up, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Up,
                        Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up,
                        Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up,
                        Bipolar.Up, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Up
                };

        Bipolar[] t2 =
                {
                        Bipolar.Up, // sign
                        Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Up, Bipolar.Up, Bipolar.Dw,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Up, Bipolar.Dw,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw,
                        Bipolar.Dw, Bipolar.Up, Bipolar.Dw, Bipolar.Dw, Bipolar.Dw
                };

        String filePathTest = "output/test_signed.txt";

        SignedHopfieldSimulation hopfieldTest =
                new SignedHopfieldSimulation(
                        filePathTest,
                        25 + 1,
                        0.138
                );

        hopfieldTest.AddBasePattern(new SignedBipolarPattern(b1));
        hopfieldTest.AddBasePattern(new SignedBipolarPattern(b2));
        hopfieldTest.AddBasePattern(new SignedBipolarPattern(b3));

        hopfieldTest.AddTestPattern(new SignedBipolarPattern(t1));
        hopfieldTest.AddTestPattern(new SignedBipolarPattern(t2));

        hopfieldTest.SetOutputFrame(5, 5);
        hopfieldTest.Launch();
    }

}
