package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;

public class AlbumDTO {

    private Long id;
    private String name;
    private List<TrackDTO> tracks;
    private String cover;
    private Genre genre;
    private Artist artist;

    public AlbumDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AlbumDTO(long id, String name, List<TrackDTO> tracks, String cover, Genre genre,Artist artist) {
        super();
        this.id = id;
        this.name = name;
        this.tracks = tracks;
        this.cover = cover;
        this.genre = genre;
        this.artist = artist;
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
    public int hashCode() {
        return Objects.hash( cover, id, name, tracks,genre,artist);
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
        return  Objects.equals(cover, other.cover) && id == other.id 
        		&& Objects.equals(name, other.name) && Objects.equals(tracks, other.tracks)
        		&& Objects.equals(genre, other.genre) && Objects.equals(artist, other.artist);
    }

}
