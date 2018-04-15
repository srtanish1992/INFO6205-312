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
public class GAPopulation {
    
    private ArrayList<GASolution> solutions;
    
    public GAPopulation(int populationSize){
        this.solutions = new ArrayList<GASolution>(populationSize);
        IntStream.range(0, populationSize).forEach(x -> solutions.add(new GASolution()));
    }

    public ArrayList<GASolution> getSolutions() {
        return solutions;
    }
    
    public ArrayList<GASolution> sortByFitness(){
        solutions.sort((solution1, solution2) -> {
           if(solution1.getFitness() > solution2.getFitness()) return -1;
           else if(solution1.getFitness() < solution2.getFitness()) return 1;
           return 0;
        });
        return solutions;
    }
    
}
