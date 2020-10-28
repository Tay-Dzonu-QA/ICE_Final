package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtistDTOTest {
	

	ArtistDTO testArtistDTO;
	final Long id = 1L;
	final String name = "Fleetwood Mac";
	List<AlbumDTO> albums;
	
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	

	@BeforeEach
	void init() {
		this.albums = new ArrayList<AlbumDTO>();
		this.testArtistDTO = new ArtistDTO(
				this.id, 
				this.name);
		
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
	public void noArguConstructorTest() {
		ArtistDTO newArtist = new ArtistDTO();
		
		assertThat(newArtist instanceof ArtistDTO);
	}
	
	@Test
	public void twoArguConstructorTest() {
			ArtistDTO newArtDTO = new ArtistDTO(
					this.id, 
					this.name);	
			
			assertThat(newArtDTO instanceof ArtistDTO);
		
	}
		
	@Test
	public void AllArguConstructorTest() {
		ArtistDTO newArtDTO = new ArtistDTO(
				this.id, 
				this.name,
				this.albums);
		
		assertThat(newArtDTO instanceof ArtistDTO);
	}
	

	@Test
	void GetIdTest() {
		
		assertThat(this.testArtistDTO.getId() == this.id);
	}
	
	@Test
	void SetIdTest() {

		Long newId = 2l;
		this.testArtistDTO.setId(newId);
		
		assertThat(this.testArtistDTO.getId() == newId);
	}
	
	@Test
	void GetNameTest() {
		assertThat(this.testArtistDTO.getName().equals(this.name));
	}
	
	@Test
	void SetNameTest() {

		String newName = "Astral Weeks";
	this.testArtistDTO.setName(newName);
	
	assertThat(this.testArtistDTO.getName().equals(newName));
	}

	@Test
	void SetAlbumTest() {

		List<AlbumDTO> newTracks = new ArrayList<AlbumDTO>();
		this.testArtistDTO.setAlbums(newTracks);
		
		assertThat(this.testArtistDTO.getAlbums() == newTracks);
	}
	
		@Test
	void testEquals() {
			ArtistDTO emptyAlbum = new ArtistDTO();
			ArtistDTO fullAlbum = new ArtistDTO(
					this.id,
					this.name,
					this.albums);
			
			assertThat(!this.testArtistDTO.equals(emptyAlbum));
			assertThat(this.testArtistDTO.equals(fullAlbum));
	}
	
	
		@Test
		void HashCodeTest() {
		
			ArtistDTO a1 = new ArtistDTO(id,name,albums);
			ArtistDTO a2 = new ArtistDTO(id,name,albums);
			
			assertTrue(a1.hashCode() == a2.hashCode());
		}
	
	@AfterEach
	void teardown() {
		this.testArtistDTO = null;
	}
}
