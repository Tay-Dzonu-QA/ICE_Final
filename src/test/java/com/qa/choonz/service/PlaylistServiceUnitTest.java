package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;


@SpringBootTest
public class PlaylistServiceUnitTest {
	
	@Autowired
    private PlaylistService service;
	
	@Autowired
	 private MockMvc mock;

    @MockBean
    private PlaylistRepository repository;

    @MockBean
    private ModelMapper modelMapper;
    
    private ObjectMapper objectMapper;


    private List<Playlist> playlists;
    private Playlist testPlaylist;
    private Playlist testPlaylistWithId;
    private PlaylistDTO playlistDTO;

    final Long id = 1L;
    final String testName = "Funky Disco Robot";
    final String testDesc = "UpBeat";
    final String testArtwork = "art";
    

    @BeforeEach
    void init() {
        this.playlists = new ArrayList<>();
        this.testPlaylist = new Playlist(testName);
        this.playlists.add(testPlaylist);
        this.testPlaylistWithId = new Playlist(testPlaylist.getName());
        this.testPlaylistWithId.setId(id);
        this.playlistDTO = modelMapper.map(testPlaylistWithId, PlaylistDTO.class);
    }

    @Test
    void createTest() {

        when(this.repository.save(this.testPlaylist)).thenReturn(this.testPlaylistWithId);
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        PlaylistDTO expec = this.playlistDTO;
        PlaylistDTO real = this.service.create(this.testPlaylist);
        
        assertThat(expec).isEqualTo(real);

        verify(this.repository, times(1)).save(this.testPlaylist);
    }

    @Test
    void readOneTest() {

        when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testPlaylistWithId));
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.playlistDTO).isEqualTo(this.service.read(this.id));

        verify(this.repository, times(1)).findById(this.id);
    }

    @Test
    void readAllTest() {

        when(this.repository.findAll()).thenReturn(this.playlists);
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(this.repository, times(1)).findAll();
    }

    @Test
    void updateTest() {
    	
    	final PlaylistDTO newPlaylist = new PlaylistDTO(testName,testDesc,testArtwork);
    	newPlaylist.setId(this.id);
    	Playlist updatedPlaylist = new Playlist(newPlaylist.getName(), newPlaylist.getDescription(), newPlaylist.getArtwork());
        updatedPlaylist.setId(this.id);

        String result = this.mock
                .perform(request(HttpMethod.PUT, "/playlists/update/" + this.id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newPlaylist)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedPlaylist)), result);
    }

    @Test
    void deleteTest() {

    	when(this.repository.existsById(id)).thenReturn(true, false);
		
		assertThat(this.service.delete(id)).isFalse();
		
		verify(this.repository, times(1)).deleteById(id);
		verify(this.repository, times(1)).existsById(id);
    }

}
