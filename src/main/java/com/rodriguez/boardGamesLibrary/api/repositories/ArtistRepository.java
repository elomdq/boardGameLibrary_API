package com.rodriguez.boardGamesLibrary.api.repositories;

import com.rodriguez.boardGamesLibrary.api.models.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
