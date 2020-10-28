package com.qa.choonz.persistence.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtistTest {
	

		Artist testArtist;
		final Long id = 1l;
		final String name = "Fleetwood Mac";
		List<Album> testAlbums;
		
		private static int activeTest = 1;
		private static StringBuilder sBuilder = new StringBuilder();
		private static String div = "=============================================\n";


		@BeforeEach
		void init() {

			this.testAlbums = new ArrayList<Album>();
			this.testArtist = new Artist(
					this.id, 
					this.name, 
					this.testAlbums);
			
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
		void noArguConsTest() {

			Artist newTestArtist = new Artist();
			assertThat(newTestArtist instanceof Artist);
		}
		
		@Test
		void oneArguConsTest() {

			Artist newArt = new Artist(
					this.name);
			
			assertThat(newArt instanceof Artist);
		}
		
		@Test
		void twoArguConsTest() {

			Artist newArt = new Artist(
					this.id, 
					this.name);
			
			assertThat(newArt instanceof Artist);
		}
		
		@Test
		void threeArguConsTest() {

			Long newId = this.id + 1;
			String newName = "The Rolling Stones";
			List<Album> newTestAlbums = new ArrayList<Album>();
			Artist newArt = new Artist( 
					newId, 
					newName, 
					newTestAlbums);
			
			assertThat(newArt instanceof Artist);
		}
		
		
		@Test
		void AllArguConsTest() {

			Long newId = this.id + 1;
			String newName = "The Rolling Stones";
			List<Album> newTestAlbums = new ArrayList<Album>();
			Artist newTestArtist = new Artist(
					newId, 
					newName,
					newTestAlbums);	
			assertThat(newTestArtist instanceof Artist);
		}
		
		@Test
	    public void equalsWithNull() {
	        assertFalse(testArtist.equals(null));
	    }
		
		@Test
	    public void equalsWithDifferentObject() {
	        assertFalse(testArtist.equals(new Object()));
	    }
		
		@Test
		public void checkEquality() {
		     assertTrue(testArtist.equals(testArtist));
		    }
		
		
		@Test
		void GetIdTest() {
			assertThat(this.testArtist.getId() == this.id);
		}
		
		@Test
		void SetIdTest() {

			Long newId = 2l;
			this.testArtist.setId(newId);
			assertThat(this.testArtist.getId() == newId);
		}
		
		@Test
		void GetNameTest() {
			assertThat(this.testArtist.getName() == this.name);
		}
		
		@Test
		void SetNameTest() {
			String newName = "Surfaces";
			this.testArtist.setName(newName);
			
			assertThat(this.testArtist.getName().equals(newName));
		}
		
		@Test
		void GetAlbumsTest() {
			assertThat(this.testArtist.getAlbums() == this.testAlbums);
		}
		
		@Test
		void SetAlbumsTest() {

			Album newAlbum = new Album();
			List<Album> newAlbums = new ArrayList<Album>();
			newAlbums.add(newAlbum);
			this.testArtist.setAlbums(newAlbums);
			assertThat(this.testArtist.getAlbums() == newAlbums);
		}
		
		
		@Test
		void EqualsTest() {
			Artist emptyArtist = new Artist();
			Artist fullArtist = new Artist(
					this.id, 
					this.name, 
					this.testAlbums);
			
			assertThat(!this.testArtist.equals(emptyArtist));
			assertThat(this.testArtist.equals(fullArtist));
		}
		
		@Test
		void HashCodeTest() {
			Artist a1 = new Artist(id,name,testAlbums);
			Artist a2 = new Artist(id,name,testAlbums);
			
			assertTrue(a1.hashCode() == a2.hashCode());
		}
		
		@Test
		void ToStringTest() {
			assertThat(this.testArtist.toString()
					.equals("Artist [id=1, name=Fleetwood Mac, albums=[Album "
							+ "[id=0, name=null, tracks=null, artist=null, "
							+ "genre=null, cover=null]]]"));
		}
		

		@AfterEach
		void teardown() {
			this.testArtist = null;
		}

}
