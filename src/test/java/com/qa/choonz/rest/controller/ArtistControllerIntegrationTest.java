package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class ArtistControllerIntegrationTest {
	
		@Autowired
	    private MockMvc mock;

	    @Autowired
	    private ArtistRepository repository;

	    @Autowired
	    private ModelMapper modelMapper;

	    @Autowired
	    private ObjectMapper objectMapper;

	    private Artist testArtist; 
	    private Artist testArtistWithId;
	    private ArtistDTO artistDTO;
	 
	    private Long id;

		private String testName = "Gerry Rafferty";

	    private ArtistDTO mapToDTO(Artist artist) {
	        return this.modelMapper.map(artist, ArtistDTO.class);
	    }

	    @BeforeEach
	    void init() {
	        this.repository.deleteAll();
	        this.testArtist = new Artist(testName);
	        this.testArtistWithId = this.repository.save(this.testArtist);
	        this.artistDTO = this.mapToDTO(testArtistWithId);
	        this.id = this.testArtistWithId.getId();
	        this.testName = this.testArtistWithId.getName();
	    }

	    @Test
	    void testCreate() throws Exception {
	        this.mock
	                .perform(request(HttpMethod.POST, "/artists/create").contentType(MediaType.APPLICATION_JSON)
	                        .content(this.objectMapper.writeValueAsString(testArtist))
	                        .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isCreated())
	                .andExpect(content().json(this.objectMapper.writeValueAsString(artistDTO)));
	    }

	    @Test
	    void testReadOne() throws Exception {
	        this.mock.perform(request(HttpMethod.GET, "/artists/read/" + this.id).accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().json(this.objectMapper.writeValueAsString(this.artistDTO)));
	    }

	    @Test
	    void testReadAll() throws Exception {
	        List<ArtistDTO> artists = new ArrayList<>();
	        artists.add(this.artistDTO);

	        String library = this.mock
	                .perform(request(HttpMethod.GET, "/artists/read").accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

	        assertEquals(this.objectMapper.writeValueAsString(artists), library);
	    }

	    @Test
	    void testUpdate() throws Exception {
	    	ArtistDTO newArt = new ArtistDTO(id, testName);
	    	Artist updatedArt = new Artist(newArt.getName());
	        updatedArt.setId(this.id);

	        String result = this.mock
	                .perform(request(HttpMethod.PUT, "/artists/update/" + this.id).accept(MediaType.APPLICATION_JSON)
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(this.objectMapper.writeValueAsString(newArt)))
	                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

	        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedArt)), result);
	    }

	    @Test
	    void testDelete() throws Exception {
	        this.mock.perform(request(HttpMethod.DELETE, "/artists/delete/" + this.id)).andExpect(status().isNoContent());
	    }

}
