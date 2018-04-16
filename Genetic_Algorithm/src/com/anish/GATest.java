package com.anish;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GATest {

    GA ga = new GA();
    @Test
    public void checkFitness() throws Exception
    {
        GASolution ga =new GASolution();
        ArrayList<Integer> al = new ArrayList<Integer>(Arrays.asList(0,0,1,1,0,0,1,0,1,1,1,1,1,1,0,0,0,1,1,0,0,1,1,0,0,1,1,1,0,1,0,1,1));
        ga.setDigits(al);
        assertEquals(2.0,ga.getFitness());
    }
    @Test
    public void checkCrossOverSolution() throws Exception
    {
        GASolution sol1 =new GASolution();
        ArrayList<Integer> al = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        sol1.setDigits(al);
        System.out.println("Solution 1");
        for (Integer k: sol1.getDigits()) {
            System.out.print(k);
        }
        GASolution sol2 =new GASolution();
        ArrayList<Integer> al1 = new ArrayList<Integer>(Arrays.asList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1));
        sol2.setDigits(al1);
        System.out.println("\nSolution 2");
        for (Integer k: sol2.getDigits()) {
            System.out.print(k);
        }
        GASolution crossover = ga.crossoverSolution(sol1,sol2);
        GASolution temp1=null;
        GASolution temp2=null;
        if(crossover.getDigits().get(0)==sol1.getDigits().get(0))
        {
            temp1=sol1;
            temp2=sol2;
        }
        else {

            temp1= sol2;
            temp2=sol1;
        }
        int count=0;
        int i=0;
        for ( i =0;  i < crossover.getDigits().size() ; i++)
        {
            if(crossover.getDigits().get(i)!=temp1.getDigits().get(i))
                break;
            count++;
        }
        for (int j = i;j<crossover.getDigits().size() ; j++)
        {
            if(crossover.getDigits().get(i)!=temp2.getDigits().get(i))
                break;
            count++;
        }
        System.out.println("\nCrossover of Solution 1 and Solution 2");
        for (Integer k: crossover.getDigits()) {
            System.out.print(k);
        }

        assertEquals(count,crossover.getDigits().size());
    }
    @Test
    public void checkMutateSolution() throws Exception {
        GASolution sol1 =new GASolution();
        ArrayList<Integer> al = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0));
        sol1.setDigits(al);
        System.out.println("\nBefore Mutation");
        for (Integer k: sol1.getDigits()) {
            System.out.print(k);
        }
        ga.mutateSolution(sol1);
        int count=0;
        for(int i=0;i<sol1.getDigits().size();i++)
        {
            if(sol1.getDigits().get(i)==0)
                count++;
        }
        System.out.println("\nAfter mutation");
        for (Integer k: sol1.getDigits()) {
            System.out.print(k);
        }
        assertTrue(count<sol1.getDigits().size());
    }

    @Test
    public void checkFitnessSort() throws Exception{

        GAPopulation population = new GAPopulation(3);
        ArrayList<Integer> al1 = new ArrayList<Integer>(Arrays.asList(0,0,1,1,0,0,1,0,1,1,1,1,1,1,0,0,0,1,1,0,0,1,1,0,0,1,1,1,0,1,0,1,1));
        ArrayList<Integer> al2 = new ArrayList<Integer>(Arrays.asList(1,1,1,1,1,1,1,1,0,0,1,0,0,1,0,1,0,0,1,1,0,1,1,1,0,1,1,1,0,1,1,1));
        ArrayList<Integer> al3 = new ArrayList<Integer>(Arrays.asList(1,1,0,0,0,0,1,0,1,0,1,1,0,1,0,1,0,1,1,1,0,1,1,0,0,1,1,1,0,1,1,1));
        int i=0;
        for (GASolution s : population.getSolutions())
        {
            if(i==0)
                s.setDigits(al1);
            else
                if(i==1)
                    s.setDigits(al2);
                else
                    s.setDigits(al3);
            i++;
            System.out.println(s.getFitness());
        }
        population.sortByFitness();
        assertEquals(8.0,population.getSolutions().get(0).getFitness());
        assertEquals(2.0,population.getSolutions().get(1).getFitness());
        assertEquals(1.0,population.getSolutions().get(2).getFitness());
    }

}
