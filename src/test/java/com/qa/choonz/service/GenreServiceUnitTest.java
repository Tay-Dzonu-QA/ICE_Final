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

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;


@SpringBootTest
public class GenreServiceUnitTest {
	
	   @Autowired
	    private GenreService service;

	    @MockBean
	    private GenreRepository repository;

	    @MockBean
	    private ModelMapper modelMapper;


	    private List<Genre> genres;
	    private GenreDTO genreDTO;
	    private Genre testGenreWithId;
	    private Genre testGenre;

	    final Long id = 1L;
	    final String testName = "Funky";

	    @BeforeEach
	    void init() {
	    	this.genres = new ArrayList<>();
	        this.testGenre = new Genre(testName);
	        this.genres.add(testGenre);
	        this.testGenreWithId = new Genre(testGenre.getName());
	        this.testGenreWithId.setId(id);
	        this.genreDTO = modelMapper.map(testGenreWithId, GenreDTO.class);
	    }

	    @Test
	    void createTest() {

	        when(this.repository.save(this.testGenre)).thenReturn(this.testGenreWithId);
	        when(this.modelMapper.map(this.testGenreWithId, GenreDTO.class)).thenReturn(this.genreDTO);

	        GenreDTO expec = this.genreDTO;
	        
	        GenreDTO real = this.service.create(this.testGenre);
	        
	        assertThat(expec).isEqualTo(real);

	        verify(this.repository, times(1)).save(this.testGenre);
	    }

	    @Test
	    void readOneTest() {

	        when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testGenreWithId));
	        when(this.modelMapper.map(this.testGenreWithId, GenreDTO.class)).thenReturn(this.genreDTO);

	        assertThat(this.genreDTO).isEqualTo(this.service.read(this.id));

	        verify(this.repository, times(1)).findById(this.id);
	    }

	    @Test
	    void readAllTest() {

	        when(this.repository.findAll()).thenReturn(this.genres);
	        when(this.modelMapper.map(this.testGenreWithId, GenreDTO.class)).thenReturn(this.genreDTO);

	        assertThat(this.service.read().isEmpty()).isFalse();

	        verify(this.repository, times(1)).findAll();
	    }

	    @Test
	    void updateTest() {
	    	
	    	Genre genre = new Genre(testName);
	    	
	        genre.setId(this.id);

	        GenreDTO genreDTO = new GenreDTO(id, testName);

	        Genre newGenre = new Genre(genreDTO.getName());
	        newGenre.setId(this.id);

	        GenreDTO newGenreDTO = new GenreDTO(this.id, newGenre.getName());

	        when(this.repository.findById(this.id)).thenReturn(Optional.of(genre));

	        when(this.repository.save(genre)).thenReturn(newGenre);

	        when(this.modelMapper.map(newGenre, GenreDTO.class)).thenReturn(newGenreDTO);

	        assertThat(newGenreDTO).isEqualTo(this.service.update(genreDTO, this.id));

	        verify(this.repository, times(1)).findById(1L);
	        verify(this.repository, times(1)).save(newGenre);
	    }

	    @Test
	    void deleteTest() {

	    	when(this.repository.existsById(id)).thenReturn(true, false);
			
			assertThat(this.service.delete(id)).isFalse();
			
			verify(this.repository, times(1)).deleteById(id);
			verify(this.repository, times(1)).existsById(id);
	    }
}
