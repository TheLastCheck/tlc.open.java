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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwingUtils {
	private static Logger log = LoggerFactory.getLogger(SwingUtils.class);

	// Specify the look and feel to use by defining the LOOKANDFEEL constant
	// Valid values are: null (use the default), "Metal", "System", "Motif",
	// and "GTK"
	private final static String LOOKANDFEEL = "System";

	// If you choose the Metal L&F, you can also choose a theme.
	// Specify the theme to use by defining the THEME constant
	// Valid values are: "DefaultMetal", "Ocean", and "Test"
	private final static String THEME = "Ocean";

	public static Dimension screenSize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Get size
		Dimension dimension = toolkit.getScreenSize();
		// Print size
		log.debug("Width: " + dimension.width);
		log.debug("Height: " + dimension.height);
		return dimension;
	}

	public static int screenResolution() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// get resolution
		int resolution = toolkit.getScreenResolution();
		// Print size
		// log.debug("Resolution: " + resolution);
		return resolution;

	}

	public static void updateScrollPane(JScrollPane pane, int hDiff, int vDiff) {
		JScrollBar hBar = pane.getHorizontalScrollBar();
		JScrollBar vBar = pane.getVerticalScrollBar();

		int hmax = hBar.getMaximum();
		int hmin = hBar.getMinimum();
		int hval = hBar.getValue();
		int hext = hBar.getVisibleAmount();

		int vmax = vBar.getMaximum();
		int vmin = vBar.getMinimum();
		int vval = vBar.getValue();
		int vext = vBar.getVisibleAmount();

		hval += hDiff;
		vval += vDiff;

		if ((hval < (hmax - hext)) && (hval > hmin)) {
			hBar.setValue(hval);
		}
		if ((vval < (vmax - vext)) && (vval > vmin)) {
			vBar.setValue(vval);
		}
	}

	public static GridBagConstraints set_gbc(int row, int column, int height,
			int width, int fill) {
		GridBagConstraints gbc = new GridBagConstraints();
		set_gbc(gbc, row, column, height, width, fill);
		return gbc;
	}

	public static GridBagConstraints set_gbc(GridBagConstraints gbc, int row,
			int column, int height, int width) {
		gbc.gridy = row;
		gbc.gridx = column;
		gbc.gridheight = height;
		gbc.gridwidth = width;
		return gbc;
	}

	public static GridBagConstraints set_gbc(GridBagConstraints gbc, int row,
			int column, int height, int width, int fill) {
		gbc.gridy = row;
		gbc.gridx = column;
		gbc.gridheight = height;
		gbc.gridwidth = width;
		gbc.fill = fill;
		// GridBagConstraints.NONE .HORIZONTAL .VERTICAL
		// .BOTH
		return gbc;
	}

	public static void initLookAndFeel() {
		String lookAndFeel = null;

		if (LOOKANDFEEL != null) {
			if (LOOKANDFEEL.equals("Metal")) {
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
				// an alternative way to set the Metal L&F is to replace the
				// previous line with:
				// lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";

			}

			else if (LOOKANDFEEL.equals("System")) {
				lookAndFeel = UIManager.getSystemLookAndFeelClassName();
			}

			else if (LOOKANDFEEL.equals("Motif")) {
				lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			}

			else if (LOOKANDFEEL.equals("GTK")) {
				lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
			}

			else {
				log.warn("Unexpected value of LOOKANDFEEL specified: "
						+ LOOKANDFEEL);
				lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			}

			try {

				UIManager.setLookAndFeel(lookAndFeel);

				// If L&F = "Metal", set the theme

				if (LOOKANDFEEL.equals("Metal")) {
					if (THEME.equals("DefaultMetal"))
						MetalLookAndFeel
								.setCurrentTheme(new DefaultMetalTheme());
					else if (THEME.equals("Ocean"))
						MetalLookAndFeel.setCurrentTheme(new OceanTheme());
					else
						MetalLookAndFeel
								.setCurrentTheme(new DefaultMetalTheme());

					UIManager.setLookAndFeel(new MetalLookAndFeel());
				}
				/*
				 * IRC-102: Set fonts and colors in the application for each
				 * used field instead of here on a global basis. This leaves
				 * system object fonts alone that might interfer with proper
				 * display of system dialogs.
				 */
				/**
				 * <code>
                Font font = new Font("Tahoma", Font.PLAIN, 14);
                FontUIResource fontResource = new FontUIResource(font);
                ColorUIResource colorResource = new ColorUIResource(Color.DARK_GRAY);

                UIDefaults defaults = UIManager.getDefaults();
                Enumeration<?> enum1 = defaults.keys();
                while (enum1.hasMoreElements()) {
                    // IRC-49: Couldn't get specified look and feel
                    String key = enum1.nextElement().toString();
                    String keyTest = key.toLowerCase();
                    if (keyTest.indexOf("font") != -1) {
                        log.debug(key + "=" + defaults.get(key));
                        if (defaults.get(key) instanceof FontUIResource) {
                            defaults.put(key, fontResource);
                        }
                    }
                    if (keyTest.indexOf("foreground") != -1) {
                        log.debug(key + "=" + defaults.get(key));
                        if (defaults.get(key) instanceof ColorUIResource) {
                            if (keyTest.indexOf("label") >= 0) {
                                defaults.put(key, colorResource);
                            }
                             *
                             * if (keyTest.indexOf("text") == -1 &&
                             * keyTest.indexOf("menu") == -1 &&
                             * keyTest.indexOf("button") == -1 &&
                             * keyTest.indexOf("listxxx") == -1) {
                             * defaults.put(key, colorResource); }
                             *
                        }
                    }
                }
                 * </code>
				 */
			}

			catch (ClassNotFoundException e) {
				log.warn("Couldn't find class for specified look and feel:"
						+ lookAndFeel);
				log.warn("Did you include the L&F library in the class path?");
				log.warn("Using the default look and feel.");
			}

			catch (UnsupportedLookAndFeelException e) {
				log.warn("Can't use the specified look and feel ("
						+ lookAndFeel + ") on this platform.");
				log.warn("Using the default look and feel.");
			}

			catch (Exception e) {
				log.warn("Couldn't get specified look and feel (" + lookAndFeel
						+ "), for some reason: ", e);
				log.warn("Using the default look and feel.");
			}
		}
	}

	public static Point centerPoint(int width, int height, JFrame frame) {
		Point point = null;
		Dimension frameSize = frame.getSize();
		Point framePoint = frame.getLocation();
		point = new Point(framePoint.x + (frameSize.width / 2) - (width / 2),
				framePoint.y + (frameSize.height / 2) - (height / 2));
		return point;
	}

}
