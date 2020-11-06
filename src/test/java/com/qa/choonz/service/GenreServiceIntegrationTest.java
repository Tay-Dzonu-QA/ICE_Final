package com.qa.choonz.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;

@SpringBootTest
public class GenreServiceIntegrationTest {
	
	@Autowired
    private GenreService service;

	@Autowired
    private GenreRepository repo;

    @Autowired
    private ModelMapper modelMapper;


    private Genre testGenre;
    private Genre testGenreWithId;


    
    private GenreDTO mapToDTO(Genre genre) {
        return this.modelMapper.map(genre, GenreDTO.class);
    }

    @BeforeEach
    public void init() {
    	this.repo.deleteAll();
        this.testGenre = new Genre("White Genre","hello");
        this.testGenreWithId = this.repo.save(this.testGenre);
    }

    @Test
    public void createGenreTest() {
        assertThat(this.mapToDTO(this.testGenreWithId))
        .isEqualTo(this.service.create(testGenre));
    }

 
    @Test
    void ReadByIdTest() {
        assertThat(this.service.read(this.testGenreWithId.getId()))
        .isEqualTo(this.mapToDTO(this.testGenreWithId));
    }

    @Test
    void ReadAllGenresTest() {
    	assertThat(this.service.read())
        .isEqualTo(Stream.of(this.mapToDTO(testGenreWithId)).collect(Collectors.toList()));
    }
    @Test
    void testUpdate() {
    	GenreDTO newGenre = new GenreDTO(null, "Tuesday","hello");
    	GenreDTO updatedGenre = new GenreDTO(this.testGenreWithId.getId(), newGenre.getName(),newGenre.getDescription());

        assertThat(this.service.update(newGenre, this.testGenreWithId.getId()))
            .isEqualTo(updatedGenre);
    }
    
    @Test
    void DeleteTest() {
        assertThat(this.service.delete(this.testGenreWithId.getId())).isTrue();
    }


}
