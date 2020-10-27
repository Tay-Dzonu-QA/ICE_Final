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

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;


@SpringBootTest
public class AlbumServiceUnitTest {
	
	 @Autowired
	    private AlbumService service;

	    @MockBean
	    private AlbumRepository repository;

	    @MockBean
	    private ModelMapper modelMapper;


	    private List<Album> albums;
	    private Album testAlbumWithId;
	    private AlbumDTO albumDTO;
	    private Album testAlbum;	   	   

	    final Long id = 1L;
	    final String testName = "Goats Head Soup";

	    @BeforeEach
	    void init() {
	        this.albums = new ArrayList<>();
	        this.testAlbum = new Album(testName);
	        this.testAlbumWithId.setId(id);
	        this.testAlbumWithId = new Album(testAlbum.getName());
	        this.albums.add(testAlbum);	       
	        this.albumDTO = modelMapper.map(testAlbumWithId, AlbumDTO.class);
	    }

	    @Test
	    void createTest() {

	        when(this.repository.save(this.testAlbum)).thenReturn(this.testAlbumWithId);
	        when(this.modelMapper.map(this.testAlbumWithId, AlbumDTO.class)).thenReturn(this.albumDTO);

	        AlbumDTO expec = this.albumDTO;
	        AlbumDTO real = this.service.create(this.testAlbum);
	        assertThat(expec).isEqualTo(real);

	        verify(this.repository, times(1)).save(this.testAlbum);
	    }

	    @Test
	    void readOneTest() {

	        when(this.repository.findById(this.id)).thenReturn(Optional.of(this.testAlbumWithId));
	        when(this.modelMapper.map(this.testAlbumWithId, AlbumDTO.class)).thenReturn(this.albumDTO);

	        assertThat(this.albumDTO).isEqualTo(this.service.read(this.id));

	        verify(this.repository, times(1)).findById(this.id);
	    }

	    @Test
	    void readAllTest() {

	        when(this.repository.findAll()).thenReturn(this.albums);
	        when(this.modelMapper.map(this.testAlbumWithId, AlbumDTO.class)).thenReturn(this.albumDTO);

	        assertThat(this.service.read().isEmpty()).isFalse();

	        verify(this.repository, times(1)).findAll();
	    }

	    @Test
	    void updateTest() {
	    	
	        Album al = new Album("Sticky Fingers");
	        
	        al.setId(this.id);

	        AlbumDTO albumDTO = new AlbumDTO(id, "Sticky Fingers");

	        Album updatedAlbum = new Album(albumDTO.getName());
	        
	        updatedAlbum.setId(this.id);

	        AlbumDTO updatedAlbumDTO = new AlbumDTO(this.id, updatedAlbum.getName());

	        when(this.repository.findById(this.id)).thenReturn(Optional.of(al));
	        when(this.repository.save(al)).thenReturn(updatedAlbum);
	        when(this.modelMapper.map(updatedAlbum, AlbumDTO.class)).thenReturn(updatedAlbumDTO);

	        assertThat(updatedAlbumDTO).isEqualTo(this.service.update(albumDTO, this.id));

	        verify(this.repository, times(1)).findById(1L);
	        verify(this.repository, times(1)).save(updatedAlbum);
	    }

	    @Test
	    void deleteTest() {

	    	when(this.repository.existsById(id)).thenReturn(true, false);
			
			assertThat(this.service.delete(id)).isFalse();
			
			verify(this.repository, times(1)).deleteById(id);
			verify(this.repository, times(1)).existsById(id);
	    }


}
