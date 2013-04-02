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
package com.dhbw.zombiz;

import com.dhbw.zombiz.gameEngine.logic.Player;
import com.dhbw.zombiz.output.audio.Sound;
import com.dhbw.zombiz.output.display.Screen;
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
        
        Sound testSound = new Sound("test");
        testSound.playSound();

        
        System.out.println("and NOT!");
    }
}
