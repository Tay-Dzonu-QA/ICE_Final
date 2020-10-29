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

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;


@SpringBootTest
public class PlaylistServiceUnitTest {
	
	@Autowired
    private PlaylistService service;

    @MockBean
    private PlaylistRepository repository;
    
    @MockBean
    private TrackRepository trackRepo;

    @MockBean
    private ModelMapper modelMapper;


    private List<Playlist> playlists;
    private Playlist testPlaylist;
    private Playlist testPlaylistWithId;
    private PlaylistDTO playlistDTO;
    
    private Track testTrack;
    private Track testTrackWithId;
    private TrackDTO trackDTO;

    final Long id = 1L;
    final String testName = "Funky Disco Robot";

    @BeforeEach
    void init() {
        this.playlists = new ArrayList<>();
        this.testPlaylist = new Playlist(testName);
        this.playlists.add(testPlaylist);
        this.testPlaylistWithId = new Playlist(testPlaylist.getName());
        this.testPlaylistWithId.setId(id);
        this.playlistDTO = modelMapper.map(testPlaylistWithId, PlaylistDTO.class);
        
        this.testTrack = new Track(testName);
        this.testTrackWithId = new Track(testTrack.getName());
        this.testTrackWithId.setId(id);
        this.trackDTO = modelMapper.map(testTrackWithId, TrackDTO.class);
    }

    @Test
    void createTest() {

        when(this.repository.save(this.testPlaylist)).thenReturn(this.testPlaylistWithId);
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        PlaylistDTO expec = this.playlistDTO;
        PlaylistDTO real = this.service.create(this.testPlaylist);
        
        assertThat(expec).isEqualTo(real);

        verify(this.repository, times(1)).save(this.testPlaylist);
    }

    @Test
    void readOneTest() {

        when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testPlaylistWithId));
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.playlistDTO).isEqualTo(this.service.read(this.id));

        verify(this.repository, times(1)).findById(this.id);
    }

    @Test
    void readAllTest() {

        when(this.repository.findAll()).thenReturn(this.playlists);
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(this.repository, times(1)).findAll();
    }
    @Test
    void readUserPlaylistsTest() {

        when(this.repository.readUserPlaylists(1l)).thenReturn(this.playlists);
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.readUserPlaylists(1l).isEmpty()).isFalse();

        verify(this.repository, times(1)).readUserPlaylists(1l);
    }
    

    @Test
    void updateTest() {
    	
    	Playlist pl = new Playlist("Summer Choonz");
    	
        pl.setId(this.id);

        PlaylistDTO playlistDTO = new PlaylistDTO(id, "Summer Choonz");

        Playlist newPlaylist = new Playlist(playlistDTO.getName());
        
        newPlaylist.setId(this.id);

        PlaylistDTO newPlaylistDTO = new PlaylistDTO(this.id, newPlaylist.getName());

        when(this.repository.findById(this.id)).thenReturn(Optional.of(pl));
        when(this.repository.save(pl)).thenReturn(newPlaylist);
        when(this.modelMapper.map(newPlaylist, PlaylistDTO.class)).thenReturn(newPlaylistDTO);

        assertThat(newPlaylistDTO).isEqualTo(this.service.update(playlistDTO, this.id));

        verify(this.repository, times(1)).findById(1L);
        verify(this.repository, times(1)).save(newPlaylist);
    }
    
    @Test
    void addTrackTest() {

    	when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testPlaylistWithId));
    	when(this.trackRepo.findById(this.id)).thenReturn(Optional.of(this.testTrackWithId));
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.addTrack(1l,1l));

        verify(this.repository, times(1)).findById(this.id);
        verify(this.trackRepo, times(1)).findById(this.id);
        verify(this.repository, times(1)).save(testPlaylistWithId);
    }
    @Test
    void removeTrackTest() {

    	when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testPlaylistWithId));
    	when(this.trackRepo.findById(this.id)).thenReturn(Optional.of(this.testTrackWithId));
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.removeTrack(1l,1l));

        verify(this.repository, times(1)).findById(this.id);
        verify(this.trackRepo, times(1)).findById(this.id);
        verify(this.repository, times(1)).save(testPlaylistWithId);
    }

    @Test
    void deleteTest() {

    	when(this.repository.existsById(id)).thenReturn(true, false);
		
		assertThat(this.service.delete(id)).isFalse();
		
		verify(this.repository, times(1)).deleteById(id);
		verify(this.repository, times(1)).existsById(id);
    }

}
