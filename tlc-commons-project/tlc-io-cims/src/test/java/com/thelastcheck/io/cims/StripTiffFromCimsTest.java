package com.thelastcheck.io.cims;

import org.junit.Test;

import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.thelastcheck.commons.buffer.ByteArray;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StripTiffFromCimsTest {
    private static String CIMS_FOLDER = "../../../test-files/";

    @Test
    public void stripOutCims() throws IOException {
//        extractTiff("20190220007100101822");
//        extractTiff("20190220007100101823");
//        extractTiff("20190220007100103337");
//        extractTiff("20190220007100103338");


        File file = new File(".");
        System.out.println(file.getAbsoluteFile());

        extractTiff("20190228007300300763");
        extractTiff("20190228007300305108");

    }

    private void extractTiff(String filename) throws IOException {
        byte[] bytes = readCimsFile(filename);
        ImageObject imageObject = new ImageObject(new ByteArray(bytes));
        byte[] segment = imageObject.getSegment(1);
        writeTiffFile(segment, filename);
    }

    private void writeTiffFile(byte[] segment, String filename) throws IOException {
        ByteSink sink = Files.asByteSink(new File("target/"+filename+".tiff"));
        sink.write(segment);
    }

    private byte[] readCimsFile(String filename) throws IOException {
        ByteSource byteSource = Resources.asByteSource(new URL("file:"+CIMS_FOLDER+filename+".cim"));
        byte[] bytes = byteSource.read();
        return bytes;
    }
}