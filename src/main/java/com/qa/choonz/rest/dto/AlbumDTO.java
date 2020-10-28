package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;

public class AlbumDTO {
	
    private Long id;
    private String name;
    private List<TrackDTO> tracks = new ArrayList<>();

    private String cover;
    private Genre genre;
    private Artist artist;

    public AlbumDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public AlbumDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


    public AlbumDTO(long id, String name, List<TrackDTO> tracks, Artist artist, Genre genre, String cover) {

        super();
        this.id = id;
        this.name = name;
        this.tracks = tracks;
        this.artist = artist;
        this.genre = genre;
        this.cover = cover;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<TrackDTO> getTracks() {

        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }


    public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlbumDTO)) {
            return false;
        }
        AlbumDTO other = (AlbumDTO) obj;

        return Objects.equals(artist, other.artist) && Objects.equals(cover, other.cover)
                && Objects.equals(genre, other.genre) && id == other.id && Objects.equals(name, other.name)
                && Objects.equals(tracks, other.tracks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tracks, cover,genre,artist);
    }
}
