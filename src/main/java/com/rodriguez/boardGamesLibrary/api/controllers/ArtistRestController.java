package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.models.Artist;
import com.rodriguez.boardGamesLibrary.api.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistRestController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> findAll(){
        return artistService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist byId(@PathVariable Long id){
        return artistService.byId(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Artist create(@RequestBody Artist artist){
        return artistService.save(artist);
    }

    @PutMapping(path= "/{id}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Artist update(@PathVariable Long id, @RequestBody Artist artist){
        Artist currentArtist = artistService.byId(id);

        if(currentArtist!=null){
            crossData(currentArtist,artist);
            return artistService.save(currentArtist);
        }

        return null;
    }

    private void crossData(Artist currentArtist, Artist newArtist){
        currentArtist.setName(newArtist.getName());
        currentArtist.setLastName(newArtist.getLastName());
        currentArtist.setCountry(newArtist.getCountry());
        currentArtist.setGames(newArtist.getGames());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        artistService.delete(id);
    }
}
