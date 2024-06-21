package com.Agent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadingRepos {
	
	private static FileReader reader;
	private static Properties properties;

	public static String readingRepo(String elementName) {

		String repoPath = ReadingConfigFile.readingConfigFile("RepoPath");
		
		try {
			reader=new FileReader(System.getProperty("user.dir")+"\\Repo\\"+repoPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		properties =new Properties();
		try {
			properties.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String ur = properties.getProperty(elementName);
		if(ur!=null) {
			//System.out.println("Element found with value :"+ ur);
			return ur;
		}
		else throw new RuntimeException("Element Not found in the properties file");
	}

}
