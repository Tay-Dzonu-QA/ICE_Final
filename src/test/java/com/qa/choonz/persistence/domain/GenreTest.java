package com.qa.choonz.persistence.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenreTest {
	

		Genre testGenre;
		final Long id = 1l;
		final String name = "Tunes";
		final String description = "Heavy";
		List<Album> albums;
		
		private static int activeTest = 1;
		private static StringBuilder sBuilder = new StringBuilder();
		private static String div = "=============================================\n";
		
		@BeforeEach
		void init() {
			this.albums = new ArrayList<Album>();
			this.testGenre = new Genre(
					this.id, 
					this.name, 
					this.description, 
					this.albums);
			
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

			Genre newGenre = new Genre();			
			assertThat(newGenre instanceof Genre);
		}
		
		@Test
		void AllArguConsTest() {

			Genre newGenre = new Genre(
					this.id, 
					this.name, 
					this.description, 
					this.albums);
			
			assertThat(newGenre instanceof Genre);
		}
		
		@Test
		void GetIdTest() {
			assertThat(this.testGenre.getId() == this.id);
		}
		
		@Test
		void SetIdTest() {
			Long newId = 2l;
			this.testGenre.setId(newId);		
			assertThat(this.testGenre.getId() == newId);
		}
		
		@Test
		void GetNameTest() {
			assertThat(this.testGenre.getName().equals(this.name));
		}
		
		@Test
		void SetNameTest() {
			String newName = "Funk";
			this.testGenre.setName(newName);
			assertThat(this.testGenre.getName().equals(newName));
		}
		
		@Test
		void GetDescriptionTest() {
			assertThat(this.testGenre.getDescription().equals(this.description));
		}
		
		@Test
		void SetDescrtiptionTest() {
			String newDesc = "Funky";
			this.testGenre.setDescription(newDesc);
			assertThat(this.testGenre.getDescription().equals(newDesc));
		}
		
		
		@Test
		void GetAlbumsTest() {
			assertThat(this.testGenre.getAlbums() == this.albums);
		}
		
		@Test
		void SetAlbumsTest() {
			Album newAlbum = new Album();
			List<Album> newAlbums = new ArrayList<Album>();
			newAlbums.add(newAlbum);
			this.testGenre.setAlbums(newAlbums);
			assertThat(this.testGenre.getAlbums() == newAlbums);
		}
		
		
		@Test
		void HashcodeTest() {
			Genre pl1 = new Genre(id,name,description,null);
			Genre pl2 = new Genre(id,name,description,null);
			
			assertTrue(pl1.hashCode() == pl2.hashCode());
		}
		
		@Test
		void EqualsTest() {
			Genre emptyGenre = new Genre();
			Genre fullGenre = new Genre(
					this.id,
					this.name,
					this.description,
					this.albums);
			
			
			assertThat(!this.testGenre.equals(emptyGenre));
			assertThat(this.testGenre.equals(fullGenre));
			
			
		}
	
		@AfterEach
		void teardown() {
			this.testGenre = null;
		}
}
