package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;

public class AlbumDTOTest {
	
	
	AlbumDTO testAlbumDTO;
	final long id = 1L;
	final String name = "Rumours";
	final String cover = "no";
	final String genre = "rock";
	final String artist = "Pink Floyd";
	List<TrackDTO> tracks;
	
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	

	@BeforeEach
	void init() {
		this.tracks = new ArrayList<TrackDTO>();
		this.testAlbumDTO = new AlbumDTO(
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
		AlbumDTO newAlbum = new AlbumDTO();
		
		assertThat(newAlbum instanceof AlbumDTO);
	}
	
	@Test
	public void twoArguConstructorTest() {
		AlbumDTO newAlDTO = new AlbumDTO(
				this.id, 
				this.name);		
				assertThat(newAlDTO instanceof AlbumDTO);
	}
	
	@Test
	public void allArguConstructorTest() {
		this.tracks = new ArrayList<TrackDTO>();
		AlbumDTO newAlbumDTO = new AlbumDTO(
				this.id,
				this.name,
				this.tracks,
				null,
				null,
				this.cover);
		
		assertThat(newAlbumDTO instanceof AlbumDTO);
	}
	
	@Test
	void GetIdTest() {
		assertThat(this.testAlbumDTO.getId() == this.id);
	}
	
	@Test
	void SetIdTest() {

		Long newId = 2l;
		this.testAlbumDTO.setId(newId);		
		assertThat(this.testAlbumDTO.getId() == newId);
	}
	
	@Test
	void GetNameTest() {
		assertThat(this.testAlbumDTO.getName().equals(this.name));
	}
	
	@Test
	void SetNameTest() {

		String newName = "Astral Weeks";
	this.testAlbumDTO.setName(newName);
	assertThat(this.testAlbumDTO.getName().equals(newName));
	}

	@Test
	void SetTracksTest() {

		List<TrackDTO> newTracks = new ArrayList<TrackDTO>();
		this.testAlbumDTO.setTracks(newTracks);
		assertThat(this.testAlbumDTO.getTracks() == newTracks);
	}
	
	@Test
	void GetCoverTest() {
		assertThat(this.testAlbumDTO.getCover() == this.cover);
	}
	
	@Test
	void SetCoverTest() {
		
		String newCover = "Billy Eilish";
		this.testAlbumDTO.setCover(newCover);
		
		assertThat(this.testAlbumDTO.getCover().equals(newCover));
	}
	@Test
	void getSetGenre() {
		Genre newGenre = new Genre();
		this.testAlbumDTO.setGenre(newGenre);
		assertEquals(newGenre,this.testAlbumDTO.getGenre());
	}
	
	@Test
	void getSetArtist() {
		Artist newArtist = new Artist();
		this.testAlbumDTO.setArtist(newArtist);
		assertEquals(newArtist,this.testAlbumDTO.getArtist());
	}
	
		@Test
	void testEquals() {
			AlbumDTO emptyAlbum = new AlbumDTO();
			AlbumDTO fullAlbum = new AlbumDTO(
					this.id,
					this.name,
					this.tracks,
					null,
					null,
					this.cover);
			
			assertThat(!this.testAlbumDTO.equals(emptyAlbum));
			assertThat(this.testAlbumDTO.equals(fullAlbum));
	}
	
	
		@Test
		void HashCodeTest() {
		
			AlbumDTO a1 = new AlbumDTO(id,name,tracks,null,null,cover);
			AlbumDTO a2 = new AlbumDTO(id,name,tracks,null,null,cover);
			
			assertTrue(a1.hashCode() == a2.hashCode());
		}
	
	@AfterEach
	void teardown() {
		this.testAlbumDTO = null;
	}

}