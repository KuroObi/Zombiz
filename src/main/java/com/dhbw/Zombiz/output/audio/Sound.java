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
 *Package: com.dhbw.zombiz.output.audio
 *
 *Contributors:
 * -Christoph Schabert

 ********************************************************************************/
package com.dhbw.Zombiz.output.audio;

/**the Sound Class contains a sound/musik part
 * in a Byte Array
 * 
 * @author Christoph Schabert
 * @version 1.0
 */
public class Sound {

    private byte[] sound;

    /**the Constructor creates a new sound Byte Array
     * 
     * @param samples the Sound Byte Array
     */
    public Sound(byte[] sounds) {
        this.sound = sounds;
    }


    /**Returns the Sound Byte Array
     * 
     * @return the sound Byte Array
     */
    public byte[] getSamples() {
        return sound;
    }

}