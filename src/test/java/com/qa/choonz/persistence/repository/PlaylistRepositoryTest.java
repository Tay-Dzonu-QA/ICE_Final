package com.qa.choonz.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;

@SpringBootTest
public class PlaylistRepositoryTest {
	
	@Autowired
	private PlaylistRepository repo;
    
    @Test
	void readUserPlaylistsTest() {
		List<Playlist> repoL = this.repo.readUserPlaylists(1l);
		for(Playlist PL:repoL) {
			assertThat(PL.getUser().getId() == 1l);
		}
	
	}
  
}
