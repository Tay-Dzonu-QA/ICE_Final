package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.utils.SAPIBeanUtils;

@Service
@Transactional
public class AlbumService {

    private AlbumRepository repo;
    private ModelMapper mapper;

    public AlbumService(AlbumRepository repo, ModelMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    private AlbumDTO mapToDTO(Album album) {
        return this.mapper.map(album, AlbumDTO.class);
    }

    public AlbumDTO create(Album album) {
        Album created = this.repo.save(album);
        return this.mapToDTO(created);
    }

    public List<AlbumDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public AlbumDTO read(Long id) {
        Album found = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
        return this.mapToDTO(found);
    }
    public List<AlbumDTO> readArtist(Long id) {
    	return this.repo.readArtist(id).stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    
    public List<AlbumDTO> readGenre(Long id) {
    	return this.repo.readGenre(id).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public AlbumDTO update(AlbumDTO album, Long id) {
        Album toUpdate = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
        SAPIBeanUtils.mergeNotNull(album,toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
