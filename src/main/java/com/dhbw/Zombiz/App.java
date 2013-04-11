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

import com.dhbw.Zombiz.gameEngine.logic.Actor;
import com.dhbw.Zombiz.output.audio.Sound;
import com.dhbw.Zombiz.output.display.Screen;
import com.dhbw.Zombiz.gameEngine.parser.*;



import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import com.dhbw.Zombiz.gameEngine.logic.BuildRoom;
import com.dhbw.Zombiz.gameEngine.logic.Inventory;
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
        

    	Inventory inventory = new Inventory();
        Screen frame = new Screen();
        
        
    }
}
