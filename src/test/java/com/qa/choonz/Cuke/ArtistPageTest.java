package com.qa.choonz.Cuke;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ArtistPageTest {
	
	WebDriver driver; 
	
	WebDriverWait wait;
	Actions action;
	
	@Before("@tagArtist")
	public void init() {
		System.setProperty("webdriver.edge.driver","C:\\Users\\taydz\\Desktop\\Choonz-Starter-master\\src\\test\\resources\\msedgedriver.exe");
	    driver = new EdgeDriver(); 
	    wait = new WebDriverWait(driver,15);
	    action = new Actions(driver);
	}
	@Given("I am logged in and on the Artist page")
	public void logged_in_and_on_Artist_page() throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Artist.html?user=1");
	}
	
	@Given("I am not logged in and on the Artist page")
	public void not_logged_in_and_on_Artist_page() throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Artist.html");

	}
	
	@When("I click on the Artist with id {int}")
	public void select_Artist(int id) throws Throwable {
		WebElement ArtistRow = driver.findElement(By.id("ArtistRow"+id));
		wait.until(ExpectedConditions.visibilityOf(ArtistRow));
		action.moveToElement(ArtistRow);
	    WebElement ArtistLink = driver.findElement(By.id("ViewArtistButton"+id));
	    wait.until(ExpectedConditions.visibilityOf(ArtistLink));
	    wait.until(ExpectedConditions.elementToBeClickable(ArtistLink));
	    ArtistLink.click();
	}
	@When("I open the artist side bar and select order by {string}")
	public void select_order(String order) throws Throwable {
		if(order.equals("default")) {order="";}
		WebElement openFilter = driver.findElement(By.id("FilterByBTN"));
		wait.until(ExpectedConditions.visibilityOf(openFilter));
		wait.until(ExpectedConditions.elementToBeClickable(openFilter));
		openFilter.click();
	    WebElement OrderLink = driver.findElement(By.id("Order_"+order));
	    wait.until(ExpectedConditions.visibilityOf(OrderLink));
	    wait.until(ExpectedConditions.elementToBeClickable(OrderLink));
	    OrderLink.click();
	}
	
	@Then("I will be on the Artist page with artist id {int} and not be logged in")
	public void assert_not_logged_in_and_on_artists_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=0&artists="+id);
	}
	
	@Then("I will be on the Artist page with artist id {int} and be logged in")
	public void assert_logged_in_and_on_artists_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=1&artists="+id);
	}
	
	@Then("I will be on the Artist page with order {string} and not be logged in")
	public void assert_not_logged_in_and_on_ordered_artists(String order) throws Throwable {
		if(order.equals("default")) {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Artist.html?user=0");
		}else {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Artist.html?user=0&order="+order);
		}
	}
	@Then("I will be on the Artist page with order {string} and be logged in")
	public void assert_logged_in_and_on_ordered_artists(String order) throws Throwable {
		if(order.equals("default")) {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Artist.html?user=1");
		}else {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Artist.html?user=1&order="+order);
		}
	}

		
	
	
	@After("@tagArtist")
	public void quit() {
		driver.quit();
	}

}
