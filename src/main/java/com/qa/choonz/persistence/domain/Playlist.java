package com.qa.choonz.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @ManyToMany(mappedBy = "playlists")
    @JsonIgnoreProperties("playlists")
    private List<Track> tracks;

    @ManyToOne
    @JsonIgnoreProperties("playlist")
    private User user;

    public Playlist() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
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
    }
    public void removeTrack(Track track) {
    	this.tracks.remove(track);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return id == playlist.id &&
                name.equals(playlist.name) &&
                description.equals(playlist.description) &&
                artwork.equals(playlist.artwork) &&
                tracks.equals(playlist.tracks) &&
                user.equals(playlist.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, artwork, tracks, user);
    }
}
