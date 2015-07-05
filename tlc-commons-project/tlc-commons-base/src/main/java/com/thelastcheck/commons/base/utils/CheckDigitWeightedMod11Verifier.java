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
public class CheckDigitWeightedMod11Verifier implements CheckDigitVerifier {

    private final int[] weights;

    public static CheckDigitVerifier from(int[] weights) {
        return new CheckDigitWeightedMod11Verifier(weights);
    }

    private CheckDigitWeightedMod11Verifier(int[] weights) {
        this.weights = weights;
    }

    public boolean isValid(String id) {
        int totalWeight = 0;
        int wIndex = 0;
        int idIndex = 0;
        byte[] digits = id.getBytes();
        for (int i = 0; i < digits.length; i++) {
            byte digit = digits[i];
            if (!Character.isDigit(digit)) {
                return false;
            }
            digit -= 48; // get number value
            int weightToUse = i == digits.length - 1 ? 1 : weights[wIndex];
            totalWeight += digit * weightToUse;
            if (++wIndex >= weights.length) wIndex = 0;
        }

        return totalWeight % 11 == 0;
    }

}
