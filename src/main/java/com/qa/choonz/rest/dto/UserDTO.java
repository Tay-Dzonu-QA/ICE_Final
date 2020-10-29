package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<PlaylistDTO> playlists;

    public UserDTO(){
        super();
    }
    
    public UserDTO(Long id, String username) {
    	super();
    	this.id = id;
    	this.username = username;
    	
    }

    public UserDTO(Long id, String username, String password, List<PlaylistDTO> playlists){
        super();
    	this.id = id;
        this.username = username;
        this.password = password;
        this.playlists = playlists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PlaylistDTO> getPlaylist() {
        return playlists;
    }

    public void setPlaylist(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Track)) {
            return false;
        }
        UserDTO other = (UserDTO) obj;
        return Objects.equals(username, other.username) &&  Objects.equals(id, other.id)
                && Objects.equals(password, other.password) && Objects.equals(playlists, other.playlists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, playlists);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", playlist=" + playlists +
                '}';
    }
}
