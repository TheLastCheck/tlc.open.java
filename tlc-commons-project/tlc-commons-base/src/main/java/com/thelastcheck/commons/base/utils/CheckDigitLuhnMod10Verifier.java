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

/**
 * @author Jerry Bowman
 *         (c) 2013, The Last Check, All Rights Reserved.
 */
public class CheckDigitLuhnMod10Verifier implements CheckDigitVerifier {

    private static CheckDigitVerifier instance = new CheckDigitLuhnMod10Verifier();

    public static CheckDigitVerifier getInstance() {
        return instance;
    }

    private CheckDigitLuhnMod10Verifier() {
    }

    public boolean isValid(String id) {

        int currentDigit;
        int idSum = 0;
        int position = 0; //the current process number (to calc odd/even proc)

        for (int i = id.length() - 1; i >= 0; i--) {
            //get the current rightmost digit from the string
            String idCurrentRightmostDigit = id.substring(i, i + 1);

            //parse to int the current rightmost digit, if fail return false (not-valid id)
            try {
                currentDigit = Integer.parseInt(idCurrentRightmostDigit);
            } catch (NumberFormatException e) {
                return false;
            }

            // double value of every 2nd rightmost digit
            // if value 2 digits (for example: 18 ),
            // then sum the digits (made easy by subtracting 9, for example, 18 -> 1+8 = 9, same as 18-9 = 9)
            if (position % 2 != 0) {
                currentDigit *= 2;
                if (currentDigit > 9)
                    currentDigit -= 9;
            }
            position++; //increase the proc number

            //summarize the processed digits
            idSum += currentDigit;
        }

        //if digits sum is exactly divisible by 10, return true (valid), else false (not-valid)
        return (idSum % 10 == 0);
    }
}
