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

public class TrackPageTest {
	
	WebDriver driver; 
	
	WebDriverWait wait;
	Actions action;
	
	@Before("@tagTrack")
	public void init() {
		System.setProperty("webdriver.edge.driver","C:\\Users\\taydz\\Desktop\\Choonz-Starter-master\\src\\test\\resources\\msedgedriver.exe");
	    driver = new EdgeDriver(); 
	    wait = new WebDriverWait(driver,15);
	    action = new Actions(driver);
	}
	@Given("I am logged in and on the Track page")
	public void logged_in_and_on_Track_page() throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=1");
	}
	
	@Given("I am not logged in and on the Track page")
	public void not_logged_in_and_on_Track_page() throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Track.html");

	}
	
	@When("I click on the Track with id {int}")
	public void select_Track(int id) throws Throwable {
		WebElement TrackRow = driver.findElement(By.id("TrackRow"+id));
		action.moveToElement(TrackRow);
	    WebElement TrackLink = driver.findElement(By.id("ViewTrackButton"+id));
	    wait.until(ExpectedConditions.visibilityOf(TrackLink));
	    TrackLink.click();
	}
	@When("I open the track side bar and select Tracks in album {int}")
	public void select_by_album(int albumID) throws Throwable {
		WebElement openFilter = driver.findElement(By.id("FilterByBTN"));
		wait.until(ExpectedConditions.visibilityOf(openFilter));
		openFilter.click();
	    WebElement OrderLink = driver.findElement(By.id("Order_Album"+albumID));
	    wait.until(ExpectedConditions.visibilityOf(OrderLink));
	    OrderLink.click();
	}
	@When("I add Track {int} to Playlist {string}")
	public void add_track_to_playlist(int TrackID,String playlist) {
		WebElement TrackRow = driver.findElement(By.id("TrackRow"+TrackID));
		action.moveToElement(TrackRow);
		WebElement AddToPlBTN = driver.findElement(By.id("addToPlaylistButton"+TrackID));
	    wait.until(ExpectedConditions.visibilityOf(AddToPlBTN));
	    AddToPlBTN.click();
	    
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("AddToPlaylistModal"))));
		driver.findElement(By.id("PlaylistAdd")).sendKeys(playlist);
		driver.findElement(By.id("AddToPlaylistSubmit")).click();
	}
	@Then("I will be on the Track page with track id {int} and not be logged in")
	public void assert_not_logged_in_on_tracks_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=0&tracks="+id);
	}
	
	@Then("I will be on the Track page with track id {int} and be logged in")
	public void assert_logged_in_on_tracks_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=1&tracks="+id);
	}
	
	@Then("I will be on the Albums page for {int} and not be logged in")
	public void assert_not_logged_in_on_artists_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=0&albums="+id);
	}
	
	@Then("I will be on the Albums page for {int} and be logged in")
	public void assert_logged_in_on_artists_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=1&albums="+id);
	}
	
	@Then("The Track {int} can be found on Playlist {int}")
	public void assert_track_added_to_playlist(int TrackID,int PlaylistID) {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Playlist.html?user=1&playlists="+PlaylistID);
		WebElement PLRow = driver.findElement(By.id("PL"+PlaylistID+"PlaylistRow"+TrackID));
		wait.until(ExpectedConditions.visibilityOf(PLRow));
		action.moveToElement(PLRow);
		WebElement TrackName = driver.findElement(By.id("PL"+PlaylistID+"Trackname"+TrackID));
		wait.until(ExpectedConditions.visibilityOf(TrackName));
		assertThat(TrackName.getText()).isEqualTo("track "+TrackID);
	}



		
	
	
	@After("@tagTrack")
	public void quit() {
		driver.quit();
	}

}
