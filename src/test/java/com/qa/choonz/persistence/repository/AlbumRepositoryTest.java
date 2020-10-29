package com.qa.choonz.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;

@SpringBootTest
public class AlbumRepositoryTest {
	
	@Autowired
	private AlbumRepository repo;
    
	@Test
	void readArtistTest() {
		List<Album> repoL = this.repo.readArtist(1l);
		for(Album album:repoL) {
			assertThat(album.getArtist().getId() == 1l);
		}
	}
	@Test
	void readGenreTest() {
		List<Album> repoL = this.repo.readGenre(1l);
		for(Album album:repoL) {
			assertThat(album.getGenre().getId() == 1l);
		}
	}
  
}
