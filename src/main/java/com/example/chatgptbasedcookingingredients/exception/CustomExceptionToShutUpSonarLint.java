package com.example.chatgptbasedcookingingredients.exception;

public class CustomExceptionToShutUpSonarLint extends RuntimeException{
    public CustomExceptionToShutUpSonarLint(String message){
        super(message);
    }
}
