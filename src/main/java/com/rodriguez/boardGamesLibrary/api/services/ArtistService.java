package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.models.Artist;
import com.rodriguez.boardGamesLibrary.api.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Transactional(readOnly = true)
    public List<Artist> findAll(){
        return (List<Artist>) artistRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Artist byId(Long id){
        return artistRepository.findById(id).orElse(null);
    }

    @Transactional
    public Artist save(Artist artist){
        return artistRepository.save(artist);
    }

    @Transactional
    public void delete(Long id){
        artistRepository.deleteById(id);
    }
}
