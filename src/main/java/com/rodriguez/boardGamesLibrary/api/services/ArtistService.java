package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.dtos.ArtistDto;
import com.rodriguez.boardGamesLibrary.api.mappers.ArtistMapper;
import com.rodriguez.boardGamesLibrary.api.models.Artist;
import com.rodriguez.boardGamesLibrary.api.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistMapper artistMapper;

    @Transactional(readOnly = true)
    public Set<ArtistDto> findAll(){
        Set<ArtistDto> artistDtos = artistMapper.toDtos(Set.copyOf(
                StreamSupport
                        .stream(artistRepository.findAll().spliterator(),false)
                        .collect(Collectors.toList())
        ));
        return artistDtos;
    }

    @Transactional(readOnly = true)
    public ArtistDto findById(Long id){
        return artistMapper.toDto(artistRepository.findById(id).orElse(null));
    }

    @Transactional(readOnly = false)
    public ArtistDto save(ArtistDto artist){
        return artistMapper.toDto(artistRepository.save(artistMapper.toEntity(artist)));
    }

    @Transactional(readOnly = false)
    public void deleteById(Long id)  throws InterruptedException{
        Artist artist = artistRepository.findById(id).get();
        if(artist != null){
            artist.clearBoardGames();
        }
        artistRepository.deleteById(id);
    }
}
