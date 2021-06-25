package com.company.LoadBalancer;

import java.util.List;

public abstract class RequestRoutingStrategy {
    abstract void addServerLi(List<Pair> serverLi);

    abstract void routeRequest();

    abstract void totalRequestsHandled();
}
