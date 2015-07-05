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

package com.thelastcheck.commons.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jerry Bowman
 *         (c) 2013, The Last Check, All Rights Reserved.
 */
public class CheckDigitRoutingNumberVerifier implements CheckDigitVerifier {

    private static int[] weights = {3, 7, 1, 3, 7, 1, 3, 7, 1};
    private static CheckDigitVerifier instance = new CheckDigitRoutingNumberVerifier();

    public static CheckDigitVerifier getInstance() {
        return instance;
    }

    private CheckDigitRoutingNumberVerifier() {
    }

    public boolean isValid(String routingNumber) {
        boolean valid = true;
        char digit;
        int digitValue;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            digit = routingNumber.charAt(i);
            if (Character.isDigit(digit)) {
                digitValue = (int) digit - (int) '0';
                digitValue *= weights[i];
                sum += digitValue;
            } else {
                valid = false;
                break;
            }
        }
        if (valid) {
            if ((sum % 10) != 0) {
                valid = false;
            }
        }
        // 000000000 and 999999999 will check digit, but is not a valid RT
        if (valid) {
            if (routingNumber.equals("000000000")
                    || routingNumber.equals("999999999")) {
                valid = false;
            }
        }

        return valid;
    }
}
