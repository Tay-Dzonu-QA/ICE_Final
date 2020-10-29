package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;

@SpringBootTest
public class ArtistServiceIntegrationTest {

	@Autowired
    private ArtistService service;

	@Autowired
    private ArtistRepository repo;

	@Autowired
    private ModelMapper modelMapper;

    private Artist testArtist;
    private Artist testArtistWithId;

    
    private ArtistDTO mapToDTO(Artist artist) {
        return this.modelMapper.map(artist, ArtistDTO.class);
    }

    @BeforeEach
    public void init() {
    	this.repo.deleteAll();
        this.testArtist = new Artist("White Lines");
        this.testArtistWithId = this.repo.save(this.testArtist);
        System.out.println(this.testArtist.toString());
        System.out.println(this.testArtistWithId.toString());
    }

    @Test
    public void createArtistTest() {
        assertThat(this.mapToDTO(this.testArtistWithId))
        .isEqualTo(this.service.create(testArtist));
    }


    @Test
    void ReadByIdTest() {
        assertThat(this.service.read(this.testArtistWithId.getId()))
        .isEqualTo(this.mapToDTO(this.testArtistWithId));
    }


    @Test
    void ReadAllArtistTest() {
    	assertThat(this.service.read())
        .isEqualTo(Stream.of(this.mapToDTO(testArtistWithId)).collect(Collectors.toList()));
    }
//    @Test
//  void ReadAllArtistDescTest() {
//      assertThat(this.service.read())
//              .isEqualTo(Stream.of(this.mapToDTO(testArtistWithId))
//                      .collect(Collectors.toList()));
//  } 
//    @Test
//  void ReadNameTest() {
//} 
//    @Test
//  void ReadNameDescTest() {
//}

    
    @Test
    void testUpdate() {
    	ArtistDTO newArtist = new ArtistDTO(null, "Tuesday");
    	ArtistDTO updatedArtist = new ArtistDTO(this.testArtistWithId.getId(), newArtist.getName());

        assertThat(this.service.update(newArtist, this.testArtistWithId.getId()))
            .isEqualTo(updatedArtist);
    }
    
    @Test
    void DeleteTest() {
        assertThat(this.service.delete(this.testArtistWithId.getId())).isTrue();
    }

}
