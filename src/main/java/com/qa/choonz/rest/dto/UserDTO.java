package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private List<PlaylistDTO> playlists;

    public UserDTO(){
        super();
    }
    
    public UserDTO(Long id, String username) {
    	super();
    	this.id = id;
    	this.username = username;   	
    }
    

	public UserDTO(Long id, String username, String password) {
    	super();
    	this.id = id;
    	this.username = username;  
    	this.password = password;
    }
    public UserDTO(Long id, String username, String password,String name) {
    	super();
    	this.id = id;
    	this.username = username;  
    	this.password = password;
    	this.name=name;
    }

    public UserDTO(Long id, String username, String password,String name, List<PlaylistDTO> playlists){
        super();
    	this.id = id;
        this.username = username;
        this.password = password;
        this.name=name;
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

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public List<PlaylistDTO> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<PlaylistDTO> playlists) {
		this.playlists = playlists;
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDTO)) {
            return false;
        }
        UserDTO other = (UserDTO) obj;
        return Objects.equals(username, other.username) &&  Objects.equals(id, other.id) && Objects.equals(name, other.name)
                && Objects.equals(password, other.password) && Objects.equals(playlists, other.playlists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password,name, playlists);
    }

}
