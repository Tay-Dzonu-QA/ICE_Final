package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
public class UserServiceUnitTest {
	
	@Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @MockBean
    private ModelMapper modelMapper;


    private List<User> users;
    private User testUserlist;
    private User testUserWithId;
    private UserDTO userDTO;

    final Long id = 1L;
    final String password = "password";
    final String testUsername = "OJ";

    @BeforeEach
    void init() {
        this.users = new ArrayList<>();
        this.testUserlist = new User(testUsername);
        this.users.add(testUserlist);
        this.testUserWithId = new User(testUserlist.getUsername());
        this.testUserWithId.setId(id);
        this.userDTO = modelMapper.map(testUserWithId, UserDTO.class);
    }

    @Test
    void createTest() {

        when(this.repository.save(this.testUserlist)).thenReturn(this.testUserWithId);

        when(this.modelMapper.map(this.testUserWithId, UserDTO.class)).thenReturn(this.userDTO);

        UserDTO expec = this.userDTO;
        UserDTO real = this.service.create(this.testUserlist);
        
        assertThat(expec).isEqualTo(real);

        verify(this.repository, times(1)).save(this.testUserlist);
    }

    @Test
    void readOneTest() {

        when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testUserWithId));
        when(this.modelMapper.map(this.testUserWithId, UserDTO.class)).thenReturn(this.userDTO);

        assertThat(this.userDTO).isEqualTo(this.service.read(this.id));

        verify(this.repository, times(1)).findById(this.id);
    }

    @Test
    void readAllTest() {

        when(this.repository.findAll()).thenReturn(this.users);
        when(this.modelMapper.map(this.testUserWithId, UserDTO.class)).thenReturn(this.userDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(this.repository, times(1)).findAll();
    }

//    @Test
//    void updateTest() {
//    	
//    	User user = new User("JJ");
//    	
//        user.setId(this.id);
//
//        UserDTO userDTO = new UserDTO(id, "JJ","password",null);
//
//        User newUser = new User(userDTO.getUsername());
//        
//        newUser.setId(this.id);
//
//        UserDTO newUserDTO = new UserDTO(this.id, newUser.getUsername());
//
//        when(this.repository.findById(this.id)).thenReturn(Optional.of(user));
//        when(this.repository.save(user)).thenReturn(newUser);
//        when(this.modelMapper.map(newUser, UserDTO.class)).thenReturn(newUserDTO);
//
//        assertThat(newUserDTO).isEqualTo(this.service.update(userDTO, this.id));
//
//        verify(this.repository, times(1)).findById(1L);
//        verify(this.repository, times(1)).save(newUser);
//    }
    
//    @Test
//    void CheckPasswordTest() {
//        when(this.repository.checkPassword()).thenReturn(this.password);
//        when(this.modelMapper.map(this.testUserWithId, UserDTO.class)).thenReturn(this.userDTO);
//
//        assertThat(this.service.read().isEmpty()).isFalse();
//
//        verify(this.repository, times(1)).checkPassword();
//    }
    	

    @Test
    void deleteTest() {

    	when(this.repository.existsById(id)).thenReturn(true, false);
		
		assertThat(this.service.delete(id)).isFalse();
		
		verify(this.repository, times(1)).deleteById(id);
		verify(this.repository, times(1)).existsById(id);
    }

}
