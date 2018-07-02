package com.app;

import java.util.Random;

import com.net.simulation.CorrelatedHopfieldSimulation;
import com.rand.UniformDeviates;

public class CorrelatedMemoryApp {

    public static void main(String[] args) {

        Random rndGen = new UniformDeviates();

        String filePath = "output/correlated.txt";

        CorrelatedHopfieldSimulation hopfieldCorrelated =
                new CorrelatedHopfieldSimulation(
                        filePath,
                        rndGen,
                        25,
                        0.138,
                        0.20,
                        0.05
                );

        hopfieldCorrelated.SetOutputFrame(5, 5);
        hopfieldCorrelated.Launch();

    }

}
