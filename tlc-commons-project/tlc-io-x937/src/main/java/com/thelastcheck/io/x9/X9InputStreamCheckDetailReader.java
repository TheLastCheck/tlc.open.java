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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.collect.Lists;
import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.exception.RecordReaderException;
import com.thelastcheck.io.x9.parser.X937CheckDetailGraph;
import com.thelastcheck.io.x9.parser.X937ImageViewRecords;
import com.thelastcheck.io.x9.parser.X937RecordGraphRecordFilter;
import com.thelastcheck.io.x937.records.X937BundleControlRecord;
import com.thelastcheck.io.x937.records.X937BundleHeaderRecord;
import com.thelastcheck.io.x937.records.X937CashLetterControlRecord;
import com.thelastcheck.io.x937.records.X937CashLetterHeaderRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumARecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumBRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailAddendumCRecord;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;
import com.thelastcheck.io.x937.records.X937FileControlRecord;
import com.thelastcheck.io.x937.records.X937FileHeaderRecord;

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
                    checkGraphReady = checkGraphReady();
                    break;
                case X9Record.TYPE_BUNDLE_CONTROL:
                    checkGraphReady = checkGraphReady();
                    break;
            }
            if (checkGraphReady) {
                previousRecord = record;
            } else {
                graphFilter.filter(x9Record);
            }
        } while (!checkGraphReady);

        return makeGraphCopy(graphFilter.checkDetailGraph());

    }

    private X937CheckDetailGraph makeGraphCopy(X937CheckDetailGraph x937CheckDetailGraph) {

        final X937FileHeaderRecord fileHeaderRecord =
                (X937FileHeaderRecord) x937CheckDetailGraph.fileHeaderRecord().duplicate();

        final X937FileControlRecord fileControlRecord = x937CheckDetailGraph.fileControlRecord() == null ? null
                : (X937FileControlRecord) x937CheckDetailGraph.fileControlRecord().duplicate();

        final X937CashLetterHeaderRecord cashLetterHeaderRecord = x937CheckDetailGraph.cashLetterHeaderRecord() == null ? null
                : (X937CashLetterHeaderRecord) x937CheckDetailGraph.cashLetterHeaderRecord().duplicate();

        final X937CashLetterControlRecord cashLetterControlRecord = x937CheckDetailGraph.cashLetterControlRecord() == null ? null
                : (X937CashLetterControlRecord) x937CheckDetailGraph.cashLetterControlRecord().duplicate();

        final X937BundleHeaderRecord bundleHeaderRecord = x937CheckDetailGraph.bundleHeaderRecord() == null ? null
                : (X937BundleHeaderRecord) x937CheckDetailGraph.bundleHeaderRecord().duplicate();

        final X937BundleControlRecord bundleControlRecord = x937CheckDetailGraph.bundleControlRecord() == null ? null
                : (X937BundleControlRecord) x937CheckDetailGraph.bundleControlRecord().duplicate();

        final X937CheckDetailRecord checkDetailRecord = x937CheckDetailGraph.checkDetailRecord();
        final X937CheckDetailAddendumBRecord x937CheckDetailAddendumBRecord = x937CheckDetailGraph.checkDetailAddendumBRecord();
        final List<X937CheckDetailAddendumARecord> checkDetailAddendumARecords = Lists.newArrayList(x937CheckDetailGraph.checkDetailAddendumARecords());
        final List<X937CheckDetailAddendumCRecord> checkDetailAddendumCRecords = Lists.newArrayList(x937CheckDetailGraph.checkDetailAddendumCRecords());
        final List<X937ImageViewRecords> imageViewRecords = Lists.newArrayList(x937CheckDetailGraph.imageViewRecords());

        return new X937CheckDetailGraph() {

            @Override
            public X937CheckDetailRecord checkDetailRecord() {
                return checkDetailRecord;
            }

            @Override
            public List<X937CheckDetailAddendumARecord> checkDetailAddendumARecords() {
                return checkDetailAddendumARecords;
            }

            @Override
            public X937CheckDetailAddendumBRecord checkDetailAddendumBRecord() {
                return x937CheckDetailAddendumBRecord;
            }

            @Override
            public List<X937CheckDetailAddendumCRecord> checkDetailAddendumCRecords() {
                return checkDetailAddendumCRecords;
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

    public Stream<X937CheckDetailGraph> stream() {
        return StreamSupport.stream(this.spliterator(), false);
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
