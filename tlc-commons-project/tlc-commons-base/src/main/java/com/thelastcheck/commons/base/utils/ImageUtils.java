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

import com.thelastcheck.commons.buffer.ByteArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.media.jai.BorderExtender;
import javax.media.jai.Histogram;
import javax.media.jai.InterpolationBicubic;
import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.ParameterBlockJAI;
import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class ImageUtils {

    private static Logger log = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * this gets rid of exception for not using native acceleration
     */
    static {
        System.setProperty("com.sun.media.jai.disableMediaLib", "true");
    }

    public static RenderedImage convertToRenderedImage(byte[] data) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        ImageInputStream iis = ImageIO.createImageInputStream(stream);
		RenderedImage im = ImageIO.read(iis);

        if (im == null) {
            ByteArray ba = new ByteArray(data);
            int len = ba.getLength() < 24 ? ba.getLength() : 24;
            log.warn("Unable to find codec for data: " + ba.readPns(0, len));
        }
        return im;
    }

    public static void saveImage(byte[] data, String fileName) throws IOException {
        File file = new File(fileName);
        saveImage(data, file);
    }

    public static void saveImage(byte[] data, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
    }

    public static BufferedImage convertToBufferedImage(RenderedImage im) {
        BufferedImage image = PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
        return image;
    }

    public static BufferedImage convertToBufferedImage(byte[] data) throws IOException {
        RenderedImage im = convertToRenderedImage(data);
        BufferedImage image = convertToBufferedImage(im);
        return image;
    }

    public static BufferedImage scaleImage(RenderedImageWrapper wrapper, double scale) {
        BufferedImage bi = convertToBufferedImage(wrapper.image());
        return scaleImage(bi, scale);
    }

    public static BufferedImage scaleImage(BufferedImage bi, double scale) {
        BufferedImage biNew = new BufferedImage((int) (bi.getWidth() * scale), (int) (bi.getHeight() * scale),
                BufferedImage.TYPE_INT_RGB);
        AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
        // AffineTransformOp op = new AffineTransformOp(at, null);
        Graphics2D g = biNew.createGraphics();
        g.drawImage(bi, at, null);
        // op.filter(bi, biNew);
        return biNew;
    }

    public static ImageIcon convertToImageIcon(BufferedImage bi) {
        ImageIcon ii = new ImageIcon(bi);
        return ii;
    }

    public static BufferedImage rotateImage(BufferedImage bi, int rotations) {
        rotations = rotations % 4;
        int newWidth = bi.getWidth();
        int newHeight = bi.getHeight();
        int moveX = 0;
        int moveY = 0;
        if (rotations % 2 != 0) {
            newHeight = bi.getWidth();
            newWidth = bi.getHeight();
        }
        if (rotations > 1)
            moveY = newHeight;
        if (rotations > 0 && rotations < 3)
            moveX = newWidth;
        Graphics2D g2d = bi.createGraphics();
        AffineTransform af = new AffineTransform();
        af.concatenate(AffineTransform.getTranslateInstance(moveX, moveY));
        af.concatenate(AffineTransform.getRotateInstance(rotations * 0.5 * Math.PI));
        g2d.drawImage((Image) bi, af, null);
        return bi;
    }

    public static ParameterBlock getParameterBlockForImage(RenderedImage image) {
        ParameterBlock pb = new ParameterBlock();
        pb.addSource(image);
        return pb;
    }

    public static ParameterBlockJAI getParameterBlockJAIForImage(RenderedImage image, String op) {
        ParameterBlockJAI pb = new ParameterBlockJAI(op);
        pb.addSource(image);
        return pb;
    }

    public static RenderedImage scale(PlanarImage image, float scale) {
        return scale(image, scale, InterpolationBicubic.INTERP_BICUBIC);
    }

    public static RenderedImage scale(RenderedImage image, float scale, int interpolation) {
        ParameterBlock pb = getParameterBlockForImage(image);
        pb.add(scale);
        pb.add(scale);
        pb.add(0.0F);
        pb.add(0.0F);
        pb.add(InterpolationBicubic.getInstance(interpolation));
        return JAI.create("scale", pb);
    }

    public static RenderedImage grayscale(RenderedImage image) {
        if (image.getData().getNumBands() == 1)
            return image;
        // a lot of sample code uses the following instead of 1/3
        // double[][] matrix = { { 0.114, 0.587, 0.299, 0 } };
        final double[][] matrix1 = {{1. / 3, 1. / 3, 1. / 3, 0}};
        ParameterBlock pb1 = getParameterBlockForImage(image);
        pb1.add(matrix1);
        return JAI.create("bandcombine", pb1, null);
    }

    public static RenderedImage invert(RenderedImage image) {
        return JAI.create("invert", getParameterBlockForImage(image), null);
    }

    public static RenderedImage binarize(RenderedImage image) {
        if (image.getData().getNumBands() > 1)
            image = grayscale(image);
        return binarize(image, getBinarizationThreshold(image));
    }

    public static RenderedImage binarize(RenderedImage image, double threshold) {
        if (image.getData().getNumBands() > 1)
            image = grayscale(image);
        ParameterBlock pb = getParameterBlockForImage(image);
        pb.add(threshold);
        return JAI.create("binarize", pb);
    }

    public static double getBinarizationThreshold(RenderedImage image) {
        ParameterBlock pb = getParameterBlockForImage(image);
        pb.add(null); // The ROI
        pb.add(1);
        pb.add(1);
        pb.add(new int[]{256});
        pb.add(new double[]{0});
        pb.add(new double[]{256});
        Histogram histogram = (Histogram) JAI.create("histogram", pb).getProperty("histogram");
        return histogram.getMinFuzzinessThreshold()[0];
    }

    public static RenderedImage crop(RenderedImage image, Rectangle2D rectangle) {
        return crop(image, (float) rectangle.getX(), (float) rectangle.getY(), (float) rectangle.getWidth(),
                (float) rectangle.getHeight());
    }

    public static RenderedImage crop(RenderedImage image, float x, float y, float width, float height) {
        ParameterBlock pb = getParameterBlockForImage(image);
        pb.add(x);
        pb.add(y);
        pb.add(width);
        pb.add(height);
        return JAI.create("crop", pb);
    }

    public static RenderedImage blur(RenderedImage image, int radius) {
        int klen = Math.max(radius, 2);
        int ksize = klen * klen;
        float f = 1f / ksize;
        float[] kern = new float[ksize];
        for (int i = 0; i < ksize; i++)
            kern[i] = f;
        KernelJAI blur = new KernelJAI(klen, klen, kern);
        ParameterBlockJAI param = getParameterBlockJAIForImage(image, "Convolve");
        param.setParameter("kernel", blur);
        // hint with border extender
        RenderingHints hint = new RenderingHints(JAI.KEY_BORDER_EXTENDER, BorderExtender
                .createInstance(BorderExtender.BORDER_COPY));
        return JAI.create("Convolve", param, hint);
    }
}
