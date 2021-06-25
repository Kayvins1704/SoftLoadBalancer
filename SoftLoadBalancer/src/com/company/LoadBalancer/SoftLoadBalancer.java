package com.company.LoadBalancer;

import com.company.Exceptions.IncorrectWeightException;
import com.company.Exceptions.MinServersNotAvailable;
import com.company.Exceptions.StrategyNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SoftLoadBalancer {
    private static SoftLoadBalancer softLoadBalancer;
    private final List<Pair> serverLi;
    private RequestRoutingStrategy requestRoutingStrategy;
    private int remWeight;
    private SoftLoadBalancer(){
        this.serverLi = new ArrayList<>();
        this.remWeight = LBConstants.MAX_WEIGHT;
    }

    public static SoftLoadBalancer getInstance(){
        if(softLoadBalancer == null){
            softLoadBalancer = new SoftLoadBalancer();
        }

        return softLoadBalancer;
    }

    private void addServer(int id, int weight){
        serverLi.add(new Pair(id, weight));
    }

    public void checkNoOfServersLessThan2() throws MinServersNotAvailable {
        if(serverLi.size() < 2) throw new MinServersNotAvailable("The number of servers should be greater " +
                "than or equal to 2");;
    }

    public void implementStrategy(String strategy) throws StrategyNotFoundException {
        switch (strategy){
            case "WEIGHTEDROUNDROBIN":
                requestRoutingStrategy = new WeightedRoundRobin();
                requestRoutingStrategy.addServerLi(serverLi);
                break;
            default:
                throw new StrategyNotFoundException("The strategy was not found in the list of implementable load balancing strategies");
        }
    }

    public void checkAndAddServer(int id, int weight) throws IncorrectWeightException {
        if(weight > remWeight || weight <= 0) throw new IncorrectWeightException("Please specify correct weight");
        softLoadBalancer.addServer(id, weight);
        remWeight -= weight;
    }

    public void routeRequest(){
        requestRoutingStrategy.routeRequest();
    }

    public void routeRequestInBulk(int reqs){
        while(reqs > 0){
            requestRoutingStrategy.routeRequest();
            reqs--;
        }
    }

    public void totalRequestsHandled(){
        requestRoutingStrategy.totalRequestsHandled();
    }
}
