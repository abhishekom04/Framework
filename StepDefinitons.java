package com.Agent;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;
import org.testng.log4testng.Logger;

import com.Utilities.LoggerFile;

import io.cucumber.java.*;
import io.cucumber.java.en.*;

public class StepDefinitons {
	
	
	 public static WebDriver driver;
	 String finalScenarioName;

	 @Before
	 public void setUp(Scenario scenario) {
		 
		 
		 String chromePath = ReadingConfigFile.readingConfigFile("ChromeDriver");
		 
		 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//driver//ChromeDriver//"+chromePath);
			ChromeOptions options =new ChromeOptions();
			options.addArguments("--remote-allow-origins=*","--start-maximized=*");
			driver = new ChromeDriver(options);
			//driver.manage().window().maximize();
			
			String scenarioName = scenario.getName();		
		    finalScenarioName = (String) scenarioName.subSequence(1,scenarioName.length()-1);
		    
		    System.out.println("Final Testcase Name : "+finalScenarioName);
		    //log.info(finalScenarioName);
			System.out.println("Scenario ID :: "+scenario.getId());	
			System.out.println("Scenario LINE :: "+scenario.getLine());
			System.out.println("Scenario class :: "+scenario.getClass());
			
			

	 }
	
	public  static WebElement getxpath(String element) {
		
		if (element!=null) {
			
			return StepDefinitons.driver.findElement(By.xpath(element));
		}
		
		else throw new NullPointerException("Xpath not found");
		
	}
	
	@When("^(\\w+) opens (.*)$")
	public void openURL(String user, String Url) {
				
		String url = ReadingRepos.readingRepo(Url);		
		driver.get(url);		
		
		
	
	}
	
