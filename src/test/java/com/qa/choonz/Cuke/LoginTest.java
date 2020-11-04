package com.qa.choonz.Cuke;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When; 


public class LoginTest {
	
	WebDriver driver; 
	
	WebDriverWait wait;
	
	@Before
	public void init() {
		System.setProperty("webdriver.edge.driver","C:\\Users\\oejac\\Desktop\\ICE_Final\\src\\test\\resources\\msedgedriver.exe");
	    driver = new EdgeDriver(); 
	    wait = new WebDriverWait(driver,5);
	}
	@Given("I am logged in and on the {string} page")
	public void logged_in_and_on_page(String string) throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/"+string+".html?user=1");
	    Thread.sleep(1000);
	}
	
	@Given("I am not logged in and on the {string} page")
	public void not_logged_in_and_on_page(String string) throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/"+string+".html");

	}
	
	@When("I click on the {string} page")
	public void Nav_to_page(String string) throws Throwable {
		WebElement  bar = driver.findElement(By.id("collapseIcon"));
		if(bar.isDisplayed()) {
			bar.click();
		}
	    WebElement NavLink = driver.findElement(By.id(string+"Link"));
	    wait.until(ExpectedConditions.visibilityOf(NavLink));
	    NavLink.click();
	}
	@When("I click on the {string} button")
	public void Nav_to_index(String string) throws Throwable {
		WebElement  bar = driver.findElement(By.id("collapseIcon"));
		if(bar.isDisplayed()) {
			bar.click();
		}
	    WebElement NavLink = driver.findElement(By.id("IndexLink"));
	    wait.until(ExpectedConditions.visibilityOf(NavLink));
	    assertThat(NavLink.getText()).isEqualTo(string);
	    NavLink.click();
	}
	@When("I click on the choonz btn")
	public void Click_choonz_btn() throws Throwable {
	    WebElement NavLink = driver.findElement(By.id("LogoLink"));
	    NavLink.click();
	}
	@Then("I will be on the {string} page and be logged in")
	public void assert_logged_in_and_on_page(String string) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/"+string+".html?user=1");		
	}
	@Then("I will be on the {string} page and not be logged in")
	public void assert_not_logged_in_and_on_page(String string) throws Throwable {
		if(string.equals("index")) {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/"+string+".html");
		}else {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/"+string+".html?user=0"); 
		}
	}
		
	
	
	@After
	public void quit() {
		driver.quit();
	}
}
