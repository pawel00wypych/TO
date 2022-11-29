package org.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class RandomGame {
    private int n;
    private int k;
    private LinkedList<Integer> drawnIntegers;
    private HashMap<Integer,Integer> histogram;
    private static Integer numberOfDraws;
    public RandomGame(int n, int k) {

            if(n < 0)
                throw new IllegalArgumentException("n must be positive number");

            if(k <0)
                throw new IllegalArgumentException("k must be positive number");

            if(k > n)
                throw new IllegalArgumentException("n must be greater than k");

            this.n = n;

            this.k = k;


        drawnIntegers = new LinkedList<>();

        histogram = new HashMap<>();

        numberOfDraws = 0;
    }

    public LinkedList<Integer> draw() {

        Random rand = new Random();

        rand.setSeed(0);

        int randomInt;

        for (int i = 0, j = 0; i < k; i++) {
            randomInt = rand.nextInt(n) + 1;

            if (drawnIntegers.contains(randomInt)) {
                i--;
            } else {
                drawnIntegers.add(randomInt);

                histogram.put(randomInt, 1);
            }
        }

        numberOfDraws++;

        return drawnIntegers;
    }

    public int getMin() {
        return drawnIntegers.stream().min(Integer::compare).get();
    }

    public int getMax() {
        return drawnIntegers.stream().max(Integer::compare).get();
    }

    public HashMap<Integer,Integer> getHistogram() {
        for(int i = 0; i < n; i++)
        {
            if(!histogram.containsKey(i))
            {
                histogram.put(i,0);
            }
        }

        return histogram;
    }

    public Integer getNumberOfDraws() {
        return numberOfDraws;
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public void setKandN(int k, int n) {
        this.k = k;
        this.n = n;
    }
}
