package com.qa.choonz.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
	
	@Query(value ="SELECT * FROM genre ORDER BY id DESC", nativeQuery = true)
    List<Genre> findAllDesc();
	
	@Query(value ="SELECT * FROM genre ORDER BY name", nativeQuery = true)
    List<Genre> orderByName();
	
	@Query(value ="SELECT * FROM genre ORDER BY name DESC", nativeQuery = true)
    List<Genre> orderByNameDesc();
	
}
