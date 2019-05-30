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


import com.google.common.base.Preconditions;
import com.thelastcheck.commons.buffer.ByteArray;

public class CimsSuspectRecord {

    public static final int RECORD_LENGTH = 32;
    ByteArray buffer;

    /**
     * create default cims header, and set all predefined values
     */
    public CimsSuspectRecord() {
        buffer = new ByteArray(RECORD_LENGTH);
        //set suspect record format
        setImageRecordIndicator(ELRHIDDF_VALUE);
    }

    public CimsSuspectRecord(byte[] array) {
        Preconditions.checkArgument(array.length == RECORD_LENGTH,
                "Buffer length must be [%s], was [%s]",
                RECORD_LENGTH, array.length);
        buffer = new ByteArray(array);
    }

    public byte[] toByteArray() {
        return buffer.getBytes();
    }

    private static final int ELRHSHLQ_DISP = 0;
    private static final int ELRHSHLQ_LEN = 8;

    public String getHighLevelQualifierName() {
        return buffer.readAsString(
                ELRHSHLQ_DISP,
                ELRHSHLQ_LEN);
    }

    public void setHighLevelQualifierName(String s) {
        buffer.write(s, ELRHSHLQ_DISP, ELRHSHLQ_LEN);
    }

    private static final int ELRHIDDF_DISP = 8;
    private static final int ELRHIDDF_LEN = 1;
    private static final byte ELRHIDDF_VALUE = (byte) 0x81;

    public byte getImageRecordIndicator() {
        return buffer.readAsByte(ELRHIDDF_DISP);
    }

    public void setImageRecordIndicator(byte b) {
        buffer.write(b, ELRHIDDF_DISP);
    }

    private static final int ELRHSPLL_DISP = 9;
    private static final int ELRHSPLL_LEN = 3;

    public int getTotalRecordLength() {
        byte[] temp = new byte[4];
        buffer.read(ELRHSPLL_DISP, temp, 1, ELRHSPLL_LEN);
        ByteArray tempba = new ByteArray(temp);
        return tempba.readAsInt(0);
    }

    public void setTotalRecordLength(int totalRecordLength) {
        if (totalRecordLength < 0) {
            throw new IllegalArgumentException();
        }
        byte[] temp = new byte[4];
        ByteArray tempba = new ByteArray(temp);
        tempba.write(totalRecordLength, 0);
        buffer.write(temp, 1, ELRHSPLL_LEN, ELRHSPLL_DISP);
    }

    private static final int ELRHSCYC_DISP = 12;
    private static final int ELRHSCYC_LEN = 1;

    public String getCycleNumber() {
        return buffer.readPns(ELRHSCYC_DISP, ELRHSCYC_LEN, true);
    }

    public void setCycleNumber(String cycleNumber) {
        buffer.writeAsPns(cycleNumber, ELRHSCYC_DISP, ELRHSCYC_LEN);
    }

    private static final int ELRHSDAT_DISP = 13;
    private static final int ELRHSDAT_LEN = 4;

    public String getCycleDate() {
        return buffer.readPns(ELRHSDAT_DISP, ELRHSDAT_LEN, true);
    }

    public void setCycleDate(String cycleDate) {
        buffer.writeAsPns(cycleDate, ELRHSDAT_DISP, ELRHSDAT_LEN);
    }

    private static final int ELRHSLSZ_DISP = 17;
    private static final int ELRHSLSZ_LEN = 2;

    public int getItemsInRecord() {
        return buffer.readAsShort(ELRHSLSZ_DISP);
    }

    public void setItemsInRecord(int itemsInRecord) {
        if (itemsInRecord < 0) {
            throw new IllegalArgumentException();
        }
        buffer.write((short) itemsInRecord, ELRHSLSZ_DISP);
    }

    private static final int ELRHSISN_DISP = 20;
    private static final int ELRHSISN_LEN = 5;

    public String getItemSequenceNumber() {
        return buffer.readPns(ELRHSISN_DISP, ELRHSISN_LEN);
    }

