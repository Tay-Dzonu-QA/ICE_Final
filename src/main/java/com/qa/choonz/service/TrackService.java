package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.utils.SAPIBeanUtils;

@Service
@Transactional
public class TrackService {

    private TrackRepository repo;
    private ModelMapper mapper;

    public TrackService(TrackRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private TrackDTO mapToDTO(Track track) {
        return this.mapper.map(track, TrackDTO.class);
    }

    public TrackDTO create(Track track) {
        Track created = this.repo.save(track);
        return this.mapToDTO(created);
    }

    public List<TrackDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TrackDTO read(long id) {
        Track found = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
        return this.mapToDTO(found);
    }
    public List<TrackDTO> readAlbum(Long id) {
    	return this.repo.readAlbum(id).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TrackDTO update(TrackDTO track, long id) {
        Track toUpdate = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
        SAPIBeanUtils.mergeNotNull(track,toUpdate);

        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
