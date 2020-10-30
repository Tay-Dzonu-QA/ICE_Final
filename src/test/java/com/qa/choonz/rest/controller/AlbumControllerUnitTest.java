package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;


@SpringBootTest
public class AlbumControllerUnitTest {
	
	@Autowired
	private AlbumController controller;
	
	@Autowired
	private ModelMapper mapper;
	
	@MockBean
    private AlbumService service;

    private List<Album> albums;
    private Album testAlbum;
    private Album testAlbumWithId;
    private AlbumDTO albumDTO;
    private final Long id = 1L;
    
    private String testName = "Rumours";

    private AlbumDTO mapToDTO(Album album) {
        return this.mapper.map(album, AlbumDTO.class);
    }
    
    @BeforeEach
    void init() {
        this.albums = new ArrayList<>();
        this.testAlbum = new Album(testName);
        this.testAlbumWithId = new Album(testAlbum.getName());
        this.testAlbumWithId.setId(id);
        this.albums.add(testAlbumWithId);
        this.albumDTO = this.mapToDTO(testAlbumWithId);
    }
    
    @Test
    void createTest() {
        when(this.service.create(testAlbum))
            .thenReturn(this.albumDTO);
        
        assertThat(new ResponseEntity<AlbumDTO>(this.albumDTO, HttpStatus.CREATED))
                .isEqualTo(this.controller.create(testAlbum));
        
        verify(this.service, times(1))
            .create(this.testAlbum);
    }
    
    @Test
    void readOneTest() {
        when(this.service.read(this.id))
            .thenReturn(this.albumDTO);
        
        assertThat(new ResponseEntity<AlbumDTO>(this.albumDTO, HttpStatus.OK))
                .isEqualTo(this.controller.read(this.id));
        
        verify(this.service, times(1))
            .read(this.id);
    }

    @Test
    void readAllTest() {
        when(service.read())
            .thenReturn(this.albums
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList()));
        
        assertThat(this.controller.read().getBody()
                .isEmpty()).isFalse();
        
        verify(this.service, times(1))
            .read();
    }
    
    @Test
    void readArtistsTest() {
        when(service.readArtist(id))
            .thenReturn(this.albums
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList()));
        
        assertThat(this.controller.readArtists(id).getBody()
                .isEmpty()).isFalse();
        
        verify(this.service, times(1))
            .readArtist(id);
    }
    @Test
    void readGenresTest() {
        when(service.readGenre(1l))
            .thenReturn(this.albums
                    .stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList()));
        
        assertThat(this.controller.readGenres(1l).getBody()
                .isEmpty()).isFalse();
        
        verify(this.service, times(1))
            .readGenre(1l);
    }
    
    @Test
    void updateTest() {

        AlbumDTO newAlbum= new AlbumDTO(id, testName);
        AlbumDTO updatedAlbum= new AlbumDTO(this.id, newAlbum.getName());

        when(this.service.update(newAlbum, this.id))
            .thenReturn(updatedAlbum);
        
        assertThat(new ResponseEntity<AlbumDTO>(updatedAlbum, HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.update(newAlbum, this.id));
        
        verify(this.service, times(1))
            .update(newAlbum, this.id);
    }
    
    @Test
    void deleteTest() {
        this.controller.delete(id);

        verify(this.service, times(1))
            .delete(id);
    }

}
