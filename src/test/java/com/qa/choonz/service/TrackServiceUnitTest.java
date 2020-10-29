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

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

@SpringBootTest
public class TrackServiceUnitTest {
	
	@Autowired
    private TrackService service;

    @MockBean
    private TrackRepository repository;

    @MockBean
    private ModelMapper modelMapper;


    private List<Track> tracks;
    private Track testTracklist;
    private Track testTrackWithId;
    private TrackDTO trackDTO;

    final Long id = 1L;
    final String testName = "Start Me Up";

    @BeforeEach
    void init() {
        this.tracks = new ArrayList<>();
        this.testTracklist = new Track(testName);
        this.tracks.add(testTracklist);
        this.testTrackWithId = new Track(testTracklist.getName());
        this.testTrackWithId.setId(id);
        this.trackDTO = modelMapper.map(testTrackWithId, TrackDTO.class);
    }

    @Test
    void createTest() {

        when(this.repository.save(this.testTracklist)).thenReturn(this.testTrackWithId);

        when(this.modelMapper.map(this.testTrackWithId, TrackDTO.class)).thenReturn(this.trackDTO);

        TrackDTO expec = this.trackDTO;
        TrackDTO real = this.service.create(this.testTracklist);
        
        assertThat(expec).isEqualTo(real);

        verify(this.repository, times(1)).save(this.testTracklist);
    }

    @Test
    void readOneTest() {

        when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testTrackWithId));
        when(this.modelMapper.map(this.testTrackWithId, TrackDTO.class)).thenReturn(this.trackDTO);

        assertThat(this.trackDTO).isEqualTo(this.service.read(this.id));

        verify(this.repository, times(1)).findById(this.id);
    }

    @Test
    void readAllTest() {

        when(this.repository.findAll()).thenReturn(this.tracks);
        when(this.modelMapper.map(this.testTrackWithId, TrackDTO.class)).thenReturn(this.trackDTO);

        assertThat(this.service.read().isEmpty()).isFalse();

        verify(this.repository, times(1)).findAll();
    }

    @Test
    void updateTest() {
    	
    	Track track = new Track("Brown Sugar");
    	
        track.setId(this.id);

        TrackDTO trackDTO = new TrackDTO(id, "Brown Sugar");

        Track newTrack = new Track(trackDTO.getName());
        
        newTrack.setId(this.id);

        TrackDTO newTrackDTO = new TrackDTO(this.id, newTrack.getName());

        when(this.repository.findById(this.id)).thenReturn(Optional.of(track));
        when(this.repository.save(track)).thenReturn(newTrack);
        when(this.modelMapper.map(newTrack, TrackDTO.class)).thenReturn(newTrackDTO);

        assertThat(newTrackDTO).isEqualTo(this.service.update(trackDTO, this.id));

        verify(this.repository, times(1)).findById(1L);
        verify(this.repository, times(1)).save(newTrack);
    }

    @Test
    void deleteTest() {

    	when(this.repository.existsById(id)).thenReturn(true, false);
		
		assertThat(this.service.delete(id)).isFalse();
		
		verify(this.repository, times(1)).deleteById(id);
		verify(this.repository, times(1)).existsById(id);
    }


}
