package com.qa.choonz.service;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.UserDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserRepository repo;
    private ModelMapper mapper;


    public UserService(UserRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }
    private UserDTO mapToDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }
    public UserDTO create(User user){
        User created = this.repo.save(user);
        return this.mapToDTO(created);
    }




    public List<UserDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO read(long id) {
        User found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        return this.mapToDTO(found);
    }

    public UserDTO update(User user, long id) {
        User toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        toUpdate.setUserName(user.getUserName());
        toUpdate.setPassword(user.getPassword());
        toUpdate.setPlaylists(user.getPlaylists());
        User updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
