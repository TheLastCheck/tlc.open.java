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
package com.thelastcheck.io.x9.parser;

import java.util.ArrayList;
import java.util.List;

import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.base.RecordFilter;
import com.thelastcheck.io.x9.X9Record;
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
import com.thelastcheck.io.x937.records.X937ImageViewAnalysisRecord;
import com.thelastcheck.io.x937.records.X937ImageViewDataRecord;
import com.thelastcheck.io.x937.records.X937ImageViewDetailRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumARecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumBRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumCRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumDRecord;
import com.thelastcheck.io.x937.records.X937ReturnRecord;

public class X937RecordGraphRecordFilter implements RecordFilter {
    // private static Log log =
    // LogFactory.getLog(X937RecordGraphRecordFilter.class);

    private boolean                              returnFile = false;
    private X937CheckDetailGraph                 checkDetailGraph;
    private X937ReturnGraph                      returnGraph;

    private X937FileHeaderRecord                 fileHeaderRecord;
    private X937FileControlRecord                fileControlRecord;
    private X937CashLetterHeaderRecord           cashLetterHeaderRecord;
    private X937CashLetterControlRecord          cashLetterControlRecord;
    private X937BundleHeaderRecord               bundleHeaderRecord;
    private X937BundleControlRecord              bundleControlRecord;

    private X937CheckDetailRecord                checkDetailRecord;
    private List<X937CheckDetailAddendumARecord> checkDetailAddendumARecords;
    private X937CheckDetailAddendumBRecord       checkDetailAddendumBRecord;
    private List<X937CheckDetailAddendumCRecord> checkDetailAddendumCRecords;

    private X937ReturnRecord                     returnRecord;
    private List<X937ReturnAddendumARecord>      returnAddendumARecords;
    private X937ReturnAddendumBRecord            returnAddendumBRecord;
    private X937ReturnAddendumCRecord            returnAddendumCRecord;
    private List<X937ReturnAddendumDRecord>      returnAddendumDRecords;

    private List<X937ImageViewRecords>           imageViewRecords;

    private X937ImageViewRecordsImpl             currentImageViewRecords;

    public X937RecordGraphRecordFilter() {
        checkDetailGraph = makeCheckDetailGraph();
        returnGraph = makeReturnGraph();
    }

    public X937FileGraph fileGraph() {
        return checkDetailGraph;
    }

    public X937CashLetterGraph cashLetterGraph() {
        return checkDetailGraph;
    }

    public X937BundleGraph bundleGraph() {
        return checkDetailGraph;
    }

    public X937CheckDetailGraph checkDetailGraph() {
        return checkDetailGraph;
    }

    public X937ReturnGraph returnGraph() {
        return returnGraph;
    }

    private X937ReturnGraph makeReturnGraph() {
        returnAddendumARecords = new ArrayList<>();
        returnAddendumDRecords = new ArrayList<>();
        return new X937ReturnGraph() {

            public X937FileHeaderRecord fileHeaderRecord() {
                return fileHeaderRecord;
            }

            public X937FileControlRecord fileControlRecord() {
                return fileControlRecord;
            }

            public X937CashLetterHeaderRecord cashLetterHeaderRecord() {
                return cashLetterHeaderRecord;
            }

            public X937CashLetterControlRecord cashLetterControlRecord() {
                return cashLetterControlRecord;
            }

            public X937BundleHeaderRecord bundleHeaderRecord() {
                return bundleHeaderRecord;
            }

            public X937BundleControlRecord bundleControlRecord() {
                return bundleControlRecord;
            }

            public X937ReturnRecord returnRecord() {
                return returnRecord;
            }

            public List<X937ReturnAddendumDRecord> returnAddendumDRecords() {
                return returnAddendumDRecords;
            }

            public X937ReturnAddendumCRecord returnAddendumCRecord() {
                return returnAddendumCRecord;
            }

            public X937ReturnAddendumBRecord returnAddendumBRecord() {
                return returnAddendumBRecord;
            }

            public List<X937ReturnAddendumARecord> returnAddendumARecords() {
                return returnAddendumARecords;
            }

            public List<X937ImageViewRecords> imageViewRecords() {
                return imageViewRecords;
            }
        };
    }

    private X937CheckDetailGraph makeCheckDetailGraph() {
        checkDetailAddendumARecords = new ArrayList<>();
        checkDetailAddendumCRecords = new ArrayList<>();
        imageViewRecords = new ArrayList<>();
        return new X937CheckDetailGraph() {

            public X937FileHeaderRecord fileHeaderRecord() {
                return fileHeaderRecord;
            }

            public X937FileControlRecord fileControlRecord() {
                return fileControlRecord;
            }

            public X937CashLetterHeaderRecord cashLetterHeaderRecord() {
                return cashLetterHeaderRecord;
            }

            public X937CashLetterControlRecord cashLetterControlRecord() {
                return cashLetterControlRecord;
            }

            public X937BundleHeaderRecord bundleHeaderRecord() {
                return bundleHeaderRecord;
            }

            public X937BundleControlRecord bundleControlRecord() {
                return bundleControlRecord;
            }

            public List<X937ImageViewRecords> imageViewRecords() {
                return imageViewRecords;
            }

            public X937CheckDetailRecord checkDetailRecord() {
                return checkDetailRecord;
            }

            public List<X937CheckDetailAddendumCRecord> checkDetailAddendumCRecords() {
                return checkDetailAddendumCRecords;
            }

            public X937CheckDetailAddendumBRecord checkDetailAddendumBRecord() {
                return checkDetailAddendumBRecord;
            }

            public List<X937CheckDetailAddendumARecord> checkDetailAddendumARecords() {
                return checkDetailAddendumARecords;
            }
        };
    }

