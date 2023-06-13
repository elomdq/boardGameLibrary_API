package com.rodriguez.boardGamesLibrary.api.services;

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

    @Transactional(readOnly = true)
    public List<BoardGame> findAll(){
        return (List<BoardGame>) boardGameRepository.findAll();
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
    public BoardGame byId(Long id){
        return boardGameRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = false)
    public BoardGame save(BoardGame game){
        return boardGameRepository.save(game);
    }

    @Transactional(readOnly = false)
    public void delete(Long id){
        boardGameRepository.deleteById(id);
    }
}
