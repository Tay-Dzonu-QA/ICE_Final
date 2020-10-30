package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArtistDTO {

    private Long id;
    private String name;

    private List<AlbumDTO> albums= new ArrayList<>();


    public ArtistDTO() {
        super();
        // TODO Auto-generated constructor stub
    }


    public ArtistDTO(Long id, String name, List<AlbumDTO> albums) {

        super();
        this.id = id;
        this.name = name;
        this.albums = albums;
    }
    public ArtistDTO(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
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

    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDTO> albums) {
        this.albums = albums;
    }

    @Override
    public int hashCode() {
        return Objects.hash(albums, id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArtistDTO)) {
            return false;
        }
        ArtistDTO other = (ArtistDTO) obj;
        System.out.println(id == other.id);
        return Objects.equals(albums, other.albums) && Objects.equals(id, other.id) && Objects.equals(name, other.name);
    }
}
