package com.qa.choonz.service;



import com.qa.choonz.exception.UserNotFoundException;
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

    public UserDTO read(long id) {
        User found = this.repo.findById(id).orElseThrow(UserNotFoundException::new);

        return this.mapToDTO(found);
    }
    
    public String checkPassword(String username) {
        return this.repo.checkPassword(username);
    }

    public String checkUsername(String username) {
        return this.repo.checkUsername(username);
    }

    public UserDTO findUser(String username){
        if (this.repo.findUsersByUsernameEquals(username) != null)
        return this.mapToDTO(this.repo.findUsersByUsernameEquals(username));
        else
            return new UserDTO();
    }

    public UserDTO update(UserDTO user, long id) {
        User toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
        SAPIBeanUtils.mergeNotNull(user,toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
