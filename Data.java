package com.Agent;

import java.util.HashMap;

public class Data {
	
	
	public static String innerText=null;
	public static String statusTag=null;
	public static HashMap<String,String> tags = new HashMap<String,String>();
	public static String URL= null;
	

	public static String getURL() {
		return URL;
	}

	public static void setURL(String uRL) {
		URL = uRL;
	}

	public static String getStatusTag() {
		return statusTag;
	}

	public static void setStatusTag(String statusTag) {
		Data.statusTag = statusTag;
	}

	public static String getInnerText() {
		return innerText;
	}

	public static void setInnerText(String innerText) {
		Data.innerText = innerText;
	}

}
