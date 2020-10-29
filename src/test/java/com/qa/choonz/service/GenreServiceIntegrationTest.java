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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;

@SpringBootTest
public class GenreServiceIntegrationTest {
	
	@InjectMocks
    private GenreService service;

    @Mock
    private GenreRepository repo;

    @Mock
    private ModelMapper modelMapper;

    private List<Genre> genre;

    private Genre testGenre;

    private Genre testGenreWithId;

    private GenreDTO albumDTO;

    final long id = 1L;
    
    private GenreDTO mapToDTO(Genre genre) {
        return this.modelMapper.map(genre, GenreDTO.class);
    }

    @BeforeEach
    public void init() {
        this.genre = new ArrayList<>();
        this.genre.add(testGenre);
        this.testGenre = new Genre("Rock");
        this.testGenreWithId = new Genre(id, testGenre.getName(),testGenre.getDescription(),testGenre.getAlbums());
        this.testGenreWithId.setId(id);
        this.albumDTO = new ModelMapper().map(testGenreWithId, GenreDTO.class);
    }

    @Test
    public void createGenreTest() {
        when(this.repo.save(testGenre)).thenReturn(testGenreWithId);
        when(this.modelMapper.map(testGenreWithId, GenreDTO.class)).thenReturn(albumDTO);

        assertEquals(this.albumDTO, this.service.create(testGenre));

        verify(this.repo, times(1)).save(this.testGenre);
    }

 
    @Test
    void ReadByIdTest() {
        assertThat(this.albumDTO)
               .isEqualTo(this.service.read(this.id));
        assertThat(this.service.read(this.testGenreWithId.getId()))
        .isEqualTo(this.mapToDTO(this.testGenreWithId));
    }

    @Test
    void ReadAllGenreTest() {
        assertThat(this.service.read())
                .isEqualTo(Stream.of(this.mapToDTO(testGenreWithId))
                        .collect(Collectors.toList()));
    }
    
    @Test
    void DeleteTest() {
        assertThat(this.service.delete(this.id)).isTrue();
    }


}
