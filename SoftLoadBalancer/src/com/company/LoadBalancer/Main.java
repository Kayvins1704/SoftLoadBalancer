package com.company.LoadBalancer;

import com.company.Exceptions.IncorrectWeightException;
import com.company.Exceptions.MinServersNotAvailable;
import com.company.Exceptions.StrategyNotFoundException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws StrategyNotFoundException, MinServersNotAvailable {
        // write your code here
        SoftLoadBalancer softLoadBalancer = SoftLoadBalancer.getInstance();
        System.out.println("Please specify Server with its weight " +
                "total assignable weight is : " + LBConstants.MAX_WEIGHT);
        Scanner sc = new Scanner(System.in);
        int remWeight = LBConstants.MAX_WEIGHT;
        int id = 0;

        while(remWeight > 0){
            try{
                System.out.println("Please add server weight :");
                int weight = sc.nextInt();
                softLoadBalancer.checkAndAddServer(id, weight);
                id++;
                remWeight -= weight;
                System.out.println("Remaining server weight is :" + remWeight);
            } catch (IncorrectWeightException e) {
                e.printStackTrace();
            }
        }

        softLoadBalancer.checkNoOfServersLessThan2();

        softLoadBalancer.implementStrategy(StrategyList.WEIGHTEDROUNDROBIN.name());

        System.out.println("Want to route request one by one or in bulk (O/B)?");
        String ans = sc.next();

        if(ans.equals("O")){
            while(true){
                System.out.println("Please type 1 if you want to route a request");
                int req = sc.nextInt();
                if(req == 1){
                    softLoadBalancer.routeRequest();
                }else{
                    break;
                }
            }
        }else{
            System.out.println("Specify number of requests :");
            int reqs = sc.nextInt();
            softLoadBalancer.routeRequestInBulk(reqs);
        }

        softLoadBalancer.totalRequestsHandled();
    }
}
