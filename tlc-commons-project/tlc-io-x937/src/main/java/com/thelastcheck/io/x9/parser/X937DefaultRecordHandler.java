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

public class X937DefaultRecordHandler implements
        X937FileHeaderHandler,
        X937FileControlHandler,
        X937CashLetterHeaderHandler,
        X937CashLetterControlHandler,
        X937BundleHeaderHandler,
        X937BundleControlHandler,
        X937CheckDetailHandler,
        X937CheckDetailAddendumAHandler,
        X937CheckDetailAddendumBHandler,
        X937CheckDetailAddendumCHandler,
        X937ImageViewDetailHandler,
        X937ImageViewDataHandler,
        X937ImageViewAnalysisHandler,
        X937ReturnHandler,
        X937ReturnAddendumAHandler,
        X937ReturnAddendumBHandler,
        X937ReturnAddendumCHandler,
        X937ReturnAddendumDHandler,
        X937NonHitTotalsDetailHandler,
        X937AccountTotalsDetailHandler,
        X937BoxSummaryHandler,
        X937RoutingNumberSummaryHandler {

    public void handle(X937CashLetterGraph graph, X937RoutingNumberSummaryRecord record) {
    }

    public void handle(X937CashLetterGraph graph, X937BoxSummaryRecord record) {
    }

    public void handle(X937CashLetterGraph graph, X937AccountTotalsDetailRecord record) {
    }

    public void handle(X937CashLetterGraph graph, X937NonHitTotalsDetailRecord record) {
    }

    public void handle(X937ReturnGraph graph, X937ReturnAddendumDRecord record) {
    }

    public void handle(X937ReturnGraph graph, X937ReturnAddendumCRecord record) {
    }

    public void handle(X937ReturnGraph graph, X937ReturnAddendumBRecord record) {
    }

    public void handle(X937ReturnGraph graph, X937ReturnAddendumARecord record) {
    }

    public void handle(X937ReturnGraph graph, X937ReturnRecord record) {
    }

    public void handle(X937CheckDetailGraph graph, X937ImageViewAnalysisRecord record) {
    }

    public void handle(X937CheckDetailGraph graph, X937ImageViewDataRecord record) {
    }

    public void handle(X937CheckDetailGraph graph, X937ImageViewDetailRecord record) {
    }

    public void handle(X937CheckDetailGraph graph, X937CheckDetailAddendumCRecord record) {
    }

    public void handle(X937CheckDetailGraph graph, X937CheckDetailAddendumBRecord record) {
    }

    public void handle(X937CheckDetailGraph graph, X937CheckDetailAddendumARecord record) {
    }

    public void handle(X937CheckDetailGraph graph, X937CheckDetailRecord record) {
    }

    public void handle(X937BundleGraph graph, X937BundleControlRecord record) {
    }

    public void handle(X937BundleGraph graph, X937BundleHeaderRecord record) {
    }

    public void handle(X937CashLetterGraph graph, X937CashLetterControlRecord record) {
    }

    public void handle(X937CashLetterGraph graph, X937CashLetterHeaderRecord record) {
    }

    public void handle(X937FileGraph graph, X937FileControlRecord record) {
    }

    public void handle(X937FileGraph graph, X937FileHeaderRecord record) {
    }
}
