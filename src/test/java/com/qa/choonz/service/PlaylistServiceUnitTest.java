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
    private PlaylistRepository repo;

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
    final String testName = "Dance Hits";

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

        when(this.repo.save(this.testPlaylist)).thenReturn(this.testPlaylistWithId);
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        PlaylistDTO expected = this.playlistDTO;
        PlaylistDTO actual = this.service.create(this.testPlaylist);
        assertThat(expected).isEqualTo(actual);

        verify(this.repo, times(1)).save(this.testPlaylist);
    }

    @Test
    void readOneTest() {

        when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testPlaylistWithId));
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.playlistDTO).isEqualTo(this.service.read(this.id));

        verify(this.repo, times(1)).findById(this.id);
    }

    @Test
    void readAllTest() {

        when(this.repo.findAll()).thenReturn(this.playlists);

        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(this.repo, times(1)).findAll();
    }
    @Test
    void readUserPlaylistsTest() {

        when(this.repo.readUserPlaylists(1l)).thenReturn(this.playlists);
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.readUserPlaylists(1l).isEmpty()).isFalse();

        verify(this.repo, times(1)).readUserPlaylists(1l);
    }
    

    @Test
    void updateTest() {
    	Playlist playlist = new Playlist(testName);
        playlist.setId(this.id);

        PlaylistDTO playlistDTO = new PlaylistDTO(null, testName);

        Playlist updatedPlaylist = new Playlist(playlistDTO.getName());
        updatedPlaylist.setId(this.id);

        PlaylistDTO updatedPlaylistDTO = new PlaylistDTO(this.id, updatedPlaylist.getName());

        when(this.repo.findById(this.id)).thenReturn(Optional.of(playlist));
        when(this.repo.save(playlist)).thenReturn(updatedPlaylist);
        when(this.modelMapper.map(updatedPlaylist, PlaylistDTO.class)).thenReturn(updatedPlaylistDTO);

        assertThat(updatedPlaylistDTO).isEqualTo(this.service.update(playlistDTO, this.id));

        verify(this.repo, times(1)).findById(1L);
        verify(this.repo, times(1)).save(updatedPlaylist);
    }
    
    @Test
    void addTrackTest() {

    	when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testPlaylistWithId));
    	when(this.trackRepo.findById(this.id)).thenReturn(Optional.of(this.testTrackWithId));
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.addTrack(1l,1l));

        verify(this.repo, times(1)).findById(this.id);
        verify(this.trackRepo, times(1)).findById(this.id);
        verify(this.repo, times(1)).save(testPlaylistWithId);
    }
    @Test
    void removeTrackTest() {

    	when(this.repo.findById(this.id)).thenReturn(Optional.of(this.testPlaylistWithId));
    	when(this.trackRepo.findById(this.id)).thenReturn(Optional.of(this.testTrackWithId));
        when(this.modelMapper.map(this.testPlaylistWithId, PlaylistDTO.class)).thenReturn(this.playlistDTO);

        assertThat(this.service.removeTrack(1l,1l));

        verify(this.repo, times(1)).findById(this.id);
        verify(this.trackRepo, times(1)).findById(this.id);
        verify(this.repo, times(1)).save(testPlaylistWithId);
    }

    @Test
    void deleteTest() {

        when(this.repo.existsById(id)).thenReturn(true, false);

        assertThat(this.service.delete(id)).isFalse();

        verify(this.repo, times(1)).deleteById(id);
    }
}
