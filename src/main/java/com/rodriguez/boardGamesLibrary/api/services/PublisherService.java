package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.models.Publisher;
import com.rodriguez.boardGamesLibrary.api.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional(readOnly = true)
    public List<Publisher> findAll(){
        return (List<Publisher>) publisherRepository.findAll();
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
        publisherRepository.deleteById(id);
    }
}
