package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.services.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.Position;
import java.util.List;

@CrossOrigin(origins={""}) //Cross-Origin Resource Sharing
@RestController
@RequestMapping(path = "/boardgames")
public class BoardGameRestController {

    @Autowired
    private BoardGameService boardGameService;

    @GetMapping("/list")
    public List<BoardGame> list(){
        return boardGameService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK) //por default
    public BoardGame byId(@PathVariable Long id){
        return boardGameService.byId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardGame save(@RequestBody BoardGame game){
        return boardGameService.save(game);
    }

    @PutMapping(path="/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardGame update(@PathVariable Long id, @RequestBody BoardGame game){
        BoardGame currentGame = boardGameService.byId(id);

        if(currentGame!=null){
            crossData(currentGame,game);
            return boardGameService.save(currentGame);
        }

        return null;
    }

    private void crossData(BoardGame currentGame, BoardGame newGame){
        currentGame.setName(newGame.getName());
        currentGame.setYear(newGame.getYear());
        currentGame.setMinPlayers(newGame.getMinPlayers());
        currentGame.setMaxPlayers(newGame.getMaxPlayers());
        currentGame.setMaxPlayers(newGame.getMaxPlayers());
        currentGame.setMinAge(newGame.getMinAge());
        currentGame.setBgg(newGame.getBgg());
        currentGame.setLikes(newGame.getLikes());
        currentGame.setImages(newGame.getImages());
        currentGame.setArtists(newGame.getArtists());
        currentGame.setDesigners(newGame.getDesigners());
        currentGame.setPublishers(newGame.getPublishers());
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        boardGameService.delete(id);
    }
}
