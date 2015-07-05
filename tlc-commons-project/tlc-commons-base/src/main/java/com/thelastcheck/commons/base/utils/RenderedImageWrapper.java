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

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;
import com.sun.media.jai.codec.TIFFDirectory;
import com.sun.media.jai.codec.TIFFEncodeParam;
import com.sun.media.jai.codec.TIFFField;
import com.sun.media.jai.codecimpl.TIFFImageDecoder;

public class RenderedImageWrapper {

	public static final int			TAG_YRESOLUTION					= TIFFImageDecoder.TIFF_Y_RESOLUTION;
	public static final int			TAG_XRESOLUTION					= TIFFImageDecoder.TIFF_X_RESOLUTION;
	public static final int			TAG_RESOLUTION_UNIT				= TIFFImageDecoder.TIFF_RESOLUTION_UNIT;
	public static final int			TAG_PHOTOMETRIC_INTERPRETATION	= TIFFImageDecoder.TIFF_PHOTOMETRIC_INTERPRETATION;

	private RenderedImage			image;
	private TIFFDirectory			directory;
	private Map<Integer, TIFFField>	fieldMap						= new HashMap<Integer, TIFFField>();
	private boolean					isTiff							= false;

	@SuppressWarnings("unused")
	private RenderedImageWrapper() {
	}

	public RenderedImageWrapper(RenderedImage image) {
		this.image = image;
		Object obj = image.getProperty("tiff_directory");
		if (obj != Image.UndefinedProperty) {
			isTiff = true;
			directory = (TIFFDirectory) obj;
			TIFFField[] fields = directory.getFields();
			for (int i = 0; i < fields.length; i++) {
				TIFFField tiffField = fields[i];
				fieldMap.put(tiffField.getTag(), tiffField);
			}
		}
		// TiffImage tImage = new TiffImage();
	}

	public TIFFField tiffField(int tag) {
		TIFFField field = null;
		if (isTiff) {
			if (fieldMap.containsKey(tag)) {
				field = fieldMap.get(tag);
			}
		}
		return field;
	}

	public boolean isTiff() {
		return isTiff;
	}

	public RenderedImage image() {
		return image;
	}

	public long resolution() {
		long[] values = resolutionValues(image);
		long res = values[0];
		return res;
	}

	public long[] resolutionValues(RenderedImage im) {
		TIFFField xresField = fieldMap.get(TAG_XRESOLUTION);
		TIFFField yresField = fieldMap.get(TAG_YRESOLUTION);
		long xres = getFieldValue(xresField);
		long yres = getFieldValue(yresField);
		long[] values = new long[2];
		values[0] = xres;
		values[1] = yres;
		return values;
	}

	private long getFieldValue(TIFFField field) {
		long value;
		if (field.getType() == TIFFField.TIFF_DOUBLE) {
			value = (int) field.getAsDouble(0);
		} else if (field.getType() == TIFFField.TIFF_FLOAT) {
			value = (int) field.getAsFloat(0);
		} else if (field.getType() == TIFFField.TIFF_RATIONAL) {
			long[] values = field.getAsRational(0);
			value = (int) ((double) values[0] / (double) values[1]);
		} else if (field.getType() == TIFFField.TIFF_LONG) {
			value = (int) field.getAsLong(0);
		} else {
			value = field.getAsInt(0);
		}
		return value;
	}

	public byte[] convertToTiff() throws IOException {
		RenderedImage image = this.image;
		TIFFEncodeParam param = new TIFFEncodeParam();
		param.setCompression(TIFFEncodeParam.COMPRESSION_GROUP4);
		param.setLittleEndian(true);
		param.setTileSize(image.getWidth(), image.getHeight());

		TIFFField xresField = tiffField(RenderedImageWrapper.TAG_XRESOLUTION);
		TIFFField yresField = tiffField(RenderedImageWrapper.TAG_YRESOLUTION);
		TIFFField photoMetricField = tiffField(RenderedImageWrapper.TAG_PHOTOMETRIC_INTERPRETATION);

		char[] resUnitValue = new char[1];
		resUnitValue[0] = 2;
		TIFFField resUnitField = new TIFFField(TAG_RESOLUTION_UNIT, TIFFField.TIFF_SHORT, 1, resUnitValue);

		if (xresField == null) {
			long[][] rational = new long[][] { { (long) 240, (long) 1 }, { (long) 0, (long) 0 } };
			xresField = new TIFFField(TAG_XRESOLUTION, TIFFField.TIFF_RATIONAL, 1, rational);
		}
		if (yresField == null) {
			long[][] rational = new long[][] { { (long) 240, (long) 1 }, { (long) 0, (long) 0 } };
			yresField = new TIFFField(TAG_YRESOLUTION, TIFFField.TIFF_RATIONAL, 1, rational);
		}
		if (photoMetricField == null) {
			char[] value = new char[1];
			value[0] = 0;
			photoMetricField = new TIFFField(TAG_PHOTOMETRIC_INTERPRETATION, TIFFField.TIFF_SHORT, 1, value);
		}
		TIFFField[] extraFields = { photoMetricField, xresField, yresField, resUnitField };
		param.setExtraFields(extraFields);

		int size = ((image.getWidth() * image.getHeight()) / 8) + 2048;
		ByteArrayOutputStream stream = new ByteArrayOutputStream(size);
		ImageEncoder enc = ImageCodec.createImageEncoder("tiff", stream, param);
		enc.encode(image);

		byte[] ba = stream.toByteArray();
		return ba;
	}

	public RenderedImage rotateImage(int degree) {
		return rotateImage(image, degree);
	}

	public static RenderedImage rotateImage(RenderedImage image, int degree) {

		// Create the rotation angle and convert to radians.
		float angle = (float) (degree * (Math.PI / 180.0F));

		// Create a ParameterBlock and specify the source and parameters
		ParameterBlock pb = new ParameterBlock();

		pb.addSource(image); // The source image

		float centerX = image.getWidth() / 2f;
		float centerY = image.getHeight() / 2f;

		pb.add(centerX); // The x origin
		pb.add(centerY); // The y origin
		pb.add(angle); // The rotation angle

		pb.add(new InterpolationNearest()); // The interpolation

		// Create the rotate operation
		RenderedImage result = JAI.create("Rotate", pb, null);

		return result;
	}

}
