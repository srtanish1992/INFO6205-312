/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anish;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author anish1992
 */
public class GA {
    
    public GAPopulation evolve (GAPopulation gaPopulation) {
        return mutatePopulation(crossoverPopulation(gaPopulation));
    }
    
    private GAPopulation crossoverPopulation(GAPopulation gaPopulation)
    {
        gaPopulation.sortByFitness();
        GAPopulation crossoverPopulation = new GAPopulation(gaPopulation.getSolutions().size());
        IntStream.range(0, RobotDriver.eliteSol).forEach(x -> {
           crossoverPopulation.getSolutions().set(x, gaPopulation.getSolutions().get(x));
        });
        IntStream.range(RobotDriver.eliteSol, gaPopulation.getSolutions().size()).forEach(x -> {
            GASolution sol1 = gaPopulation.getSolutions().get(x);
            GASolution sol2 = selectTournamentPop(gaPopulation).get(0);
            crossoverPopulation.getSolutions().set(x, crossoverSolution(sol1, sol2));
        });
        
        return crossoverPopulation;
    }
    
    public GASolution crossoverSolution(GASolution sol1, GASolution sol2)
    {
        GASolution crossoverSOl = new GASolution();
        if(RobotDriver.crossRate > Math.random()) 
        {
            int ranSwapPos = (int) (Math.random() * (sol1.getDigits().size() + 1));
            for (int x = 0; x < sol1.getDigits().size(); x++ ){
                if (x < ranSwapPos) crossoverSOl.getDigits().set(x, sol1.getDigits().get(x));
                else crossoverSOl.getDigits().set(x, sol2.getDigits().get(x));
            }
        }else crossoverSOl = sol1;
        
        return crossoverSOl;
    }
    
    private GAPopulation mutatePopulation(GAPopulation gaPopulation)
    {
        gaPopulation.sortByFitness();
        GAPopulation mutatePop = new GAPopulation(RobotDriver.popSize);
        IntStream.range(0, RobotDriver.eliteSol).forEach(x -> {
            mutatePop.getSolutions().set(x, gaPopulation.getSolutions().get(x));
        });
        IntStream.range(RobotDriver.eliteSol, gaPopulation.getSolutions().size()).forEach(x -> {
            mutatePop.getSolutions().set(x, mutateSolution(gaPopulation.getSolutions().get(x)));
        });
        
        return mutatePop;
    }
    
    public GASolution mutateSolution(GASolution gaSolution)
    {
        IntStream.range(0, gaSolution.getDigits().size()).forEach(x -> {
            if (RobotDriver.mutRate > Math.random()) {
                int binaryStep  = 1;
                if (gaSolution.getDigits().get(x) == 1) binaryStep = 0;
                gaSolution.getDigits().set(x, binaryStep);
            }
        });
        
        return gaSolution;
    }
    
    private ArrayList<GASolution> selectTournamentPop(GAPopulation gAPopulation)
    {
        GAPopulation tournPop = new GAPopulation(RobotDriver.tournSelSize);
        IntStream.range(0, RobotDriver.tournSelSize).forEach(x -> {
            tournPop.getSolutions().set(x, gAPopulation.getSolutions().get((int) (Math.random() * gAPopulation.getSolutions().size())));
        });
        return tournPop.sortByFitness();
    }
    
}
