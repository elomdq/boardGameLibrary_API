package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.mappers.BoardGameMapper;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.repositories.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardGameService {

    @Autowired
    BoardGameRepository boardGameRepository;

    @Autowired
    BoardGameMapper bgMapper;

    @Transactional(readOnly = true)
    public List<BoardGame> findAll(){
        List<BoardGame> boardGames = (List<BoardGame>)boardGameRepository.findAll();
/*
        System.out.println("DISEÑADORES");

        boardGames.forEach(boardGame -> {
            System.out.println(boardGame.getDesigners());
        });

        System.out.println(boardGames);*/
        return boardGames;
    }

    @Transactional(readOnly = true)
    public List<BoardGame> findByDesignerId(Long id){
        return (List<BoardGame>) boardGameRepository.findByDesignerId(id);
    }

    @Transactional(readOnly = true)
    public List<BoardGame> findByPublisherId(Long id){
        return (List<BoardGame>) boardGameRepository.findByPublisherId(id);
    }

    @Transactional(readOnly = true)
    public BoardGame findById(Long id){
        BoardGame bg = boardGameRepository.findById(id).orElse(null);
        //System.out.println(bg);

        return bg;
    }

    /*@Transactional(readOnly = true)
    public BoardGame byId(Long id){
        Optional<BoardGame> opBoardGame = boardGameRepository.findById(id);
        if(opBoardGame.isPresent())
            return opBoardGame.get();
        else
            throw new BoardGameNotFoundException("No se encontro el juego");
    }*/

    @Transactional(readOnly = false)
    public BoardGame save(BoardGame game){
        return boardGameRepository.save(game);
    }

    @Transactional(readOnly = false)
    public void delete(Long id){
        boardGameRepository.deleteById(id);
    }
}
