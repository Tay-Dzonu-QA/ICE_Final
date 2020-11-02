package com.qa.choonz.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Artist;

@SpringBootTest
public class ArtistRepositoryTest {
	
	@Autowired
	private ArtistRepository repo;
	
	private final Artist TestArtist1 = new Artist("ABC");
	private final Artist TestArtist2 = new Artist("XYZ");
	private final Artist TestArtist3 = new Artist("MNO");
	
	private List<Artist> results;
    
    
    @BeforeEach
    void init() {
	   this.repo.deleteAll();
	   this.results = new ArrayList<>();
	   this.repo.save(this.TestArtist1);
	   this.repo.save(this.TestArtist2);
	   this.repo.save(this.TestArtist3);
    }
    
    @Test
	void findAllDescTest() {
		List<Artist> repoL = this.repo.findAllDesc();
		this.results.add(TestArtist3);
		this.results.add(TestArtist2);
		this.results.add(TestArtist1);
		assertThat(repoL.get(0).getName()).isEqualTo(this.results.get(0).getName());
		assertThat(repoL.get(1).getName()).isEqualTo(this.results.get(1).getName());
		assertThat(repoL.get(2).getName()).isEqualTo(this.results.get(2).getName());
	}
    @Test
	void orderByNameTest() {
		List<Artist> repoL = this.repo.orderByName();
		this.results.add(TestArtist1);
		this.results.add(TestArtist3);
		this.results.add(TestArtist2);
		assertThat(repoL.get(0).getName()).isEqualTo(this.results.get(0).getName());
		assertThat(repoL.get(1).getName()).isEqualTo(this.results.get(1).getName());
		assertThat(repoL.get(2).getName()).isEqualTo(this.results.get(2).getName());
	}
    @Test
	void orderByNameDescTest() {
		List<Artist> repoL = this.repo.orderByNameDesc();
		this.results.add(TestArtist2);
		this.results.add(TestArtist3);
		this.results.add(TestArtist1);
		assertThat(repoL.get(0).getName()).isEqualTo(this.results.get(0).getName());
		assertThat(repoL.get(1).getName()).isEqualTo(this.results.get(1).getName());
		assertThat(repoL.get(2).getName()).isEqualTo(this.results.get(2).getName());
	}

}
