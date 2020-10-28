package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private List<PlaylistDTO> playlist;

    public UserDTO(){
        super();
    }

    public UserDTO(Long id, String username, String password, List<PlaylistDTO> playlist){
        this.id = id;
        this.username = username;
        this.password = password;
        this.playlist = playlist;


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
        return playlist;
    }

    public void setPlaylist(List<PlaylistDTO> playlist) {
        this.playlist = playlist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id.equals(userDTO.id) &&
                username.equals(userDTO.username) &&
                password.equals(userDTO.password) &&
                playlist.equals(userDTO.playlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, playlist);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", playlist=" + playlist +
                '}';
    }
}
