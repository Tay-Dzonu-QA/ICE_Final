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

public class AlbumPageTest {
	
	WebDriver driver; 
	
	WebDriverWait wait;
	Actions action;
	
	@Before("@tagAlbum")
	public void init() {
		System.setProperty("webdriver.edge.driver","C:\\Users\\taydz\\Desktop\\Choonz-Starter-master\\src\\test\\resources\\msedgedriver.exe");
	    driver = new EdgeDriver(); 
	    wait = new WebDriverWait(driver,15);
	    action = new Actions(driver);
	}
	@Given("I am logged in and on the Album page")
	public void logged_in_and_on_Album_page() throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=1");
	}
	
	@Given("I am not logged in and on the Album page")
	public void not_logged_in_and_on_Album_page() throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Album.html");

	}
	
	@When("I click on the Album with id {int}")
	public void select_Album(int id) throws Throwable {
		WebElement AlbumRow = driver.findElement(By.id("AlbumRow"+id));
		action.moveToElement(AlbumRow);
	    WebElement AlbumLink = driver.findElement(By.id("ViewAlbumButton"+id));
	    wait.until(ExpectedConditions.visibilityOf(AlbumLink));
	    AlbumLink.click();
	}
	@When("I open the album side bar and select Albums by {int}")
	public void select_by_artist(int artistID) throws Throwable {
		WebElement openFilter = driver.findElement(By.id("FilterByBTN"));
		wait.until(ExpectedConditions.visibilityOf(openFilter));
		openFilter.click();
	    WebElement OrderLink = driver.findElement(By.id("Order_Artist"+artistID));
	    wait.until(ExpectedConditions.visibilityOf(OrderLink));
	    OrderLink.click();
	}
	@When("I open the album side bar and select {int} Albums")
	public void select_by_genre(int genreID) throws Throwable {
		WebElement openFilter = driver.findElement(By.id("FilterByBTN"));
		wait.until(ExpectedConditions.visibilityOf(openFilter));
		openFilter.click();
	    WebElement OrderLink = driver.findElement(By.id("Order_Genre"+genreID));
	    wait.until(ExpectedConditions.visibilityOf(OrderLink));
	    OrderLink.click();
	}
	@Then("I will be on the Album page with album id {int} and not be logged in")
	public void assert_not_logged_in_on_albums_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=0&albums="+id);
	}
	
	@Then("I will be on the Album page with album id {int} and be logged in")
	public void assert_logged_in_on_albums_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=1&albums="+id);
	}
	
	@Then("I will be on the Artists page for {int} and not be logged in")
	public void assert_not_logged_in_on_artists_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=0&artists="+id);
	}
	
	@Then("I will be on the Artists page for {int} and be logged in")
	public void assert_logged_in_on_artists_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=1&artists="+id);
	}
	@Then("I will be on the Genre page for {int} and not be logged in")
	public void assert_not_logged_in_on_genres_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=0&genres="+id);
	}
	
	@Then("I will be on the Genre page for {int} and be logged in")
	public void assert_logged_in_on_genres_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Album.html?user=1&genres="+id);
	}


		
	
	
	@After("@tagAlbum")
	public void quit() {
		driver.quit();
	}

}
