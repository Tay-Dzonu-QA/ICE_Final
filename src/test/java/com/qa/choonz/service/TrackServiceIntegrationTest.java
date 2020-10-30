package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

@SpringBootTest
public class TrackServiceIntegrationTest {

	@Autowired
    private TrackService service;

    @Autowired
    private TrackRepository repo;

    @Autowired	
    private ModelMapper modelMapper;


    private Track testTrack;
    private Track testTrackWithId;


    
    private TrackDTO mapToDTO(Track track) {
        return this.modelMapper.map(track, TrackDTO.class);
    }


    @BeforeEach
    public void init() {
    	this.repo.deleteAll();
        this.testTrack = new Track("White Track",1);
        this.testTrackWithId = this.repo.save(this.testTrack);
    }

    @Test
    public void createTrackTest() {
        assertThat(this.mapToDTO(this.testTrackWithId))
        .isEqualTo(this.service.create(testTrack));
    }

 
    @Test
    void ReadByIdTest() {
        assertThat(this.service.read(this.testTrackWithId.getId()))
        .isEqualTo(this.mapToDTO(this.testTrackWithId));
    }

    @Test
    void ReadAllTracksTest() {
    	assertThat(this.service.read())
        .isEqualTo(Stream.of(this.mapToDTO(testTrackWithId)).collect(Collectors.toList()));
    }
    @Test
    void testUpdate() {
    	TrackDTO newTrack = new TrackDTO(null, "Tuesday",1);
    	TrackDTO updatedTrack = new TrackDTO(this.testTrackWithId.getId(), newTrack.getName(),newTrack.getDuration());

    	System.out.println(updatedTrack.toString());
    	TrackDTO newT = this.service.update(newTrack, this.testTrackWithId.getId());
        assertThat(newT)
            .isEqualTo(updatedTrack);
        System.out.println(updatedTrack.toString());
        System.out.println(newT.toString());
    }
    
    @Test
    void DeleteTest() {
        assertThat(this.service.delete(this.testTrackWithId.getId())).isTrue();
    }

}
