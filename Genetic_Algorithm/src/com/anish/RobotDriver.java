/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anish;

/**
 *
 * @author anish1992
 */
public class RobotDriver {
    
    public static final int maxNoOFGen = 1000;
    public static final int popSize = 10;
    public static final double mutRate = 0.05;
    public static final double crossRate = 0.9;
    public static final int eliteSol = 1;
    public static final int tournSelSize = 4;
    
    public static void main(String[] args){
        RobotDriver rDriver = new RobotDriver();
        int generationNumber = 0;
        GA ga = new GA();
        GAPopulation population = new GAPopulation(popSize);
        population.getSolutions().forEach(x -> x.getFitness());
        population.sortByFitness();
        rDriver.printPopulation(population, generationNumber++);
        while(generationNumber <= maxNoOFGen)
        {
            population = ga.evolve(population);
            population.getSolutions().forEach(x -> x.getFitness());
            population.sortByFitness();
            rDriver.printPopulation(population,generationNumber++);
        }
        GASolution fittestSolution = population.getSolutions().get(0);
        rDriver.printFittestSolution(fittestSolution);
        rDriver.printRoutePositions(fittestSolution);
    }
    public void printPopulation(GAPopulation population, int generationNumber)
    {
        System.out.println(" Generation --> " + generationNumber);
        for(int i=0;i<population.getSolutions().get(0).getActions().size(); i++)
        {
            if(i<10)
                System.out.print("0"+i +"|");
            else
                System.out.print(i + "|");
        }
        System.out.println("  Fitness");
        System.out.println("- - -- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        population.getSolutions().forEach(x -> System.out.println(x + "|   " + x.getFitness()));
        System.out.println();

    }
    public void printFittestSolution(GASolution solution)
    {
        System.out.println(" Fittest Solution -->");
        System.out.print("|");
        for(int i=0;i<solution.getActions().size();i++)
        {
            if(i<10)
                System.out.print("0"+i+"|");
            else
                System.out.print(i+"|");
        }
        System.out.println("  <-- Detectors");
        System.out.println("- - - -- - - - - - - - -- - - - - - - - - - -- - - - - - -  - - - -- - - - - -- - - - - - - ");
        System.out.println("|"+solution+"|  <-- Actions");
    }
      public void printRoutePositions(GASolution solution)
    {
        System.out.println("  Robot navigate  route coordinates: ");
        RoboNavigator navigator = new RoboNavigator();
        navigator.activate(solution.getActions());
        navigator.getRouteString().forEach(x -> System.out.print(x));
        System.out.println();
        }
    
}