	@Then("^(\\w+) click on (.*)$")
	public  void clickOnElement(String user, String elementName) {
		
		String xpath = ReadingRepos.readingRepo(elementName);
		WebElement wb = getxpath(xpath);
		
		
		if (wb.isEnabled()) {
			wb.click();
		}
		else throw new ElementNotInteractableException("Element is not clickable");
	}
	
	
	@When("^(\\w+) waits untill the visibility of (.*)$")
	public  void waitUntillVisibility(String user, String elementName) {
		
		String xpath = ReadingRepos.readingRepo(elementName);
		WebElement wb = getxpath(xpath);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	
	@And("^(\\w+) waits for (\\d+) seconds$")
	public void waitFor(String user, int sec) {
		
		//String s = String.valueOf(sec)+"000";
		//int secs = Integer.parseInt(s);
		
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@When("^(\\w+) enters \"([^\"]*)\" on control (.*)$")
	public void enterOnControl(String user, String data, String element) throws Exception {
		
		String xpath = ReadingRepos.readingRepo(element);	
		waitUntillVisibility(user, element);
		WebElement wb = getxpath(xpath);
		String data1;  		
		String actualData;
		
		if(data.startsWith("{") && data.endsWith("}")) {			
			//actualData = data.substring(1, data.length()-1);
			//System.out.println(actualData);
			data1 = ReadDataFromExcel.getData(finalScenarioName,data);
			System.out.println("Data read from Excel  :::::::  "+data1);
			wb.clear();	
	        wb.sendKeys(data1);
			
		}
		
		else if (data.replace("\"","").startsWith("@")) {
			
			wb.sendKeys(Data.tags.get(data));	
			System.out.println("Data read from the innerText :::::::::  "+Data.tags.get(data));
			
		}
		else {
			
			wb.clear();
			wb.sendKeys(data);
			System.out.println("Data read from the step ::::::  "+data);
		}
		
	}
	
	@When("^(\\w+) stores \"([^\"]*)\" of (.*) into (.*)$")
	public void readAttribute(String user, String innerText,String elementName,String save) {
				
		String s =Methods.innerText(elementName);
		//System.out.println("Before setStatusTag :::: "+save);
		Data.setStatusTag(save);     
		save= save.replace("\"","");
		//System.out.println("Before put ::::: "+save);
		Data.tags.put(save, s);
	}
	
	@When("^(\\w+) compares (.*) to (.*)")
	public void compareValues(String user,String value,String valueToCompare) throws Exception {
				
		valueToCompare = valueToCompare.replace("\"","");
		//System.out.println(value);
		if(value.equals(Data.getStatusTag())) {
		
			if(Boolean.parseBoolean(Methods.getInnerText(valueToCompare))==false) {
				
				throw new RuntimeException("Values are not equal");
			}
			else {
				System.out.println("Values are equal");
			}
		}
		else {
			
			throw new RuntimeException("Tags are not equal");
		}
		
	}
	
	@And("^(\\w+) is on (.*)$")
	public void onPage(String user, String pageName) {
		
		waitUntillVisibility(user, pageName);
		
	}
	
	@When("^(\\w+) moves to (.*)$")
	public void moveToElement(String user, String elementName) {
		
		waitUntillVisibility(user, elementName);
		String xpath = ReadingRepos.readingRepo(elementName);
		WebElement element = getxpath(xpath);
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].scrollIntoView(true);",element);

	}
	
	@And("(\\w+) verify (.*) is present with specialValue (.*)$")
	public void verifyIsPresent(String user, String elementName, String data) {
		
		
		data= data.replace("\"","");	
		String actualData;
		String data1=null;
		
		//keyName = keyName.replace("\"", "");		
        String xpath = ReadingRepos.readingRepo(elementName);
        
        if(data.startsWith("{") && data.endsWith("}")) {			
			//actualData = data.substring(1, data.length()-1);
			//System.out.println(actualData);
			data1 = ReadDataFromExcel.getData(finalScenarioName,data);
			System.out.println("Data read from Excel  :::::::  "+data1);	
				
		}
		
		else if (data.startsWith("@")) {
			System.out.println("Data is not from excel");
		}
		else {
			data1= data;		
		}
               
		xpath= xpath.replace("SpecialValue", data1);
		System.out.println(xpath);
		
       if(Boolean.parseBoolean(Methods.visbileMethod(xpath))==true){
			
			throw new RuntimeException("E N F");
		}
	}
	
	@And("(\\w+) verify (.*) is not present with specialValue (.*)$")
	public void verifyIsNotPresent(String user, String elementName, String data)  {
		
		
		data= data.replace("\"","");	
		String actualData;
		String data1=null;
		
		//keyName = keyName.replace("\"", "");		
        String xpath = ReadingRepos.readingRepo(elementName);
        
        if(data.startsWith("{") && data.endsWith("}")) {			
			//actualData = (String)data.subSequence(1, data.length()-1);
			//System.out.println(actualData);
			data1 = ReadDataFromExcel.getData(finalScenarioName,data);
			System.out.println("Data read from Excel  :::::::  "+data1);	
				
		}
		
		else if (data.startsWith("@")) {
			System.out.println("Data is not from excel");
		}
		else {
			data1= data;		
		}      
        
		xpath= xpath.replace("SpecialValue", data1);
		System.out.println(xpath);
		
		
		if(Boolean.parseBoolean(Methods.notVisbileMethod(xpath))==false){
			
			throw new RuntimeException("E F");
		}
		
	}
			
	
	
	@Then("^(\\w+) press \"([^\"]*)\"")
	public void keyPress(String user, String keyName) throws AWTException {
			
		Actions action = new Actions(driver);
		action.sendKeys(Keys.valueOf(keyName)).perform();
	    //action.sendKeys(Keys.PA
	}
	
	@Then("^(\\w+) closes the browser$")
	public void quitBrowser(String user) {
		driver.quit();
	}
	
	@Then("(\\w+) checks for (.*) and opens (.*)$")
	public void switchPage(String user,String homePage, String url) throws Exception {
		
		String xpath = ReadingRepos.readingRepo(homePage);
        url = url.replace("\"", "");
	   
       if(Boolean.parseBoolean(Methods.switchPages(xpath))){
			
        	driver.get(url);
        	System.out.println("IN if condition");
        	System.out.println(driver.getCurrentUrl());
		}
        else {
        
        	getxpath("//textarea[@title=\"Search\"]").sendKeys("gmail");
        	//driver.get(url);
        	System.out.println("IN else condition");
        	//System.out.println(driver.getCurrentUrl());
        }

	}
	
     
	@When("user stores the current url")
	public void storeURL() {
		
		 String currentURL = driver.getCurrentUrl();
		 Data.setURL(currentURL);
		
		
	}
	
	@Then("user open the stored url")
	public void opensStoredURL() {
		
		driver.get(Data.getURL());
		
	}
	
	@When("user reads innerText of (.*)$")
	public void readInnerText(String elementName) {
		
		Methods.innerText(elementName);
	}
	
	@Then("^user gets the value$")
	public void getText() {
		
		//Methods.getInnerText();
	}
	
	
	@After
	public void afterClose(Scenario scenario) {
		
		driver.close();
		driver.quit();
		System.out.println();
		
		HashMap<String,String> sc = new HashMap<String,String>();		
		sc.put(scenario.getName(), scenario.getStatus().toString());
				
		System.out.println("Passed count : "+Collections.frequency(sc.values(),"PASSED"));		
		
	}

}
