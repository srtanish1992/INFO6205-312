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
public class GASolution {
    
    private static final int numb_Of_Digits = 32;
    private boolean fitnessFlag = false;
    private double fitness = -1;
    
    private ArrayList<Integer> digits;
    
    public GASolution() {
        digits = new ArrayList<Integer>(numb_Of_Digits);
        IntStream.range(0, numb_Of_Digits).forEach(x -> digits.add(null));
        IntStream.range(0, numb_Of_Digits).forEach(x -> {
            if(0.5 < Math.random()) digits.set(x,1);
            else digits.set(x, 0);
        });
    }
    
    public ArrayList<Integer> getDigits() {
        return digits;
    }

    public void setDigits(ArrayList<Integer> a)
    {
        this.digits=a;
    }
    
    public ArrayList<Integer> getActions(){
        int numberOfActions = digits.size()/2;
        ArrayList<Integer> actions = new ArrayList<Integer>(numberOfActions);
        IntStream.range(0, numberOfActions).forEach(x -> {
           Integer action = 0;
           if (digits.get(x*2) == 1)
               action += 2;
           if (digits.get((x*2)+1) == 1)
               action += 1;
           actions.add(x, action);
        });
        return actions;
    }
    
    public double getFitness(){
        if(!fitnessFlag) fitness = calFitness();
        return fitness;
    }
    
    private double calFitness() {
        ArrayList<Position> positions = new RoboNavigator().activate(getActions());
        boolean isVisited[][] = new boolean[RobotRoute.coordinates.length][RobotRoute.coordinates[0].length];
        fitness = 0;
        positions.forEach(position -> {
           if(RobotRoute.coordinates[position.getY()][position.getX()] == RobotRoute.route && isVisited[position.getY()][position.getX()] == false){
               fitness++;
               isVisited[position.getY()][position.getX()] = true;
           } 
        });
        return fitness;
    }
    
    public String toString(){
        String solutionAsString = "";
        for(int x = 0; x < digits.size()-1; x++){
            solutionAsString += digits.get(x);
            if(x % 2 != 0) solutionAsString += "|";
        }
        solutionAsString += digits.get(digits.size()-1);
        return solutionAsString;
    }
    
}
