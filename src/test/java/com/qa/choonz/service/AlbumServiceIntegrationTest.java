package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;


@SpringBootTest
public class AlbumServiceIntegrationTest {
	

    @Autowired
    private AlbumService service;

    @Autowired
    private AlbumRepository repo;

    @Autowired
    private ModelMapper modelMapper;


    private Album testAlbum;
    private Album testAlbumWithId;


    
    private AlbumDTO mapToDTO(Album album) {
        return this.modelMapper.map(album, AlbumDTO.class);
    }


    @BeforeEach
    public void init() {
    	this.repo.deleteAll();
        this.testAlbum = new Album("White Album");
        this.testAlbumWithId = this.repo.save(this.testAlbum);
    }

    @Test
    public void createAlbumTest() {
        assertThat(this.mapToDTO(this.testAlbumWithId))
        .isEqualTo(this.service.create(testAlbum));
    }

 
    @Test
    void ReadByIdTest() {
        assertThat(this.service.read(this.testAlbumWithId.getId()))
        .isEqualTo(this.mapToDTO(this.testAlbumWithId));
    }

    @Test
    void ReadAllAlbumsTest() {
    	assertThat(this.service.read())
        .isEqualTo(Stream.of(this.mapToDTO(testAlbumWithId)).collect(Collectors.toList()));
    }
    @Test
    void testUpdate() {
    	AlbumDTO newAlbum = new AlbumDTO(null, "Tuesday");
    	AlbumDTO updatedAlbum = new AlbumDTO(this.testAlbumWithId.getId(), newAlbum.getName());

        assertThat(this.service.update(newAlbum, this.testAlbumWithId.getId()))
            .isEqualTo(updatedAlbum);
    }
    
    @Test
    void DeleteTest() {
        assertThat(this.service.delete(this.testAlbumWithId.getId())).isTrue();
    }


}
