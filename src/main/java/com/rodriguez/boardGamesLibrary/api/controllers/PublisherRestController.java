package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.dtos.PublisherDto;
import com.rodriguez.boardGamesLibrary.api.mappers.PublisherMapper;
import com.rodriguez.boardGamesLibrary.api.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/publishers")
public class PublisherRestController {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherMapper publisherMapper;

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
    public ResponseEntity<PublisherDto> findById(@PathVariable Long id){
        PublisherDto publisherDto;
        try{
            publisherDto = publisherService.findById(id);
            if(publisherDto==null){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(publisherDto);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PublisherDto> create(@RequestBody PublisherDto publisher){
        PublisherDto publisherDto;
        try{
            publisherDto = publisherService.save(publisher);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherDto);
    }

    @PutMapping(path="/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PublisherDto> update(@PathVariable Long id, @RequestBody PublisherDto publisher){
        PublisherDto currentPublisherDto;
        try{
            currentPublisherDto = publisherService.findById(id);

            if(currentPublisherDto!=null){
                crossData(currentPublisherDto,publisher);
                currentPublisherDto = publisherService.save(currentPublisherDto);
            }
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

        return ResponseEntity.status(HttpStatus.OK).body(currentPublisherDto);
    }

    private void crossData(PublisherDto currentPublisher, PublisherDto newPublisher){
        currentPublisher.setName(newPublisher.getName());
        currentPublisher.setWeb(newPublisher.getWeb());
        currentPublisher.setCountry(newPublisher.getCountry());
        currentPublisher.setGames(newPublisher.getGames());
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            publisherService.deleteById(id);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
