package com.qa.choonz.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Track;

@SpringBootTest
public class TrackRepositoryTest {
	
	@Autowired
	private TrackRepository repo;
    
    @Test
	void readAlbumTest() {
		List<Track> repoL = this.repo.readAlbum(2l);
		for(Track track:repoL) {
			assertThat(track.getAlbum().getId() == 2l);
		}

	}
  
}
