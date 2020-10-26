package com.qa.choonz.persistence.domain;


import static org.assertj.core.api.Assertions.assertThat;

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


		@BeforeEach
		void init() {

			this.testAlbums = new ArrayList<Album>();
			this.testArtist = new Artist(
					this.id, 
					this.name, 
					this.testAlbums);
		}
		

		@Test
		void noArguConsTest() {

			Artist newTestArtist = new Artist();
			assertThat(newTestArtist instanceof Artist);
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
			assertThat(this.testArtist.hashCode() == -1259434520);
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
