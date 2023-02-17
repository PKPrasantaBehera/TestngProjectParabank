package com.framework.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadProp {
	
	public static Properties readData(String filename) {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\Config\\"+filename);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return prop;		
	}
}
