package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.dtos.PublisherDto;
import com.rodriguez.boardGamesLibrary.api.models.Publisher;
import com.rodriguez.boardGamesLibrary.api.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/publishers")
public class PublisherRestController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PublisherDto>> findAll(){
        List<PublisherDto> publisherDtos = null;
        try{
             publisherDtos = List.copyOf(publisherService.findAll());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(publisherDtos);
    }

    @GetMapping(path="/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher byId(@PathVariable Long id){
        return publisherService.byId(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher create(@RequestBody Publisher publisher){
        return publisherService.save(publisher);
    }

    @PutMapping(path="/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Publisher update(@PathVariable Long id, @RequestBody Publisher publisher){
        Publisher currentPublisher = publisherService.byId(id);

        if(currentPublisher!=null){
            crossData(currentPublisher,publisher);
            return publisherService.save(currentPublisher);
        }

        return null;
    }

    private void crossData(Publisher currentPublisher, Publisher newPublisher){
        currentPublisher.setName(newPublisher.getName());
        currentPublisher.setWeb(newPublisher.getWeb());
        currentPublisher.setCountry(newPublisher.getCountry());
        currentPublisher.setGames(newPublisher.getGames());
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        publisherService.deleteById(id);
    }

}
