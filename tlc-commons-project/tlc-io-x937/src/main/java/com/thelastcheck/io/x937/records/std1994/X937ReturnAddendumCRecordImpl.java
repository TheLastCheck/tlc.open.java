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

package com.thelastcheck.io.x937.records.std1994;

import java.util.Date;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.commons.base.fields.OnUsField;
import com.thelastcheck.commons.base.fields.RoutingNumber;
import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937ReturnAddendumCRecord;
import com.thelastcheck.io.x937.records.base.X937ReturnAddendumCRecordBase;

public class X937ReturnAddendumCRecordImpl extends X937ReturnAddendumCRecordBase {

    private static int maxFieldNumber = 5;
    private static Field fields[] = new Field[maxFieldNumber+1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("MicrofilmArchiveSequenceNumber", 2, 2, 15, FieldType.STRING);
        fields[3] = new Field("ImageArchiveSequenceNumber", 3, 17, 15, FieldType.STRING);
        fields[4] = new Field("UserField", 4, 32, 24, FieldType.STRING);
        fields[5] = new Field("Reserved", 5, 56, 24, FieldType.STRING);
    }


    /*
     * X937ReturnAddendumCRecordImpl
     */

    public X937ReturnAddendumCRecordImpl() {
        super();
    }

    public X937ReturnAddendumCRecordImpl(int stdLevel) {
        super(stdLevel);
    }

    public X937ReturnAddendumCRecordImpl(String encoding, int stdLevel) {
        super(encoding, stdLevel);
    }

    public X937ReturnAddendumCRecordImpl(ByteArray record, int stdLevel) {
        super(record, stdLevel);
    }

    @Override
    protected void resetDynamicFields() {
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
        return fields[fieldNumber];
    }


    public String microfilmArchiveSequenceNumber() {
        return getFieldAsString(field(2));
    }

    public X937ReturnAddendumCRecord microfilmArchiveSequenceNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public String imageArchiveSequenceNumber() {
        return getFieldAsString(field(3));
    }

    public X937ReturnAddendumCRecord imageArchiveSequenceNumber(String value) {
        setField(value, field(3));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(4));
    }

    public X937ReturnAddendumCRecord userField(String value) {
        setField(value, field(4));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(5));
    }

    public X937ReturnAddendumCRecord reserved(String value) {
        setField(value, field(5));
        return this;
    }

}

