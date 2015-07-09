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
package com.thelastcheck.io.x9.factory;

import com.thelastcheck.commons.buffer.ByteArray;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.exception.InvalidFormatException;
import com.thelastcheck.io.x9.X9Record;

public interface X9RecordFactory {

    /**
     * Returns the appropriate X9 record object based on the contents of the
     * ByteArray.
     * 
     * @param record
     *            is a ByteArray that contains an X9 record.
     * @return an instance of an X9 class that knows how to retrieve and set
     *         field values in the X9 record contained in the ByteArray.
     * @throws InvalidFormatException
     */
    X9Record newX9Record(ByteArray record) throws InvalidFormatException;

    /**
     * Returns a new empty X9 record object based on the recordType value.
     * 
     * @param recordType
     *            is a ByteArray that contains an X9 record.
     * @return an instance of an X9 class that knows how to retrieve and set
     *         field values in the X9 record identified by the recordType.
     * @throws InvalidFormatException
     */
    Record newX9Record(int recordType) throws InvalidFormatException;

}