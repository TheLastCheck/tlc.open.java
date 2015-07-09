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

import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9InputStreamRecordReader;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x937.records.X937AccountTotalsDetailRecord;
import com.thelastcheck.io.x937.records.X937BoxSummaryRecord;
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
import com.thelastcheck.io.x937.records.X937NonHitTotalsDetailRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumARecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumBRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumCRecord;
import com.thelastcheck.io.x937.records.X937ReturnAddendumDRecord;
import com.thelastcheck.io.x937.records.X937ReturnRecord;
import com.thelastcheck.io.x937.records.X937RoutingNumberSummaryRecord;

public class X9InputStreamRecordParser {

    private final X9InputStreamRecordReader   reader;
    private final X937RecordGraphRecordFilter filter;
    private final X937DefaultRecordHandler    defaultHandler              = new X937DefaultRecordHandler();
    private X937FileHeaderHandler             fileHeaderHandler           = defaultHandler;
    private X937FileControlHandler            fileControlHandler          = defaultHandler;
    private X937CheckDetailHandler            checkDetailHandler          = defaultHandler;
    private X937AccountTotalsDetailHandler    accountTotalsDetailHandler  = defaultHandler;
    private X937BoxSummaryHandler             boxSummaryHandler           = defaultHandler;
    private X937BundleControlHandler          bundleControlHandler        = defaultHandler;
    private X937BundleHeaderHandler           bundleHeaderHandler         = defaultHandler;
    private X937CashLetterControlHandler      cashLetterControlHandler    = defaultHandler;
    private X937CashLetterHeaderHandler       cashLetterHeaderHandler     = defaultHandler;
    private X937CheckDetailAddendumAHandler   checkDetailAddendumAHandler = defaultHandler;
    private X937CheckDetailAddendumBHandler   checkDetailAddendumBHandler = defaultHandler;
    private X937CheckDetailAddendumCHandler   checkDetailAddendumCHandler = defaultHandler;
    private X937ImageViewAnalysisHandler      imageViewAnalysisHandler    = defaultHandler;
    private X937ImageViewDataHandler          imageViewDataHandler        = defaultHandler;
    private X937ImageViewDetailHandler        imageViewDetailHandler      = defaultHandler;
    private X937NonHitTotalsDetailHandler     nonHitTotalsHandler         = defaultHandler;
    private X937ReturnHandler                 returnHandler               = defaultHandler;
    private X937ReturnAddendumAHandler        returnAddendumAHandler      = defaultHandler;
    private X937ReturnAddendumBHandler        returnAddendumBHandler      = defaultHandler;
    private X937ReturnAddendumCHandler        returnAddendumCHandler      = defaultHandler;
    private X937ReturnAddendumDHandler        returnAddendumDHandler      = defaultHandler;
    private X937RoutingNumberSummaryHandler   routingNumberSummaryHandler = defaultHandler;

    public X9InputStreamRecordParser(X9InputStreamRecordReader reader) {
        this.reader = reader;
        filter = new X937RecordGraphRecordFilter();
        reader.addFilter(filter);
    }

    public void readAllRecords() {
        for (Record record : reader) {
            handle((X9Record) record);
        }
    }

