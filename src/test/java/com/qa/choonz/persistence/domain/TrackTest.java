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

public class TrackTest {
	
	Track testTrack;
	Track testTrack2;
	final Long id = 1l;
	final String name = "Brown Sugar";
	final String album = "Sticky Fingers";
	final int duration = 200;
	final String lyrics = "Gold Coast slave ship bound for cotton fields";
	List<Playlist> playlists;
	
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	
	@BeforeEach
	void init() {
		this.playlists = new ArrayList<Playlist>();
		this.testTrack = new Track(
				this.id, 
				this.name, 
				null, 
				this.playlists,
				this.duration,
				this.lyrics);
		
		this.testTrack2 = new Track(
				this.id, 
				this.name, 
				null, 
				this.playlists,
				this.duration,
				this.lyrics);
		
		// Start of console test format
		sBuilder.setLength(0);
		sBuilder
		.append("\tTest ").append(activeTest).append("\n")
		.append(div);
		
		System.out.println(sBuilder.toString());
		activeTest++;
		// End of console test format
	}
	
	
	@Test
	public void consTests() {
		Track newTrack = new Track();		
		assertTrue(newTrack instanceof Track);
		
		Album album = new Album();
		
		Track track = new Track(id,name,album,playlists,229,lyrics);
		
		assertTrue(track instanceof Track);
	}
	
	@Test
	void noArguConsTest() {

		Track newTrack = new Track();			
		assertThat(newTrack instanceof Track);
	}
	
	@Test
	void oneArguConsTest() {

		Track newGenre = new Track(
				this.name);
		
		assertThat(newGenre instanceof Track);
	}		
	
	@Test
	void AllArguConsTest() {

		Track newTrack = new Track(
				this.id, 
				this.name, 
				null, 
				null,
				this.duration, 
				this.lyrics);
		
		assertThat(newTrack instanceof Track);
	}
	
	@Test
    public void equalsWithNull() {
        assertFalse(testTrack.equals(null));
    }
	
	@Test
    public void equalsWithDifferentObject() {
        assertFalse(testTrack.equals(new Object()));
    }
	
	@Test
	public void checkEquality() {
	     assertTrue(testTrack.equals(testTrack));
	}
	
	@Test
    public void checkEqualityBetweenDifferentObjects() {
        assertTrue(testTrack.equals(testTrack2));
    }
	
	 @Test
	    public void otherIdDifferent() {
	        testTrack2.setId(2L);
	        assertFalse(testTrack.equals(testTrack2));
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
	
//	@Test
//	public void getSetPlaylistTest() {
//		Track newTrack = new Track();
//		Playlist pl = new Playlist();
//		
//		newTrack.setPlaylist(pl);		
//		assertEquals(pl,newTrack.getPlaylist());
//	}
	
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
		
		Track track1 = new Track(id,name,null,null,229,lyrics);
		Track track2 = new Track(id,name,null,null,229,lyrics);
		
		assertTrue(track1.hashCode() == track2.hashCode());
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void equalsTest() {
		Album al = new Album();
		
		Track track = new Track(id,name,null,null,229,lyrics);		
		assertTrue(track.equals(track));
		assertFalse(track.equals(al));
	}
	
	@AfterEach
	void teardown() {
		this.testTrack = null;
	}

}
