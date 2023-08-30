package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.dtos.ArtistDto;
import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.dtos.ImageDto;
import com.rodriguez.boardGamesLibrary.api.dtos.PublisherDto;
import com.rodriguez.boardGamesLibrary.api.mappers.BoardGameMapper;
import com.rodriguez.boardGamesLibrary.api.models.Artist;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.models.Image;
import com.rodriguez.boardGamesLibrary.api.models.Publisher;
import com.rodriguez.boardGamesLibrary.api.services.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(path = "/boardgames")
public class BoardGameRestController {

    @Autowired
    private BoardGameService boardGameService;

    @GetMapping
    public ResponseEntity<List<BoardGame>> list(){
        List<BoardGame> boardGames;
        try{
            boardGames = boardGameService.findAll();
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(boardGames);
    }

    @GetMapping("/by-designer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<BoardGame> findByDesignerId(@PathVariable Long id){
        return boardGameService.findByDesignerId(id);
    }

    @GetMapping("/by-publisher/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<BoardGame> findByPublisherId(@PathVariable Long id){
        return boardGameService.findByPublisherId(id);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK) //por default
    public BoardGame byId(@PathVariable Long id){
        return boardGameService.findById(id);
    }

    /*
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK) //por default
    public ResponseEntity<BoardGame> byId(@PathVariable Long id){
        try {
            return new ResponseEntity<BoardGame>(boardGameService.byId(id), HttpStatus.OK);
        }catch (BoardGameNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el juego");
        }
    }*/

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardGame save(@RequestBody BoardGame game){
        return boardGameService.save(game);
    }

    @PutMapping(path="/{id}",consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardGame update(@PathVariable Long id, @RequestBody BoardGame game){
        BoardGame currentGame = boardGameService.findById(id);

        if(currentGame!=null){
            crossData(currentGame,game);
            return boardGameService.save(currentGame);
        }

        return null;
    }

    private void crossData(BoardGame currentGame, BoardGame newGame){
        currentGame.setName(newGame.getName().toLowerCase());
        currentGame.setBgYear(newGame.getBgYear());
        currentGame.setMinPlayers(newGame.getMinPlayers());
        currentGame.setMaxPlayers(newGame.getMaxPlayers());
        currentGame.setMaxPlayers(newGame.getMaxPlayers());
        currentGame.setMinAge(newGame.getMinAge());
        currentGame.setBgg(newGame.getBgg().toLowerCase());
        currentGame.setLikes(newGame.getLikes());
        currentGame.setImages((Set<Image>) newGame.getImages());
        currentGame.setArtists((Set<Artist>) newGame.getArtists());
        currentGame.setDesigners(newGame.getDesigners());
        currentGame.setPublishers((Set<Publisher>) newGame.getPublishers());
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        boardGameService.delete(id);
    }
}
