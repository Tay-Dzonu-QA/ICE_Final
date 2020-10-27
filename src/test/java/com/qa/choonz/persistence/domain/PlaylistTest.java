package com.qa.choonz.persistence.domain;

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
	final Long id = 1l;
	final String name = "Party";
	final String desc = "Rock";
	final String artwork = "Guitar";
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
				this.tracks);
		
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
	public void consTests() {
		Playlist newPl = new Playlist();
		
		assertTrue(newPl instanceof Playlist);		
		Playlist pl = new Playlist(id,name,desc,artwork,null);
		assertTrue(pl instanceof Playlist);
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
		Playlist pl1 = new Playlist(id,name,desc,artwork,null);
		Playlist pl2 = new Playlist(id,name,desc,artwork,null);
		
		assertTrue(pl1.hashCode() == pl2.hashCode());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void equalsTest() {
		Playlist pl = new Playlist(id,name,desc,artwork,null);
		Track track = new Track();
		
		assertTrue(pl.equals(pl));
		assertFalse(pl.equals(track));
	}
	
	@AfterEach
	void teardown() {
		this.testPl = null;
	}

}
