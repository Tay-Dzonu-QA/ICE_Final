package com.qa.choonz.persistence.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlbumTest {
	

		Album testAlbum;
		final Long id = 1l;
		final String name = "Rumours";
		final String cover = "no";
		List<Track> tracks;

		private static int activeTest = 1;
		private static StringBuilder sBuilder = new StringBuilder();
		private static String div = "=============================================\n";

		@BeforeEach
		void init() {
			this.tracks = new ArrayList<Track>();
			this.testAlbum = new Album(
					this.id, 
					this.name, 
					this.tracks, 
					null, 
					null, 
					this.cover);
			
					
			sBuilder.setLength(0);
			sBuilder
			.append("\tTest ").append(activeTest).append("\n")
			.append(div);
			// Append info about vending machine here
			
			System.out.println(sBuilder.toString());
			activeTest++;
		}
		

		@Test
		void NoArguConsTest() {

			Album newAlbum = new Album();
			assertThat(newAlbum instanceof Album);
		}
		
		@Test
		void OneArguConsTest() {
		
		Album newAl = new Album(
		this.name);		
		assertThat(newAl instanceof Album);
		}
		
		@Test
		void AllArguConsTest() {

			Album newAl = new Album(
					this.id,
					this.name,
					this.tracks,
					null,
					null,
					this.cover);
			assertThat(newAl instanceof Album);
		}
		
		@Test
	    public void equalsWithNull() {
	        assertFalse(testAlbum.equals(null));
	    }
		
		@Test
	    public void equalsWithDifferentObject() {
	        assertFalse(testAlbum.equals(new Object()));
	    }
		
		@Test
		public void checkEquality() {
		     assertTrue(testAlbum.equals(testAlbum));
		    }
		  
		
		@Test
		void GetIdTest() {
			assertThat(this.testAlbum.getId() == this.id);
		}
		
		@Test
		void SetIdTest() {

			Long newId = 2l;
			this.testAlbum.setId(newId);		
			assertThat(this.testAlbum.getId() == newId);
		}
		
		@Test
		void GetNameTest() {
			assertThat(this.testAlbum.getName().equals(this.name));
		}
		
		@Test
		void SetNameTest() {

			String newName = "Astral Weeks";
			this.testAlbum.setName(newName);
			assertThat(this.testAlbum.getName().equals(newName));
		}
		
		@Test
		void GetTracksTest() {
			assertThat(this.testAlbum.getTracks() == this.tracks);
		}
		
		@Test
		void SetTracksTest() {
		
			List<Track> newTracks = new ArrayList<Track>();
			this.testAlbum.setTracks(newTracks);
			assertThat(this.testAlbum.getTracks() == newTracks);
		}
		
		@Test
		void GetArtistTest() {
			assertThat(this.testAlbum.getArtist() == null);
		}
		
		@Test
		void SetArtistTest() {
			
			this.testAlbum.setArtist(null);		
			assertThat(this.testAlbum.getArtist() == null);
		}
		
		@Test
		void GetCoverTest() {
			assertThat(this.testAlbum.getCover() == this.cover);
		}
		
		@Test
		void SetCoverTest() {
			
			String newCover = "Billy Eilish";
			this.testAlbum.setCover(newCover);
			
			assertThat(this.testAlbum.getCover().equals(newCover));
		}
		
		@Test
		void GetGenreTest() {
			assertThat(this.testAlbum.getGenre() == null);
		}
		
		@Test
		void SetGenreTest() {
			
			this.testAlbum.setGenre(null);	
			assertThat(this.testAlbum.getGenre() == null);
		}
		
		@Test
		void EqualsTest() {
			
			Album emptyAlbum = new Album();
			Album fullAlbum = new Album(
					this.id,
					this.name,
					this.tracks,
					null,
					null,
					this.cover);
			
			
			assertThat(!this.testAlbum.equals(emptyAlbum));
			assertThat(this.testAlbum.equals(fullAlbum));
		}
		
		@Test
		void ToStringTest() {
			
			assertThat(this.testAlbum.toString()
					.equals("Album [id=1, name=Rumours, "
							+ "tracks=[], artist=null, genre=null, "
							+ "cover=no]"));
		}
		
		@Test
		void HashCodeTest() {
		
			Album a1 = new Album(id,name,tracks,null,null,cover);
			Album a2 = new Album(id,name,tracks,null,null,cover);
			
			assertTrue(a1.hashCode() == a2.hashCode());
		}
		
		@AfterEach
		void teardown() {
			this.testAlbum = null;
		}

}
