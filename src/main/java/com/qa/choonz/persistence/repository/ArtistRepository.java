package com.qa.choonz.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
	
	@Query(value ="SELECT * FROM artist ORDER BY id DESC", nativeQuery = true)
    List<Artist> findAllDesc();
	
	@Query(value ="SELECT * FROM artist ORDER BY name", nativeQuery = true)
    List<Artist> orderByName();
	
	@Query(value ="SELECT * FROM artist ORDER BY name DESC", nativeQuery = true)
    List<Artist> orderByNameDesc();

}
