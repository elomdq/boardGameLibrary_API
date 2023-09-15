package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.dtos.PublisherDto;
import com.rodriguez.boardGamesLibrary.api.mappers.PublisherMapper;
import com.rodriguez.boardGamesLibrary.api.models.Publisher;
import com.rodriguez.boardGamesLibrary.api.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherMapper publisherMapper;

    @Transactional(readOnly = true)
    public Set<PublisherDto> findAll(){
        Set<PublisherDto> publishersDtos = publisherMapper.toDtos(Set.copyOf(
                StreamSupport
                        .stream(publisherRepository.findAll().spliterator(),false)
                        .collect(Collectors.toList())
        ));
        return publishersDtos;
    }

    @Transactional(readOnly = true)
    public PublisherDto findById(Long id){
        return publisherMapper.toDto(publisherRepository.findById(id).orElse(null));
    }

    @Transactional
    public PublisherDto save(PublisherDto publisher){
        return publisherMapper.toDto(publisherRepository.save(publisherMapper.toEntity(publisher)));
    }

    @Transactional
    public void deleteById(Long id){
        Publisher publisher = publisherRepository.findById(id).get();
        if(publisher != null){
            publisher.clearBoardGames();
        }
        publisherRepository.deleteById(id);
    }
}
