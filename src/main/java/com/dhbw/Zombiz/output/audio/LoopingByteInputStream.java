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

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**The LoopingByteInputStream loops a Sound until the close() function is called.
 * 
 * @author Christoph Schabert
 * @version 1.0
 */
public class LoopingByteInputStream extends ByteArrayInputStream {

    private boolean closed;
    
    /**  Creates a new LoopingByteInputStream with the specified
     *   byte array. The array is not copied. 
     *   
     * @param buffer byte array with the Input Stream Buffer
     */
    public LoopingByteInputStream(byte[] buffer) {
        super(buffer);
        closed = false;
    }

    
    /**calculates the length of the sound
     * 
     * @param buffer the Sound Byte Array
     * @param offset the point to Start
     * @param length the length of the Sound
     */
    public int read(byte[] buffer, int offset, int length) {
        if (closed) {
            return -1;
        }
        int totalBytesRead = 0;

        while (totalBytesRead < length) {
            int numBytesRead = super.read(buffer,
                offset + totalBytesRead,
                length - totalBytesRead);

            if (numBytesRead > 0) {
                totalBytesRead += numBytesRead;
            }
            else {
                reset();
            }
        }
        return totalBytesRead;
    }


    /**closes the input Stream,
     * the read method will returning a -1.
     */
    public void close() throws IOException {
        super.close();
        closed = true;
    }

}