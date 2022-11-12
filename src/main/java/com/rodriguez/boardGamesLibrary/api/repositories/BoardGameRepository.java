package com.rodriguez.boardGamesLibrary.api.repositories;

import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import org.springframework.data.repository.CrudRepository;


public interface BoardGameRepository extends CrudRepository<BoardGame, Long> {

}
