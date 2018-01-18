package com.example;

public class SelfDrivngCar extends Car {

    public SelfDrivngCar(String chosenColor, String chosenInterior) {
        super(chosenColor, chosenInterior);
        System.out.println("Constructing self driving car. Installing Skynet version 0.92.4");
    }
}
