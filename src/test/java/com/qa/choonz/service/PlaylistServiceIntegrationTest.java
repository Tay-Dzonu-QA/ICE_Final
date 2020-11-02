package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;


@SpringBootTest
public class PlaylistServiceIntegrationTest {
	
	@Autowired
    private PlaylistService service;

    @Autowired
    private PlaylistRepository repo;
    
    @Autowired
    private TrackRepository TRepo;

    @Autowired
    private ModelMapper modelMapper;


    private Playlist testPlaylist;
    private Playlist testPlaylistWithId;


    
    private PlaylistDTO mapToDTO(Playlist playlist) {
        return this.modelMapper.map(playlist, PlaylistDTO.class);
    }


    @BeforeEach
    public void init() {
    	
    	this.TRepo.deleteAll();
    	this.repo.deleteAll();
    	
        this.testPlaylist = new Playlist("White Playlist","this","this");
        this.testPlaylistWithId = this.repo.save(this.testPlaylist);
    }

    @Test
    public void createPlaylistTest() {
        assertThat(this.mapToDTO(this.testPlaylistWithId))
        .isEqualTo(this.service.create(testPlaylist));
    }

 
    @Test
    void ReadByIdTest() {
        assertThat(this.service.read(this.testPlaylistWithId.getId()))
        .isEqualTo(this.mapToDTO(this.testPlaylistWithId));
    }

    @Test
    void ReadAllPlaylistsTest() {
    	assertThat(this.service.read())
        .isEqualTo(Stream.of(this.mapToDTO(testPlaylistWithId)).collect(Collectors.toList()));
    }
    @Test
    void testUpdate() {
    	PlaylistDTO newPlaylist = new PlaylistDTO( null,"Tuesday","that","that");
    	PlaylistDTO updatedPlaylist = new PlaylistDTO(this.testPlaylistWithId.getId(), newPlaylist.getName(),newPlaylist.getDescription(),newPlaylist.getArtwork());

        assertThat(this.service.update(newPlaylist, this.testPlaylistWithId.getId()))
            .isEqualTo(updatedPlaylist);
    }
    
    @Test
    void DeleteTest() {
        assertThat(this.service.delete(this.testPlaylistWithId.getId())).isTrue();
    }

}
