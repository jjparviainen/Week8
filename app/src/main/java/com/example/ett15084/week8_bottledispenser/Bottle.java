package com.example.ett15084.week8_bottledispenser;

public class Bottle {

    String type = "";
    float amount;
    float price;

    public Bottle(String t, float a, float p){
        type = t;
        amount  = a;
        price = p;
    }

    public String getType(){
        return type;
    }

    public float getAmount(){
        return amount;
    }

    public float getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return type;
    }
}
