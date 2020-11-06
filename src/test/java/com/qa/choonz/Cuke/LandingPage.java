package com.qa.choonz.Cuke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When; 


public class LandingPage {
	
	WebDriver driver; 
	
	WebDriverWait wait;
	
	@Before("@tagLanding")
	public void init() {
		System.setProperty("webdriver.edge.driver","C:\\Users\\oejac\\Desktop\\ICE_Final\\src\\test\\resources\\msedgedriver.exe");
	    driver = new EdgeDriver(); 
	    wait = new WebDriverWait(driver,5);
	    
	}
	    @Given("I have my browser running")
		public void i_have_my_browser_running() throws Throwable {
			driver.get("https://google.com");		
	        assertEquals("Google", driver.getTitle());
	        Thread.sleep(1000);
		}

		@When("I enter the url")
		public void i_enter_the_url() throws Throwable {
			driver.get("http://127.0.0.1:5501/html/index.html");
			Thread.sleep(1000);
		}

		@Then("I will be taken to the Landing Page")
		public void i_will_be_taken_to_the_Landing_Page() throws Throwable {
			driver.get("http://127.0.0.1:5501/html/index.html");
			assertEquals("Choonz Music", driver.getTitle());
		}
		
	    @Given("I am on the landing page")
		public void i_am_on_the_landing_page() throws Throwable {
			driver.get("http://127.0.0.1:5501/html/index.html");		
	        assertEquals("Choonz Music", driver.getTitle());
	        Thread.sleep(1000);
		}

		@When("I enter my username and password")
		public void i_enter_my__login_details() throws Throwable {
			
			WebElement name = driver.findElement(By.id("login-username"));
			WebElement password = driver.findElement(By.id("login-password"));
			name.sendKeys("rootroot");
			password.sendKeys("rootroot");
			
			driver.findElement(By.xpath("/html/body/div/div/div[1]/form/div[3]/button"))
			.click();
			Thread.sleep(1000);
		}

		@Then("I will be taken to the Home Page")
		public void i_will_be_taken_to_the_HomePage() throws Throwable {			
			assertEquals("Home Page", driver.getTitle());
		}

		@When("I enter my details")
		public void i_enter_my_details() throws Throwable {
			WebElement name2 = driver.findElement(By.id("name"));
			WebElement username = driver.findElement(By.id("username"));
			WebElement password2 = driver.findElement(By.id("password"));
			WebElement password3 = driver.findElement(By.id("secondPassword"));
			name2.sendKeys("Owen");
			username.sendKeys("OEJACKSON");
			password2.sendKeys("password");
			password3.sendKeys("password");
			
			driver.findElement(By.xpath("/html/body/div/div/div[2]/form/div[5]/button"))
			.click();
			Thread.sleep(1000);
		}

		@Then("I will create an account")
		public void i_will_create_an_account() throws Throwable {
			String text = driver.switchTo().alert().getText();
			Thread.sleep(1000);
			driver.switchTo().alert().accept();
			Thread.sleep(100);
			driver.switchTo().alert().accept();
			Thread.sleep(1000);
			assertEquals("You have created an account", text);
			
		}
			
	@After("@tagLanding")
	public void quit() {
		driver.quit();
	}
}
