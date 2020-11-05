package com.qa.choonz.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 500)
    @Column(unique = true)
    private String description;

    @NotNull
    @Size(max = 1000)
    @Column(unique = true)
    private String artwork;

    @ManyToMany(mappedBy = "playlists",cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JsonIgnoreProperties({"playlist","album"})
    private List<Track> tracks= new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("playlists")
    private User user;

    public Playlist() {
        super();
    }
    
    public Playlist(@NotNull @Size(max = 100) String name) {
    	super();
    	this.name = name;
    }
    
    public Playlist(@NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
            @NotNull @Size(max = 1000) String artwork) {
        super();
        this.name = name;
        this.description = description;
        this.artwork = artwork;
    }
    
    public Playlist(String name, String description, String artwork, List<Track> tracks) {
        super();
        this.name = name;
        this.description = description;
        this.artwork = artwork;
        this.tracks = tracks;
    }

    public Playlist(Long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
            @NotNull @Size(max = 1000) String artwork, List<Track> tracks, User user) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.artwork = artwork;
        this.tracks = tracks;
        this.user = user;
    }
    
    public void addTrack(Track track) {
    	this.tracks.add(track);
    	track.getPlaylist().add(this);
    }
    public void removeTrack(Track track) {
    	this.tracks.remove(track);
    	track.getPlaylist().remove(this);
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

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtwork() {
        return artwork;
    }


    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public List<Track> getTracks() {
        return tracks;
    }

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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Playlist)) {
            return false;
        }
        Playlist other = (Playlist) obj;
        return Objects.equals(name, other.name) &&  id.equals(other.id) && Objects.equals(user, other.user)
                && Objects.equals(artwork, other.artwork) && Objects.equals(tracks, other.tracks)
                && Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, artwork, tracks, user);
    }
}
