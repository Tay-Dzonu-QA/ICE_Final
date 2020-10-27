package com.qa.choonz.service;

import com.qa.choonz.exception.PlaylistNotFoundException;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;

import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.utils.SAPIBeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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

    public UserDTO read(Long id) {
        User found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        return this.mapToDTO(found);
    }
    
    public String checkPassword(String username) {
        return this.repo.checkPassword(username);
    }

    public UserDTO update(UserDTO user, Long id) {
        User toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        SAPIBeanUtils.mergeNotNull(user,toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