    private void handle(X9Record record) {
        switch (record.recordType()) {
        case X9Record.TYPE_ACCOUNT_TOTALS_DETAIL:
            accountTotalsDetailHandler.handle(filter.cashLetterGraph(), (X937AccountTotalsDetailRecord) record);
            break;
        case X9Record.TYPE_BOX_SUMMARY:
            boxSummaryHandler.handle(filter.cashLetterGraph(), (X937BoxSummaryRecord) record);
            break;
        case X9Record.TYPE_BUNDLE_CONTROL:
            bundleControlHandler.handle(filter.bundleGraph(), (X937BundleControlRecord) record);
            break;
        case X9Record.TYPE_BUNDLE_HEADER:
            bundleHeaderHandler.handle(filter.bundleGraph(), (X937BundleHeaderRecord) record);
            break;
        case X9Record.TYPE_CASH_LETTER_CONTROL:
            cashLetterControlHandler.handle(filter.cashLetterGraph(), (X937CashLetterControlRecord) record);
            break;
        case X9Record.TYPE_CASH_LETTER_HEADER:
            cashLetterHeaderHandler.handle(filter.cashLetterGraph(), (X937CashLetterHeaderRecord) record);
            break;
        case X9Record.TYPE_CHECK_DETAIL:
            checkDetailHandler.handle(filter.checkDetailGraph(), (X937CheckDetailRecord) record);
            break;
        case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_A:
            checkDetailAddendumAHandler.handle(filter.checkDetailGraph(), (X937CheckDetailAddendumARecord) record);
            break;
        case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_B:
            checkDetailAddendumBHandler.handle(filter.checkDetailGraph(), (X937CheckDetailAddendumBRecord) record);
            break;
        case X9Record.TYPE_CHECK_DETAIL_ADDENDUM_C:
            checkDetailAddendumCHandler.handle(filter.checkDetailGraph(), (X937CheckDetailAddendumCRecord) record);
            break;
        case X9Record.TYPE_FILE_CONTROL:
            fileControlHandler.handle(filter.fileGraph(), (X937FileControlRecord) record);
            break;
        case X9Record.TYPE_FILE_HEADER:
            fileHeaderHandler.handle(filter.fileGraph(), (X937FileHeaderRecord) record);
            break;
        case X9Record.TYPE_IMAGE_VIEW_ANALYSIS:
            imageViewAnalysisHandler.handle(filter.checkDetailGraph(), (X937ImageViewAnalysisRecord) record);
            break;
        case X9Record.TYPE_IMAGE_VIEW_DATA:
            imageViewDataHandler.handle(filter.checkDetailGraph(), (X937ImageViewDataRecord) record);
            break;
        case X9Record.TYPE_IMAGE_VIEW_DETAIL:
            imageViewDetailHandler.handle(filter.checkDetailGraph(), (X937ImageViewDetailRecord) record);
            break;
        case X9Record.TYPE_NON_HIT_TOTALS_DETAIL:
            nonHitTotalsHandler.handle(filter.cashLetterGraph(), (X937NonHitTotalsDetailRecord) record);
            break;
        case X9Record.TYPE_RETURN:
            returnHandler.handle(filter.returnGraph(), (X937ReturnRecord) record);
            break;
        case X9Record.TYPE_RETURN_ADDENDUM_A:
            returnAddendumAHandler.handle(filter.returnGraph(), (X937ReturnAddendumARecord) record);
            break;
        case X9Record.TYPE_RETURN_ADDENDUM_B:
            returnAddendumBHandler.handle(filter.returnGraph(), (X937ReturnAddendumBRecord) record);
            break;
        case X9Record.TYPE_RETURN_ADDENDUM_C:
            returnAddendumCHandler.handle(filter.returnGraph(), (X937ReturnAddendumCRecord) record);
            break;
        case X9Record.TYPE_RETURN_ADDENDUM_D:
            returnAddendumDHandler.handle(filter.returnGraph(), (X937ReturnAddendumDRecord) record);
            break;
        case X9Record.TYPE_ROUTING_NUMBER_SUMMARY:
            routingNumberSummaryHandler.handle(filter.cashLetterGraph(), (X937RoutingNumberSummaryRecord) record);
            break;
        }
    }

