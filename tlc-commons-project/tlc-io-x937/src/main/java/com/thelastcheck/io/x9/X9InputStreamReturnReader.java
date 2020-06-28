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

import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.exception.RecordReaderException;
import com.thelastcheck.io.x9.parser.X937ImageViewRecords;
import com.thelastcheck.io.x9.parser.X937RecordGraphRecordFilter;
import com.thelastcheck.io.x9.parser.X937ReturnGraph;
import com.thelastcheck.io.x937.records.X937BundleControlRecord;
import com.thelastcheck.io.x937.records.X937BundleHeaderRecord;
import com.thelastcheck.io.x937.records.X937CashLetterControlRecord;
import com.thelastcheck.io.x937.records.X937CashLetterHeaderRecord;
import com.thelastcheck.io.x937.records.X937FileControlRecord;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumARecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumBRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumCRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumDRecord;
import com.thelastcheck.io.x937.records.X937ReturnRecord;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class X9InputStreamReturnReader implements Iterable<X937ReturnGraph>, Closeable {

    private X9InputStreamRecordReader reader;
    private X937RecordGraphRecordFilter graphFilter;
    private Record previousRecord;

    private X937ReturnGraph cachedRecord;


    public X9InputStreamReturnReader(InputStream inputStream) {
        this(inputStream, false);
    }

    public X9InputStreamReturnReader(X9InputStreamRecordReader reader) {
        this.reader = reader;
        this.graphFilter = new X937RecordGraphRecordFilter();
    }

    public X9InputStreamReturnReader(InputStream inputStream, boolean skipInvalidRecords) {
        this.reader = createInputStreamReader(inputStream, skipInvalidRecords);
        this.graphFilter = new X937RecordGraphRecordFilter();
    }

    public X937ReturnGraph readNextReturn() throws IOException {
        if (cachedRecord != null) {
            X937ReturnGraph record = cachedRecord;
            cachedRecord = null;
            return record;
        }

        if (previousRecord != null) {
            graphFilter.filter(previousRecord);
        }
        boolean returnGraphReady = false;
        previousRecord = null;
        do {
            Record record = reader.nextRecord();
            if (record == null) {
                reader.close();
                return null;
            }
            X9Record x9Record = (X9Record) record;
            switch (x9Record.recordType()) {
                case X9Record.TYPE_RETURN:
                case X9Record.TYPE_BUNDLE_CONTROL:
                    returnGraphReady = checkGraphReady();
                    break;
            }
            if (returnGraphReady) {
                previousRecord = record;
            } else {
                graphFilter.filter(x9Record);
            }
        } while (!returnGraphReady);

        return makeGraphCopy(graphFilter.returnGraph());

    }

    private X937ReturnGraph makeGraphCopy(X937ReturnGraph x937ReturnGraph) {

        final X937FileHeaderRecord fileHeaderRecord =
                (X937FileHeaderRecord) x937ReturnGraph.fileHeaderRecord().duplicate();

        final X937FileControlRecord fileControlRecord = x937ReturnGraph.fileControlRecord() == null ? null
                : (X937FileControlRecord) x937ReturnGraph.fileControlRecord().duplicate();

        final X937CashLetterHeaderRecord cashLetterHeaderRecord = x937ReturnGraph.cashLetterHeaderRecord() == null ? null
                : (X937CashLetterHeaderRecord) x937ReturnGraph.cashLetterHeaderRecord().duplicate();

        final X937CashLetterControlRecord cashLetterControlRecord = x937ReturnGraph.cashLetterControlRecord() == null ? null
                : (X937CashLetterControlRecord) x937ReturnGraph.cashLetterControlRecord().duplicate();

        final X937BundleHeaderRecord bundleHeaderRecord = x937ReturnGraph.bundleHeaderRecord() == null ? null
                : (X937BundleHeaderRecord) x937ReturnGraph.bundleHeaderRecord().duplicate();

        final X937BundleControlRecord bundleControlRecord = x937ReturnGraph.bundleControlRecord() == null ? null
                : (X937BundleControlRecord) x937ReturnGraph.bundleControlRecord().duplicate();

        final X937ReturnRecord returnRecord = x937ReturnGraph.returnRecord() == null ? null
                : (X937ReturnRecord) x937ReturnGraph.returnRecord().duplicate();

        final List<X937ReturnAddendumARecord> returnAddendumARecords = new ArrayList<>(x937ReturnGraph.returnAddendumARecords());

        final X937ReturnAddendumBRecord returnAddendumBRecord = x937ReturnGraph.returnAddendumBRecord() == null ? null
                : (X937ReturnAddendumBRecord) x937ReturnGraph.returnAddendumBRecord().duplicate();

        final X937ReturnAddendumCRecord returnAddendumCRecord = x937ReturnGraph.returnAddendumCRecord() == null ? null
                : (X937ReturnAddendumCRecord) x937ReturnGraph.returnAddendumCRecord().duplicate();

        final List<X937ReturnAddendumDRecord> returnAddendumDRecords = new ArrayList<>(x937ReturnGraph.returnAddendumDRecords());

        final List<X937ImageViewRecords> imageViewRecords = new ArrayList<>(x937ReturnGraph.imageViewRecords());

        return new X937ReturnGraph() {

            @Override
            public X937ReturnRecord returnRecord() {
                return returnRecord;
            }

            @Override
            public List<X937ReturnAddendumARecord> returnAddendumARecords() {
                return returnAddendumARecords;
            }

            @Override
            public X937ReturnAddendumBRecord returnAddendumBRecord() {
                return returnAddendumBRecord;
            }

            @Override
            public X937ReturnAddendumCRecord returnAddendumCRecord() {
                return returnAddendumCRecord;
            }

            @Override
            public List<X937ReturnAddendumDRecord> returnAddendumDRecords() {
                return returnAddendumDRecords;
            }

            @Override
            public List<X937ImageViewRecords> imageViewRecords() {
                return imageViewRecords;
            }

            @Override
            public X937BundleHeaderRecord bundleHeaderRecord() {
                return bundleHeaderRecord;
            }

            @Override
            public X937BundleControlRecord bundleControlRecord() {
                return bundleControlRecord;
            }

            @Override
            public X937CashLetterHeaderRecord cashLetterHeaderRecord() {
                return cashLetterHeaderRecord;
            }

            @Override
            public X937CashLetterControlRecord cashLetterControlRecord() {
                return cashLetterControlRecord;
            }

            @Override
            public X937FileHeaderRecord fileHeaderRecord() {
                return fileHeaderRecord;
            }

            @Override
            public X937FileControlRecord fileControlRecord() {
                return fileControlRecord;
            }
        };

    }

    private boolean checkGraphReady() {
        X937ReturnGraph detailGraph = graphFilter.returnGraph();
        X937ReturnRecord returnRecord = detailGraph.returnRecord();
        return returnRecord != null;
    }

    private X9InputStreamRecordReader createInputStreamReader(InputStream inputStream, boolean skipInvalidRecords) {
        X9InputStreamRecordReader reader = new X9InputStreamRecordReader(inputStream, skipInvalidRecords);
        return reader;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public Stream<X937ReturnGraph> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    public Iterator<X937ReturnGraph> iterator() {
        return new Iterator<X937ReturnGraph>() {

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
                    cachedRecord = readNextReturn();
                    if (cachedRecord == null) {
                        return false;
                    }
                } catch (Exception e) {
                    throw new RecordReaderException(e);
                }
                return true;
            }

            public X937ReturnGraph next() {
                X937ReturnGraph record;
                try {
                    record = readNextReturn();
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
