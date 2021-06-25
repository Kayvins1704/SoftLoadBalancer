package com.company.Exceptions;

public class IncorrectWeightException extends Exception{
    public IncorrectWeightException(){
        System.out.println("Please add a correct weight which is less than remaining weight"
                + " and greater than zero");
    }
}
