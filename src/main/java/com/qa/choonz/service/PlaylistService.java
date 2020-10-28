package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.utils.SAPIBeanUtils;

@Service
public class PlaylistService {

    private PlaylistRepository repo;
    private TrackRepository trackRepo;
    private ModelMapper mapper;

    public PlaylistService(PlaylistRepository repo, ModelMapper mapper,TrackRepository trackRepo) {
        super();
        this.repo = repo;
        this.mapper = mapper;
        this.trackRepo=trackRepo;
    }

    private PlaylistDTO mapToDTO(Playlist playlist) {
        return this.mapper.map(playlist, PlaylistDTO.class);
    }

    public PlaylistDTO create(Playlist playlist) {
        Playlist created = this.repo.save(playlist);
        return this.mapToDTO(created);
    }

    public List<PlaylistDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PlaylistDTO read(Long id) {
        Playlist found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        return this.mapToDTO(found);
    }
    
    public List<PlaylistDTO> readUserPlaylists(Long id) {
        return this.repo.readUserPlaylists(id).stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public PlaylistDTO update(PlaylistDTO playlist, Long id) {
        Playlist toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
        SAPIBeanUtils.mergeNotNull(playlist,toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }
    
    public PlaylistDTO addTrack(Long playlistId,Long TrackId) {
    	Playlist toUpdate = this.repo.findById(playlistId).orElseThrow(PlaylistNotFoundException::new);
    	Track toAdd = this.trackRepo.findById(TrackId).orElseThrow(TrackNotFoundException::new);
    	toUpdate.addTrack(toAdd);
    	this.trackRepo.save(toAdd);
    	return this.mapToDTO(this.repo.save(toUpdate));
    }
    
    public PlaylistDTO removeTrack(Long playlistId,Long TrackId) {
    	Playlist toUpdate = this.repo.findById(playlistId).orElseThrow(PlaylistNotFoundException::new);
    	Track toAdd = this.trackRepo.findById(TrackId).orElseThrow(TrackNotFoundException::new);
    	toUpdate.removeTrack(toAdd);
    	return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
