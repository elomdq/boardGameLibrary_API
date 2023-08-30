package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.dtos.DesignerDto;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import com.rodriguez.boardGamesLibrary.api.models.Designer;
import jdk.jfr.Name;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DesignerMapper {

    public Designer toEntity (DesignerDto designerDto);
    public DesignerDto toDto (Designer designer);

    public List<Designer> toEntities (List<DesignerDto> designerDtos);
    public List<DesignerDto> DesignerToDesignerDtos (List<Designer> designers);

    /*@Mapping(target="designers", ignore=true)
    public BoardGame boardGameDtoToBoardGame(BoardGameDto boardGameDto);

    @Mapping(target= "designers", ignore=true)
    public BoardGameDto BoardGameToBoardGameDto(BoardGame boardGame);*/
}
