package com.qa.choonz.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
	
	@Query(value ="SELECT * FROM track Where album_id=?1", nativeQuery = true)
    List<Track> readAlbum(Long id);

}
