package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PlaylistTest {
	
	Playlist testPl;
	final Long id = 1l;
	final String name = "Party";
	final String desc = "Rock";
	final String artwork = "guitar";
	
	@Test
	public void consTests() {
		Playlist newPl = new Playlist();
		
		assertTrue(newPl instanceof Playlist);
		
		Playlist pl = new Playlist(1L,name,desc,artwork,null);
		assertTrue(pl instanceof Playlist);
	}
	
	@Test
	public void getSetIdTest() {
		Playlist newPl = new Playlist();
		
		newPl.setId(1L);
		
		assertEquals(1L,newPl.getId());
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
	public void toStringTests() {		
		Playlist pl = new Playlist(1L,name,desc,artwork,null);
		
		assertNotNull(pl.toString());
		assertEquals("Playlist [id=1, name=Party, description=Rock, artwork=guitar, tracks=null]"
				,pl.toString());
	}
	
	@Test
	public void hashCodeTest() {		
		Playlist pl1 = new Playlist(1L,name,desc,artwork,null);
		Playlist pl2 = new Playlist(1L,name,desc,artwork,null);
		
		assertTrue(pl1.hashCode() == pl2.hashCode());
	}
	
	@Test
	public void equalsTest() {
		Playlist pl = new Playlist(1L,name,desc,artwork,null);
		Track track = new Track();
		
		assertTrue(pl.equals(pl));
		assertFalse(pl.equals(track));
	}

}
