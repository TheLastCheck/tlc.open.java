/**
 * ****************************************************************************
 * Copyright (c) 2009-2015 The Last Check, LLC, All Rights Reserved
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ****************************************************************************
 */

package com.thelastcheck.io.x937.records.std2001;

import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Field;
import com.thelastcheck.io.base.FieldType;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;
import com.thelastcheck.io.x937.records.base.X937CheckDetailAddendumBRecordBase;

public class X937CheckDetailAddendumBRecordImpl extends X937CheckDetailAddendumBRecordBase {

    private static int maxFieldNumber = 5;
    private static Field fields[] = new Field[maxFieldNumber + 1];

    static {
        fields[0] = null;
        fields[1] = recordTypeField;
        fields[2] = new Field("MicrofilmArchiveSequenceNumber", 2, 2, 15, FieldType.STRING);
        fields[3] = new Field("ImageArchiveSequenceNumber", 3, 17, 24, FieldType.STRING);
        fields[4] = new Field("UserField", 4, 41, 24, FieldType.STRING);
        fields[5] = new Field("Reserved", 5, 65, 15, FieldType.STRING);
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

    public X937CheckDetailAddendumBRecord microfilmArchiveSequenceNumber(String value) {
        setField(value, field(2));
        return this;
    }

    public String imageArchiveSequenceNumber() {
        return getFieldAsString(field(3));
    }

    public X937CheckDetailAddendumBRecord imageArchiveSequenceNumber(String value) {
        setField(value, field(3));
        return this;
    }

    public String userField() {
        return getFieldAsString(field(4));
    }

    public X937CheckDetailAddendumBRecord userField(String value) {
        setField(value, field(4));
        return this;
    }

    public String reserved() {
        return getFieldAsString(field(5));
    }

    public X937CheckDetailAddendumBRecord reserved(String value) {
        setField(value, field(5));
        return this;
    }

}

