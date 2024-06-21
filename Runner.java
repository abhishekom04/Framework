package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features={"Features"},
		glue= {"com.Agent"},
		monochrome=true,
		strict=true,
		tags= {"@Google"},
		plugin = {"pretty"},
		dryRun = false
	   // "com.cucumber.listener.ExtentCucumberFormatter:src\\reports\\cucumber-reports\\report.html" },		
		)
public class Runner {

}
