package com.company.LoadBalancer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightedRoundRobin extends RequestRoutingStrategy {
    private List<Pair> serverLi;
    private final List<Integer> totRequestsHandled;
    private final List<Integer> requestsHandled;
    private int currServer;
    private int currInd;
    private int totIndicesfilled;

    public WeightedRoundRobin(){
        this.serverLi = new ArrayList<>();
        this.totRequestsHandled = new ArrayList<>();
        this.requestsHandled = new ArrayList<>();
        this.currServer = -1;
        this.currInd = 0;
        this.totIndicesfilled = 0;
    }
    @Override
    void addServerLi(List<Pair> serverLi) {
        this.serverLi = serverLi;
        for(int i=0; i<serverLi.size(); i++){
            requestsHandled.add(0);
            totRequestsHandled.add(0);
        }
        Collections.sort(serverLi, (Pair p1, Pair p2) -> p2.getWeight() - p1.getWeight());
    }

    @Override
    void routeRequest() {
        if(currServer == -1) currServer = serverLi.get(currInd).getId();
        if(requestsHandled.get(currServer) < serverLi.get(currInd).getWeight()){
            System.out.println("Request has been handled by Server with ID: " + currServer);
            requestsHandled.set(currServer, requestsHandled.get(currServer) + 1);
            totRequestsHandled.set(currServer, totRequestsHandled.get(currServer) + 1);
            if(requestsHandled.get(currServer) == serverLi.get(currInd).getWeight()){
                currInd++;
                if(currInd < serverLi.size()) currServer = serverLi.get(currInd).getId();
                else {
                    currInd = 0;
                    currServer = -1;
                }
                totIndicesfilled++;
            }
        }

        if(totIndicesfilled == serverLi.size()) reinstateServers();
    }

    private void reinstateServers(){
        for(int i=0; i<requestsHandled.size(); i++){
            requestsHandled.set(i, 0);
        }
    }

    @Override
    public void totalRequestsHandled(){
        for(int i=0; i<totRequestsHandled.size(); i++){
            System.out.println("Server " + i + " has handled " + totRequestsHandled.get(i) + " requests");
        }
    }


}
