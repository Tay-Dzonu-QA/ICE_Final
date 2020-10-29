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

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

@SpringBootTest
public class TrackServiceIntegrationTest {
	
	 @InjectMocks
	    private TrackService service;

	    @Mock
	    private TrackRepository repo;

	    @Mock
	    private ModelMapper modelMapper;

	    private List<Track> tracks;

	    private Track testTrack;

	    private Track testTrackWithId;

	    private TrackDTO trackDTO;
	    
	    private String testName = "North American Scum";

	    final long id = 1L;
	    
	    private TrackDTO mapToDTO(Track track) {
	        return this.modelMapper.map(track, TrackDTO.class);
	    }

	    @BeforeEach
	    public void init() {
	        this.tracks = new ArrayList<>();
	        this.tracks.add(testTrack);
	        this.testTrack = new Track(testName);
	        this.testTrackWithId = new Track(id, testTrack.getName(),testTrack.getAlbum(),testTrack.getPlaylist(),testTrack.getDuration(),testTrack.getLyrics());
	        this.testTrackWithId.setId(id);
	        this.trackDTO = new ModelMapper().map(testTrackWithId, TrackDTO.class);
	    }

	    @Test
	    public void createAlbumTest() {
	        when(this.repo.save(testTrack)).thenReturn(testTrackWithId);
	        when(this.modelMapper.map(testTrackWithId, TrackDTO.class)).thenReturn(trackDTO);

	        assertEquals(this.trackDTO, this.service.create(testTrack));

	        verify(this.repo, times(1)).save(this.testTrack);
	    }

	 
	    @Test
	    void ReadByIdTest() {
	        assertThat(this.trackDTO)
	               .isEqualTo(this.service.read(this.id));
	        assertThat(this.service.read(this.testTrackWithId.getId()))
	        .isEqualTo(this.mapToDTO(this.testTrackWithId));
	    }

	    @Test
	    void ReadAllAlbumsTest() {
	        assertThat(this.service.read())
	                .isEqualTo(Stream.of(this.mapToDTO(testTrackWithId))
	                        .collect(Collectors.toList()));
	    }
	    
	    @Test
	    void DeleteTest() {
	        assertThat(this.service.delete(this.id)).isTrue();
	    }


}
