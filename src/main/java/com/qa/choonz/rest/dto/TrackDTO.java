package com.qa.choonz.rest.dto;

import java.util.Objects;

public class TrackDTO {

    private long id;
    private String name;
    private int duration;
    private String lyrics;

    public TrackDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public TrackDTO(Long id, String name, Integer duration, String lyrics) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.lyrics = lyrics;
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

    @Override
    public int hashCode() {
        return Objects.hash( id, name, duration,lyrics);
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
                && id == other.id && Objects.equals(name, other.name) ;
    }

}
