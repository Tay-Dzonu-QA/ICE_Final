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
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntergrationTest {
	
	@Autowired
    private MockMvc mock;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser; 
    private User testUserWithId;
    private UserDTO userDTO;

    private Long id;
    @SuppressWarnings("unused")
	private String testUsername;

    private UserDTO mapToDTO(User track) {
        return this.modelMapper.map(track, UserDTO.class);
    }

    @BeforeEach
    void init() {
        this.repository.deleteAll();

        this.testUser = new User("OJ");
        this.testUserWithId = this.repository.save(this.testUser);
        this.userDTO = this.mapToDTO(testUserWithId);

        this.id = this.testUserWithId.getId();
        this.testUsername = this.testUserWithId.getUsername();
    }

    @Test
    void testCreate() throws Exception {
        this.mock
                .perform(request(HttpMethod.POST, "/users/create").contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(testUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(this.objectMapper.writeValueAsString(userDTO)));
    }

    @Test
    void testReadOne() throws Exception {
        this.mock
        		.perform(request(HttpMethod.GET, "/users/read/" + this.id)
        				.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(this.objectMapper.writeValueAsString(this.userDTO)));
    }

    @Test
    void testReadAll() throws Exception {
        List<UserDTO> tracks = new ArrayList<>();
        tracks.add(this.userDTO);

        String content = this.mock
                .perform(request(HttpMethod.GET, "/users/read").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertEquals(this.objectMapper.writeValueAsString(tracks), content);
    }

    @Test
    void testUpdate() throws Exception {
    	UserDTO newUser = new UserDTO(id, "JJ");
    	User updatedUser = new User(newUser.getUsername());
        updatedUser.setId(this.id);

        String result = this.mock
                .perform(request(HttpMethod.PUT, "/users/update/" + this.id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isAccepted()).andReturn().getResponse().getContentAsString();

        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedUser)), result);
    }

    @Test
    void testDelete() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/users/delete/" + this.id)).andExpect(status().isNoContent());
    }

}
