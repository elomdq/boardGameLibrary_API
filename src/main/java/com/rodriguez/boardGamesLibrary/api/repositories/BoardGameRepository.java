package com.rodriguez.boardGamesLibrary.api.repositories;

import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface BoardGameRepository extends CrudRepository<BoardGame, Long> {

    @Query("select b from BoardGame b inner join b.designers d where d.id = ?1")
    Iterable<BoardGame> findByDesignerId(Long gameId);

    @Query("select b from BoardGame b inner join b.publishers p where p.id = ?1")
    Iterable<BoardGame> findByPublisherId(Long gameId);

}
