package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.dtos.ArtistDto;
import com.rodriguez.boardGamesLibrary.api.services.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/artist")
public class ArtistRestController {
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> findAll(){

        List<ArtistDto> artistDtos;
        try{
            artistDtos = List.copyOf(artistService.findAll());
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

        return ResponseEntity.status(HttpStatus.OK).body(artistDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDto> findById(@PathVariable Long id){
        ArtistDto artistDto;
        try{
            artistDto = artistService.findById(id);
            if(artistDto==null){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(artistDto);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArtistDto> create(@Valid @RequestBody ArtistDto artist, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors: \n");
            for(ObjectError error:errors){
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }

        ArtistDto artistDto;
        try {
            artistDto = artistService.save(artist);
        }catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(artistDto);
    }

    @PutMapping(path= "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ArtistDto> update(@PathVariable Long id, @Valid @RequestBody ArtistDto artist, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors: \n");
            for(ObjectError error:errors){
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }

        ArtistDto currentArtist;
        try {
            currentArtist = artistService.findById(id);

            if(currentArtist!=null){
                crossData(currentArtist,artist);
                currentArtist = artistService.save(currentArtist);
            }
        }catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }

        return ResponseEntity.status(HttpStatus.OK).body(currentArtist);
    }

    private void crossData(ArtistDto currentArtist, ArtistDto newArtist){
        currentArtist.setName(newArtist.getName());
        currentArtist.setLastName(newArtist.getLastName());
        currentArtist.setCountry(newArtist.getCountry());
        currentArtist.setGames(newArtist.getGames());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            artistService.deleteById(id);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
