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

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;


@SpringBootTest
public class ArtistServiceUnitTest {
	

    @Autowired
    private ArtistService service;

    @MockBean
    private ArtistRepository repository;

    @MockBean
    private ModelMapper modelMapper;


    private List<Artist> artists;
    private ArtistDTO artistDTO;
    private Artist testArtistWithId;
    private Artist testArtist;

    final Long id = 1L;
    final String testName = "Lewis Capaldi";
	
	   @BeforeEach
	    void init() {
		   this.artists = new ArrayList<>();
	        this.testArtist = new Artist(testName);
	        this.artists.add(testArtist);
	        this.testArtistWithId = new Artist(testArtist.getName());
	        this.testArtistWithId.setId(id);
	        this.artistDTO = modelMapper.map(testArtistWithId, ArtistDTO.class);
	    }

	    @Test
	    void createTest() {

	        when(this.repository.save(this.testArtist)).thenReturn(this.testArtistWithId);
	        when(this.modelMapper.map(this.testArtistWithId, ArtistDTO.class)).thenReturn(this.artistDTO);

	        ArtistDTO expec = this.artistDTO;
	        ArtistDTO real = this.service.create(this.testArtist);
	        
	        assertThat(expec).isEqualTo(real);

	        verify(this.repository, times(1)).save(this.testArtist);
	    }

	    @Test
	    void readOneTest() {

	        when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testArtistWithId));
	        when(this.modelMapper.map(this.testArtistWithId, ArtistDTO.class)).thenReturn(this.artistDTO);

	        assertThat(this.artistDTO).isEqualTo(this.service.read(this.id));

	        verify(this.repository, times(1)).findById(this.id);
	    }

	    @Test
	    void readAllTest() {

	        when(this.repository.findAll()).thenReturn(this.artists);
	        when(this.modelMapper.map(this.testArtistWithId, ArtistDTO.class)).thenReturn(this.artistDTO);

	        assertThat(this.service.read().isEmpty()).isFalse();

	        verify(this.repository, times(1)).findAll();
	    }

	    @Test
	    void updateTest() {
	    	
	        Artist art = new Artist(testName);
	        
	        art.setId(this.id);

	        ArtistDTO artistDTO = new ArtistDTO(id, testName);

	        Artist newArtist = new Artist(artistDTO.getName());
	        
	        newArtist.setId(this.id);

	        ArtistDTO newArtistDTO = new ArtistDTO(this.id, newArtist.getName());

	        when(this.repository.findById(this.id)).thenReturn(Optional.of(art));
	        when(this.repository.save(art)).thenReturn(newArtist);
	        when(this.modelMapper.map(newArtist, ArtistDTO.class)).thenReturn(newArtistDTO);

	        assertThat(newArtistDTO).isEqualTo(this.service.update(artistDTO, this.id));

	        verify(this.repository, times(1)).findById(id);
	        verify(this.repository, times(1)).save(newArtist);
	    }

	    @Test
	    void deleteTest() {

	    	when(this.repository.existsById(id)).thenReturn(true, false);
			
			assertThat(this.service.delete(id)).isFalse();
			verify(this.repository, times(1)).deleteById(id);
			verify(this.repository, times(1)).existsById(id);
	    }

}
