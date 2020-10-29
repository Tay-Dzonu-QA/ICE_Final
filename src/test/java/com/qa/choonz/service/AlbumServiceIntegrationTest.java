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

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;


@SpringBootTest
public class AlbumServiceIntegrationTest {
	

    @InjectMocks
    private AlbumService service;

    @Mock
    private AlbumRepository repo;

    @Mock
    private ModelMapper modelMapper;

    private List<Album> albums;

    private Album testAlbum;

    private Album testAlbumWithId;

    private AlbumDTO albumDTO;

    final long id = 1L;
    
    private AlbumDTO mapToDTO(Album album) {
        return this.modelMapper.map(album, AlbumDTO.class);
    }

    @BeforeEach
    public void init() {
        this.albums = new ArrayList<>();
        this.albums.add(testAlbum);
        this.testAlbum = new Album("White Album");
        this.testAlbumWithId = new Album(id, testAlbum.getName(),testAlbum.getArtist(),testAlbum.getGenre(),testAlbum.getCover());
        this.testAlbumWithId.setId(id);
        this.albumDTO = new ModelMapper().map(testAlbumWithId, AlbumDTO.class);
    }

    @Test
    public void createAlbumTest() {
        when(this.repo.save(testAlbum)).thenReturn(testAlbumWithId);
        when(this.modelMapper.map(testAlbumWithId, AlbumDTO.class)).thenReturn(albumDTO);

        assertEquals(this.albumDTO, this.service.create(testAlbum));

        verify(this.repo, times(1)).save(this.testAlbum);
    }

 
    @Test
    void ReadByIdTest() {
        assertThat(this.albumDTO)
               .isEqualTo(this.service.read(this.id));
        assertThat(this.service.read(this.testAlbumWithId.getId()))
        .isEqualTo(this.mapToDTO(this.testAlbumWithId));
    }

    @Test
    void ReadAllAlbumsTest() {
        assertThat(this.service.read())
                .isEqualTo(Stream.of(this.mapToDTO(testAlbumWithId))
                        .collect(Collectors.toList()));
    }
    
    @Test
    void DeleteTest() {
        assertThat(this.service.delete(this.id)).isTrue();
    }


}
