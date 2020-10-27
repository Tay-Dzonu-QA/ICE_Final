package com.qa.choonz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
	
	@Query(value ="SELECT name FROM genre Where id=?1", nativeQuery = true)
    String getGenreName(Long id);

}
