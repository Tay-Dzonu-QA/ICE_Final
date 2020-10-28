package com.qa.choonz.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
@CrossOrigin
public class PlaylistController {

    private PlaylistService service;

    public PlaylistController(PlaylistService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<PlaylistDTO> create(@RequestBody Playlist playlist) {
        return new ResponseEntity<PlaylistDTO>(this.service.create(playlist), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<PlaylistDTO>> read() {
        return new ResponseEntity<List<PlaylistDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PlaylistDTO> read(@PathVariable Long id) {
        return new ResponseEntity<PlaylistDTO>(this.service.read(id), HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<PlaylistDTO>> readUserPlaylists(@PathVariable Long id) {
        return new ResponseEntity<List<PlaylistDTO>>(this.service.readUserPlaylists(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlaylistDTO> update(@RequestBody PlaylistDTO playlist, @PathVariable Long id) {
        return new ResponseEntity<PlaylistDTO>(this.service.update(playlist, id), HttpStatus.ACCEPTED);
    }
    @PutMapping("/add/{id}")
    public ResponseEntity<PlaylistDTO> addTrack(@RequestBody Long trackId, @PathVariable Long PlaylistId) {
        return new ResponseEntity<PlaylistDTO>(this.service.addTrack(PlaylistId, trackId), HttpStatus.ACCEPTED);
    }
    
    @PutMapping("/remove/{id}")
    public ResponseEntity<PlaylistDTO> removeTrack(@RequestBody Long trackId, @PathVariable Long PlaylistId) {
        return new ResponseEntity<PlaylistDTO>(this.service.removeTrack(PlaylistId, trackId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlaylistDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<PlaylistDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<PlaylistDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
