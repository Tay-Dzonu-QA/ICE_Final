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

public class GenrePageTest {
	
	WebDriver driver; 
	
	WebDriverWait wait;
	Actions action;
	
	@Before("@tagGenre")
	public void init() {
		System.setProperty("webdriver.edge.driver","C:\\Users\\taydz\\Desktop\\Choonz-Starter-master\\src\\test\\resources\\msedgedriver.exe");
	    driver = new EdgeDriver(); 
	    wait = new WebDriverWait(driver,15);
	    action = new Actions(driver);
	}
	@Given("I am logged in and on the Genre page")
	public void logged_in_and_on_Genre_page() throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Genre.html?user=1");
	}
	
	@Given("I am not logged in and on the Genre page")
	public void not_logged_in_and_on_Genre_page() throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Genre.html");
	}
	
	@When("I click on the Genre with id {int}")
	public void select_Genre(int id) throws Throwable {
		WebElement GenreRow = driver.findElement(By.id("GenreRow"+id));
		action.moveToElement(GenreRow);
	    WebElement GenreLink = driver.findElement(By.id("ViewGenreButton"+id));
	    wait.until(ExpectedConditions.visibilityOf(GenreLink));
	    GenreLink.click();
	}
	@When("I open the genre side bar and select order by {string}")
	public void select_order(String order) throws Throwable {
		if(order.equals("default")) {order="";}
		WebElement openFilter = driver.findElement(By.id("FilterByBTN"));
		wait.until(ExpectedConditions.visibilityOf(openFilter));
		openFilter.click();
	    WebElement OrderLink = driver.findElement(By.id("Order_"+order));
	    wait.until(ExpectedConditions.visibilityOf(OrderLink));
	    OrderLink.click();
	}
	
	@Then("I will be on the Genre page with genre id {int} and not be logged in")
	public void assert_not_logged_in_and_on_genres_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=0&genres="+id);
	}
	
	@Then("I will be on the Genre page with genre id {int} and be logged in")
	public void assert_logged_in_and_on_genres_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=1&genres="+id);
	}
	
	@Then("I will be on the Genre page with order {string} and not be logged in")
	public void assert_not_logged_in_and_on_ordered_genres(String order) throws Throwable {
		if(order.equals("default")) {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Genre.html?user=0");
		}else {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Genre.html?user=0&order="+order);
		}
	}
	@Then("I will be on the Genre page with order {string} and be logged in")
	public void assert_logged_in_and_on_ordered_genres(String order) throws Throwable {
		if(order.equals("default")) {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Genre.html?user=1");
		}else {
			assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Genre.html?user=1&order="+order);
		}
	}

		
	
	
	@After("@tagGenre")
	public void quit() {
		driver.quit();
	}

}
