package com.qa.choonz.persistence.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaylistTest {
	
	Playlist testPl;
	Playlist testPl2;
	final Long id = 1l;
	final String name = "Party";
	final String desc = "Rock";
	final String artwork = "Guitar";
	final String user = "Johnny";
	List<Track> tracks;
	
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	
	
	@BeforeEach
	void init() {
		this.tracks = new ArrayList<Track>();
		this.testPl = new Playlist(
				this.id, 
				this.name, 
				this.desc, 
				this.artwork,
				this.tracks,
				null);
		
		this.testPl2 = new Playlist(
				this.id, 
				this.name, 
				this.desc, 
				this.artwork,
				this.tracks,
				null);
		
		// Start of console test format
		sBuilder.setLength(0);
		sBuilder
		.append("\tTest ").append(activeTest).append("\n")
		.append(div);
		
		System.out.println(sBuilder.toString());
		activeTest++;
		// End of console test format
		
	}
	
	void noArguConsTest() {

		Playlist newTestPlaylist = new Playlist();
		assertThat(newTestPlaylist instanceof Playlist);
	}
	
	@Test
	void oneArguConsTest() {

		Playlist newPl = new Playlist(
				this.name);
		
		assertThat(newPl instanceof Playlist);
	}
	
	@Test
	void threeArguConsTest() {

		Playlist newPl = new Playlist( 
				this.name, 
				this.desc, 
				this.artwork);
		
		assertThat(newPl instanceof Playlist);
	}
	
	@Test
	void fourArguConsTest() {

		Playlist newPl = new Playlist(
				this.name, 
				this.desc, 
				this.artwork, 
				null);
		
		assertThat(newPl instanceof Playlist);
	}
	
	@Test
	void AllArguConsTest() {

		Playlist newPl = new Playlist(
				this.id, 
				this.name, 
				this.desc, 
				this.artwork,
				null,
				null);
		
		assertThat(newPl instanceof Playlist);
	}	
	
	@Test
	public void consTests() {
		Playlist newPl = new Playlist();
		
		assertTrue(newPl instanceof Playlist);		
		Playlist pl = new Playlist(id,name,desc,artwork,null,null);
		assertTrue(pl instanceof Playlist);
	}
	
	@Test
    public void equalsWithNull() {
        assertFalse(testPl.equals(null));
    }
	
	@Test
    public void equalsWithDifferentObject() {
        assertFalse(testPl.equals(new Object()));
    }
	
	@Test
	public void checkEquality() {
	     assertTrue(testPl.equals(testPl));
	 }
	
	 @Test
	    public void otherIdDifferent() {
	        testPl2.setId(2L);
	        assertFalse(testPl.equals(testPl2));
	    }
	
	@Test
	public void getSetIdTest() {
		Playlist newPl = new Playlist();
		
		newPl.setId(id);
		
		assertEquals(id,newPl.getId());
	}
	
	@Test
	public void getSetNameTest() {
		Playlist newPl = new Playlist();
		newPl.setName(name);
		
		assertEquals(name,newPl.getName());
	}
	
	@Test
	public void getSetDescriptionTest() {
		Playlist newPl = new Playlist();
		newPl.setDescription(desc);
		
		assertEquals(desc,newPl.getDescription());
	}
	
	@Test
	public void getSetArtworkTest() {
		Playlist newPl = new Playlist();
		newPl.setArtwork(artwork);
		
		assertEquals(artwork,newPl.getArtwork());
	}
	
	@Test
	public void getSetTracksTest() {
		Track track = new Track();
		List<Track> tracks = new ArrayList<>();
		tracks.add(track);
		
		Playlist emptyPlaylist = new Playlist();
		emptyPlaylist.setTracks(tracks);
		
		assertEquals(tracks,emptyPlaylist.getTracks());
	}
	
	
	@Test
	public void hashCodeTest() {		
		Playlist pl1 = new Playlist(id,name,desc,artwork,null,null);
		Playlist pl2 = new Playlist(id,name,desc,artwork,null,null);
		
		assertTrue(pl1.hashCode() == pl2.hashCode());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void equalsTest() {
		Playlist pl = new Playlist(id,name,desc,artwork,null,null);
		Track track = new Track();
		
		assertTrue(pl.equals(pl));
		assertFalse(pl.equals(track));
	}
	
	@AfterEach
	void teardown() {
		this.testPl = null;
	}

}
