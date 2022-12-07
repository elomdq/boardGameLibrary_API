package com.rodriguez.boardGamesLibrary.api.repositories;

import com.rodriguez.boardGamesLibrary.api.models.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {

    @Query("select i from Image i where i.game.id = ?1")
    Iterable<Image> findAllByGameId(Long gameId);

}
