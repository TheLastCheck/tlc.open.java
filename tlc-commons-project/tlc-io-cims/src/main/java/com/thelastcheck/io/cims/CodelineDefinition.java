/*******************************************************************************
 * Copyright (c) 2009-2016 The Last Check, LLC, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.thelastcheck.io.cims;

import com.thelastcheck.commons.buffer.ByteArray;

import java.nio.ByteOrder;

public abstract class CodelineDefinition {

    protected ByteArray buffer;

    public CodelineDefinition() {
        buffer = new ByteArray(getBufferLength());
    }

    protected abstract int getBufferLength();

    public byte[] getBuffer() {
        return buffer.getArray().value;
    }

    public void reset() {
        buffer.fill((byte) 0);
    }

    protected boolean getBufferValueAsBoolean(int fieldDisp, int fieldLen) {
        switch (fieldLen) {
            case 1:
                return buffer.readAsByte(fieldDisp) == 1;
            case 2:
                return buffer.readAsShort(fieldDisp) == 1;
            case 4:
                return buffer.readAsInt(fieldDisp) == 1;
        }
        return false;
    }

    protected void setBufferValueAsBoolean(boolean b, int fieldDisp, int fieldLen) {
        switch (fieldLen) {
            case 1:
                buffer.write(b ? (byte) 1 : (byte) 0, fieldDisp);
                break;
            case 2:
                buffer.write(b ? (short) 1 : (short) 0, fieldDisp);
                break;
            case 4:
                buffer.write(b ? 1 : 0, fieldDisp);
                break;
        }
        return;
    }

    public void setBuffer(byte[] newBuffer) {
        buffer = new ByteArray(getBufferLength(), ByteOrder.LITTLE_ENDIAN);
        int len = newBuffer.length;
        if (len > getBufferLength()) {
            len = getBufferLength();
        }
        buffer.write(newBuffer, 0, len, 0);
    }
}