    public Record filter(Record record) {
        X9Record x9Record = (X9Record) record;
        switch (x9Record.recordType()) {
        case X9Record.TYPE_CHECK_DETAIL:
            clearCheckReturnRecords();
            checkDetailRecord = (X937CheckDetailRecord) x9Record;
            break;
        case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_A:
            checkDetailAddendumARecords.add((X937CheckDetailAddendumARecord) x9Record);
            break;
        case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_B:
            checkDetailAddendumBRecord = (X937CheckDetailAddendumBRecord) x9Record;
            break;
        case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_C:
            checkDetailAddendumCRecords.add((X937CheckDetailAddendumCRecord) x9Record);
            break;
        case X9Record.TYPE_RETURN_ADDENDUM_A:
            returnAddendumARecords.add((X937ReturnAddendumARecord) x9Record);
            break;
        case X9Record.TYPE_RETURN_ADDENDUM_B:
            returnAddendumBRecord = (X937ReturnAddendumBRecord) x9Record;
            break;
        case X9Record.TYPE_RETURN_ADDENDUM_C:
            returnAddendumCRecord = (X937ReturnAddendumCRecord) x9Record;
            break;
        case X9Record.TYPE_RETURN_ADDENDUM_D:
            returnAddendumDRecords.add((X937ReturnAddendumDRecord) x9Record);
            break;
        case X9Record.TYPE_RETURN:
            clearCheckReturnRecords();
            returnRecord = (X937ReturnRecord) x9Record;
            break;
        case X9Record.TYPE_IMAGE_VIEW_DETAIL:
            currentImageViewRecords = new X937ImageViewRecordsImpl();
            imageViewRecords.add(currentImageViewRecords);
            currentImageViewRecords.imageViewDetailRecord = (X937ImageViewDetailRecord) x9Record;
            break;
        case X9Record.TYPE_IMAGE_VIEW_DATA:
            if (currentImageViewRecords == null) {
                currentImageViewRecords = new X937ImageViewRecordsImpl();
                imageViewRecords.add(currentImageViewRecords);
            }
            currentImageViewRecords.imageViewDataRecord = (X937ImageViewDataRecord) x9Record;
            break;
        case X9Record.TYPE_IMAGE_VIEW_ANALYSIS:
            if (currentImageViewRecords == null) {
                currentImageViewRecords = new X937ImageViewRecordsImpl();
                imageViewRecords.add(currentImageViewRecords);
            }
            currentImageViewRecords.imageViewAnalysisRecord = (X937ImageViewAnalysisRecord) x9Record;
            currentImageViewRecords = null;
            break;
        case X9Record.TYPE_BUNDLE_CONTROL:
            clearCheckReturnRecords();
            bundleControlRecord = (X937BundleControlRecord) x9Record;
            break;
        case X9Record.TYPE_BUNDLE_HEADER:
            bundleHeaderRecord = (X937BundleHeaderRecord) x9Record;
            String cashLetterCollectionType = cashLetterHeaderRecord.collectionTypeIndicator();
            if (cashLetterCollectionType.equals("99")) {
                String type = bundleHeaderRecord.collectionTypeIndicator();
                if (type.equals("03") || type.equals("04") || type.equals("05") || type.equals("06")) {
                    returnFile = true;
                }
            }
            break;
        case X9Record.TYPE_CASH_LETTER_CONTROL:
            clearCheckReturnRecords();
            clearBundleRecords();
            cashLetterControlRecord = (X937CashLetterControlRecord) x9Record;
            break;
        case X9Record.TYPE_CASH_LETTER_HEADER:
            cashLetterHeaderRecord = (X937CashLetterHeaderRecord) x9Record;
            String type = cashLetterHeaderRecord.collectionTypeIndicator();
            if (type.equals("03") || type.equals("04") || type.equals("05") || type.equals("06")) {
                returnFile = true;
            }
            break;
        case X9Record.TYPE_FILE_CONTROL:
            clearCheckReturnRecords();
            clearBundleRecords();
            clearCashLetterRecords();
            fileControlRecord = (X937FileControlRecord) x9Record;
            break;
        case X9Record.TYPE_FILE_HEADER:
            fileHeaderRecord = (X937FileHeaderRecord) x9Record;
            break;
        default:
        }
        return record;
    }

    private void clearCheckReturnRecords() {
        if (returnFile) {
            returnRecord = null;
            returnAddendumARecords.clear();
            returnAddendumBRecord = null;
            returnAddendumCRecord = null;
            returnAddendumDRecords.clear();
        } else {
            checkDetailRecord = null;
            checkDetailAddendumARecords.clear();
            checkDetailAddendumBRecord = null;
            checkDetailAddendumCRecords.clear();
        }
        imageViewRecords.clear();
        currentImageViewRecords = null;
    }

    private void clearBundleRecords() {
        bundleHeaderRecord = null;
        bundleControlRecord = null;
    }

    private void clearCashLetterRecords() {
        cashLetterHeaderRecord = null;
        cashLetterControlRecord = null;
    }

    private static class X937ImageViewRecordsImpl implements X937ImageViewRecords {

        private X937ImageViewDetailRecord   imageViewDetailRecord;
        private X937ImageViewDataRecord     imageViewDataRecord;
        private X937ImageViewAnalysisRecord imageViewAnalysisRecord;

        public X937ImageViewDetailRecord imageViewDetailRecord() {
            return imageViewDetailRecord;
        }

        public X937ImageViewDataRecord imageViewDataRecord() {
            return imageViewDataRecord;
        }

        public X937ImageViewAnalysisRecord imageViewAnalysisRecord() {
            return imageViewAnalysisRecord;
        }
    };
}
