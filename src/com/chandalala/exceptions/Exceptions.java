package com.chandalala.exceptions;

/**
 * Ways of handling errors
 * 1. Look Before You Leap>>>>using if statements to test if something is valid
 * 2. Try Catch blocks
 *
 * EXCEPTIONS
 * exception and runtime exception are classes defined in java.lang and each have their own subclass
 * normally when catching exceptions you specify which subclass of exception to catch than generalizing
 *
 * The call stack shown when an exception is printed, is a list of all the method calls at any particular point in
 * a java program execution
 *
 * Each thread of execution has its own call stack and the thread is shown as the first line of the stack trace
 * to make sense of the stacktrace you start reading at the bottom
 */

public class Exceptions {

    public static void main(String[] args) {
        int x=98;
        int y=0;
        System.out.println(divide(x,y));
        System.out.println(divide2(x,y));


    }

    //eg
    private static int divide(int x, int y){
        if (y != 0){
            return x/y;
        }
        else {
            return 0;
        }
    }


    private static int divide2(int x, int y){
        try{
            return x/y;
        }
        catch (ArithmeticException e){
            return 0;
        }
    }
}
