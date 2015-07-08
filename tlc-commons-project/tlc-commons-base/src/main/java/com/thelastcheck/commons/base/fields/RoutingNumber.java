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

package com.thelastcheck.commons.base.fields;

import com.thelastcheck.commons.base.utils.CheckDigitVerifier;
import com.thelastcheck.commons.base.utils.CheckDigitVerifiers;

import static com.thelastcheck.commons.base.utils.CheckDigitVerifiers.Verifier.*;

public class RoutingNumber {

    private String routingNumber;
    private boolean canadian = false;
    private char checkDigit;

    public RoutingNumber(String number) {
        routingNumber = normalizeSize(number, 9);
        routingNumber = normalizeDashes(routingNumber);
        if (hasCheckDigit(routingNumber)) {
            checkDigit = routingNumber.charAt(8);
            routingNumber = routingNumber.substring(0, 8);
        } else {
            checkDigit = ' ';
            routingNumber = normalizeSize(routingNumber, 8);
        }
    }

    public RoutingNumber(String number, String checkDigit) {
        routingNumber = normalizeSize(number, 8);
        checkDigit = normalizeSize(checkDigit, 1);
        this.checkDigit = checkDigit.charAt(0);
    }

    public boolean isCanadian() {
        return canadian;
    }

    public char getCheckDigit() {
        return checkDigit;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    private boolean hasCheckDigit(String number) {
        return number.length() > 8 && (number.charAt(8) != ' ');
    }

    public boolean hasCheckDigit() {
        return (checkDigit != ' ');
    }

    private String normalizeSize(String number, int digits) {
        if (number.length() == digits) {
            return number;
        }
        if (number.length() > digits) {
            return number.substring(0, digits);
        }
        number = number + "         ".substring(0, digits - number.length());
        return number;
    }

    private String normalizeDashes(String number) {
        if (number.contains("-")) {
            if (number.charAt(5) == '-') {
                canadian = true;
            }
            StringBuilder sb = new StringBuilder(9);
            for (Character ch: number.toCharArray()) {
                if (ch != '-') sb.append(ch);
            }
            number = sb.toString();
        }
        return number;
    }

    private static int[] weights = {3, 7, 1, 3, 7, 1, 3, 7, 1};

    public void calculateAndSaveCheckDigit() {
        checkDigit = calculateCheckDigit(routingNumber).charAt(0);
        return;
    }

    public static String calculateCheckDigit(String routingNumber) {
        boolean valid = true;
        char digit;
        int digitValue;
        int sum = 0;
        for (int i = 0; i < 8; i++) {
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
            // If remainder is 0m then that is the check digit.
            int checkDigit = sum % 10;
            if (checkDigit > 0) {
                checkDigit = 10 - checkDigit;
            }
            String s = "" + checkDigit;
            return s;
        }
        return " ";
    }

    public boolean isValid() {
        if (hasCheckDigit()) {
            CheckDigitVerifier verifier = CheckDigitVerifiers.getVerifier(RoutingNumber);
            return verifier.isValid(this.toString());
        }
        return isValidNumber();
    }

    private boolean isValidNumber() {
        if (routingNumber.length() == 0) return true;
        for (char c : routingNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof RoutingNumber) {
            RoutingNumber aRoutingNumber = (RoutingNumber) anObject;
            if (this.routingNumber.equals(aRoutingNumber.routingNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return routingNumber + checkDigit;
    }

    public static RoutingNumber valueOf(String routingNumber) {
        return new RoutingNumber(routingNumber);
    }
}
