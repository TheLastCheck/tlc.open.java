/*******************************************************************************
 * Copyright (c) 2009-2016 The Last Check, LLC, All Rights Reserved
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

package com.thelastcheck.io.cims;

import com.thelastcheck.commons.base.utils.Preconditions;
import com.thelastcheck.commons.buffer.ByteArray;

public class CimsCodedDataHeader {

    public static final int RECORD_LENGTH = 1500;

    private final ByteArray buffer;

    public CimsCodedDataHeader() {
        buffer = new ByteArray(RECORD_LENGTH);
        setRecordVersionNumber((byte) 0x84);
    }

    public CimsCodedDataHeader(byte[] array) {
        Preconditions.checkArgument(array.length == RECORD_LENGTH,
                "Buffer length must be [%s], was [%s]",
                RECORD_LENGTH, array.length);
        buffer = new ByteArray(array);
    }

    public byte[] toByteArray() {
        return buffer.getArray().value;
    }

    /**
     * Internal helper routine to set/clear bits
     *
     * @param b            - boolean value true to set, false to clear
     * @param displacement is the location in the buffer of the byte to set the bits in
     * @param value        is the bits to set in the byte -- only last byte is used.
     */
    private void setOrClearBit(boolean b, int displacement, byte value) {
        if (b) {
            buffer.setBit(displacement, value);
        } else {
            buffer.clearBit(displacement, value);
        }
    }

    private static final int REC_VERSION_NUM_DISP = 0;

    public byte getRecordVersionNumber() {
        return buffer.readAsByte(REC_VERSION_NUM_DISP);
    }

    public void setRecordVersionNumber(byte b) {
        buffer.write(b, REC_VERSION_NUM_DISP);
    }

    private static final int XP_CODE_LEVEL_DISP = 8;

    public String getXPProcessorMicrocodeLevel() {
        return buffer.readAsString(XP_CODE_LEVEL_DISP, 6);
    }

    public void setXPProcessorMicrocodeLevel(String s) {
        buffer.write(s, XP_CODE_LEVEL_DISP, 6);
    }

    private static final int ICP_CODE_LEVEL_DISP = 14;

    public String getIcpMicrocodeLevel() {
        return buffer.readAsString(ICP_CODE_LEVEL_DISP, 6);
    }

    public void setIcpMicrocodeLevel(String s) {
        buffer.write(s, ICP_CODE_LEVEL_DISP, 6);
    }

    private static final int CIMS_CODE_LEVEL_DISP = 20;

    public byte[] getCimsCodeLevel() {
        return buffer.read(CIMS_CODE_LEVEL_DISP, 838);
    }

    public void setCimsCodeLevel(byte[] b) {
        buffer.write(b, 0, 838, CIMS_CODE_LEVEL_DISP);
    }

    // using area 300-355 for process buffer for VRR
    private static final int CIMS_CODE_LEVEL_PROC_BUFFER_DISP = CIMS_CODE_LEVEL_DISP + 300;

    /**
     * This is an Extension to the CIMS Coded Data Header (segment 0).
     * This data is stored as part of the CIMS Code Level field.
     *
     * @param byteArray
     */
    public void setProcessBufferInCimsCodeLevel(byte[] byteArray) {
        buffer.write(byteArray, 0, 256, CIMS_CODE_LEVEL_PROC_BUFFER_DISP);
    }

    /**
     * 256 bytes of CIMS Code Level filed containing process buffer data
     * <p>
     * This is an Extension to the CIMS Coded Data Header (segment 0).
     * This data is stored as part of the CIMS Code Level field.
     *
     * @return a byte array containing the process buffer data
     */
    public byte[] getProcessBufferInCimsCodeLevel() {
        return buffer.read(CIMS_CODE_LEVEL_PROC_BUFFER_DISP, 256);
    }

    private static final int CS0DT_DISP = 858;

    public byte[] getCodeLineDefinitionTable() {
        return buffer.read(CS0DT_DISP, 149);
    }

    public void setCodeLineDefinitionTable(byte[] value) {
        buffer.write(value, 0, 149, CS0DT_DISP);
    }

    public void setCodeLineDefinitionTable(CodelineDefinitionTable cdt) {
        buffer.write(cdt.toByteArray(), 0, 149, CS0DT_DISP);
    }

    private static final int CS0_DKDT_OFFSET_DISP = 1007;

    public byte getDIDMKeyAndDocumentTypeOffset() {
        return buffer.readAsByte(CS0_DKDT_OFFSET_DISP);
    }

    public void setDIDMKeyAndDocumentTypeOffset(byte b) {
        buffer.write(b, CS0_DKDT_OFFSET_DISP);
    }

    public static final int CS0RR_DISP = 1008;

    public byte[] getReadRecordData() {
        return buffer.read(CS0RR_DISP, 256);
    }

    public void setReadRecordData(byte[] value) {
        buffer.write(value, 0, 256, CS0RR_DISP);
    }

    private static final int HICINFO_SCI_DISP = 1264;

    public boolean getCaptureFBW() {
        return buffer.testBit(HICINFO_SCI_DISP, (byte) 0x80);
    }

    public void setCaptureFBW(boolean b) {
        setOrClearBit(b, HICINFO_SCI_DISP, (byte) 0x80);
    }

    public boolean getCaptureFGS() {
        return buffer.testBit(HICINFO_SCI_DISP, (byte) 0x40);
    }

    public void setCaptureFGS(boolean b) {
        setOrClearBit(b, HICINFO_SCI_DISP, (byte) 0x40);
    }

    public boolean getCaptureBBW() {
        return buffer.testBit(HICINFO_SCI_DISP, (byte) 0x20);
    }

    public void setCaptureBBW(boolean b) {
        setOrClearBit(b, HICINFO_SCI_DISP, (byte) 0x20);
    }

    public boolean getCaptureBGS() {
        return buffer.testBit(HICINFO_SCI_DISP, (byte) 0x10);
    }

    public void setCaptureBGS(boolean b) {
        setOrClearBit(b, HICINFO_SCI_DISP, (byte) 0x10);
    }

    private static final int HICINFO_DTN_DISP = 1265;

    public byte getDocumentTrackingNumber() {
        return buffer.readAsByte(HICINFO_DTN_DISP);
    }

    public void setDocumentTrackingNumber(byte b) {
        buffer.write(b, HICINFO_DTN_DISP);
    }

    private static final int HICINFO_OVER_UNDER_DISP = 1267;

    public boolean getBGSUnder() {
        return buffer.testBit(HICINFO_OVER_UNDER_DISP, (byte) 0x80);
    }

    public void setBGSUnder(boolean b) {
        setOrClearBit(b, HICINFO_OVER_UNDER_DISP, (byte) 0x80);
    }

    public boolean getBBWUnder() {
        return buffer.testBit(HICINFO_OVER_UNDER_DISP, (byte) 0x40);
    }

    public void setBBWUnder(boolean b) {
        setOrClearBit(b, HICINFO_OVER_UNDER_DISP, (byte) 0x40);
    }

    public boolean getFGSUnder() {
        return buffer.testBit(HICINFO_OVER_UNDER_DISP, (byte) 0x20);
    }

    public void setFGSUnder(boolean b) {
        setOrClearBit(b, HICINFO_OVER_UNDER_DISP, (byte) 0x20);
    }

    public boolean getFBWUnder() {
        return buffer.testBit(HICINFO_OVER_UNDER_DISP, (byte) 0x10);
    }

    public void setFBWUnder(boolean b) {
        setOrClearBit(b, HICINFO_OVER_UNDER_DISP, (byte) 0x10);
    }

    public boolean getBGSOver() {
        return buffer.testBit(HICINFO_OVER_UNDER_DISP, (byte) 0x08);
    }

    public void setBGSOver(boolean b) {
        setOrClearBit(b, HICINFO_OVER_UNDER_DISP, (byte) 0x08);
    }

    public boolean getBBWOver() {
        return buffer.testBit(HICINFO_OVER_UNDER_DISP, (byte) 0x04);
    }

    public void setBBWOver(boolean b) {
        setOrClearBit(b, HICINFO_OVER_UNDER_DISP, (byte) 0x04);
    }

    public boolean getFGSOver() {
        return buffer.testBit(HICINFO_OVER_UNDER_DISP, (byte) 0x02);
    }

    public void setFGSOver(boolean b) {
        setOrClearBit(b, HICINFO_OVER_UNDER_DISP, (byte) 0x02);
    }

    public boolean getFBWOver() {
        return buffer.testBit(HICINFO_OVER_UNDER_DISP, (byte) 0x01);
    }

    public void setFBWOver(boolean b) {
        setOrClearBit(b, HICINFO_OVER_UNDER_DISP, (byte) 0x01);
    }

    private static final int HICINFO_SEGCAP_DISP = 1268;

    public boolean getBGSCaptured() {
        return buffer.testBit(HICINFO_SEGCAP_DISP, (byte) 0x08);
    }

    public void setBGSCaptured(boolean b) {
        setOrClearBit(b, HICINFO_SEGCAP_DISP, (byte) 0x08);
    }

    public boolean getBBWCaptured() {
        return buffer.testBit(HICINFO_SEGCAP_DISP, (byte) 0x04);
    }

    public void setBBWCaptured(boolean b) {
        setOrClearBit(b, HICINFO_SEGCAP_DISP, (byte) 0x04);
    }

    public boolean getFGSCaptured() {
        return buffer.testBit(HICINFO_SEGCAP_DISP, (byte) 0x02);
    }

    public void setFGSCaptured(boolean b) {
        setOrClearBit(b, HICINFO_SEGCAP_DISP, (byte) 0x02);
    }

    public boolean getFBWCaptured() {
        return buffer.testBit(HICINFO_SEGCAP_DISP, (byte) 0x01);
    }

    public void setFBWCaptured(boolean b) {
        setOrClearBit(b, HICINFO_SEGCAP_DISP, (byte) 0x01);
    }

    private static final int LCCQB_FR_IASTAT_DISP = 1278;

    public boolean getFrontCornerMissingOnTrailingEdge() {
        return buffer.testBit(LCCQB_FR_IASTAT_DISP, (byte) 0x40);
    }

    public void setFrontCornerMissingOnTrailingEdge(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT_DISP, (byte) 0x40);
    }

    public boolean getFrontCornerMissingOnLeadingEdge() {
        return buffer.testBit(LCCQB_FR_IASTAT_DISP, (byte) 0x20);
    }

    public void setFrontCornerMissingOnLeadingEdge(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT_DISP, (byte) 0x20);
    }

    public boolean getFrontHeightDifferenceTooBig() {
        return buffer.testBit(LCCQB_FR_IASTAT_DISP, (byte) 0x10);
    }

    public void setFrontHeightDifferenceTooBig(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT_DISP, (byte) 0x10);
    }

    public boolean getFrontBottomCutOff() {
        return buffer.testBit(LCCQB_FR_IASTAT_DISP, (byte) 0x08);
    }

    public void setFrontBottomCutOff(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT_DISP, (byte) 0x08);
    }

    public boolean getFrontTopCutOff() {
        return buffer.testBit(LCCQB_FR_IASTAT_DISP, (byte) 0x04);
    }

    public void setFrontTopCutOff(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT_DISP, (byte) 0x04);
    }

    public boolean getFrontTrailingEdgeCutOff() {
        return buffer.testBit(LCCQB_FR_IASTAT_DISP, (byte) 0x02);
    }

    public void setFrontTrailingEdgeCutOff(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT_DISP, (byte) 0x02);
    }

    public boolean getFrontLeadingEdgeCutOff() {
        return buffer.testBit(LCCQB_FR_IASTAT_DISP, (byte) 0x01);
    }

    public void setFrontLeadingEdgeCutOff(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT_DISP, (byte) 0x01);
    }

    private static final int LCCQB_FR_IASTAT2_DISP = 1279;

    public boolean getFrontCornersOutOfBounds() {
        return buffer.testBit(LCCQB_FR_IASTAT2_DISP, (byte) 0x80);
    }

    public void getFrontCornersOutOfBounds(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT2_DISP, (byte) 0x80);
    }

    public boolean getFrontTrailingEdgeTooLate() {
        return buffer.testBit(LCCQB_FR_IASTAT2_DISP, (byte) 0x40);
    }

    public void setFrontTrailingEdgeTooLate(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT2_DISP, (byte) 0x40);
    }

    public boolean getFrontLeadingEdgeTooEarly() {
        return buffer.testBit(LCCQB_FR_IASTAT2_DISP, (byte) 0x20);
    }

    public void setFrontLeadingEdgeTooEarly(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT2_DISP, (byte) 0x20);
    }

    public boolean getFrontHeightBellowMinimumSize() {
        return buffer.testBit(LCCQB_FR_IASTAT2_DISP, (byte) 0x08);
    }

    public void setFrontHeightBellowMinimumSize(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT2_DISP, (byte) 0x08);
    }

    public boolean getFrontLengthBellowMinimumSize() {
        return buffer.testBit(LCCQB_FR_IASTAT2_DISP, (byte) 0x04);
    }

    public void setFrontLengthBellowMinimumSize(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT2_DISP, (byte) 0x04);
    }

    public boolean getFrontExcessiveImageSkew() {
        return buffer.testBit(LCCQB_FR_IASTAT2_DISP, (byte) 0x02);
    }

    public void setFrontExcessiveImageSkew(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT2_DISP, (byte) 0x02);
    }

    public boolean getFrontDarkSegmentDetected() {
        return buffer.testBit(LCCQB_FR_IASTAT2_DISP, (byte) 0x01);
    }

    public void setFrontDarkSegmentDetected(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT2_DISP, (byte) 0x01);
    }

    private static final int LCCQB_FR_IASTAT3_DISP = 1328;

    public boolean getFrontImageTooDark() {
        return buffer.testBit(LCCQB_FR_IASTAT3_DISP, (byte) 0x80);
    }

    public void setFrontImageTooDark(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT3_DISP, (byte) 0x80);
    }

    public boolean getFrontHeightExceedsMaximumSize() {
        return buffer.testBit(LCCQB_FR_IASTAT3_DISP, (byte) 0x20);
    }

    public void setFrontHeightExceedsMaximumSize(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT3_DISP, (byte) 0x20);
    }

    public boolean getFrontLengthExceedsMaximumSize() {
        return buffer.testBit(LCCQB_FR_IASTAT3_DISP, (byte) 0x10);
    }

    public void setFrontLengthExceedsMaximumSize(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT3_DISP, (byte) 0x10);
    }

    public boolean getFrontDynamicRangeBellowMinimum() {
        return buffer.testBit(LCCQB_FR_IASTAT3_DISP, (byte) 0x08);
    }

    public void setFrontDynamicRangeBellowMinimum(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT3_DISP, (byte) 0x08);
    }

    public boolean getFrontBrightnessBellowMinimum() {
        return buffer.testBit(LCCQB_FR_IASTAT3_DISP, (byte) 0x04);
    }

    public void setFrontBrightnessBellowMinimum(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT3_DISP, (byte) 0x04);
    }

    public boolean getFrontSharpnessBellowMinimum() {
        return buffer.testBit(LCCQB_FR_IASTAT3_DISP, (byte) 0x02);
    }

    public void setFrontSharpnessBellowMinimum(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT3_DISP, (byte) 0x02);
    }

    public boolean getFrontSharpnessExceedsMaximum() {
        return buffer.testBit(LCCQB_FR_IASTAT3_DISP, (byte) 0x01);
    }

    public void setFrontSharpnessExceedsMaximum(boolean b) {
        setOrClearBit(b, LCCQB_FR_IASTAT3_DISP, (byte) 0x01);
    }

    private static final int LCCQB_BK_IASTAT_DISP = 1366;

    public boolean getBackCornerMissingOnTrailingEdge() {
        return buffer.testBit(LCCQB_BK_IASTAT_DISP, (byte) 0x40);
    }

    public void setBackCornerMissingOnTrailingEdge(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT_DISP, (byte) 0x40);
    }

    public boolean getBackCornerMissingOnLeadingEdge() {
        return buffer.testBit(LCCQB_BK_IASTAT_DISP, (byte) 0x20);
    }

    public void setBackCornerMissingOnLeadingEdge(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT_DISP, (byte) 0x20);
    }

    public boolean getBackHeightDifferenceTooBig() {
        return buffer.testBit(LCCQB_BK_IASTAT_DISP, (byte) 0x10);
    }

    public void setBackHeightDifferenceTooBig(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT_DISP, (byte) 0x10);
    }

    public boolean getBackBottomCutOff() {
        return buffer.testBit(LCCQB_BK_IASTAT_DISP, (byte) 0x08);
    }

    public void setBackBottomCutOff(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT_DISP, (byte) 0x08);
    }

    public boolean getBackTopCutOff() {
        return buffer.testBit(LCCQB_BK_IASTAT_DISP, (byte) 0x04);
    }

    public void setBackTopCutOff(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT_DISP, (byte) 0x04);
    }

    public boolean getBackTrailingEdgeCutOff() {
        return buffer.testBit(LCCQB_BK_IASTAT_DISP, (byte) 0x02);
    }

    public void setBackTrailingEdgeCutOff(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT_DISP, (byte) 0x02);
    }

    public boolean getBackLeadingEdgeCutOff() {
        return buffer.testBit(LCCQB_BK_IASTAT_DISP, (byte) 0x01);
    }

    public void setBackLeadingEdgeCutOff(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT_DISP, (byte) 0x01);
    }

    private static final int LCCQB_BK_IASTAT2_DISP = 1367;

    public boolean getBackCornersOutOfBounds() {
        return buffer.testBit(LCCQB_BK_IASTAT2_DISP, (byte) 0x80);
    }

    public void setBackCornersOutOfBounds(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT2_DISP, (byte) 0x80);
    }

    public boolean getBackTrailingEdgeTooLate() {
        return buffer.testBit(LCCQB_BK_IASTAT2_DISP, (byte) 0x40);
    }

    public void setBackTrailingEdgeTooLate(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT2_DISP, (byte) 0x40);
    }

    public boolean getBackLeadingEdgeTooEarly() {
        return buffer.testBit(LCCQB_BK_IASTAT2_DISP, (byte) 0x20);
    }

    public void setBackLeadingEdgeTooEarly(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT2_DISP, (byte) 0x20);
    }

    public boolean getBackHeightBellowMinimumSize() {
        return buffer.testBit(LCCQB_BK_IASTAT2_DISP, (byte) 0x08);
    }

    public void setBackHeightBellowMinimumSize(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT2_DISP, (byte) 0x08);
    }

    public boolean getBackLengthBellowMinimumSize() {
        return buffer.testBit(LCCQB_BK_IASTAT2_DISP, (byte) 0x04);
    }

    public void setBackLengthBellowMinimumSize(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT2_DISP, (byte) 0x04);
    }

    public boolean getBackExcessiveImageSkew() {
        return buffer.testBit(LCCQB_BK_IASTAT2_DISP, (byte) 0x02);
    }

    public void setBackExcessiveImageSkew(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT2_DISP, (byte) 0x02);
    }

    public boolean getBackDarkSegmentDetected() {
        return buffer.testBit(LCCQB_BK_IASTAT2_DISP, (byte) 0x01);
    }

    public void setBackDarkSegmentDetected(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT2_DISP, (byte) 0x01);
    }

    private static final int LCCQB_BK_IASTAT3_DISP = 1416;

    public boolean getBackImageTooDark() {
        return buffer.testBit(LCCQB_BK_IASTAT3_DISP, (byte) 0x80);
    }

    public void setBackImageTooDark(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT3_DISP, (byte) 0x80);
    }

    public boolean getBackHeightExceedsMaximumSize() {
        return buffer.testBit(LCCQB_BK_IASTAT3_DISP, (byte) 0x20);
    }

    public void setBackHeightExceedsMaximumSize(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT3_DISP, (byte) 0x20);
    }

    public boolean getBackLengthExceedsMaximumSize() {
        return buffer.testBit(LCCQB_BK_IASTAT3_DISP, (byte) 0x10);
    }

    public void setBackLengthExceedsMaximumSize(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT3_DISP, (byte) 0x10);
    }

    public boolean getBackDynamicRangeBellowMinimum() {
        return buffer.testBit(LCCQB_BK_IASTAT3_DISP, (byte) 0x08);
    }

    public void setBackDynamicRangeBellowMinimum(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT3_DISP, (byte) 0x08);
    }

    public boolean getBackBrightnessBellowMinimum() {
        return buffer.testBit(LCCQB_BK_IASTAT3_DISP, (byte) 0x04);
    }

    public void setBackBrightnessBellowMinimum(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT3_DISP, (byte) 0x04);
    }

    public boolean getBackSharpnessBellowMinimum() {
        return buffer.testBit(LCCQB_BK_IASTAT3_DISP, (byte) 0x02);
    }

    public void setBackSharpnessBellowMinimum(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT3_DISP, (byte) 0x02);
    }

    public boolean getBackSharpnessExceedsMaximum() {
        return buffer.testBit(LCCQB_BK_IASTAT3_DISP, (byte) 0x01);
    }

    public void setBackSharpnessExceedsMaximum(boolean b) {
        setOrClearBit(b, LCCQB_BK_IASTAT3_DISP, (byte) 0x01);
    }

    private static final int HCC_CHAN_ERRS_DISP = 1471;

    public boolean getBGSRemoved() {
        return buffer.testBit(HCC_CHAN_ERRS_DISP, (byte) 0x08);
    }

    public void setBGSRemoved(boolean b) {
        setOrClearBit(b, HCC_CHAN_ERRS_DISP, (byte) 0x08);
    }

    public boolean getBBWRemoved() {
        return buffer.testBit(HCC_CHAN_ERRS_DISP, (byte) 0x04);
    }

    public void setBBWRemoved(boolean b) {
        setOrClearBit(b, HCC_CHAN_ERRS_DISP, (byte) 0x04);
    }

    public boolean getFGSRemoved() {
        return buffer.testBit(HCC_CHAN_ERRS_DISP, (byte) 0x02);
    }

    public void setFGSRemoved(boolean b) {
        setOrClearBit(b, HCC_CHAN_ERRS_DISP, (byte) 0x02);
    }

    public boolean getFBWRemoved() {
        return buffer.testBit(HCC_CHAN_ERRS_DISP, (byte) 0x01);
    }

    public void setFBWRemoved(boolean b) {
        setOrClearBit(b, HCC_CHAN_ERRS_DISP, (byte) 0x01);
    }

    private static final int HCC_CAPTSUM_DISP = 1472;

    public boolean getSuspectLogged() {
        return buffer.testBit(HCC_CAPTSUM_DISP, (byte) 0x02);
    }

    public void setSuspectLogged(boolean b) {
        setOrClearBit(b, HCC_CAPTSUM_DISP, (byte) 0x02);
    }

    public boolean getSuspect() {
        return buffer.testBit(HCC_CAPTSUM_DISP, (byte) 0x01);
    }

    public void setSuspect(boolean b) {
        setOrClearBit(b, HCC_CAPTSUM_DISP, (byte) 0x01);
    }

    private static final int MACH_TYPE_DISP = 1490;

    public String getMachType() {
        return buffer.readAsString(MACH_TYPE_DISP, 4);
    }

    public void setMachType(String s) {
        buffer.write(s, MACH_TYPE_DISP, 4);
    }

    private static final int MACH_TYPE_MODEL_DISP = 1494;

    public String getModelNumber() {
        return buffer.readAsString(MACH_TYPE_MODEL_DISP, 1);
    }

    public void setModelNumber(String s) {
        buffer.write(s, MACH_TYPE_MODEL_DISP, 1);
    }

    private static final int MACH_SER_NUM_DISP = 1495;

    public String getMachineSerialNumber() {
        return buffer.readAsString(MACH_SER_NUM_DISP, 5);
    }

    public void setMachineSerialNumber(String s) {
        buffer.write(s, MACH_SER_NUM_DISP, 5);
    }
}
