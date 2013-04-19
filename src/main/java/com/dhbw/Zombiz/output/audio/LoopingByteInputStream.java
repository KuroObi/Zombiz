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

/**
    The LoopingByteInputStream is a ByteArrayInputStream that
    loops indefinitely. The looping stops when the close() method
    is called.
    <p>Possible ideas to extend this class:<ul>
    <li>Add an option to only loop a certain number of times.
    </ul>
*/
public class LoopingByteInputStream extends ByteArrayInputStream {

    private boolean closed;

    /**
        Creates a new LoopingByteInputStream with the specified
        byte array. The array is not copied.
    */
    public LoopingByteInputStream(byte[] buffer) {
        super(buffer);
        closed = false;
    }


    /**
        Reads <code>length</code> bytes from the array. If the
        end of the array is reached, the reading starts over from
        the beginning of the array. Returns -1 if the array has
        been closed.
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


    /**
        Closes the stream. Future calls to the read() methods
        will return 1.
    */
    public void close() throws IOException {
        super.close();
        closed = true;
    }

}