package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
public class UserServiceIntergrationTest {
	
	@InjectMocks
    private UserService service;

    @Mock
    private UserRepository repo;

    @Mock
    private ModelMapper modelMapper;

    private List<User> users;

    private User testUser;

    private User testUserWithId;

    private UserDTO userDTO;
    
    private String testName = "OJ";

    final long id = 1L;
    
    private UserDTO mapToDTO(User user) {
        return this.modelMapper.map(user, UserDTO.class);
    }

    @BeforeEach
    public void init() {
        this.users = new ArrayList<>();
        this.users.add(testUser);
        this.testUser = new User(testName);
        this.testUserWithId = new User(id, testUser.getUsername());
        this.testUserWithId.setId(id);
        this.userDTO = new ModelMapper().map(testUserWithId, UserDTO.class);
    }

    @Test
    public void createAlbumTest() {
        when(this.repo.save(testUser)).thenReturn(testUserWithId);
        when(this.modelMapper.map(testUserWithId, UserDTO.class)).thenReturn(userDTO);

        assertEquals(this.userDTO, this.service.create(testUser));

        verify(this.repo, times(1)).save(this.testUser);
    }

 
    @Test
    void ReadByIdTest() {
        assertThat(this.userDTO)
               .isEqualTo(this.service.read(this.id));
        assertThat(this.service.read(this.testUserWithId.getId()))
        .isEqualTo(this.mapToDTO(this.testUserWithId));
    }

    @Test
    void ReadAllAlbumsTest() {
        assertThat(this.service.read())
                .isEqualTo(Stream.of(this.mapToDTO(testUserWithId))
                        .collect(Collectors.toList()));
    }
    
    @Test
    void DeleteTest() {
        assertThat(this.service.delete(this.id)).isTrue();
    }

}
