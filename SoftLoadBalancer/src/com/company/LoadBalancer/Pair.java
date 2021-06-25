package com.company.LoadBalancer;

public class Pair {
    int id, weight;
    public Pair(int id, int weight){
        this.id = id;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getId() {
        return id;
    }
}