    public void register(RecordHandler handler) {
        if (handler instanceof X937FileHeaderHandler) {
            fileHeaderHandler = makeFileHeaderHandler(fileHeaderHandler, (X937FileHeaderHandler) handler);
        }
        if (handler instanceof X937FileControlHandler) {
            fileControlHandler = makeFileControlHandler(fileControlHandler, (X937FileControlHandler) handler);
        }
        if (handler instanceof X937CheckDetailHandler) {
            checkDetailHandler = makeCheckDetailHandler(checkDetailHandler, (X937CheckDetailHandler) handler);
        }
        if (handler instanceof X937AccountTotalsDetailHandler) {
            accountTotalsDetailHandler = makeAccountTotalsDetailHandler(accountTotalsDetailHandler,
                    (X937AccountTotalsDetailHandler) handler);
        }
        if (handler instanceof X937BoxSummaryHandler) {
            boxSummaryHandler = makeBoxSummaryHandler(boxSummaryHandler, (X937BoxSummaryHandler) handler);
        }
        if (handler instanceof X937BundleControlHandler) {
            bundleControlHandler = makeBundleControlHandler(bundleControlHandler, (X937BundleControlHandler) handler);
        }
        if (handler instanceof X937BundleHeaderHandler) {
            bundleHeaderHandler = makeBundleHeaderHandler(bundleHeaderHandler, (X937BundleHeaderHandler) handler);
        }
        if (handler instanceof X937CashLetterControlHandler) {
            cashLetterControlHandler = makeCashLetterControlHandler(cashLetterControlHandler,
                    (X937CashLetterControlHandler) handler);
        }
        if (handler instanceof X937CashLetterHeaderHandler) {
            cashLetterHeaderHandler = makeCashLetterHeaderHandler(cashLetterHeaderHandler,
                    (X937CashLetterHeaderHandler) handler);
        }
        if (handler instanceof X937CheckDetailAddendumAHandler) {
            checkDetailAddendumAHandler = makeCheckDetailAddendumAHandler(checkDetailAddendumAHandler,
                    (X937CheckDetailAddendumAHandler) handler);
        }
        if (handler instanceof X937CheckDetailAddendumBHandler) {
            checkDetailAddendumBHandler = makeCheckDetailAddendumBHandler(checkDetailAddendumBHandler,
                    (X937CheckDetailAddendumBHandler) handler);
        }
        if (handler instanceof X937CheckDetailAddendumCHandler) {
            checkDetailAddendumCHandler = makeCheckDetailAddendumCHandler(checkDetailAddendumCHandler,
                    (X937CheckDetailAddendumCHandler) handler);
        }
        if (handler instanceof X937ImageViewAnalysisHandler) {
            imageViewAnalysisHandler = makeImageViewAnalysisHandler(imageViewAnalysisHandler,
                    (X937ImageViewAnalysisHandler) handler);
        }
        if (handler instanceof X937ImageViewDataHandler) {
            imageViewDataHandler = makeImageViewDataHandler(imageViewDataHandler, (X937ImageViewDataHandler) handler);
        }
        if (handler instanceof X937ImageViewDetailHandler) {
            imageViewDetailHandler = makeImageViewDetailHandler(imageViewDetailHandler,
                    (X937ImageViewDetailHandler) handler);
        }
        if (handler instanceof X937NonHitTotalsDetailHandler) {
            nonHitTotalsHandler = makeNonHitTotalsDetailHandler(nonHitTotalsHandler,
                    (X937NonHitTotalsDetailHandler) handler);
        }
        if (handler instanceof X937ReturnHandler) {
            returnHandler = makeReturnHandler(returnHandler, (X937ReturnHandler) handler);
        }
        if (handler instanceof X937ReturnAddendumAHandler) {
            returnAddendumAHandler = makeReturnAddendumAHandler(returnAddendumAHandler,
                    (X937ReturnAddendumAHandler) handler);
        }
        if (handler instanceof X937ReturnAddendumBHandler) {
            returnAddendumBHandler = makeReturnAddendumBHandler(returnAddendumBHandler,
                    (X937ReturnAddendumBHandler) handler);
        }
        if (handler instanceof X937ReturnAddendumCHandler) {
            returnAddendumCHandler = makeReturnAddendumCHandler(returnAddendumCHandler,
                    (X937ReturnAddendumCHandler) handler);
        }
        if (handler instanceof X937ReturnAddendumDHandler) {
            returnAddendumDHandler = makeReturnAddendumDHandler(returnAddendumDHandler,
                    (X937ReturnAddendumDHandler) handler);
        }
        if (handler instanceof X937RoutingNumberSummaryHandler) {
            routingNumberSummaryHandler = makeRoutingNumberSummaryHandler(routingNumberSummaryHandler,
                    (X937RoutingNumberSummaryHandler) handler);
        }

    }

