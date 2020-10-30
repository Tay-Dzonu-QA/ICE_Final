package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserDTOTest {
	
	UserDTO testUserDTO;
	final Long id = 1l;
	final String username = "OJ";
	final String password = "password";
	List<PlaylistDTO> playlists;
	
	private static int activeTest = 1;
	private static StringBuilder sBuilder = new StringBuilder();
	private static String div = "=============================================\n";
	
	@BeforeEach
	void init() {
		this.playlists = new ArrayList<PlaylistDTO>();
		this.testUserDTO = new UserDTO(
				this.id, 
				this.username, 
				this.password,
				this.playlists);
		
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
	void noArguConsTest() {

		UserDTO newUser = new UserDTO();			
		assertThat(newUser instanceof UserDTO);
	}
	
	@Test
	void twoArguConsTest() {

		UserDTO newUser = new UserDTO(
				this.id, 
				this.username);			
		assertThat(newUser instanceof UserDTO);
	}
	
	@Test
	void threeArguConsTest() {

		UserDTO newUser = new UserDTO(
				this.id, 
				this.username,
				this.password);	
		
		assertThat(newUser instanceof UserDTO);
	}
	
	@Test
	void AllArguConsTest() {

		UserDTO newUser = new UserDTO(
				this.id, 
				this.username, 
				this.password,
				this.playlists);
		
		assertThat(newUser instanceof UserDTO);
	}
	
	@Test
    public void equalsWithNull() {
        assertFalse(testUserDTO.equals(null));
    }
	
	@Test
    public void equalsWithDifferentObject() {
        assertFalse(testUserDTO.equals(new Object()));
    }
	
	@Test
	public void checkEquality() {
	     assertTrue(testUserDTO.equals(testUserDTO));
	    }
	
	@Test
	void GetIdTest() {
		assertThat(this.testUserDTO.getId() == this.id);
	}
	
	@Test
	void SetIdTest() {
		Long newId = 2l;
		this.testUserDTO.setId(newId);		
		assertThat(this.testUserDTO.getId() == newId);
	}
	
	@Test
	void GetUserameTest() {
		assertThat(this.testUserDTO.getUsername().equals(this.username));
	}
	
	@Test
	void SetUsernameameTest() {
		String newUsername = "Funk";
		this.testUserDTO.setUsername(newUsername);
		assertThat(this.testUserDTO.getUsername().equals(newUsername));
	}
	
	@Test
	void GetPasswordTest() {
		assertThat(this.testUserDTO.getPassword().equals(this.password));
	}
	
	@Test
	void SetPasswordTest() {
		String newPass = "Funky";
		this.testUserDTO.setPassword(newPass);
		assertThat(this.testUserDTO.getPassword().equals(newPass));
	}
	
	
	@Test
	void GetPlaylistTest() {
		assertThat(this.testUserDTO.getPlaylist() == this.playlists);
	}
	
	@Test
	void SetAlbumsTest() {
		PlaylistDTO newPl = new PlaylistDTO();
		List<PlaylistDTO> newPlaylists = new ArrayList<PlaylistDTO>();
		newPlaylists.add(newPl);
		this.testUserDTO.setPlaylist(newPlaylists);
		assertThat(this.testUserDTO.getPlaylist() == newPlaylists);
	}
	
	
	@Test
	void HashcodeTest() {
		UserDTO pl1 = new UserDTO(id,username,password,playlists);
		UserDTO pl2 = new UserDTO(id,username,password,playlists);
		
		assertTrue(pl1.hashCode() == pl2.hashCode());
	}
	
	@Test
	void EqualsTest() {
		UserDTO emptyUser = new UserDTO();
		UserDTO fullUser = new UserDTO(
				this.id, 
				this.username, 
				this.password,
				this.playlists);
		
		
		assertThat(!this.testUserDTO.equals(emptyUser));
		assertThat(this.testUserDTO.equals(fullUser));		
	}

	@AfterEach
	void teardown() {
		this.testUserDTO = null;
	}

}
