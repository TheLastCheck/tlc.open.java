/* 
 *  Copyright 2009 The Last Check, LLC, All Rights Reserved
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0 
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.thelastcheck.commons.buffer;

import org.slf4j.LoggerFactory;

/**
 * @author Jerry Bowman
 * @copyright (c) 2009, The Last Check, All Rights Reserved.
 * @version $Date: 2010/11/15 17:42:05 $ $Revision: 1.2 $
 */
@SuppressWarnings("serial")
public class BufferCapacityReachedException extends RuntimeException {
    private static final String CVS_ID = "$Date: 2010/11/15 17:42:05 $ $Revision: 1.2 $";
    static {
        String className = BufferCapacityReachedException.class.getName();
        LoggerFactory.getLogger("version").info(className + " | " + CVS_ID);
    }

    public BufferCapacityReachedException() {
    }

    public BufferCapacityReachedException(String message) {
        super(message);
    }

    public BufferCapacityReachedException(Throwable cause) {
        super(cause);
    }

    public BufferCapacityReachedException(String message, Throwable cause) {
        super(message, cause);
    }

}
