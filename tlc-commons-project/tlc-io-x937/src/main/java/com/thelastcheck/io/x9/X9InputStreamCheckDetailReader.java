/*******************************************************************************
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
 ******************************************************************************/

package com.thelastcheck.io.x9;


import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.exception.RecordReaderException;
import com.thelastcheck.io.x9.parser.X937CheckDetailGraph;
import com.thelastcheck.io.x9.parser.X937RecordGraphRecordFilter;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;

public class X9InputStreamCheckDetailReader implements Iterable<X937CheckDetailGraph>, Closeable {

    private X9InputStreamRecordReader reader;
    private X937RecordGraphRecordFilter graphFilter;
    private Record previousRecord;

    private X937CheckDetailGraph cachedRecord;


    public X9InputStreamCheckDetailReader(InputStream inputStream) {
        this(inputStream, false);
    }

    public X9InputStreamCheckDetailReader(X9InputStreamRecordReader reader) {
        this.reader = reader;
        this.graphFilter = new X937RecordGraphRecordFilter();
    }

    public X9InputStreamCheckDetailReader(InputStream inputStream, boolean skipInvalidRecords) {
        this.reader = createInputStreamReader(inputStream, skipInvalidRecords);
        this.graphFilter = new X937RecordGraphRecordFilter();
    }

    public X937CheckDetailGraph readNextCheckDetail() throws IOException {
        if (cachedRecord != null) {
            X937CheckDetailGraph record = cachedRecord;
            cachedRecord = null;
            return record;
        }

        if (previousRecord != null) {
            graphFilter.filter(previousRecord);
        }
        boolean checkGraphReady = false;
        previousRecord = null;
        do {
            Record record = reader.nextRecord();
            if (record == null) {
                reader.close();
                return null;
            }
            X9Record x9Record = (X9Record) record;
            switch (x9Record.recordType()) {
                case X9Record.TYPE_CHECK_DETAIL:
                    checkGraphReady = checkGraphReady(x9Record);
                    break;
                case X9Record.TYPE_BUNDLE_CONTROL:
                    checkGraphReady = checkGraphReady(x9Record);
                    break;
            }
            if (checkGraphReady) {
                previousRecord = record;
            } else {
                graphFilter.filter(x9Record);
            }
        } while (!checkGraphReady);

        return graphFilter.checkDetailGraph();

    }

    private boolean checkGraphReady(X9Record x9Record) {
        X937CheckDetailGraph detailGraph = graphFilter.checkDetailGraph();
        X937CheckDetailRecord cdr = detailGraph.checkDetailRecord();
        return cdr != null;
    }


    private X9InputStreamRecordReader createInputStreamReader(InputStream inputStream, boolean skipInvalidRecords) {
        X9InputStreamRecordReader reader = new X9InputStreamRecordReader(inputStream, skipInvalidRecords);
        return reader;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public Iterator<X937CheckDetailGraph> iterator() {
        return new Iterator<X937CheckDetailGraph>() {

            public boolean hasNext() {
                /*
                 * If we already have a record waiting to be retrieved, then
                 * return true;
                 */
                if (cachedRecord != null) {
                    return true;
                }
                /*
                 * Go see if we have a next record. If not, return false. If
                 * true, store it for use in the next pickup.
                 */
                try {
                    cachedRecord = readNextCheckDetail();
                    if (cachedRecord == null) {
                        return false;
                    }
                } catch (Exception e) {
                    throw new RecordReaderException(e);
                }
                return true;
            }

            public X937CheckDetailGraph next() {
                X937CheckDetailGraph record = null;
                try {
                    record = readNextCheckDetail();
                } catch (Exception e) {
                    throw new RecordReaderException(e);
                }
                if (record == null) {
                    throw new NoSuchElementException();
                }
                return record;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

}
