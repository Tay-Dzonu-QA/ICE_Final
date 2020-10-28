package com.qa.choonz.rest.dto;

import java.util.Objects;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;

import java.util.Objects;

public class TrackDTO {

    private Long id;
    private String name;
    private Integer duration;
    private String lyrics;
    private Album album;

    public TrackDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
	public TrackDTO(Long id, String name, Integer duration, String lyrics,Album album) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.lyrics = lyrics;
		this.album=album;
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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}
	

    public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
    public int hashCode() {
        return Objects.hash( id, name, duration,lyrics,album);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TrackDTO)) {
            return false;
        }
        TrackDTO other = (TrackDTO) obj;
        return Objects.equals(duration, other.duration) && Objects.equals(lyrics, other.lyrics)
                && id == other.id && Objects.equals(name, other.name)
                		&& Objects.equals(album, other.album);
    }
    


}
