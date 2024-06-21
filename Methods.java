package com.Agent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Methods {
	
	
    public static String notVisbileMethod(String elementName) {    	
		
		String tf=null;
		try {
			if(StepDefinitons.getxpath(elementName)!=null) {				
				 tf ="false";
				return tf;				
				
		     	}
			}		
			catch(Exception e) {
			
				 tf ="true";
				return tf;				
			}
		return tf;				
		
     }
    
    public static String visbileMethod(String elementName) {
    		
		String tf=null;
		try {
			if(StepDefinitons.getxpath(elementName)!=null) {
				
				 tf ="false";
				return tf;				
				
		     	}
			}		
			catch(Exception e) {
			
				 tf ="true";
				return tf;				
			}
		return tf;			
		
      }
    
    public static String switchPages(String home) {
    	
    	
    	String tf=null;
		try {
			if(StepDefinitons.getxpath(home).isDisplayed()) {
				
				 tf ="true";
				return tf;				
				
		     	}
			}		
			catch(Exception e) {
			
				 tf ="false";
				return tf;				
			}
		return tf;	
    	
    }
    
    public static String comapreValues(String value, String valueToCompare) {
    	
    	String tf=null;
    	try {
    		if(value.equals(valueToCompare)) {
    			 tf ="true";
  				return tf;	
    		}
    		
    		}
    		catch(Exception e) {
    			
    			 tf ="false";
 				return tf;	
    		}
    	return tf;	
    }

     public static void switchPages2(String home,String url) throws Exception {
    	
  		try {
			if(StepDefinitons.driver.findElement(By.xpath("//a[contains(text(),'Gmailss')]"))!=null) {			
				
				StepDefinitons.getxpath("//textarea[@title=\"Search\"]").sendKeys("gmail");
							
		     	}
			else {
				
				StepDefinitons.driver.get(url);
				System.out.println( StepDefinitons.driver.getCurrentUrl());	
			}
			}		
			catch(Exception e) {
			
				System.out.println("Error message ::::::::::");
				System.out.println(e.getMessage());
			}
		
    	
    }
     
     public static String innerText(String elementName ) {
    	 
    	 String xpath = ReadingRepos.readingRepo(elementName);	
 		WebElement wb = StepDefinitons.getxpath(xpath);
 		
 		String s = wb.getAttribute("innerText");
 		System.out.println("Text of the field  : "+s);
 		
 		Data.setInnerText(s);
    	 return s;
     }

     public static String getInnerText(String valueToCompare) {
    	 
    	 String value = Data.getInnerText();
    	//System.out.println( Data.getInnerText());
    	
    	String tf=null;
    	try {
    		if(value.equals(valueToCompare)) {
    			 tf ="true";
  				return tf;	
    		}
    		
    		}
    		catch(Exception e) {
    			
    			 tf ="false";
 				return tf;	
    		}
    	return tf;	
    	 
     }
     
     public static String settingTagData() {
    	 
    	 String statusTag =Data.getStatusTag();
    	 String innerTest=Data.getInnerText();
    	 
    	return statusTag=innerTest;  	 
    	 
     }
}


