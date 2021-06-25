package com.company.Exceptions;

public class StrategyNotFoundException extends Exception{
    public StrategyNotFoundException(){
        System.out.println("No strategy was found as such");
    }
}
