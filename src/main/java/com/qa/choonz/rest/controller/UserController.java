package com.qa.choonz.rest.controller;
import com.qa.choonz.persistence.domain.User;


import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        super();
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
        ModelMapper mapper = new ModelMapper();
        User returned = mapper.map(user,User.class);
        return new ResponseEntity<>(this.service.create(returned), HttpStatus.CREATED);
    }
    @GetMapping("/read")
    public ResponseEntity<List<UserDTO>> read() {
        return new ResponseEntity<>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable long id) {
        return new ResponseEntity<>(this.service.read(id), HttpStatus.OK);
    }
    @GetMapping("/password/{username}")
	public ResponseEntity<String> readLastID(@PathVariable String username){
		return ResponseEntity.ok(this.service.checkPassword(username));
	}
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> findUser(@PathVariable String username){
        return ResponseEntity.ok(this.service.findUser(username));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user, @PathVariable Long id) {
        return new ResponseEntity<>(this.service.update(user, id), HttpStatus.ACCEPTED);
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable long id) {
        return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
