package com.rodriguez.boardGamesLibrary.api.services;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.mappers.BoardGameMapper;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.repositories.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BoardGameService {

    @Autowired
    BoardGameRepository boardGameRepository;

    @Autowired
    BoardGameMapper bgMapper;

    @Transactional(readOnly = true)
    public Set<BoardGameDto> findAll(){
        Set<BoardGameDto> boardGamesDto = bgMapper.toDtos(Set.copyOf(
                StreamSupport
                        .stream(boardGameRepository.findAll().spliterator(),false)
                        .collect(Collectors.toList())
                )
        );
        return boardGamesDto;
    }

    @Transactional(readOnly = true)
    public Set<BoardGameDto> findAllByDesignerId(Long id){
        Set<BoardGameDto> boardGames = bgMapper.toDtos(Set.copyOf(
                StreamSupport
                        .stream(boardGameRepository.findByDesignerId(id).spliterator(), false)
                        .collect(Collectors.toList())
        ));
        return boardGames;
    }

    @Transactional(readOnly = true)
    public Set<BoardGameDto> findAllByPublisherId(Long id){
        Set<BoardGameDto> boardGames = bgMapper.toDtos(Set.copyOf(
                StreamSupport
                        .stream(boardGameRepository.findByPublisherId(id).spliterator(),false)
                        .collect(Collectors.toList())
        ));
        return boardGames;
    }

    @Transactional(readOnly = true)
    public BoardGameDto findById(Long id){
        BoardGame bg = boardGameRepository.findById(id).orElse(null);
        return bgMapper.toDto(bg);
    }

    @Transactional(readOnly = false)
    public BoardGameDto save(BoardGameDto game){
        BoardGame bg = boardGameRepository.save(bgMapper.toEntity(game));
        return bgMapper.toDto(bg);
    }

    @Transactional(readOnly = false)
    public void delete(Long id){
        BoardGame boardGame = boardGameRepository.findById(id).get();

        if(boardGame != null){
            boardGame.clearDesigners();
            boardGame.clearPublishers();
            boardGame.clearArtists();
        }

        boardGameRepository.deleteById(id);
    }
}
