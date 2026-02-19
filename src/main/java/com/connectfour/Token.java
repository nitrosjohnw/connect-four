package com.connectfour;


public enum Token {
    RED, YELLOW; //"types of token"
    

    @Override
    public String toString() {
        return this == RED ? "R" : "Y"; 
    }
}