package com.qa.choonz.rest.dto;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;

public class TrackDTO {

    private Long id;
    private String name;
    private Album album;
    private Playlist playlist;
    private Integer duration;
    private String lyrics;

    public TrackDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

}
