//package com.qa.choonz.rest.dto;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class PlaylistDTOTest {
//
//
//	PlaylistDTO testPlaylistDTO;
//	final Long id = 1L;
//	private String description;
//	private String artwork;
//	private String name;
//	private List<TrackDTO>tracks;
//
//	private static int activeTest = 1;
//	private static StringBuilder sBuilder = new StringBuilder();
//	private static String div = "=============================================\n";
//
//
//	@BeforeEach
//	void init() {
//		this.tracks = new ArrayList<TrackDTO>();
//		this.testPlaylistDTO = new PlaylistDTO(
//				this.id,
//				this.name);
//
//		// Start of console test format
//				sBuilder.setLength(0);
//				sBuilder
//				.append("\tTest ").append(activeTest).append("\n")
//				.append(div);
//				// Append info about vending machine here
//
//				System.out.println(sBuilder.toString());
//				activeTest++;
//				// End of console test format
//	}
//
//	@Test
//	public void noArguConstructorTest() {
//		PlaylistDTO newPl = new PlaylistDTO();
//
//		assertThat(newPl instanceof PlaylistDTO);
//	}
//
//	@Test
//	public void twoArguConstructorTest() {
//			PlaylistDTO newArtDTO = new PlaylistDTO(
//					this.id,
//					this.name);
//
//			assertThat(newArtDTO instanceof PlaylistDTO);
//	}
//
//	@Test
//	public void fourArguConstructorTest() {
//		PlaylistDTO newPlaylistDTO = new PlaylistDTO(
//				this.name,
//				this.description,
//				this.artwork,
//				this.tracks);
//
//		assertThat(newPlaylistDTO instanceof PlaylistDTO);
//	}
//
//	@Test
//	public void AllArguConstructorTest() {
//		PlaylistDTO newPlaylistDTO = new PlaylistDTO(
//				this.id,
//				this.name,
//				this.description,
//				this.artwork,
//				this.tracks,
//				null);
//
//		assertThat(newPlaylistDTO instanceof PlaylistDTO);
//	}
//
//
//	@Test
//	void GetIdTest() {
//
//		assertThat(this.testPlaylistDTO.getId() == this.id);
//	}
//
//	@Test
//	void SetIdTest() {
//
//		Long newId = 2l;
//		this.testPlaylistDTO.setId(newId);
//
//		assertThat(this.testPlaylistDTO.getId() == newId);
//	}
//
////	@Test
////	void GetNameTest() {
////		assertThat(this.testPlaylistDTO.getName().equals(this.name));
////	}
//
//	@Test
//	void SetNameTest() {
//
//		String newName = "Astral Weeks";
//	this.testPlaylistDTO.setName(newName);
//
//	assertThat(this.testPlaylistDTO.getName().equals(newName));
//	}
//
//	@Test
//	void SetDescrtiptionTest() {
//		String newDesc = "Funky";
//		this.testPlaylistDTO.setDescription(newDesc);
//		assertThat(this.testPlaylistDTO.getDescription().equals(newDesc));
//	}
//
//	@Test
//	public void getSetArtworkTest() {
//		PlaylistDTO newPl = new PlaylistDTO();
//		newPl.setArtwork(artwork);
//
//		assertEquals(artwork,newPl.getArtwork());
//	}
//
//	@Test
//	void SetAlbumTest() {
//
//		List<TrackDTO> newTracks = new ArrayList<TrackDTO>();
//		this.testPlaylistDTO.setTracks(newTracks);
//
//		assertThat(this.testPlaylistDTO.getTracks() == newTracks);
//	}
//
//		@Test
//	void testEquals() {
//			PlaylistDTO emptyPl = new PlaylistDTO();
//			PlaylistDTO fullPl = new PlaylistDTO(
//					this.id,
//					this.name,
//					this.description,
//					this.artwork,
//					this.tracks,
//					null);
//
//			assertThat(!this.testPlaylistDTO.equals(emptyPl));
//			assertThat(this.testPlaylistDTO.equals(fullPl));
//	}
//
//
//		@Test
//		void HashCodeTest() {
//
//			PlaylistDTO a1 = new PlaylistDTO(id,name,description,artwork,tracks,null);
//			PlaylistDTO a2 = new PlaylistDTO(id,name,description,artwork,tracks,null);
//
//			assertTrue(a1.hashCode() == a2.hashCode());
//		}
//
//	@AfterEach
//	void teardown() {
//		this.testPlaylistDTO = null;
//	}
//
//}
