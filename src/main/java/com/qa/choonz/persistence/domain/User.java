package com.qa.choonz.persistence.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;


    @NotNull
    @Column(unique = true)
    @Size(min = 8)
    private String username;

    @NotNull
    @Column
    @Size(min = 8)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Playlist> playlists = new ArrayList<>();

    public User(){
        super();
    }
    
    public User(@NotNull @Size(max = 100) String username){
        super();
        this.username = username;
    }
    public User(@NotNull @Size(max = 100) String username,@NotNull @Size(max = 100) String password){
        super();
        this.username = username;
        this.password =password;
    }
    
    public User(Long id,@NotNull @Size(max = 100) String username){
        super();
        this.id = id;
        this.username = username;
    }
       
    
    public User(Long id, @NotNull @Size(max = 100) String username,
                @NotNull @Size(max = 100) String password, List<Playlist> playlists){
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

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(username, other.username) &&  Objects.equals(id, other.id)
                && Objects.equals(password, other.password) && Objects.equals(playlists, other.playlists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, playlists);
    }

}
