package com.dhbw.zombiz;

import com.dhbw.zombiz.output.audio.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("We Are Zombizzzzzzzzzzzzz" );
        

        
        Thread t1 =   new Thread( new Audio("test") );
        t1.  start();
   
    }
}
