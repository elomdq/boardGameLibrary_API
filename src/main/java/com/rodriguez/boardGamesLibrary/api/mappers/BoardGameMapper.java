package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.dtos.DesignerDto;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(uses = {DesignerMapper.class}
    /*,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE*/)
public interface BoardGameMapper {

    public BoardGame boardGameDtoToBoardGame(BoardGameDto boardGameDto);

    @Mapping(target= "designers", qualifiedByName = "toDesignerDtosWithoutGames")
    @InheritInverseConfiguration
    public BoardGameDto BoardGameToBoardGameDto(BoardGame boardGame);

    public List<BoardGameDto> BoardGamesToBoardGameDtos (List<BoardGame> boardGames);
    public List<BoardGame> BoardGameDtosToBoardGames (List<BoardGameDto> boardGameDtos);


    @Named("toDesignerDtoWithoutGames")
    @Mapping(target = "games", ignore = true)
    @InheritInverseConfiguration
    public DesignerDto toDesignerDtoWithoutGames (Designer designer);

    @Named("toDesignerDtosWithoutGames")
    @IterableMapping(qualifiedByName = "toDesignerDtoWithoutGames")
    @InheritInverseConfiguration
    public Set<DesignerDto> toDesignerDtosWithoutGames (Set<Designer> designers);





}
