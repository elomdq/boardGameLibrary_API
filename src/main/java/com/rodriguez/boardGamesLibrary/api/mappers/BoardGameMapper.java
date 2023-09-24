package com.rodriguez.boardGamesLibrary.api.mappers;

import com.rodriguez.boardGamesLibrary.api.dtos.BoardGameDto;
import com.rodriguez.boardGamesLibrary.api.models.BoardGame;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;


@Mapper(uses = {DesignerMapper.class, PublisherMapper.class, ImageMapper.class, ArtistMapper.class})
public interface BoardGameMapper {
    //public BoardGame boardGameDtoToBoardGame(BoardGameDto boardGameDto);

    /*Este mapper es el que usamos para las entidades BoardGame y evitamos la recursividad de los game childs dentro de las entidades Designer, Publisher, Artist y Image*/
    @Mapping(target= "designers", qualifiedByName = "toDtosIgnoreGames")
    @Mapping(target= "publishers", qualifiedByName = "toDtosIgnoreGames")
    //@Mapping(target= "images", ignore = true)
    //@Mapping(target= "artists", ignore= true)
    public BoardGameDto toDto(BoardGame boardGame);

    @Mapping(target= "designers", qualifiedByName = "toEntitiesIgnoreGames")
    @Mapping(target= "publishers", qualifiedByName = "toEntitiesIgnoreGames")
    @Mapping(target= "images", ignore = true)
    @Mapping(target= "artists", ignore= true)
    public BoardGame toEntity(BoardGameDto boardGameDto);

    public Set<BoardGameDto> toDtos(Set<BoardGame> boardGames);
    public Set<BoardGame> toEntities (Set<BoardGameDto> boardGameDtos);



    //MAPPER A USAR POR OTROS MAPPERS DE OTRAS ENTIDADES PARA EVITAR RECURSIVIDAD

    /*Este mapper nos sirve para cuando queremos mapear una entidad Designer, Publisher o Artist, y queremos cortar la recursividad de las listas de su propia entidad en sus juegos*/
    /*@Named("toDtoIgnoreChildLists")
    @Mapping(target = "designers", ignore = true)
    @Mapping(target = "publishers", ignore = true)
    @Mapping(target = "artists", ignore = true)
    @Mapping(target = "images", ignore = true)
    public BoardGameDto toDtoIgnoreChildLists(BoardGame boardGame);

    @Named("toEntityIgnoreChildLists")
    @Mapping(target = "designers", ignore = true)
    @Mapping(target = "publishers", ignore = true)
    @Mapping(target = "artists", ignore = true)
    @Mapping(target = "images", ignore = true)
    public BoardGame toEntityIgnoreChildLists(BoardGameDto boardGameDto);

    @Named("toDtosIgnoreChildLists")
    @IterableMapping(qualifiedByName = "toDtoIgnoreChildLists")
    public Set<BoardGameDto> toDtosIgnoreChildLists(Set<BoardGame> boardGames);

    @Named("toEntitiesIgnoreChildLists")
    @IterableMapping(qualifiedByName = "toEntityIgnoreChildLists")
    public Set<BoardGame> toEntitiesIgnoreChildLists(Set<BoardGameDto> boardGamesDtos);*/
}