    private X937FileHeaderHandler makeFileHeaderHandler(final X937FileHeaderHandler chainHandler,
            final X937FileHeaderHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937FileHeaderHandler() {

            public void handle(X937FileGraph graph, X937FileHeaderRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937FileControlHandler makeFileControlHandler(final X937FileControlHandler chainHandler,
            final X937FileControlHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937FileControlHandler() {

            public void handle(X937FileGraph graph, X937FileControlRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937CheckDetailHandler makeCheckDetailHandler(final X937CheckDetailHandler chainHandler,
            final X937CheckDetailHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937CheckDetailHandler() {

            public void handle(X937CheckDetailGraph graph, X937CheckDetailRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937AccountTotalsDetailHandler makeAccountTotalsDetailHandler(
            final X937AccountTotalsDetailHandler chainHandler, final X937AccountTotalsDetailHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937AccountTotalsDetailHandler() {

            public void handle(X937CashLetterGraph graph, X937AccountTotalsDetailRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937BoxSummaryHandler makeBoxSummaryHandler(final X937BoxSummaryHandler chainHandler,
            final X937BoxSummaryHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937BoxSummaryHandler() {

            public void handle(X937CashLetterGraph graph, X937BoxSummaryRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937BundleControlHandler makeBundleControlHandler(final X937BundleControlHandler chainHandler,
            final X937BundleControlHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937BundleControlHandler() {

            public void handle(X937BundleGraph graph, X937BundleControlRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937BundleHeaderHandler makeBundleHeaderHandler(final X937BundleHeaderHandler chainHandler,
            final X937BundleHeaderHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937BundleHeaderHandler() {

            public void handle(X937BundleGraph graph, X937BundleHeaderRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937CashLetterControlHandler makeCashLetterControlHandler(
            final X937CashLetterControlHandler chainHandler, final X937CashLetterControlHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937CashLetterControlHandler() {

            public void handle(X937CashLetterGraph graph, X937CashLetterControlRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937CashLetterHeaderHandler makeCashLetterHeaderHandler(
            final X937CashLetterHeaderHandler chainHandler, final X937CashLetterHeaderHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937CashLetterHeaderHandler() {

            public void handle(X937CashLetterGraph graph, X937CashLetterHeaderRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937CheckDetailAddendumAHandler makeCheckDetailAddendumAHandler(
            final X937CheckDetailAddendumAHandler chainHandler, final X937CheckDetailAddendumAHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937CheckDetailAddendumAHandler() {

            public void handle(X937CheckDetailGraph graph, X937CheckDetailAddendumARecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937CheckDetailAddendumBHandler makeCheckDetailAddendumBHandler(
            final X937CheckDetailAddendumBHandler chainHandler, final X937CheckDetailAddendumBHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937CheckDetailAddendumBHandler() {

            public void handle(X937CheckDetailGraph graph, X937CheckDetailAddendumBRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937CheckDetailAddendumCHandler makeCheckDetailAddendumCHandler(
            final X937CheckDetailAddendumCHandler chainHandler, final X937CheckDetailAddendumCHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937CheckDetailAddendumCHandler() {

            public void handle(X937CheckDetailGraph graph, X937CheckDetailAddendumCRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937ImageViewAnalysisHandler makeImageViewAnalysisHandler(
            final X937ImageViewAnalysisHandler chainHandler, final X937ImageViewAnalysisHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937ImageViewAnalysisHandler() {

            public void handle(X937CheckDetailGraph graph, X937ImageViewAnalysisRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937ImageViewDataHandler makeImageViewDataHandler(final X937ImageViewDataHandler chainHandler,
            final X937ImageViewDataHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937ImageViewDataHandler() {

            public void handle(X937CheckDetailGraph graph, X937ImageViewDataRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937ImageViewDetailHandler makeImageViewDetailHandler(final X937ImageViewDetailHandler chainHandler,
            final X937ImageViewDetailHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937ImageViewDetailHandler() {

            public void handle(X937CheckDetailGraph graph, X937ImageViewDetailRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937NonHitTotalsDetailHandler makeNonHitTotalsDetailHandler(
            final X937NonHitTotalsDetailHandler chainHandler, final X937NonHitTotalsDetailHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937NonHitTotalsDetailHandler() {

            public void handle(X937CashLetterGraph graph, X937NonHitTotalsDetailRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937ReturnHandler makeReturnHandler(final X937ReturnHandler chainHandler, final X937ReturnHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937ReturnHandler() {

            public void handle(X937ReturnGraph graph, X937ReturnRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937ReturnAddendumAHandler makeReturnAddendumAHandler(final X937ReturnAddendumAHandler chainHandler,
            final X937ReturnAddendumAHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937ReturnAddendumAHandler() {

            public void handle(X937ReturnGraph graph, X937ReturnAddendumARecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937ReturnAddendumBHandler makeReturnAddendumBHandler(final X937ReturnAddendumBHandler chainHandler,
            final X937ReturnAddendumBHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937ReturnAddendumBHandler() {

            public void handle(X937ReturnGraph graph, X937ReturnAddendumBRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937ReturnAddendumCHandler makeReturnAddendumCHandler(final X937ReturnAddendumCHandler chainHandler,
            final X937ReturnAddendumCHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937ReturnAddendumCHandler() {

            public void handle(X937ReturnGraph graph, X937ReturnAddendumCRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937ReturnAddendumDHandler makeReturnAddendumDHandler(final X937ReturnAddendumDHandler chainHandler,
            final X937ReturnAddendumDHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937ReturnAddendumDHandler() {

            public void handle(X937ReturnGraph graph, X937ReturnAddendumDRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }

    private X937RoutingNumberSummaryHandler makeRoutingNumberSummaryHandler(
            final X937RoutingNumberSummaryHandler chainHandler, final X937RoutingNumberSummaryHandler handler) {
        if (chainHandler == defaultHandler) {
            return handler;
        }
        return new X937RoutingNumberSummaryHandler() {

            public void handle(X937CashLetterGraph graph, X937RoutingNumberSummaryRecord record) {
                chainHandler.handle(graph, record);
                handler.handle(graph, record);
            }
        };
    }
}
