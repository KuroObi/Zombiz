/*******************************************************************************
 * Copyright (c) 2013 DHBW.
 * This source is subject to the DHBW Permissive License.
 * Please see the License.txt file for more information.
 * All other rights reserved.
 * 
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY 
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 *Project: Zombiz
 *Package: com.dhbw.zombiz
 *
 * 
 *Contributors:
 * -Christoph Schabert

 ********************************************************************************/

package com.dhbw.Zombiz;

import com.dhbw.Zombiz.gameEngine.logic.Player;
import com.dhbw.Zombiz.output.audio.Sound;
import com.dhbw.Zombiz.output.display.Screen;
import com.dhbw.Zombiz.gameEngine.parser.*;



import java.util.ArrayList;
import java.util.List;

import com.dhbw.Zombiz.gameEngine.logic.Item;
import com.dhbw.Zombiz.gameEngine.logic.Actor;
import com.dhbw.Zombiz.gameEngine.logic.Room;
import com.dhbw.Zombiz.gameEngine.parser.XmlParser;
import com.dhbw.Zombiz.output.audio.*;

/**
 * Starts the Main Game
 * 
 * at this State only for test purpose
 *
 */
@SuppressWarnings("unused")
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("We Are Zombizzzzzzzzzzzzz" );

        //Audio Test
<<<<<<< HEAD
        Sound testSound = new Sound("test");
        Sound overSound = new Sound("test2");
        testSound.playSound();
        overSound.playSound();
        
=======
        

       // Sound testSound = new Sound("test");
       // testSound.playSound();

       //Screen menu = new Screen();
       
        XmlParser p = new XmlParser("src/main/resources/XML/chapter1.xml");
        System.out.println("Numbers "+p.getListOfActors().size());
  
        
        
        
       

        //Audio Test
        /*
        Thread t1 =   new Thread( new Audio("test") );
        t1.  start();
   		*/
>>>>>>> Jan
        
        System.out.println("and NOT!");
    }
}
