package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper
public interface BoardGameSlimMapper {

    @Mapping(target= "designers", ignore = true)
    @Mapping(target= "publishers", ignore = true)
    @Mapping(target= "images", ignore = true)
    @Mapping(target= "artists", ignore= true)
    public BoardGameDto toDto(BoardGame boardGame);

    @Mapping(target= "designers", ignore = true)
    @Mapping(target= "publishers", ignore = true)
    @Mapping(target= "images", ignore = true)
    @Mapping(target= "artists", ignore= true)
    public BoardGame toEntity(BoardGameDto boardGameDto);
    public Set<BoardGameDto> toDtosIgnoreChildLists(Set<BoardGame> boardGames);
    public Set<BoardGame> toEntitiesIgnoreChildLists(Set<BoardGameDto> boardGamesDtos);
}
