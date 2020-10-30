package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;

@SpringBootTest
public class UserControllerUnitTest {
	
	@Autowired
	private UserController controller;
	
	@Autowired
	private ModelMapper mapper;
	
	@MockBean
    private UserService service;

    private List<User> users;
    private User testUser;
    private User testUserWithId;
    private UserDTO userDTO;
    private final Long id = 1L;
    
    private String testName = "OJ";

    private UserDTO mapToDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }
    
    @BeforeEach
    void init() {
        this.users = new ArrayList<>();
        this.testUser = new User(testName);
        this.testUserWithId = new User(testUser.getUsername());
        this.testUserWithId.setId(id);
        this.users.add(testUserWithId);
        this.userDTO = this.mapToDTO(testUserWithId);
    }
    
    @Test
    void createTest() {
        when(this.service.create(testUser))
            .thenReturn(this.userDTO);
        
        assertThat(new ResponseEntity<UserDTO>(this.userDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testUser));
        
        verify(this.service, times(1))
            .create(this.testUser);
    }
    
    @Test
    void readOneTest() {
        when(this.service.read(this.id))
            .thenReturn(this.userDTO);
        
        assertThat(new ResponseEntity<UserDTO>(this.userDTO, HttpStatus.OK))
                .isEqualTo(this.controller.read(this.id));
        
        verify(this.service, times(1))
            .read(this.id);
    }

    @Test
    void readAllTest() {
        when(service.read())
            .thenReturn(this.users
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList()));
        
        assertThat(this.controller.read().getBody()
                .isEmpty()).isFalse();
        
        verify(this.service, times(1))
            .read();
    }
    
    @Test
    void updateTest() {
        UserDTO newUser= new UserDTO(id, testName);
        UserDTO updatedUser= new UserDTO(this.id, newUser.getUsername());

        when(this.service.update(newUser, this.id))
            .thenReturn(updatedUser);
        
        assertThat(new ResponseEntity<UserDTO>(updatedUser, HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(newUser, this.id));
        
        verify(this.service, times(1))
            .update(newUser, this.id);
    }
    
    @Test
    void deleteTest() {
        this.controller.delete(id);

        verify(this.service, times(1))
            .delete(id);
    }

}
