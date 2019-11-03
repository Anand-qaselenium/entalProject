package com.entel.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig() {
		File src = new File("configurations//config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Exception is: "+e.getMessage() );
		} catch (IOException e) {
			System.out.println("Exception is: "+e.getMessage() );
		}
	}
		
	
	public String getConfigValue(String conID) {
		String configVal = pro.getProperty(conID);
		return configVal.trim();
	}

}
