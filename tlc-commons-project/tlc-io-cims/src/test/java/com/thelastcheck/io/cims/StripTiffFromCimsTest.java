package com.thelastcheck.io.cims;

import com.thelastcheck.commons.buffer.ByteArray;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StripTiffFromCimsTest {
    private static final String CIMS_FOLDER = "../../../test-files/";

    @Test
    public void stripOutCims() throws Exception {
//        extractTiff("20190220007100101822");
//        extractTiff("20190220007100101823");
//        extractTiff("20190220007100103337");
//        extractTiff("20190220007100103338");


        File file = new File(".");
        System.out.println(file.getAbsoluteFile());

        extractTiff("20190228007300300763");
        extractTiff("20190228007300305108");

    }

    private void extractTiff(String filename) throws Exception {
        byte[] bytes = readCimsFile(filename);
        ImageObject imageObject = new ImageObject(new ByteArray(bytes));
        byte[] segment = imageObject.getSegment(1);
        writeTiffFile(segment, filename);
    }

    private void writeTiffFile(byte[] segment, String filename) throws Exception {
        Path path = Paths.get("target/" + filename + ".tiff");
        Files.write(path, segment);
    }

    private byte[] readCimsFile(String filename) throws Exception {
        Path path = Paths.get(CIMS_FOLDER+filename+".cim");
        return Files.readAllBytes(path);
    }
}