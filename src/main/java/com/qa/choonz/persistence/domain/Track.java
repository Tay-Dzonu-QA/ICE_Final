package com.qa.choonz.persistence.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("tracks")
    private Album album;

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH
    })
    @JsonIgnoreProperties({"tracks","user"})
    private List<Playlist> playlists= new ArrayList<>();

    // in seconds
    private int duration;

    private String lyrics;

    public Track() {
        super();
    }


    public Track(@NotNull @Size(max =100) String name) {
    	super();
    	this.name = name;
    }
    public Track(@NotNull @Size(max =100) String name,int duration) {
    	super();
    	this.name = name;
    	this.duration = duration;
    }
    
    public Track(Long id, @NotNull @Size(max = 100) String name, Album album, List<Playlist> playlists, int duration, String lyrics) {
        super();
        this.id = id;
        this.name = name;
        this.album = album;
        this.playlists = playlists;
        this.duration = duration;
        this.lyrics = lyrics;
    }
    
    public void addPlaylist(Playlist playlist) {
    	this.playlists.add(playlist);
    }
    public void removePlaylist(Playlist playlist) {
    	this.playlists.remove(playlist);
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Playlist> getPlaylist() {
        return playlists;
    }

    public void setPlaylist(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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
        return Objects.hash(album, duration, id, lyrics, name, playlists);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Track)) {
            return false;
        }
        Track other = (Track) obj;
        return Objects.equals(album, other.album) && duration == other.duration && id == other.id
                && Objects.equals(lyrics, other.lyrics) && Objects.equals(name, other.name)
                && Objects.equals(playlists, other.playlists);
    }

}