    public void setItemSequenceNumber(String itemSequenceNumber) {
        buffer.writeAsPns(itemSequenceNumber, ELRHSISN_DISP, ELRHSISN_LEN);
    }

    private static final int ELRHSITN_DISP = 25;
    private static final int ELRHSITN_LEN = 1;

    public int getItemTrackingNumber() {
        return buffer.readAsByte(ELRHSITN_DISP);
    }

    public void setItemTrackingNumber(int itemTrackingNumber) {
        if (itemTrackingNumber < 0) {
            throw new IllegalArgumentException();
        }
        buffer.write((byte) itemTrackingNumber, ELRHSITN_DISP);
    }

    private static final int ELRHSR01_DISP = 26;
    private static final int ELRHSR02_DISP = 27;
    private static final int ELRHSR03_DISP = 28;
    private static final int ELRHSR04_DISP = 29;
    private static final int ELRHSR05_DISP = 30;

    public boolean isFBWImageExceededMaxImageSize() {
        return getBooleanField(ELRHSR01_DISP, (byte) 0x01);
    }

    public boolean isFGSImageExceededMaxImageSize() {
        return getBooleanField(ELRHSR01_DISP, (byte) 0x02);
    }

    public boolean isBBWImageExceededMaxImageSize() {
        return getBooleanField(ELRHSR01_DISP, (byte) 0x04);
    }

    public boolean isBGSImageExceededMaxImageSize() {
        return getBooleanField(ELRHSR01_DISP, (byte) 0x08);
    }

    public boolean isFBWImageUnderMinImageSize() {
        return getBooleanField(ELRHSR01_DISP, (byte) 0x10);
    }

    public boolean isFGSImageUnderMinImageSize() {
        return getBooleanField(ELRHSR01_DISP, (byte) 0x20);
    }

    public boolean isBBWImageUnderMinImageSize() {
        return getBooleanField(ELRHSR01_DISP, (byte) 0x40);
    }

    public boolean isBGSImageUnderMinImageSize() {
        return getBooleanField(ELRHSR01_DISP, (byte) 0x80);
    }

    public boolean isFrontScannedImageQualitySuspect() {
        return getBooleanField(ELRHSR02_DISP, (byte) 0x20);
    }

    public boolean isBackScannedImageQualitySuspect() {
        return getBooleanField(ELRHSR03_DISP, (byte) 0x20);
    }

    public boolean isNoImage() {
        return getBooleanField(ELRHSR05_DISP, (byte) 0x01);
    }

    public boolean isImagesRemovedDueToHostDataSizeLimit() {
        return getBooleanField(ELRHSR05_DISP, (byte) 0x10);
    }

    public boolean isItemInvolvedInQuickstop() {
        return getBooleanField(ELRHSR05_DISP, (byte) 0x40);
    }

    public boolean isSCIImageChoicesNotHonored() {
        return getBooleanField(ELRHSR05_DISP, (byte) 0x80);
    }

    public void setSCIImageChoicesNotHonored(boolean value) {
        setBooleanField(ELRHSR05_DISP, (byte) 0x80, value);
    }

    private static final int ELRHPATH_DISP = 31;
    private static final int ELRHPATH_LEN = 1;

    public short getBackPathNumber() {
        String value =
                buffer.readPns(
                        ELRHPATH_DISP,
                        ELRHPATH_LEN);
        value = value.substring(0, 1);
        short number = Short.parseShort(value, 16);
        return number;
    }

    public short getFrontPathNumber() {
        String value =
                buffer.readPns(
                        ELRHPATH_DISP,
                        ELRHPATH_LEN);
        value = value.substring(1, 2);
        short number = Short.parseShort(value, 16);
        return number;
    }

    /**
     * @param fldDisp  The offset in the buffer, first byte is 0.
     * @param bitValue The bit to be checked in the byte.
     * @return A boolean value indicating if the bit was one.
     */
    private boolean getBooleanField(int fldDisp, byte bitValue) {
        return buffer.testBit(fldDisp, bitValue);
    }

    private void setBooleanField(int fldDisp, byte bitValue, boolean value) {
        buffer.setBit(fldDisp, bitValue, value);
    }

}
