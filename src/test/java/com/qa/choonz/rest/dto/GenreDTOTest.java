package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenreDTOTest {
	

	GenreDTO testGenreDTO;
	final Long id = 1L;
	private String description;
	final String name = "90s";
	List<AlbumDTO> albums;
	
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	

	@BeforeEach
	void init() {
		this.albums = new ArrayList<AlbumDTO>();
		this.testGenreDTO = new GenreDTO(
				this.id, 
				this.name);
		
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
	public void noArguConstructorTest() {
		GenreDTO newGenre = new GenreDTO();
		
		assertThat(newGenre instanceof GenreDTO);
	}
	
	@Test
	public void twoArguConstructorTest() {
			GenreDTO newArtDTO = new GenreDTO(
					this.id, 
					this.name);	
			
			assertThat(newArtDTO instanceof GenreDTO);		
	}
		
	@Test
	public void AllArguConstructorTest() {
		GenreDTO newGenreDTO = new GenreDTO(
				this.id, 
				this.name,
				this.description,
				this.albums);
		
		assertThat(newGenreDTO instanceof GenreDTO);
	}
	

	@Test
	void GetIdTest() {
		
		assertThat(this.testGenreDTO.getId() == this.id);
	}
	
	@Test
	void SetIdTest() {

		Long newId = 2l;
		this.testGenreDTO.setId(newId);
		
		assertThat(this.testGenreDTO.getId() == newId);
	}
	
	@Test
	void GetNameTest() {
		assertThat(this.testGenreDTO.getName().equals(this.name));
	}
	
	@Test
	void SetNameTest() {

		String newName = "Astral Weeks";
	this.testGenreDTO.setName(newName);
	
	assertThat(this.testGenreDTO.getName().equals(newName));
	}
	
	@Test
	void SetDescrtiptionTest() {
		String newDesc = "Funky";
		this.testGenreDTO.setDescription(newDesc);
		assertThat(this.testGenreDTO.getDescription().equals(newDesc));
	}

	@Test
	void SetAlbumTest() {

		List<AlbumDTO> newTracks = new ArrayList<AlbumDTO>();
		this.testGenreDTO.setAlbums(newTracks);
		
		assertThat(this.testGenreDTO.getAlbums() == newTracks);
	}
	
		@Test
	void testEquals() {
			GenreDTO emptyAlbum = new GenreDTO();
			GenreDTO fullAlbum = new GenreDTO(
					this.id,
					this.name,
					this.description,
					this.albums);
			
			assertThat(!this.testGenreDTO.equals(emptyAlbum));
			assertThat(this.testGenreDTO.equals(fullAlbum));
	}
	
	
		@Test
		void HashCodeTest() {
		
			GenreDTO a1 = new GenreDTO(id,name,description,albums);
			GenreDTO a2 = new GenreDTO(id,name,description,albums);
			
			assertTrue(a1.hashCode() == a2.hashCode());
		}
	
	@AfterEach
	void teardown() {
		this.testGenreDTO = null;
	}

}
