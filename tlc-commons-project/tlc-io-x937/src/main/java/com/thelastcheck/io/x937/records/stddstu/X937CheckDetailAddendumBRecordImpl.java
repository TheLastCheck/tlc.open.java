/*******************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
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

package com.thelastcheck.io.x937.records.stddstu;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;
import com.thelastcheck.io.x937.records.base.X937CheckDetailAddendumBRecordBase;

public class X937CheckDetailAddendumBRecordImpl extends X937CheckDetailAddendumBRecordBase {

    private static int maxFieldNumber = 8;
    private static Field fields[] = new Field[maxFieldNumber+1];
    private Field localFieldCache[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("VariableSizeRecordIndicator", 2, 2, 1, FieldType.STRING);
        fields[3] = new Field("MicrofilmArchiveSequenceNumber", 3, 3, 15, FieldType.STRING);
        fields[4] = new Field("LengthOfImageArchiveSequenceNumber", 4, 18, 4, FieldType.INT);
        fields[5] = null;
        fields[6] = null;
        fields[7] = null;
        fields[8] = null;
    }


    /*
     * X937CheckDetailAddendumBRecordImpl
     */

    public X937CheckDetailAddendumBRecordImpl() {
        super();
    }

    public X937CheckDetailAddendumBRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937CheckDetailAddendumBRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937CheckDetailAddendumBRecordImpl(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    @Override
    protected void resetDynamicFields() {
        localFieldCache[5] = null;
        localFieldCache[6] = null;
        localFieldCache[7] = null;
        localFieldCache[8] = null;
    }

    @Override
    public int numberOfFields() {
        return maxFieldNumber;
    }

    @Override
    protected Field field(int fieldNumber) {
        if (fieldNumber < 1 || fieldNumber > maxFieldNumber) {
            throw new IllegalArgumentException(INVALID_FIELD_NUMBER);
        }
        if (localFieldCache[fieldNumber] == null) {
            switch (fieldNumber) {
            case 5:
                localFieldCache[5] = calculateFieldImageArchiveSequenceNumber();
                break;
            case 6:
                localFieldCache[6] = calculateFieldDescription();
                break;
            case 7:
                localFieldCache[7] = calculateFieldUserField();
                break;
            case 8:
                localFieldCache[8] = calculateFieldReserved();
                break;
            default:
                localFieldCache[fieldNumber] = fields[fieldNumber];
            }
        }
        return localFieldCache[fieldNumber];
    }

    private Field calculateFieldImageArchiveSequenceNumber() {
        // following is code for dynamically building field
        int X = 34;
        if (variableSizeRecordIndicator().equals("1")) {
            try {
                X = lengthOfImageArchiveSequenceNumberAsInt();
            } catch (InvalidDataException e) {
            }
        }
        return new Field("ImageArchiveSequenceNumber", 5, 22, X);
        // above is code for dynamically building field
    }

    private Field calculateFieldDescription() {
        // following is code for dynamically building field
        int X = 34;
        if (variableSizeRecordIndicator().equals("1")) {
            try {
                X = lengthOfImageArchiveSequenceNumberAsInt();
            } catch (InvalidDataException e) {
            }
        }
        return new Field("Description", 6, 22 + X, 15);
        // above is code for dynamically building field
    }

    private Field calculateFieldUserField() {
        // following is code for dynamically building field
        int X = 34;
        if (variableSizeRecordIndicator().equals("1")) {
            try {
                X = lengthOfImageArchiveSequenceNumberAsInt();
            } catch (InvalidDataException e) {
            }
        }
        return new Field("UserField", 7, 37 + X, 4);
        // above is code for dynamically building field
    }

    private Field calculateFieldReserved() {
        // following is code for dynamically building field
        int X = 34;
        if (variableSizeRecordIndicator().equals("1")) {
            try {
                X = lengthOfImageArchiveSequenceNumberAsInt();
            } catch (InvalidDataException e) {
            }
        }
        return new Field("Reserved", 8, 41 + X, 5);
        // above is code for dynamically building field
    }


    public String variableSizeRecordIndicator() {
        return getFieldAsString(field(2));
    }

    public X937CheckDetailAddendumBRecord variableSizeRecordIndicator(String value) {
        setField(value, field(2));
        return this;
    }

    public String microfilmArchiveSequenceNumber() {
        return getFieldAsString(field(3));
    }

    public X937CheckDetailAddendumBRecord microfilmArchiveSequenceNumber(String value) {
        setField(value, field(3));
        return this;
    }

    public String lengthOfImageArchiveSequenceNumber() {
        return getFieldAsString(field(4));
    }

    private X937CheckDetailAddendumBRecord lengthOfImageArchiveSequenceNumber(String value) {
        setField(value, field(4));
        return this;
    }

    public int lengthOfImageArchiveSequenceNumberAsInt()
        throws InvalidDataException {
        return getFieldAsInt(field(4));
    }

    private X937CheckDetailAddendumBRecord lengthOfImageArchiveSequenceNumber(int value) {
        setField(value, field(4));
        return this;
    }

    public String imageArchiveSequenceNumber() {
        return getFieldAsString(field(5));
    }

    public X937CheckDetailAddendumBRecord imageArchiveSequenceNumber(String value) {
        if (variableSizeRecordIndicator().equals("0")) {
            setField(34, field(4));
            setField(value, field(5));
            return this;
        }
        int currentLength = 0;
        try {
            currentLength = lengthOfImageArchiveSequenceNumberAsInt();
        } catch (InvalidDataException e) {
        }
        if (currentLength != value.length()) {
            allocateNewRecord(currentLength, value.length(), 4, 5);
        }
        setField(value, field(5));
        return this;
    }

    public String description() {
        return getFieldAsString(field(6));
    }

    public X937CheckDetailAddendumBRecord description(String value) {
        setField(value, field(6));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(7));
    }

    public X937CheckDetailAddendumBRecord userField(String value) {
        setField(value, field(7));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(8));
    }

    public X937CheckDetailAddendumBRecord reserved(String value) {
        setField(value, field(8));
        return this;
    }

}

