package com.autodeskcrm.genericutility;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * @author JEET DAS
 * 
 */
public class FileLib {
	/**
	 * used to get the data from properties file based on key
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String getPropertyKeyValue(String key) throws Throwable
	{
		/*step1:-create java representation object of the physical file*/
		FileInputStream fis=new FileInputStream("./testData/commonData.properties");
		
		/*step2:-using Properties class load all the key into the java object*/
		Properties pobj=new Properties();
		pobj.load(fis);
		
		/*step3:-read the data from properties file*/
		String value=pobj.getProperty(key);
		/*System.out.println(value);*/
		
		return value;
	}

}
