package com.rodriguez.boardGamesLibrary.api.controllers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.services.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/boardgames")
public class BoardGameRestController {

    @Autowired
    private BoardGameService boardGameService;

    @GetMapping
    public ResponseEntity<List<BoardGameDto>> findAll(){
        List<BoardGameDto> boardGames;
        try{
            boardGames = List.copyOf(boardGameService.findAll());
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(boardGames);
    }

    @GetMapping("/by-designer/{id}")
    public ResponseEntity<List<BoardGameDto>> findByDesignerId(@PathVariable Long id){
        List<BoardGameDto> boardGames;
        try{
            boardGames = List.copyOf(boardGameService.findAllByDesignerId(id));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(boardGames);
    }

    @GetMapping("/by-publisher/{id}")
    public ResponseEntity<List<BoardGameDto>> findByPublisherId(@PathVariable Long id){
        List<BoardGameDto> boardGames;
        try{
            boardGames = List.copyOf(boardGameService.findAllByPublisherId(id));
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
        return ResponseEntity.status(HttpStatus.OK).body(boardGames);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BoardGameDto> findById(@PathVariable Long id){
        BoardGameDto boardGame;
        try{
            boardGame = boardGameService.findById(id);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(boardGame);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BoardGameDto> save(@RequestBody BoardGameDto game){
        BoardGameDto boardGameDto;
        try{
            boardGameDto = boardGameService.save(game);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(boardGameDto);
    }

    @PutMapping(path="/{id}",consumes = "application/json", produces = "application/json")
    public ResponseEntity<BoardGameDto> update(@PathVariable Long id, @RequestBody BoardGameDto game){
        BoardGameDto currentGame;
        try{
            currentGame = boardGameService.findById(id);
            if(currentGame!=null){
                crossData(currentGame,game);
                currentGame = boardGameService.save(currentGame);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(currentGame);
    }

    private void crossData(BoardGameDto currentGame, BoardGameDto newGame){
        currentGame.setName(newGame.getName().toLowerCase());
        currentGame.setBgYear(newGame.getBgYear());
        currentGame.setMinPlayers(newGame.getMinPlayers());
        currentGame.setMaxPlayers(newGame.getMaxPlayers());
        currentGame.setMaxPlayers(newGame.getMaxPlayers());
        currentGame.setMinAge(newGame.getMinAge());
        currentGame.setBgg(newGame.getBgg().toLowerCase());
        currentGame.setLikes(newGame.getLikes());
        currentGame.setImages(newGame.getImages());
        currentGame.setArtists(newGame.getArtists());
        currentGame.setDesigners(newGame.getDesigners());
        currentGame.setPublishers(newGame.getPublishers());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            boardGameService.delete(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
