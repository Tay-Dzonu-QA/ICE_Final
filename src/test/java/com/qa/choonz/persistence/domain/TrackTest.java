package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackTest {
	
	Track testTrack;
	final Long id = 1l;
	final String name = "Brown Sugar";
	final String album = "Sticky Fingers";
	final String playlist = "Rock";
	final int duration = 200;
	final String lyrics = "Gold Coast slave ship bound for cotton fields";
	List<Track> tracks;
	
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	
	@BeforeEach
	void init() {
		this.tracks = new ArrayList<Track>();
		this.testTrack = new Track(
				this.id, 
				this.name, 
				null, 
				null,
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
	public void consTests() {
		Track newTrack = new Track();		
		assertTrue(newTrack instanceof Track);
		
		Album album = new Album();
		Playlist playlist = new Playlist();
		
		Track track = new Track(id,name,album,playlist,229,lyrics);
		
		assertTrue(track instanceof Track);
	}
	
	@Test
	public void getSetIdTest() {
		Track newTrack = new Track();
		
		newTrack.setId(id);
		
		assertEquals(id,newTrack.getId());
	}
	
	@Test
	public void getSetNameTest() {
		Track newTrack = new Track();
		newTrack.setName(name);
		
		assertEquals(name,newTrack.getName());
	}
	
	@Test
	public void getSetAlbumTest() {
		Track newTrack = new Track();
		Album al = new Album();
		
		newTrack.setAlbum(al);		
		assertEquals(al,newTrack.getAlbum());
	}
	
	@Test
	public void getSetPlaylistTest() {
		Track newTrack = new Track();
		Playlist pl = new Playlist();
		
		newTrack.setPlaylist(pl);		
		assertEquals(pl,newTrack.getPlaylist());
	}
	
	@Test
	public void getSetDurationTest() {
		Track newTrack = new Track();
		
		newTrack.setDuration(229);		
		assertEquals(229,newTrack.getDuration());
	}
	
	@Test
	public void getSetLyricsTest() {
		Track newTrack = new Track();
		
		newTrack.setLyrics(lyrics);
		
		assertEquals(lyrics,newTrack.getLyrics());
	}

	
	@Test
	public void hashCodeTest() {
		Album album = new Album();
		Playlist pl = new Playlist();
		
		Track track1 = new Track(id,name,album,pl,229,lyrics);
		Track track2 = new Track(id,name,album,pl,229,lyrics);
		
		assertTrue(track1.hashCode() == track2.hashCode());
	}
	
	@Test
	public void equalsTest() {
		Album al = new Album();
		Playlist pl = new Playlist();
		
		Track track = new Track(id,name,al,pl,229,lyrics);		
		assertTrue(track.equals(track));
		assertFalse(track.equals(al));
	}
	
	@AfterEach
	void teardown() {
		this.testTrack = null;
	}

}
