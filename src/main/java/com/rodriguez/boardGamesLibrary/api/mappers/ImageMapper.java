package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.dtos.DesignerDto;
import com.rodriguez.boardGamesLibrary.api.dtos.ImageDto;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import com.rodriguez.boardGamesLibrary.api.models.Image;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Set;


@Mapper(uses = BoardGameSlimMapper.class)
public interface ImageMapper {

    //@Mapping(target= "game", ignore = true)
    //@Mapping(target = "game", source = "game.id")
    public ImageDto toDto(Image image);

    //@Mapping(target= "game", ignore = true)
    public Image toEntity(ImageDto imageDto);

    public Set<Image> toEntities (Set<ImageDto> imageDtos);

    public Set<ImageDto> toDtos(Set<Image> images);


}
