package com.rodriguez.boardGamesLibrary.api.repositories;

import com.rodriguez.boardGamesLibrary.api.models.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
