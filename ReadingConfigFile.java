package com.Agent;
	
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.util.Properties;

	public class ReadingConfigFile {
		
		private static FileReader reader;
		private static Properties properties;

		
		public static String readingConfigFile(String elementName) {

			try {
				reader=new FileReader(System.getProperty("user.dir")+"\\Config\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("File Not Found error");
			}
			properties =new Properties();
			try {
				properties.load(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Input Output error");
			}
			
			String ur = properties.getProperty(elementName);
			if(ur!=null) {
				//System.out.println("Element found with value :"+ ur);
				return ur;
			}
			else throw new RuntimeException("Element Not found in the properties file");
		}
}
