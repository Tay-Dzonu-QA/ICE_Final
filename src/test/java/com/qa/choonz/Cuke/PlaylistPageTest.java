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

public class PlaylistPageTest {
	
	WebDriver driver; 
	
	WebDriverWait wait;
	Actions action;
	
	@Before
	public void init() {
		System.setProperty("webdriver.edge.driver","C:\\Users\\taydz\\Desktop\\Choonz-Starter-master\\src\\test\\resources\\msedgedriver.exe");
	    driver = new EdgeDriver(); 
	    wait = new WebDriverWait(driver,15);
	    action = new Actions(driver);
	}
	@Given("I am logged in and on Playlist {int} page")
	public void logged_in_and_on_playlist_page(int PlID) throws Throwable {
	    driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Playlist.html?user=1&playlists="+PlID);
	}
	

	
	@When("I click to view the Track with id {int} for Playlist {int}")
	public void select_track_from_pl(int TrackID,int PlID) throws Throwable {
		WebElement PlRow = driver.findElement(By.id("PL"+PlID+"PlaylistRow"+TrackID));
		action.moveToElement(PlRow);
	    WebElement TrackLink = driver.findElement(By.id("PL"+PlID+"ViewTrackButton"+TrackID));
	    wait.until(ExpectedConditions.visibilityOf(TrackLink));
	    TrackLink.click();
	}
	@When("I open the Playlist side bar and select Playlist {int}")
	public void select_playlist(int PlID) throws Throwable {
		WebElement openFilter = driver.findElement(By.id("FilterByBTN"));
		wait.until(ExpectedConditions.visibilityOf(openFilter));
		openFilter.click();
	    WebElement SelectLink = driver.findElement(By.id("Select_"+PlID));
	    wait.until(ExpectedConditions.visibilityOf(SelectLink));
	    SelectLink.click();
	}
	@When("I add Track {int} {string} to Playlist {int}")
	public void playlist_add_track(int TrackID,String TrackName,int PlID) {
		WebElement AddTrackBTN = driver.findElement(By.id("AddTrackButton"+PlID));
	    wait.until(ExpectedConditions.visibilityOf(AddTrackBTN));
	    AddTrackBTN.click();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("AddTrackPLModal"))));
		driver.findElement(By.id("TrackAdd")).sendKeys(TrackID+". "+TrackName);
		driver.findElement(By.id("AddTrackPLSubmit")).click();
	}
	@When("I remove Track {int} from Playlist {int}")
	public void playlist_remove_track(int TrackID,int PlID) {
		WebElement PlRow = driver.findElement(By.id("PL"+PlID+"PlaylistRow"+TrackID));
		action.moveToElement(PlRow);
	    WebElement RemoveTrackBTN = driver.findElement(By.id("PL"+PlID+"RemoveTrackButton"+TrackID));
	    wait.until(ExpectedConditions.visibilityOf(RemoveTrackBTN));
	    RemoveTrackBTN.click();
	}
	@When("I edit the Playlist {int} info")
	public void edit_playlist(int PlID) {
		WebElement EditPLBTN = driver.findElement(By.id("EditPlButton"+PlID));
	    wait.until(ExpectedConditions.visibilityOf(EditPLBTN));
	    EditPLBTN.click();
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("EditPLModal"))));
		driver.findElement(By.id("EditPLName")).sendKeys("TEST PL NAME");
		driver.findElement(By.id("EditPLDescription")).sendKeys("TEST PL Description");
		driver.findElement(By.id("EditPLSubmit")).click();
		
	}
	@When("I delete Playlist {int}")
	public void delete_playlist(int PlID) {
		WebElement DeletePLBTN = driver.findElement(By.id("DeletePLButton"+PlID));
	    wait.until(ExpectedConditions.visibilityOf(DeletePLBTN));
	    DeletePLBTN.click();
	}

	
	@Then("I will be on the Track page with track id {int} and be logged in")
	public void assert_logged_in_on_tracks_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=1&tracks="+id);
	}
	
	@Then("I will be on the Playlist page for {int} and be logged in")
	public void assert_logged_in_on_playlist_page(int id) throws Throwable {
		assertThat(driver.getCurrentUrl()).isEqualTo("http://127.0.0.1:5500/src/main/resources/static/html/Track.html?user=0&albums="+id);
	}
	
	@Then("The Track {int} can be found on Playlist {int}")
	public void assert_track_added_to_playlist(int TrackID,int PlaylistID) {
		WebElement PLRow = driver.findElement(By.id("PL"+PlaylistID+"PlaylistRow"+TrackID));
		wait.until(ExpectedConditions.visibilityOf(PLRow));
		action.moveToElement(PLRow);
		WebElement TrackName = driver.findElement(By.id("PL"+PlaylistID+"Trackname"+TrackID));
		wait.until(ExpectedConditions.visibilityOf(TrackName));
		assertThat(TrackName.getText()).isEqualTo("track "+TrackID);
	}
	@Then("The Track {int} can not be found on Playlist {int}")
	public void assert_track_removed_from_playlist(int TrackID,int PlaylistID) {
		WebElement PLempty = driver.findElement(By.id("PLEmpty"+PlaylistID));
		assertThat(PLempty.getText()).isEqualTo("NO TRACKS");
	}
	@Then("Playlist {int} info has been changed")
	public void assert_playlist_info_changed(int PlaylistID) throws Throwable {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Playlist.html?user=1&playlists="+PlaylistID);
		WebElement PLName = driver.findElement(By.id("PLTitile"));
		assertThat(PLName.getText()).isEqualTo("TEST PL NAME");
		WebElement PLDescription = driver.findElement(By.id("PLDescription"));
		assertThat(PLDescription.getText()).isEqualTo("TEST PL Description");
	}
	@Then("Playlist {int} is not found")
	public void assert_playlist_deleted(int PlaylistID) throws Throwable {
		driver.get("http://127.0.0.1:5500/src/main/resources/static/html/Playlist.html?user=1&playlists="+PlaylistID);
		WebElement PLName = driver.findElement(By.id("PLTitile"));
		assertThat(PLName.getText()).isEqualTo("");
		WebElement PLDescription = driver.findElement(By.id("PLDescription"));
		assertThat(PLDescription.getText()).isEqualTo("");
	}

	@After
	public void quit() {
		driver.quit();
	}

}
