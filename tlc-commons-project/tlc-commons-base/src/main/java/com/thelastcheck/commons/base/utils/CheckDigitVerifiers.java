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

public abstract class CheckDigitVerifiers {

    public enum Verifier {
        LuhnMod10, WeightedMod11, RoutingNumber
    }

    public static CheckDigitVerifier getVerifier(Verifier verifierType) {
        if (verifierType == Verifier.WeightedMod11) {
            throw new IllegalArgumentException("Weighted Mod 11 requires weight values");
        }
        return getVerifier(verifierType, null);
    }

    public static CheckDigitVerifier getVerifier(Verifier verifierType, int[] weights) {
        if (verifierType == Verifier.RoutingNumber) {
            return CheckDigitRoutingNumberVerifier.getInstance();
        }
        if (verifierType == Verifier.LuhnMod10) {
            return CheckDigitLuhnMod10Verifier.getInstance();
        }
        if (verifierType == Verifier.WeightedMod11) {
            return CheckDigitWeightedMod11Verifier.from(weights);
        }
        return null;
    }

}
