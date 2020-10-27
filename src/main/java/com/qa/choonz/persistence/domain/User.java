package com.qa.choonz.persistence.domain;


import javax.persistence.*;
import javax.validation.constraints.Min;
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
    private String userName;

    @NotNull
    @Column
    @Size(min = 8)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Playlist> playlists = new ArrayList<>();

    public User(){
        super();
    }
    public User(Long id, @NotNull @Size(max = 100) String userName,
                @NotNull @Size(max = 100) String password, List<Playlist> playlists){
        super();
        this.id = id;
        this.password = password;
        this.playlists = playlists;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                userName.equals(user.userName) &&
                password.equals(user.password) &&
                playlists.equals(user.playlists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, playlists);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", playlists=" + playlists +
                '}';
    }
}
