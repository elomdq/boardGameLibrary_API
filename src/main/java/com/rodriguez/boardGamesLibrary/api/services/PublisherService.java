package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.dtos.PublisherDto;
import com.rodriguez.boardGamesLibrary.api.mappers.PublisherMapper;
import com.rodriguez.boardGamesLibrary.api.models.Publisher;
import com.rodriguez.boardGamesLibrary.api.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Publisher byId(Long id){
        return publisherRepository.findById(id).orElse(null);
    }

    @Transactional
    public Publisher save(Publisher publisher){
        return publisherRepository.save(publisher);
    }

    @Transactional
    public void deleteById(Long id){
        Publisher publisher = this.byId(id);
        if(publisher != null){
            publisher.getGames().forEach(c->c.getPublishers().remove(publisher));
        }
        publisherRepository.deleteById(id);
    }
}
