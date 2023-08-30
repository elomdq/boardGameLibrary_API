package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.dtos.ImageDto;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.models.Image;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    public Image toEntity (ImageDto image);
    public ImageDto toDto (Image image);

    List<Image> toEntities (List<ImageDto> imageDtos);
    List<ImageDto> toDtos (List<Image> images);
}
