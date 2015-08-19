package com.thelastcheck.io.x9.copy;

import com.thelastcheck.commons.base.exception.InvalidDataException;
import com.thelastcheck.io.x9.X9Record;

/**
 * Created by Jerry Bowman
 */
public interface X937RecordCopier {
    X9Record copy(X9Record input) throws InvalidDataException;
}
