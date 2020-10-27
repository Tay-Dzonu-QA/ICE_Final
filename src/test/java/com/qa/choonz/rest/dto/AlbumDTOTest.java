package com.qa.choonz.rest.dto;

import org.junit.jupiter.api.Test;

//import static org.assertj.core.api.Assertions.assertThat;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.qa.choonz.persistence.domain.Album;
//import com.qa.choonz.persistence.domain.Track;
//
//import com.qa.choonz.rest.dto.AlbumDTO;
//
//import nl.jqno.equalsverifier.EqualsVerifier;

public class AlbumDTOTest {
	
//	@Test 
//	public void testEquals() { 
//		EqualsVerifier.simple().forClass(AlbumDTO.class).verify(); 
//	}
	
//	AlbumDTO testAlbumDTO;
//	final Long id = 1L;
//	final String name = "Rumours";
//	final String cover = "no";
//	List<Tracks> tracks;
//
//	@BeforeEach
//	void init() {
//		this.tracks = new ArrayList<Track>();
//		this.testAlbumDTO = new AlbumDTO(
//				this.id, 
//				this.name, 
//				this.tracks, 
//				null, 
//				null, 
//				this.cover);
//	}
//	
//	@Test
//	public void noArguConstructorTest() {
//		AlbumDTO newAlbum = new AlbumDTO();
//		
//		assertThat(newAlbum instanceof AlbumDTO);
//	}
//	
//	@Test
//	public void twoArguConstructorTest() {
//		AlbumDTO newAlDTO = new AlbumDTO(
//				this.id, 
//				this.name);		
//				assertThat(newAlDTO instanceof AlbumDTO);
//	}
//	
//	@Test
//	public void allArguConstructorTest() {
//		this.tracks = new ArrayList<Track>();
//		AlbumDTO newAlbumDTO = new AlbumDTO(
//				this.id,
//				this.name,
//				this.tracks,
//				this.cover);
//		
//		assertThat(newAlbumDTO instanceof AlbumDTO);
//	}
//	
//	@Test
//	void GetIdTest() {
//		assertThat(this.testAlbumDTO.getId() == this.id);
//	}
//	
//	@Test
//	void SetIdTest() {
//
//		Long newId = 2l;
//		this.testAlbumDTO.setId(newId);		
//		assertThat(this.testAlbumDTO.getId() == newId);
//	}
//	
//	@Test
//	void GetNameTest() {
//		assertThat(this.testAlbumDTO.getName().equals(this.name));
//	}
//	
//	@Test
//	void SetNameTest() {
//
//		String newName = "Astral Weeks";
//		this.testAlbumDTO.setName(newName);
//		assertThat(this.testAlbumDTO.getName().equals(newName));
//	}
//	
//	@Test
//	void GetTracksTest() {
//		assertThat(this.testAlbumDTO.getTracks() == this.tracks);
//	}
//	
//	@Test
//	void SetTracksTest() {
//	
//		List<Track> newTracks = new ArrayList<Track>();
//		this.testAlbumDTO.setTracks(newTracks);
//		assertThat(this.testAlbumDTO.getTracks() == newTracks);
//	}
//	
//	@Test
//	void GetCoverTest() {
//		assertThat(this.testAlbumDTO.getCover() == this.cover);
//	}
//	
//	@Test
//	void SetCoverTest() {
//		
//		String newCover = "Billy Eilish";
//		this.testAlbumDTO.setCover(newCover);
//		
//		assertThat(this.testAlbumDTO.getCover().equals(newCover));
//	}
//	
//	
//	@Test
//	void testEquals() {
//		this.tracks = new ArrayList<Track>();
//		AlbumDTO emptyAlbum = new AlbumDTO();
//		AlbumDTO fullAlbum = new AlbumDTO(
//				this.id,
//				this.name,
//				this.tracks,
//				this.cover);
//		
//		assertThat(!this.testAlbumDTO.equals(emptyAlbum));
//		assertThat(this.testAlbumDTO.equals(fullAlbum));
//	}
//	
//	
//	@Test
//	void testHashCode() {
//		assertThat(this.testAlbumDTO.hashCode() == -1870013350);
//	}
//	
//	@AfterEach
//	void teardown() {
//		this.testAlbumDTO = null;
//	}

}
