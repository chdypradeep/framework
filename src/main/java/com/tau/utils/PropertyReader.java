package com.tau.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public static Properties readpropertyFile(String filePath) {
		InputStream is = null;
		Properties prop = new Properties();
		try {
			is = new FileInputStream(System.getProperty("user.dir") + filePath);
			prop.load(is);
			is.close();
		} catch (IOException e) {
			System.out.println("Error in reading file at path " + filePath);
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

}
