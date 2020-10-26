package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TrackTest {
	
	Track testTrack;
	final Long id = 1l;
	final String name = "Brown Sugar";
	final String lyrics = "Gold Coast slave ship bound for cotton fields";
	
	
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
		Album album = new Album();
		
		newTrack.setAlbum(album);		
		assertEquals(album,newTrack.getAlbum());
	}
	
	@Test
	public void getSetPlaylistTest() {
		Track newTrack = new Track();
		Playlist playlist = new Playlist();
		
		newTrack.setPlaylist(playlist);		
		assertEquals(playlist,newTrack.getPlaylist());
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
	public void toStringTests() {		
		Track track = new Track(id,name,null,null,229,lyrics);
		
		assertNotNull(track.toString());
		assertEquals("Track [id=1, name=Brown Sugar, album=null, playlist=null, duration=229, lyrics=Gold Coast slave ship bound for cotton fields]"
				,track.toString());
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
		Album album = new Album();
		Playlist pl = new Playlist();
		
		Track track = new Track(id,name,album,pl,229,lyrics);		
		assertTrue(track.equals(track));
		assertFalse(track.equals(album));
	}

}
