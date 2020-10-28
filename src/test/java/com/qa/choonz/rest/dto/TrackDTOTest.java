package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackDTOTest {
	

	TrackDTO testTrackDTO;
	final Long id = 1L;
	private Integer duration;
	private String lyrics;
	final String name = "Man in the Mirror";
	
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	

	@BeforeEach
	void init() {
		this.testTrackDTO = new TrackDTO(
				this.id, 
				this.name,
				this.duration,
				this.lyrics);
		
		// Start of console test format
				sBuilder.setLength(0);
				sBuilder
				.append("\tTest ").append(activeTest).append("\n")
				.append(div);
				// Append info about vending machine here
				
				System.out.println(sBuilder.toString());
				activeTest++;
				// End of console test format
	}
	
	@Test
	public void noArguConstructorTest() {
		TrackDTO newTrack = new TrackDTO();
		
		assertThat(newTrack instanceof TrackDTO);
	}
	
	@Test
	public void twoArguConstructorTest() {
			TrackDTO newTrackDTO = new TrackDTO(
					this.id, 
					this.name);	
			
			assertThat(newTrackDTO instanceof TrackDTO);		
	}
		
	@Test
	public void AllArguConstructorTest() {
		TrackDTO newPlaylistDTO = new TrackDTO( 
				this.id,
				this.name,
				this.duration,
				this.lyrics);
		
		assertThat(newPlaylistDTO instanceof TrackDTO);
	}
	
	

	@Test
	void GetIdTest() {
		
		assertThat(this.testTrackDTO.getId() == this.id);
	}
	
	@Test
	void SetIdTest() {

		Long newId = 2l;
		this.testTrackDTO.setId(newId);
		
		assertThat(this.testTrackDTO.getId() == newId);
	}
	
	@Test
	void GetNameTest() {
		assertThat(this.testTrackDTO.getName().equals(this.name));
	}
	
	@Test
	void SetNameTest() {

		String newName = "Astral Weeks";
	this.testTrackDTO.setName(newName);
	
	assertThat(this.testTrackDTO.getName().equals(newName));
	}
	
	@Test
	public void getSetDurationTest() {
		TrackDTO newTrack = new TrackDTO();
		
		newTrack.setDuration(229);		
		assertEquals(229,newTrack.getDuration());
	}
	
	@Test
	public void getSetLyricsTest() {
		TrackDTO newTrack = new TrackDTO();
		
		newTrack.setLyrics(lyrics);
		
		assertEquals(lyrics,newTrack.getLyrics());
	}

	
		@Test
	void testEquals() {
			TrackDTO emptyPl = new TrackDTO();
			TrackDTO fullPl = new TrackDTO(
					this.id,
					this.name,
					this.duration,
					this.lyrics);
			
			assertThat(!this.testTrackDTO.equals(emptyPl));
			assertThat(this.testTrackDTO.equals(fullPl));
	}
	
	
		@Test
		void HashCodeTest() {
		
			TrackDTO a1 = new TrackDTO(id,name,duration,lyrics);
			TrackDTO a2 = new TrackDTO(id,name,duration,lyrics);
			
			assertTrue(a1.hashCode() == a2.hashCode());
		}
	
	@AfterEach
	void teardown() {
		this.testTrackDTO = null;
	}


}
