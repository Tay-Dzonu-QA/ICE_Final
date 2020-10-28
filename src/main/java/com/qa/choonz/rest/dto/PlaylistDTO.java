package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;

public class PlaylistDTO {

    private Long id;
    private String name;
    private String description;
    private String artwork;
    private List<Track> tracks = new ArrayList<>();
    private User user;

    public PlaylistDTO() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public PlaylistDTO(Long id, String name) {
    	super();
    	this.id = id;
    	this.name = name;
    }
    
    public PlaylistDTO(String name, String description, String artwork, List<TrackDTO> tracks) {
        super();
        this.name = name;
        this.description = description;
        this.artwork = artwork;
        this.tracks = tracks;
    }


    public PlaylistDTO(Long id, String name, String description, String artwork, List<Track> tracks,User user) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.artwork = artwork;
        this.tracks = tracks;
        this.user=user;
    }
    
    public void addTrack(Track track) {
    	this.tracks.add(track);
    }
    public void removeTrack(Track track) {
    	this.tracks.remove(track);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the artwork
     */
    public String getArtwork() {
        return artwork;
    }

    /**
     * @param artwork the artwork to set
     */
    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    /**
     * @return the tracks
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * @param tracks the tracks to set
     */
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
    

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public int hashCode() {
        return Objects.hash(artwork, description, id, name, tracks,user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaylistDTO)) {
            return false;
        }
        PlaylistDTO other = (PlaylistDTO) obj;
        return Objects.equals(artwork, other.artwork) && Objects.equals(description, other.description)
                && id == other.id && Objects.equals(name, other.name) && Objects.equals(tracks, other.tracks)
                && Objects.equals(user, other.user);
    }

}
