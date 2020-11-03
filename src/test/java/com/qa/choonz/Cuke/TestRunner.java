package com.qa.choonz.Cuke;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Cuke",
		plugin = {"pretty","html:target/reports/htmlReports","json:target/reports/json/ChoonzReport.json","junit:target/reports/xml/Choonz.xml"},
		monochrome = true	
		)
public class TestRunner {
	
	public TestRunner() {
		
	}
}
