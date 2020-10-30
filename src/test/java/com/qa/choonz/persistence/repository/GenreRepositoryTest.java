package com.qa.choonz.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Genre;

@SpringBootTest
public class GenreRepositoryTest {
	
	@Autowired
	private GenreRepository repo;
	
	private final Genre TestGenre1 = new Genre("ABC","a");
	private final Genre TestGenre2 = new Genre("XYZ","b");
	private final Genre TestGenre3 = new Genre("MNO","c");
	
	private List<Genre> results;
    
    
    @BeforeEach
    void init() {
	   this.repo.deleteAll();
	   this.results = new ArrayList<>();
	   this.repo.save(this.TestGenre1);
	   this.repo.save(this.TestGenre2);
	   this.repo.save(this.TestGenre3);
    }
    
    @Test
	void findAllDescTest() {
		List<Genre> repoL = this.repo.findAllDesc();
		this.results.add(TestGenre3);
		this.results.add(TestGenre2);
		this.results.add(TestGenre1);
		assertThat(repoL.get(0).getName()).isEqualTo(this.results.get(0).getName());
		assertThat(repoL.get(1).getName()).isEqualTo(this.results.get(1).getName());
		assertThat(repoL.get(2).getName()).isEqualTo(this.results.get(2).getName());
	}
    @Test
	void orderByNameTest() {
		List<Genre> repoL = this.repo.orderByName();
		this.results.add(TestGenre1);
		this.results.add(TestGenre3);
		this.results.add(TestGenre2);
		assertThat(repoL.get(0).getName()).isEqualTo(this.results.get(0).getName());
		assertThat(repoL.get(1).getName()).isEqualTo(this.results.get(1).getName());
		assertThat(repoL.get(2).getName()).isEqualTo(this.results.get(2).getName());
	}
    @Test
	void orderByNameDescTest() {
		List<Genre> repoL = this.repo.orderByNameDesc();
		this.results.add(TestGenre2);
		this.results.add(TestGenre3);
		this.results.add(TestGenre1);
		assertThat(repoL.get(0).getName()).isEqualTo(this.results.get(0).getName());
		assertThat(repoL.get(1).getName()).isEqualTo(this.results.get(1).getName());
		assertThat(repoL.get(2).getName()).isEqualTo(this.results.get(2).getName());
	}

}
