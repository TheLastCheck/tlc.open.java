package com.thelastcheck.io.x937.records.stddstu;

import org.junit.Test;

import com.thelastcheck.io.base.Record;
import com.thelastcheck.io.x9.X9Record;
import com.thelastcheck.io.x9.factory.DefaultX9RecordFactoryStrategy;
import com.thelastcheck.io.x9.factory.X9RecordFactory;
import com.thelastcheck.io.x9.factory.X9RecordFactoryStrategy;
import com.thelastcheck.io.x937.records.X937CheckDetailRecord;

import static org.junit.Assert.assertEquals;

public class X937CheckDetailRecordImplTest {

    @Test
    public void doesClone() throws CloneNotSupportedException {
        X9RecordFactoryStrategy strategy = new DefaultX9RecordFactoryStrategy();
        X9RecordFactory factory = strategy.factory(X9RecordFactoryStrategy.X937_STANDARD_DSTU);
        Record x9Record = factory.newX9Record(X9Record.TYPE_CHECK_DETAIL);
        X937CheckDetailRecord check = (X937CheckDetailRecord) x9Record;
        check.recordPosition(10);
        check.offsetPosition(200L);
        X937CheckDetailRecord check2 = (X937CheckDetailRecord) check.clone();
        assertEquals(check.recordStandardLevel(), check2.recordStandardLevel());
        assertEquals(check.recordPosition(), check2.recordPosition());
        assertEquals(check.offsetPosition(), check2.offsetPosition());
    }

    @Test
    public void doesDuplicate() throws CloneNotSupportedException {
        X9RecordFactoryStrategy strategy = new DefaultX9RecordFactoryStrategy();
        X9RecordFactory factory = strategy.factory(X9RecordFactoryStrategy.X937_STANDARD_DSTU);
        Record x9Record = factory.newX9Record(X9Record.TYPE_CHECK_DETAIL);
        X937CheckDetailRecord check = (X937CheckDetailRecord) x9Record;
        check.recordPosition(10);
        check.offsetPosition(200L);
        X937CheckDetailRecord check2 = (X937CheckDetailRecord) check.duplicate();
        assertEquals(check.recordStandardLevel(), check2.recordStandardLevel());
        assertEquals(check.recordPosition(), check2.recordPosition());
        assertEquals(check.offsetPosition(), check2.offsetPosition());
    }
}